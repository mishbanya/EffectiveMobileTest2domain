package com.mishbanya.effectivemobiletest2domain

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel
import com.mishbanya.effectivemobiletest2data.courses.service.ICoursesService
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject


//ACTIVITY ONLY FOR TESTING
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var service: ICoursesService

    private val compositeDisposable = CompositeDisposable()
    private val _courses = MutableLiveData<List<CourseModel>?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("HI")
        val disposable = service.getCourse(4)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response.body()?.let { _courses.value = it.courses }
                } else {
                    Log.e("getCourses", "getCourses failed: ${response.code()}")
                }
                println(_courses.value)
            }, { error ->
                Log.e("getCourses", "getCourses failed", error)
                if (error is HttpException) {
                    Log.d("getCourses", "HTTP Error: ${error.code()}")
                } else {
                    Log.d("getCourses", "Error: ${error.message}")
                }
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}