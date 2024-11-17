package com.mishbanya.effectivemobiletest.domain.common.repository

import com.mishbanya.effectivemobiletest.domain.common.entity.ResponseData
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface IOffersAndVacanciesRepository {
    fun getOffersAndVacancies(): Single<Response<ResponseData>>
}