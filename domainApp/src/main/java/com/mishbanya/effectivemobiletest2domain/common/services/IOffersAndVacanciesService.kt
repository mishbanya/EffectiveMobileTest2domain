package com.mishbanya.effectivemobiletest.domain.common.services

import com.mishbanya.effectivemobiletest.domain.common.entity.ResponseData
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface IOffersAndVacanciesService {
    @GET
    fun getOffersAndVacancies(@Url fileUrl: String): Single<Response<ResponseData>>
}