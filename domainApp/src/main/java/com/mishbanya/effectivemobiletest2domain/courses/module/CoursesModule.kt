package com.mishbanya.effectivemobiletest2domain.courses.module

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.mishbanya.effectivemobiletest2data.courses.service.ICoursesService
import com.mishbanya.effectivemobiletest2domain.courses.repository.IChangeCourseFavoritenessRepository
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesGetterRepository
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesRepository
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesSaverRepository
import com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl.ChangeCourseFavoritenessRepositoryImpl
import com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl.CoursesGetterRepositoryImpl
import com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl.CoursesRepositoryImpl
import com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl.CoursesSaverRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoursesModule {
    @Provides
    @Singleton
    fun provideCoursesSaverRepositoryImpl(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): ICoursesSaverRepository {
        Log.d("Hilt", "Creating CoursesSaverRepositoryImpl client instance")
        return CoursesSaverRepositoryImpl(
            sharedPreferences,
            gson
        )
    }
    @Provides
    @Singleton
    fun provideCoursesGetterRepositoryImpl(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): ICoursesGetterRepository {
        Log.d("Hilt", "Creating CoursesGetterRepositoryImpl client instance")
        return CoursesGetterRepositoryImpl(
            sharedPreferences,
            gson
        )
    }

    @Provides
    @Singleton
    fun provideChangeCourseFavoritenessRepositoryImpl(
        coursesSaverRepository: ICoursesSaverRepository,
        coursesGetterRepository: ICoursesGetterRepository
    ): IChangeCourseFavoritenessRepository {
        Log.d("Hilt", "Creating ChangeCourseFavoritenessRepositoryImpl client instance")
        return ChangeCourseFavoritenessRepositoryImpl(
            coursesSaverRepository,
            coursesGetterRepository
        )
    }

    @Provides
    @Singleton
    fun provideCoursesRepositoryImpl(
        coursesService: ICoursesService
    ): ICoursesRepository {
        Log.d("Hilt", "Creating ChangeCourseFavoritenessRepositoryImpl client instance")
        return CoursesRepositoryImpl(
            coursesService
        )
    }
}