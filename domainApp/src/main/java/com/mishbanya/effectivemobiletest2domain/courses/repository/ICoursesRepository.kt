package com.mishbanya.effectivemobiletest2domain.courses.repository

import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel

interface ICoursesRepository {
    fun getCourses(page: Int): kotlin.collections.List<CourseModel>?
}