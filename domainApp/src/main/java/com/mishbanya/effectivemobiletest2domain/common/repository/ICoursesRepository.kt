package com.mishbanya.effectivemobiletest.domain.common.repository

import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface ICoursesRepository {
    fun getCourses(): Single<Response<CoursesResponse>>
}