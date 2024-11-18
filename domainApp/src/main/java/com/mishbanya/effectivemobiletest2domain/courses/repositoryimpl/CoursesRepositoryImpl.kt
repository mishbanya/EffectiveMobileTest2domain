package com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl

import android.util.Log
import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel
import com.mishbanya.effectivemobiletest2data.courses.service.ICoursesService
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val coursesService: ICoursesService
): ICoursesRepository {
    override fun getCourses(page: Int): List<CourseModel>? {
        var courses: List<CourseModel>? = null

        val disposable = coursesService.getCourse(4)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        if(it.courses.isNotEmpty())
                            courses = it.courses
                    }
                } else {
                    Log.e("CoursesRepositoryImpl", "getCourses failed: ${response.code()}")
                }
            }, { error ->
                if (error is HttpException) {
                    Log.e("CoursesRepositoryImpl", "HTTP Error: ${error.code()}")
                } else {
                    Log.e("CoursesRepositoryImpl", "Error: ${error.message}")
                }
            })

        disposable.dispose()
        return courses
    }

}