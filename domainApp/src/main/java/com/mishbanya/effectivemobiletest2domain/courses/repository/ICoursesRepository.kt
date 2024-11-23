package com.mishbanya.effectivemobiletest2domain.courses.repository

import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel
import com.mishbanya.effectivemobiletest2data.courses.model.CoursesResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Response

interface ICoursesRepository {
    fun getCourses(page: Int): Single<Response<CoursesResponse>>?
}