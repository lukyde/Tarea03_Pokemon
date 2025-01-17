package dam.pmdm.tarea_03_rmlp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Cerrar extends DialogFragment {

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Creamos el objeto AlertDialog.Builder
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        // Asignamos las propiedades que se mostrarán.
        builder.setTitle("Cerrar seccion")
                .setMessage("¿Seguro que deseas cerrar sección?")
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AuthUI.getInstance()
                                        .signOut(requireActivity())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            public void onComplete(@NonNull Task<Void> task) {
                                                // ...
                                            }
                                        });
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Acciones a realizar cuando pulsamos el botón.
                                dialog.cancel();
                            }
                        });
        return builder.create();
    }
}
