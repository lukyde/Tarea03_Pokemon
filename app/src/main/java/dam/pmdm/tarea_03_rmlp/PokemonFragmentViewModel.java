package dam.pmdm.tarea_03_rmlp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Set;

public class PokemonFragmentViewModel extends ViewModel {
    private final MutableLiveData<List<Pokemon>> pokedex = new MutableLiveData<>();
    private final MutableLiveData<Set<String>> capturados = new MutableLiveData<>();

    public LiveData<List<Pokemon>> getPokedex() {
        return pokedex;
    }

    public void setPokedex(List<Pokemon> pokemonList) {
        pokedex.setValue(pokemonList);
    }

    public LiveData<Set<String>> getCapturados() {
        return capturados;
    }

    public void setCapturados(Set<String> capturadosList) {
        capturados.setValue(capturadosList);
    }
}
