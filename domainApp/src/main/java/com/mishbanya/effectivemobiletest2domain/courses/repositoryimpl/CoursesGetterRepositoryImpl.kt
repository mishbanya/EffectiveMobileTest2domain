package com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mishbanya.effectivemobiletest2data.common.entity.SharedPreferenceKeys.ACCESS_TOKEN_KEY
import com.mishbanya.effectivemobiletest2data.courses.model.CourseModel
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesGetterRepository
import javax.inject.Inject

class CoursesGetterRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
): ICoursesGetterRepository {
    override fun getCourses(): List<CourseModel>? {
        val jsonCoursesList = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
        return if (jsonCoursesList != null) {
            Log.d("SharedPreferences", "Courses get successful: $jsonCoursesList")
            val type = object : TypeToken<List<CourseModel>>() {}.type
            gson.fromJson(jsonCoursesList, type)
        } else {
            Log.d("SharedPreferences", "Courses get failed.")
            null
        }
    }
}