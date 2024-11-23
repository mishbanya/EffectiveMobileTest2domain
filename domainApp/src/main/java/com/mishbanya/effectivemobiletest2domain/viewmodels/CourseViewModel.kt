package com.mishbanya.effectivemobiletest2domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor() : ViewModel() {

    private val _courseData = MutableLiveData<CourseModel>()
    val courseData: LiveData<CourseModel>
        get() = _courseData

    fun load(course: CourseModel) {
        _courseData.value = course
    }
}