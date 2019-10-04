package com.lambdaschool.congressdata.model



import android.widget.Toast
import com.google.gson.Gson
import com.lambdaschool.congressdata.model.ApiRetro.Factory.Companion.URL_MEMBERS_HOUSE_ALL
import com.lambdaschool.congressdata.model.ApiRetro.Factory.Companion.URL_MEMBERS_HOUSE_CALIFORNIA

import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiRetro {


    @Headers(
        "x-api-key: glCbNQgEiisCdhTwucffBYZfvBXiHCiZ18S2pEFL"
    )
    @GET(URL_MEMBERS_HOUSE_CALIFORNIA)
    fun getCACongressList(): Call<CongressPersonAll>

    @Headers(
        "x-api-key: glCbNQgEiisCdhTwucffBYZfvBXiHCiZ18S2pEFL"
    )
    @GET(URL_MEMBERS_HOUSE_ALL)
    suspend fun getMemmbersAll(): Response<CongressPersonAll>

    @GET("pokemon-species/{name}")
    fun getPokemonDetails(@Path("name") name: String): Call<List<OfficialOverview>>

    @GET("pokedex/{id}")
    suspend fun getSusPokedex(@Path("id") id:String): Response<OfficialOverview>

    @GET("pokemon-form/{name}")
    suspend  fun getSusPokemonForm(@Path("name") name: String): Response<OfficialOverview>

    @GET("pokemon-species/{name}")
    suspend  fun getSusPokemonDetails(@Path("name") name: String): Response<OfficialOverview>

    class Factory {
        companion object {
            const val API_KEY = "x-api-key: glCbNQgEiisCdhTwucffBYZfvBXiHCiZ18S2pEFL"
         //   connection.setRequestProperty("x-api-key", API_KEY);
            const val BASE_URL = "https://api.propublica.org/congress/v1/"

            const val URL_MEMBERS_HOUSE_CALIFORNIA = BASE_URL + "members/house/CA/current.json"
           const val CONGRESS_NUMBER = "115"
          const val URL_MEMBERS_HOUSE_ALL = BASE_URL + CONGRESS_NUMBER + "/house/members.json"
           const  val URL_MEMBERS_SENATE_CALIFORNIA = BASE_URL + "members/senate/CA/current.json"
           const val URL_MEMBERS_SENATE_ALL = BASE_URL + CONGRESS_NUMBER + "/senate/members.json"

           const val MEMBER_DETAILS = BASE_URL + "members/"

           const val IMAGE_URL = "https://theunitedstates.io/images/congress/450x550/"

            const  val JPG = ".jpg"
            const val JSON = ".json"













            private val TIMEOUT = 3000

            var pokedexList = mutableListOf<OfficialOverview>()
            val gson = Gson()


            fun create(): ApiRetro {

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

                return retrofit.create(ApiRetro::class.java)
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
