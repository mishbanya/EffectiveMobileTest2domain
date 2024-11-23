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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val coursesRepository: ICoursesRepository,
    private val coursesSaverRepositoryImpl: ICoursesSaverRepository,
    private val coursesGetterRepositoryImpl: ICoursesGetterRepository,
    private val changeCourseFavoritenessRepository: IChangeCourseFavoritenessRepository
) :ViewModel() {
    private val _courses = MutableLiveData<List<CourseModel>?>()
    private val disposables = CompositeDisposable()

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
        val recievedCourses = getCoursesFromSP()
        if (!recievedCourses.isNullOrEmpty()) {
            _courses.value = recievedCourses
            return true
        }else {
            return false
        }
    }

    fun getCourses(){
        tryGettingCoursesFromSP()
        disposables.clear()
        val disposable = coursesRepository.getCourses(4)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ response ->
                if (response.isSuccessful) {
                    response.body()?.let { setResponse(it.courses) }
                } else {
                    Log.e("getOffersAndVacancies", "getOffersAndVacancies failed: ${response.code()}")
                    setResponse(null)
                }
            }, { error ->
                Log.e("getOffersAndVacancies", "getOffersAndVacancies failed", error)

                if (error is HttpException) {
                    Log.d("getOffersAndVacancies", "HTTP Error: ${error.code()}")
                } else {
                    Log.d("getOffersAndVacancies", "Error: ${error.message}")
                }
                setResponse(null)
            })
        disposables.add(disposable!!)
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
    override fun onCleared() {
        disposables.dispose()
    }
}