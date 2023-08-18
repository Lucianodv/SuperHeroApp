package com.example.superohero

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse (
   @SerializedName("response") var response:String,
   @SerializedName("results")  var superHeroes:List<SuperoHeroItemResponse>  //creamos una lista porque el objeto era entre corchetes []

   )
//un objeto hijo que se encuentran dentro de results
data class SuperoHeroItemResponse(
    @SerializedName("id") var superheroId:String,
    @SerializedName("name") var superheroName:String,
    @SerializedName("image") val superheroImage:SuperheroImageResponse //este accede al campo imagen y es de tipo de la url de abajo
)

//accedemos a la url de la imagen en otra data class porque es otro objeto, arriba creamos el objeto de tipo imagenresponse
data class SuperheroImageResponse(
    @SerializedName("url") val url:String,
)








