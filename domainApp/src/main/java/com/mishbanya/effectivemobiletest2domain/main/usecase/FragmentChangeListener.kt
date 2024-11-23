package com.mishbanya.effectivemobiletest2domain.main.usecase

import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel

interface FragmentChangeListener {
    fun onMainClicked()
    fun onBackToMainClicked()
    fun onFavoritesClicked()
    fun onProfileClicked()
    fun onCourseClicked(data: CourseModel)
}