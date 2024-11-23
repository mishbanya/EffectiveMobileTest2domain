package com.mishbanya.effectivemobiletest2domain.courses.usecases

import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel


interface IOnCourseClickListener {
    fun onCourseClick(data: CourseModel)
}