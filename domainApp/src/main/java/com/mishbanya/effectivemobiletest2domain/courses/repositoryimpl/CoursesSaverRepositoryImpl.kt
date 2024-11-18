package com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.mishbanya.effectivemobiletest2data.common.entity.SharedPreferenceKeys.ACCESS_TOKEN_KEY
import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesSaverRepository
import javax.inject.Inject

class CoursesSaverRepositoryImpl@Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
    ): ICoursesSaverRepository {
    override fun saveCourses(courses: List<CourseModel>?): Boolean {
        if (courses != null) {
            if (courses.isEmpty()) return false
            Log.d("SharedPreferences", "Courses get successful.")
            val jsonVacancyList = gson.toJson(courses)
            return try {
                Log.d("SharedPreferences", "Saving Courses")
                with(sharedPreferences.edit()) {
                    putString(ACCESS_TOKEN_KEY, jsonVacancyList)
                    apply()
                }
                true
            } catch (e: Exception) {
                Log.e("SharedPreferences", "Error saving Courses", e)
                false
            }
        }else{
            with(sharedPreferences.edit()) {
                clear()
                apply()
            }
            Log.d("SharedPreferences", "Courses cleared")
            return true
        }
    }
}