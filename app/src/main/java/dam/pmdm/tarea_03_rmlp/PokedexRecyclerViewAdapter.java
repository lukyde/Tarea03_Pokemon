package dam.pmdm.tarea_03_rmlp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dam.pmdm.tarea_03_rmlp.databinding.CardBinding;

import dam.pmdm.tarea_03_rmlp.databinding.ListaPokemonBinding;
import retrofit2.Call;

public class PokedexRecyclerViewAdapter extends RecyclerView.Adapter<PokedexViewHolder>{
    private Set<String> capturadosNombre =new HashSet<>();
    private final ArrayList<Pokemon> pokemon;
    private final Context context;
    private List<Pokemon> pokemons;

    public PokedexRecyclerViewAdapter(ArrayList<Pokemon> pokemon, Context context) {

        this.pokemon = pokemon;
        this.context = context;



    }
    public void setCapturadosIds( Set<String> capturadosIds) {
        this.capturadosNombre = capturadosIds;
        notifyDataSetChanged();


    }
    public void updateData(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       ListaPokemonBinding binding = ListaPokemonBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);


        return new PokedexViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
        Pokemon currentPokemon = this.pokemon.get(position);
        holder.bind(currentPokemon,capturadosNombre);

        holder.itemView.setOnClickListener(view -> itemClicked(currentPokemon, view));
    }


    @Override
    public int getItemCount() {
        return pokemon.size();
    }



    public void itemClicked(Pokemon currentPokemon, View view){

        ((MainActivity) context).pokemonClicked(currentPokemon, view);
    }
}
