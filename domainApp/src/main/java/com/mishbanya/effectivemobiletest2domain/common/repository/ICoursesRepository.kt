package com.mishbanya.effectivemobiletest2domain.common.repository

import com.mishbanya.effectivemobiletest2data.courses.model.CoursesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface ICoursesRepository {
    fun getCourses(): Single<Response<CoursesResponse>>
}