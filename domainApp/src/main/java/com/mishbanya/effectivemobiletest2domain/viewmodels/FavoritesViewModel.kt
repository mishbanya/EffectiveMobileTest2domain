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
class FavoritesViewModel @Inject constructor(
    private val coursesRepository: ICoursesRepository,
    private val coursesSaverRepositoryImpl: ICoursesSaverRepository,
    private val coursesGetterRepositoryImpl: ICoursesGetterRepository,
    private val changeCourseFavoritenessRepository: IChangeCourseFavoritenessRepository
) :ViewModel() {

    private val _courses = MutableLiveData<List<CourseModel>?>()

    val courses: MutableLiveData<List<CourseModel>?>
        get() = _courses

    private val _favoriteCourses = MutableLiveData<List<CourseModel>?>()

    val favoriteVacancies: MutableLiveData<List<CourseModel>?>
        get() = _favoriteCourses
    private fun setResponse(vacancies: List<CourseModel>?) {
        if (vacancies != null) {
            _courses.value = vacancies
            _favoriteCourses.value = vacancies.filter { it.isFavorite }
            if (_courses.value != tryGettingCoursesFromSP()) {
                if (saveCourses(vacancies)) {
                    Log.d("CoursesSaverRepository", "courses saved")
                }
            }
        }
    }
    private fun setCourses(courses: List<CourseModel>?) {
        if (courses != null) {
            _courses.value = courses
        }
    }
    private fun tryGettingCoursesFromSP(): List<CourseModel>? {
        val recievedVacancies = getCoursesFromSP()
        if (!recievedVacancies.isNullOrEmpty()) {
            return recievedVacancies
        }
        return null
    }

    fun getCourses(){
        if(tryGettingCoursesFromSP() == null)
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