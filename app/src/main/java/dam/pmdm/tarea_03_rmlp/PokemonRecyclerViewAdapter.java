package dam.pmdm.tarea_03_rmlp;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import dam.pmdm.tarea_03_rmlp.databinding.CardBinding;
import dam.pmdm.tarea_03_rmlp.databinding.CardBindingImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonRecyclerViewAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private final ArrayList<Pokemon> pokemon;
    private final Context context;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private pokemonFragment fragment;
    private List<Pokemon> pokemons;

    public PokemonRecyclerViewAdapter(ArrayList<Pokemon> pokemon, Context context, pokemonFragment fragment) {
        this.pokemon = pokemon;
        this.context = context;
        this.fragment = fragment;


    }


    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        CardBinding binding = CardBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        ;


        return new PokemonViewHolder(binding, fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

        Pokemon currentPokemon = this.pokemon.get(position);
        holder.bind(currentPokemon);

        holder.itemView.setOnClickListener(view -> itemClicked(currentPokemon, view));
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }


    public void itemClicked(Pokemon currentPokemon, View view) {


        ((MainActivity) context).PokemonDetalleClicked(currentPokemon, view);
    }


}
