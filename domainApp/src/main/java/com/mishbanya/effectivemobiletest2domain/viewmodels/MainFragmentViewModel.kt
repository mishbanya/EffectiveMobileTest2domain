package com.mishbanya.effectivemobiletest2domain.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel
import com.mishbanya.effectivemobiletest2domain.courses.repository.IChangeCourseFavoritenessRepository
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesGetterRepository
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesRepository
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesSaverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val coursesRepository: ICoursesRepository,
    private val coursesSaverRepositoryImpl: ICoursesSaverRepository,
    private val coursesGetterRepositoryImpl: ICoursesGetterRepository,
    private val changeCourseFavoritenessRepository: IChangeCourseFavoritenessRepository
) :ViewModel() {
    private val _courses = MutableLiveData<List<CourseModel>?>()

    val courses: MutableLiveData<List<CourseModel>?>
        get() = _courses
    private fun setResponse(responseData: List<CourseModel>?) {
        if (_courses.value.isNullOrEmpty()) {
            _courses.value = responseData
            if(saveCourses(responseData)){
                Log.d("CoursesSaverRepository", "courses saved")
            }
        }
    }
    private fun setCourses(courses: List<CourseModel>?) {
        if (courses != null) {
            _courses.value = courses
        }
    }
    private fun tryGettingCoursesFromSP(): Boolean{
        val recievedCourses = this.getCoursesFromSP()
        if (!recievedCourses.isNullOrEmpty()) {
            _courses.value = recievedCourses
            return true
        }
        return false
    }

    fun getCourses(){
        if(!tryGettingCoursesFromSP())
            setResponse(coursesRepository.getCourses(4))
    }
    private fun saveCourses(courses: List<CourseModel>?): Boolean {
        return coursesSaverRepositoryImpl.saveCourses(courses)
    }
    private fun getCoursesFromSP(): List<CourseModel>?{
        return coursesGetterRepositoryImpl.getCourses()
    }
    fun changeFavoriteness(position: Int){
        val coursesList = _courses.value?.toList()
        coursesList?.get(position)?.let { changeCourseFavoritenessRepository.changeFavoriteness(it.id) }
        setCourses(this.getCoursesFromSP())
    }
}