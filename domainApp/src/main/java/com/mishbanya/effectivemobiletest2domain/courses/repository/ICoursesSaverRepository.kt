package com.mishbanya.effectivemobiletest2domain.courses.repository

import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel

interface ICoursesSaverRepository {
    fun saveCourses(courses: List<CourseModel>?): Boolean
}
