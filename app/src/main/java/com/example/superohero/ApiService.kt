package com.example.superohero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    //el path busca el name de arriba y lo reemplaza por la variable en este caso superHeroName

    @GET("api/10226203546869609/search/{name}")
  suspend fun getSuperHero(@Path("name")superHeroName:String):Response<SuperHeroDataResponse>


  @GET("/api/10226203546869609/{id}")
  suspend fun getDetailSuperHero(@Path("id")id:String):Response<SuperHeroDetailResponse>


}