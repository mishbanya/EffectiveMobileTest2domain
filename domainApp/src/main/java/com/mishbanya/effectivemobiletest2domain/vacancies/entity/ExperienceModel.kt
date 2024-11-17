package com.mishbanya.effectivemobiletest.domain.vacancies.entity

import com.google.gson.annotations.SerializedName

data class ExperienceModel(
    @SerializedName("previewText") val previewText: String,
    @SerializedName("text") val text: String
)
