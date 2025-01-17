package dam.pmdm.tarea_03_rmlp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApiService {

    @GET("pokemon?offset=0&limit=150")
    Call<ArrayPokemonResponse> getApiPokemon();


    @GET
    Call<Pokemon> getPokemonDetails(@retrofit2.http.Url String url);


}