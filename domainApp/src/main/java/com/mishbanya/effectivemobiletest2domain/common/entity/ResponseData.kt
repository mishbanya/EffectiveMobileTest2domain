package com.mishbanya.effectivemobiletest.domain.common.entity

import com.google.gson.annotations.SerializedName
import com.mishbanya.effectivemobiletest.domain.offers.entity.OfferModel
import com.mishbanya.effectivemobiletest.domain.vacancies.entity.VacancyModel

data class ResponseData(
    @SerializedName("offers") val offerModels: List<OfferModel>,
    @SerializedName("vacancies") val vacancies: List<VacancyModel>
)
