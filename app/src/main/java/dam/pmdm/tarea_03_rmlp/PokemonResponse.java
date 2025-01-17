package dam.pmdm.tarea_03_rmlp;

import com.google.gson.annotations.SerializedName;
;

public class PokemonResponse {

    @SerializedName("name")
    public String nombre;
    @SerializedName("url")
    public String url;


    public String getUrl() {
        return url;
    }

    public PokemonResponse(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
