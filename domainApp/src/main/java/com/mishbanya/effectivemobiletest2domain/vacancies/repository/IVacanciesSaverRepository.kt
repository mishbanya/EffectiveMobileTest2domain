package com.mishbanya.effectivemobiletest.domain.vacancies.repository

import com.mishbanya.effectivemobiletest.domain.vacancies.entity.VacancyModel

interface IVacanciesSaverRepository {
    fun saveVacancies(
        vacancies: List<VacancyModel>?
    ): Boolean
}