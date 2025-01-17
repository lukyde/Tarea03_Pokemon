package dam.pmdm.tarea_03_rmlp;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;


import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import dam.pmdm.tarea_03_rmlp.databinding.CardBinding;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private final CardBinding binding;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final pokemonFragment fragment;

    public PokemonViewHolder(CardBinding binding, pokemonFragment fragment) {
        super(binding.getRoot());
        this.binding = binding;

        this.fragment = fragment;
    }

    public void bind(Pokemon pokemon) {

        binding.nombre.setText(pokemon.getNombre());
        Picasso.get().load(pokemon.getImagen()).into(binding.imagen);
        binding.tipo.setText(pokemon.getTipo());
        String id = pokemon.getId();
        String nombre = pokemon.getNombre();
        binding.executePendingBindings();

        Context context = binding.getRoot().getContext();
        SharedPreferences preferencia = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        Boolean cancelarBoton = preferencia.getBoolean("delete", false);

        binding.pokemonBorrados.setEnabled(cancelarBoton);

        if (cancelarBoton) {
            binding.pokemonBorrados.setVisibility(View.VISIBLE);  // Botón visible
        } else {
            binding.pokemonBorrados.setVisibility(View.GONE);  // Botón invisible y no ocupa espacio
        }

        binding.pokemonBorrados.setOnClickListener(view -> {

            ((MainActivity) context).deletePokemon(id, nombre);
        });
    }


}
