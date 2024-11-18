package com.mishbanya.effectivemobiletest2domain.courses.repository

import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel
import com.mishbanya.effectivemobiletest2data.courses.model.CoursesResponse
import io.reactivex.rxjava3.core.Single

interface ICoursesRepository {
    fun getCourses(page: Int): List<CourseModel>?
}