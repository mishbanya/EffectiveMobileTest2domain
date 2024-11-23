package com.mishbanya.effectivemobiletest2domain.courses.repository

import android.content.Context

interface ILinkOpener {
    fun offerLinkOpen(link: String, context: Context)
}