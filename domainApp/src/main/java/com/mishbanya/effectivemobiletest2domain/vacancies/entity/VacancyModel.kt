package com.mishbanya.effectivemobiletest.domain.vacancies.entity

import com.google.gson.annotations.SerializedName

data class VacancyModel(
    @SerializedName("id") val id: String,
    @SerializedName("lookingNumber") val lookingNumber: Int?,
    @SerializedName("title") val title: String,
    @SerializedName("address") val addressModel: AddressModel,
    @SerializedName("company") val company: String,
    @SerializedName("experience") val experienceModel: ExperienceModel,
    @SerializedName("publishedDate") val publishedDate: String,
    @SerializedName("isFavorite") var isFavorite: Boolean,
    @SerializedName("salary") val salaryModel: SalaryModel,
    @SerializedName("schedules") val schedules: List<String>,
    @SerializedName("description") val description: String
)
