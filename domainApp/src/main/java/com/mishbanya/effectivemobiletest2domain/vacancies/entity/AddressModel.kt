package com.mishbanya.effectivemobiletest.domain.vacancies.entity

import com.google.gson.annotations.SerializedName

data class AddressModel(
    @SerializedName("town") val town: String,
    @SerializedName("street") val street: String,
    @SerializedName("house") val house: String
)
