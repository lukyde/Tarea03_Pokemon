package dam.pmdm.tarea_03_rmlp;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

/**
 * Clase con el que se ha construido el dialogo emergente del "acerca de"
 */


public class AcercaDialogo extends DialogFragment {

    /**
     * Constructo del diálogo
     *
     * @param savedInstanceState El estado previamente guardado del diálogo, si existe.
     * @return Un objeto Dialog configurado con el título, el mensaje y el botón "Aceptar".
     */

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Creamos el objeto AlertDialog.Builder
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        // Asignamos las propiedades que se mostrarán.
        builder.setTitle("Acerca de")
                .setMessage("Aplicación desarrollada por Raul Luna Palma. Versión 1.0.")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Acciones a realizar cuando pulsamos el botón.
                                dialog.cancel();
                            }
                        });
        return builder.create();
    }
}
