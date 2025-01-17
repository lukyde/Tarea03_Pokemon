package dam.pmdm.tarea_03_rmlp;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import dam.pmdm.tarea_03_rmlp.databinding.ListaPokemonBinding;

public class PokedexViewHolder extends RecyclerView.ViewHolder {

    private final ListaPokemonBinding binding;

    public PokedexViewHolder(ListaPokemonBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Pokemon pokemon, Set<String> capturadosNombre) {

        final Context context = itemView.getContext();
        binding.nombrepokedex.setText(pokemon.getNombre());

        binding.nombrepokedex.setTextColor(ContextCompat.getColor(context, R.color.white));
        if (capturadosNombre.contains(pokemon.getNombre())) {
            binding.nombrepokedex.setTextColor(ContextCompat.getColor(context, R.color.amarillo));
            binding.nombrepokedex.setEnabled(false);
        }


        binding.executePendingBindings();
    }
}
