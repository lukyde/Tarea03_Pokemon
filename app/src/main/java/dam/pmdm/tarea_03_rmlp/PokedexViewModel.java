package dam.pmdm.tarea_03_rmlp;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexViewModel extends ViewModel {


    private final MutableLiveData<List<Pokemon>> pokedex = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Set<String>> capturados = new MutableLiveData<>(new HashSet<>());


    public LiveData<List<Pokemon>> getPokedex() {
        return pokedex;
    }

    public void setPokedex(List<Pokemon> pokemons) {
        pokedex.setValue(pokemons);
    }

    public LiveData<Set<String>> getCapturados() {
        return capturados;
    }

    public void setCapturados(Set<String> nombresCapturados) {
        capturados.setValue(nombresCapturados);
    }
}

