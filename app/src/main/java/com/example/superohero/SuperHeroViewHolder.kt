package com.example.superohero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superohero.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val binding = ItemSuperheroBinding.bind(view)

    fun render(superoHeroItemResponse: SuperoHeroItemResponse,  onItemSelected:(String) -> Unit){


            Picasso.get().load(superoHeroItemResponse.superheroImage.url).into(binding.imageSuperhero)
            binding.tvSuperHeroName.text = superoHeroItemResponse.superheroName
            binding.root.setOnClickListener {
                onItemSelected(superoHeroItemResponse.superheroId)
            }

    }


}