package com.mishbanya.effectivemobiletest.domain.vacancies.usecases


interface IOnVacancyClickListener {
    fun onVacancyClick(position: Int)
    fun onIsFavoriteClick(position: Int)
}