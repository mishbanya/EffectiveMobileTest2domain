package com.mishbanya.effectivemobiletest2domain.viewmodels

import androidx.lifecycle.ViewModel
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesSaverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coursesSaverRepositoryImpl: ICoursesSaverRepository
) : ViewModel() {
    fun clearCourses(){
        coursesSaverRepositoryImpl.saveCourses(null)
    }
}