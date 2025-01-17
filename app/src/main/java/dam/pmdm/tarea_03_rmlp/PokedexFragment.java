package dam.pmdm.tarea_03_rmlp;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import dam.pmdm.tarea_03_rmlp.databinding.FragmentPokedexBinding;
import dam.pmdm.tarea_03_rmlp.databinding.FragmentPokemonBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PokedexFragment extends Fragment implements Callback<ArrayPokemonResponse> {

    private ArrayList<Pokemon> pokedex;
    private PokedexRecyclerViewAdapter adapter;
    private FragmentPokedexBinding binding;
    private PokedexViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPokedexBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadPokemon();
        viewModel = new ViewModelProvider(requireActivity()).get(PokedexViewModel.class);

        Set<String> capturadosNombre = ((MainActivity) getActivity()).getCapturadosCambioColor();

        viewModel.setCapturados(capturadosNombre);
        adapter = new PokedexRecyclerViewAdapter(pokedex, getActivity());
        adapter.setCapturadosIds(capturadosNombre);
        binding.pokemonRecyclerViewPokedex.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.pokemonRecyclerViewPokedex.setAdapter(adapter);

        viewModel.getPokedex().observe(getViewLifecycleOwner(), pokemons -> {


            adapter.updateData(pokemons);
        });
        viewModel.getCapturados().observe(getViewLifecycleOwner(), capturados -> {
            adapter.setCapturadosIds(capturados);


            adapter.notifyDataSetChanged();
        });


    }

    private void loadPokemon() {
        pokedex = new ArrayList<>();
        Call<ArrayPokemonResponse> call = MyApiAdapter.getApiService().getApiPokemon();
        call.enqueue(this);


    }


    @Override
    public void onResponse(Call<ArrayPokemonResponse> call, Response<ArrayPokemonResponse> response) {

        if (response.isSuccessful() && response.body() != null) {
            Pokemon ResultadoJson;
            ArrayPokemonResponse disesis = response.body();

            for (PokemonResponse pokemonResponse : disesis.getResults()) {
                ResultadoJson = new Pokemon(pokemonResponse.getNombre(), pokemonResponse.getUrl());
                pokedex.add(ResultadoJson);

            }
            viewModel.setPokedex(pokedex);


        } else {
            Toast.makeText(getActivity(), "Error en la respuesta", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<ArrayPokemonResponse> call, Throwable t) {
        Toast.makeText(getActivity(), "Error en la respuesta", Toast.LENGTH_SHORT).show();
        Log.e("API_ERROR", "Error en la llamada a la API", t);
    }


}