package dam.pmdm.tarea_03_rmlp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import dam.pmdm.tarea_03_rmlp.databinding.FragmentCapturadosBinding;


public class Capturados extends Fragment {

    private FragmentCapturadosBinding binding;
    private String nombre;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();


    public Capturados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCapturadosBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getArguments() != null) {
            String imagen = getArguments().getString("imagen");
            nombre = getArguments().getString("nombre");
            String tipo = getArguments().getString("tipo");
            String altura = getArguments().getString("altura");
            String peso = getArguments().getString("peso");


            binding.nombre.setText(nombre);
            binding.tipo.setText(tipo);
            binding.peso.setText(peso);
            binding.altura.setText(altura);
            Picasso.get().load(imagen).into(binding.imagen);


        }

    }


}