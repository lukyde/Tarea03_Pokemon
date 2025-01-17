package dam.pmdm.tarea_03_rmlp;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import dam.pmdm.tarea_03_rmlp.databinding.FragmentPokemonBinding;

public class pokemonFragment extends Fragment {

    private ArrayList<Pokemon> pokemon;
    private PokemonRecyclerViewAdapter adapter;
    private FragmentPokemonBinding binding;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String id;
    private Set<String> capturadosNombre;


    public pokemonFragment() {

    }

    /**
     * Crea y devuelve la vista del fragmento inflando su diseño XML.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPokemonBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


// Obtén el ViewModel de la actividad
        PokemonFragmentViewModel viewModel = new ViewModelProvider(requireActivity()).get(PokemonFragmentViewModel.class);

        // Observa los cambios en la lista de Pokémon
        viewModel.getPokedex().observe(getViewLifecycleOwner(), pokedex -> {
            // Actualiza la lista de Pokémon en la UI cuando el ViewModel cambie
            pokemon.clear();
            pokemon.addAll(pokedex);  // Reemplaza la lista con la nueva lista
            adapter.notifyDataSetChanged();  // Notifica al adaptador que los datos han cambiado
        });


        loadPokemon();
        //Snackbar.make(view, getString(R.string.snackbar), Snackbar.LENGTH_SHORT) .show();
        adapter = new PokemonRecyclerViewAdapter(pokemon, getActivity(), this);
        binding.pokemonRecyclerViewPokemon.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.pokemonRecyclerViewPokemon.setAdapter(adapter);
        capturadosNombre = ((MainActivity) getActivity()).getCapturadosCambioColor();


    }

    private void loadPokemon() {

        SharedPreferences prefs = requireActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        String idioma = prefs.getString("idioma", "es");
        Toast.makeText(getContext(), idioma, Toast.LENGTH_SHORT).show();
        pokemon = new ArrayList<>();
        ArrayList<String> colorCapturado = new ArrayList<>();
        db.collection("Capturados")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {

                            String nombre = document.getString("nombre");
                            String tipo = document.getString("tipo");
                            String imagen = document.getString("imagen");
                            String altura = document.getString("altura");
                            String peso = document.getString("peso");
                            id = document.getId();

                            if (idioma.equals("es")) {
                                tipo = idiomaTipo(tipo);

                            }
                            pokemon.add(new Pokemon(nombre, tipo, imagen, altura, peso, id));

                            adapter.notifyDataSetChanged();

                        }


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Maneja el error
                    }
                });


    }


    private String idiomaTipo(String tipo) {

        switch (tipo.toLowerCase()) {
            case "fire":
                return getString(R.string.tipo_fuego);
            case "water":
                return getString(R.string.tipo_agua);
            case "grass":
                return getString(R.string.tipo_planta);
            case "electric":
                return getString(R.string.tipo_electrico);
            case "normal":
                return getString(R.string.tipo_normal);
            case "fighting":
                return getString(R.string.tipo_lucha);
            case "flying":
                return getString(R.string.tipo_volador);
            case "poison":
                return getString(R.string.tipo_veneno);
            case "ground":
                return getString(R.string.tipo_tierra);
            case "rock":
                return getString(R.string.tipo_roca);
            case "bug":
                return getString(R.string.tipo_bicho);
            case "ghost":
                return getString(R.string.tipo_fantasma);
            case "steel":
                return getString(R.string.tipo_acero);
            case "psychic":
                return getString(R.string.tipo_psiquico);
            case "ice":
                return getString(R.string.tipo_hielo);
            case "dragon":
                return getString(R.string.tipo_dragon);
            case "dark":
                return getString(R.string.tipo_siniestro);
            case "fairy":
                return getString(R.string.tipo_hada);
            case "stellar":
                return getString(R.string.tipo_estelar);
            case "unknown":
                return getString(R.string.tipo_desconocido);
            default:
                return tipo;
        }


    }


}