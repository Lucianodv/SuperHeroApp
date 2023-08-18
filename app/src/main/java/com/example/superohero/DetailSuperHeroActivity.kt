package com.example.superohero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.example.superohero.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailSuperHeroBinding

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID).orEmpty()

        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch{
          val superheroDetail =  getRetrofit().create(ApiService::class.java).getDetailSuperHero(id)
            if (superheroDetail.body() != null){
                runOnUiThread {
                    createUI(superheroDetail.body()!!)
                }
            }

        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvName.text = superhero.name
        binding.fullname.text = superhero.biography.fullname
        binding.publisher.text = superhero.biography.publisher
        prepareStats(superhero.powerstats)
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeigth(binding.intelligence,powerstats.intelligence)
        updateHeigth(binding.durability,powerstats.durability)
        updateHeigth(binding.speed,powerstats.speed)
        updateHeigth(binding.strength,powerstats.strength)
        updateHeigth(binding.power,powerstats.power)
        updateHeigth(binding.combat,powerstats.combat)
    }

    private fun updateHeigth(view:View, stat:String){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,resources.displayMetrics).roundToInt()
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}