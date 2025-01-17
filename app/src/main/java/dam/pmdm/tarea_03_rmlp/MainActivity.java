package dam.pmdm.tarea_03_rmlp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dam.pmdm.tarea_03_rmlp.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Pokemon> {


    private NavController navController;
    private ActivityMainBinding binding;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Set<String> CapturadosCambioColor = new HashSet<>();
    private PokedexRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseApp.initializeApp(this);

        setCapturadosCambioColor();
        getCapturadosCambioColor();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.ajustesFragment, R.id.pokedexFragment, R.id.pokemonFragment // Añade aquí los fragmentos que no requieren botón de "Atrás"
        ).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(binding.bottonNavigation, navController);


    }

    public void pokemonClicked(Pokemon pokemon, View view) {

        String url = pokemon.getUrl();
        Call<Pokemon> call = MyApiAdapter.getApiService().getPokemonDetails(url);
        call.enqueue(this);
        ;


        Toast.makeText(getApplicationContext(), getString(R.string.mensaje) + " " + pokemon.getNombre(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
        if (response.isSuccessful() && response.body() != null) {
            Pokemon disesis = response.body();
            Map<String, Object> pokemonData = new HashMap<>();
            pokemonData.put("nombre", disesis.getNombre());
            pokemonData.put("peso", disesis.getPeso());
            pokemonData.put("altura", disesis.getAltura());
            pokemonData.put("imagen", disesis.getSprites().getOther().getHome().getImagen());
            pokemonData.put("tipo", disesis.getListaTipos().get(0).gettipo().getName());

            db.collection("Capturados").add(pokemonData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            CapturadosCambioColor.add(disesis.getNombre());
                            PokedexViewModel viewModel = new ViewModelProvider(MainActivity.this).get(PokedexViewModel.class);
                            viewModel.setCapturados(CapturadosCambioColor);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


        } else {

            Toast.makeText(getApplicationContext(), getString(R.string.er_guardar), Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onFailure(Call<Pokemon> call, Throwable t) {

        Toast.makeText(getApplicationContext(), getString(R.string.er_guardar), Toast.LENGTH_SHORT).show();
    }

    public void PokemonDetalleClicked(Pokemon pokemon, View view) {
        Bundle bundle = new Bundle();

        bundle.putString("nombre", pokemon.getNombre());
        bundle.putString("tipo", pokemon.getTipo());
        bundle.putString("imagen", pokemon.getImagen());
        bundle.putString("altura", pokemon.getAltura());
        bundle.putString("peso", pokemon.getPeso());

        Navigation.findNavController(view).navigate(R.id.capturados, bundle);
    }

    @Override
    public boolean onSupportNavigateUp() {

        return navController.navigateUp() || super.onSupportNavigateUp();
    }


    public void setCapturadosCambioColor() {
        db.collection("Capturados").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {


                            String nombre = document.getString("nombre");


                            CapturadosCambioColor.add(nombre);
                            PokedexViewModel viewModel = new ViewModelProvider(MainActivity.this).get(PokedexViewModel.class);
                            viewModel.setCapturados(CapturadosCambioColor);

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }

    public Set<String> getCapturadosCambioColor() {
        setCapturadosCambioColor();
        return CapturadosCambioColor;
    }

    public void deletePokemon(String id, String nombre) {

        db.collection("Capturados")
                .document(id)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(getApplicationContext(), getString(R.string.eliminado), Toast.LENGTH_SHORT).show();
                    CapturadosCambioColor.remove(nombre);


                    updatePokemonList();

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(), getString(R.string.eliminado_error), Toast.LENGTH_SHORT).show();
                });


    }

    private void updatePokemonList() {
        // Obtener la lista de Pokémon actualizada de Firebase
        db.collection("Capturados").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Pokemon> updatedPokemonList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Crear el objeto Pokémon y agregarlo a la lista
                        String nombre = document.getString("nombre");
                        String tipo = document.getString("tipo");
                        String imagen = document.getString("imagen");
                        String altura = document.getString("altura");
                        String peso = document.getString("peso");
                        String id = document.getId();
                        updatedPokemonList.add(new Pokemon(nombre, tipo, imagen, altura, peso, id));
                    }

                    // Ahora actualizamos el ViewModel con la nueva lista de Pokémon
                    PokemonFragmentViewModel viewModel = new ViewModelProvider(this).get(PokemonFragmentViewModel.class);
                    viewModel.setPokedex(updatedPokemonList);  // Actualiza la lista en el ViewModel

                })
                .addOnFailureListener(e -> {
                    // Manejar error al obtener los datos
                    Toast.makeText(getApplicationContext(), getString(R.string.error_actualizacion), Toast.LENGTH_SHORT).show();
                });
    }


}






