package dam.pmdm.tarea_03_rmlp;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;

import dam.pmdm.tarea_03_rmlp.databinding.FragmentAjustesBinding;


public class AjustesFragment extends Fragment {

    private FragmentAjustesBinding binding;
    private String IdiomaCambiado = "";
    private boolean delete = true;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void logoutCession(View view) {


        FragmentManager fragmentManager = getParentFragmentManager();

        Cerrar dialogo = new Cerrar();
        dialogo.show(fragmentManager, "fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAjustesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configuración del botón de cerrar sesión
        binding.botonCerrar.setOnClickListener(this::logoutCession);

        //Preferencia de idioma


        SharedPreferences prefs = requireActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.putString("idioma", IdiomaCambiado);
        editor.apply();

        SharedPreferences prefsDelete = requireActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        delete = prefsDelete.getBoolean("delete", true);

        binding.switchDelete.setChecked(delete);
        binding.switchDelete.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                delete = true;
                Toast.makeText(getActivity(), getString(R.string.boton_habilitado), Toast.LENGTH_SHORT).show();
            } else {
                delete = false;

            }
            SharedPreferences.Editor editorDelete = prefsDelete.edit();
            editorDelete.putBoolean("delete", delete);
            editorDelete.apply();
        });


        binding.switchIdioma.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                IdiomaCambiado = "es";
                cambiarIdioma("es");
            } else {
                IdiomaCambiado = "en";
                cambiarIdioma("en");
            }
        });

        //Funcionalidad del about

        binding.about.setOnClickListener(view1 -> {

            //Gestiono la pila de fragment para monstrar el dialogo, en este caso usando un metodo que devuvelve la acticity
            FragmentManager fragmentManager = getParentFragmentManager();

            AcercaDialogo dialogo = new AcercaDialogo();
            dialogo.show(fragmentManager, "fragment");

        });

    }


    private void cambiarIdioma(String es) {
        Locale locale = new Locale(es);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        editor.putString("idioma", IdiomaCambiado);
        editor.apply();
        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
        Toast.makeText(getActivity(), getString(R.string.idioma_cambiado), Toast.LENGTH_SHORT).show();
    }

}