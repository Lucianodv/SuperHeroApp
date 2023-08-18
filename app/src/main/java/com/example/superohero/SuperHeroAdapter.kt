package com.example.superohero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//el emptyList es para que la primera vez inicie vacio
class SuperHeroAdapter(var superHeroList: List<SuperoHeroItemResponse> = emptyList(), private val onItemSelected:(String) -> Unit) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(superHeroList: List<SuperoHeroItemResponse>){
       this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.render(superHeroList[position], onItemSelected)
    }

    override fun getItemCount(): Int {
        return superHeroList.size
    }
}