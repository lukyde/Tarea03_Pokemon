package dam.pmdm.tarea_03_rmlp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {

    @SerializedName("sprites")

    private Sprites sprites;

    @SerializedName("name")


    private String nombre;


    @SerializedName("height")

    private String altura;

    @SerializedName("weight")

    private String peso;

    private String url;


    private String tipo;
    private String imagen;
    private String id;

    @SerializedName("types")
    private List<TipoPokemon> tipos;

    public String getUrl() {
        return url;
    }

    public Pokemon(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
    }

    public Pokemon(String nombre, String tipo, String imagen, String altura, String peso, String id) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
        this.altura = altura;
        this.peso = peso;
        this.id = id;
    }


    public Pokemon(Sprites imagen, String nombre, List<TipoPokemon> tipos, String altura, String peso) {
        this.nombre = nombre;
        this.sprites = imagen;
        this.tipo = tipo;

        this.peso = peso;
        this.altura = altura;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAltura() {
        return altura;
    }

    public String getImagen() {
        return imagen;
    }

    public String getId() {
        return id;
    }

    public String getPeso() {
        return peso;
    }

    public List<TipoPokemon> getListaTipos() {
        return tipos;
    }
}

class TipoPokemon {
    @SerializedName("type")
    private TipoNombre tipo;

    public TipoNombre gettipo() {
        return tipo;
    }
}

class TipoNombre {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

}

class Sprites {
    @SerializedName("other")
    private Other other;

    public Other getOther() {
        return other;
    }


    public class Other {
        @SerializedName("home")
        private Home home;

        public Home getHome() {
            return home;
        }

        public class Home {
            @SerializedName("front_default")
            private String imagen;

            public String getImagen() {
                return imagen;
            }
        }
    }
}
