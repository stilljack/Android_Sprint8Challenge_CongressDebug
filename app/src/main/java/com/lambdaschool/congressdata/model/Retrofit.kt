package com.lambdaschool.congressdata.model



import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface ApiInterface {



    @GET("pokedex/{id}")
    fun getPokedex(@Path("id") id:String): Call<OfficialOverview>

    @GET("pokemon-form/{name}")
    fun getPokemonForm(@Path("name") name: String): Call<OfficialOverview>

    @GET("pokemon-species/{name}")
    fun getPokemonDetails(@Path("name") name: String): Call<OfficialOverview>

    @GET("pokedex/{id}")
    suspend fun getSusPokedex(@Path("id") id:String): Response<OfficialOverview>

    @GET("pokemon-form/{name}")
    suspend  fun getSusPokemonForm(@Path("name") name: String): Response<OfficialOverview>

    @GET("pokemon-species/{name}")
    suspend  fun getSusPokemonDetails(@Path("name") name: String): Response<OfficialOverview>

    class Factory {
        companion object {
            private val TIMEOUT = 3000
            private val API_KEY = "glCbNQgEiisCdhTwucffBYZfvBXiHCiZ18S2pEFL"
            var pokedexList = mutableListOf<OfficialOverview>()
            val BASE_URL = "https://pokeapi.co/api/v2/"
            val gson = Gson()


            fun create(): ApiInterface {

                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BASIC
                logger.level = HttpLoggingInterceptor.Level.BODY
                val okHttpClient = OkHttpClient.Builder()
                    //              .addNetworkInterceptor(StethoInterceptor())
                    .addInterceptor(logger)
                    .retryOnConnectionFailure(false)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build()
                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)) //gson
                    .build()

                return retrofit.create(ApiInterface::class.java)
            }
        }
    }

}

/*
fun aPokemonHasArrived(poke:OfficialOverview) {
    if (poke.id >=1) {
        ApiInterface.Factory.pokedexList.add(poke)
        adapter.notifyItemInserted(ApiInterface.Factory.pokedexList.size)
    }
}*/

/*

btn_submit.setOnClickListener {
    CoroutineScope(Dispatchers.IO).launch {
        val response = pokedexRetrofit.getSusPokemonForm(et_pokeentry.text.toString())
        withContext(Dispatchers.Main) {
            try {
                if (response.isSuccessful) {
                    //Do something with response e.g show to the UI.

                    aPokemonHasArrived(response.body() as PokeForms)
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: HttpException) {
                Toast.makeText(this@MainActivity, "Error: ${e.message()}", Toast.LENGTH_SHORT).show()
            } catch (e: Throwable) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Error: ${e.printStackTrace()}", Toast.LENGTH_SHORT).show()
            }
        }
*/
