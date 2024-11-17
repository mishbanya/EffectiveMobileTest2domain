package com.mishbanya.effectivemobiletest.domain.common.repositoryimpl

import com.mishbanya.effectivemobiletest.domain.common.repository.IMultipleLangRepository

class MultipleLangRepositoryImpl : IMultipleLangRepository{
    override fun multipleVacanciesLang(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "$count вакансия"
            count % 10 in 2..4 && (count % 100 !in 12..14) -> "$count вакансии"
            else -> "$count вакансий"
        }
    }

    override fun multiplePeopleLang(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "$count человек"
            count % 10 in 2..4 && (count % 100 !in 12..14) -> "$count человека"
            else -> "$count людей"
        }
    }
}