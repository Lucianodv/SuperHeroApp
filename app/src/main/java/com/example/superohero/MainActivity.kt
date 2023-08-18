package com.example.superohero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superohero.DetailSuperHeroActivity.Companion.EXTRA_ID
import com.example.superohero.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()

    }

    private fun initUI() {
        binding.svSuperHero.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })

        adapter = SuperHeroAdapter{ navigateToDetail(it) } //no hay que pasarle parametro porque ya inicio vacio antes
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperHero("$query")
            if (myResponse.isSuccessful) {
                val response: SuperHeroDataResponse? =
                    myResponse.body() //dentro del body se encuentra la respuesta
                if (response != null) { //osea si la respuesta tiene datos
                    Log.i("prueba", response.toString())
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                        adapter.updateList(response.superHeroes)
                    }
                }
            } else {
                Log.i("prueba", "no funciona ")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //vamos a crear un metodo para navegar entre pantallas
    private fun navigateToDetail(id:String){
         val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

}