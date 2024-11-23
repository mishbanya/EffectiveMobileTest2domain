package com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl

import com.mishbanya.effectivemobiletest2domain.courses.repository.IChangeCourseFavoritenessRepository
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesGetterRepository
import com.mishbanya.effectivemobiletest2domain.courses.repository.ICoursesSaverRepository
import javax.inject.Inject

class ChangeCourseFavoritenessRepositoryImpl@Inject constructor(
    private val coursesSaverRepository: ICoursesSaverRepository,
    private val coursesGetterRepository: ICoursesGetterRepository
): IChangeCourseFavoritenessRepository {
    override fun changeFavoriteness(id: Int) {
        val courses = coursesGetterRepository.getCourses()
        if (courses != null) {
            for(course in courses){
                if(course.id == id) {
                    println(course.isFavorite)
                    course.isFavorite = !course.isFavorite
                    println(course.isFavorite)
                    println(course.title)
                    break
                }
            }
        }
        println("changed")
        coursesSaverRepository.saveCourses(courses)
    }
}