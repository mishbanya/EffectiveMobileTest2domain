package com.mishbanya.effectivemobiletest.domain.vacancies.repository

import com.mishbanya.effectivemobiletest.domain.vacancies.entity.VacancyModel

interface IVacanciesGetterRepository {
    fun getVacancies(): List<VacancyModel>?
}