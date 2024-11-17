package com.mishbanya.effectivemobiletest.domain.common.repository

interface IMultipleLangRepository {
    fun multipleVacanciesLang(count: Int): String
    fun multiplePeopleLang(count: Int): String
}