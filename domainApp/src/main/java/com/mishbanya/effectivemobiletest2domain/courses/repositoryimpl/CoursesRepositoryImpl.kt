package com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl

import android.util.Log
import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel
import com.mishbanya.effectivemobiletest2data.courses.model.CoursesResponse
import com.mishbanya.effectivemobiletest2data.courses.service.ICoursesService
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val coursesService: ICoursesService
): ICoursesRepository {
    override fun getCourses(page: Int): Single<Response<CoursesResponse>> {
        return  coursesService.getCourse(page)
    }

}