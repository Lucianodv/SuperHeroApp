package com.example.superohero

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse (
        @SerializedName("name") val name:String,
        @SerializedName("powerstats")val powerstats:PowerStatsResponse,
        @SerializedName("image")val image :SuperheroImageResponse,
        @SerializedName("biography")val biography :SuperheroBiographyResponse,

        )

data class PowerStatsResponse(
    @SerializedName("intelligence")val intelligence :String,
    @SerializedName("strength")val strength:String,
    @SerializedName("speed")val speed:String,
    @SerializedName("durability")val durability:String,
    @SerializedName("power")val power:String,
    @SerializedName("combat")val combat:String

    )

data class SuperHeroImageDetailResponse(
    @SerializedName("url")val  url :String

)

data class SuperheroBiographyResponse(
    @SerializedName("full-name") val fullname:String,
    @SerializedName("publisher") val publisher:String

)

