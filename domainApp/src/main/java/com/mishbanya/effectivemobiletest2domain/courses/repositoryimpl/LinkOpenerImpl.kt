package com.mishbanya.effectivemobiletest2domain.courses.repositoryimpl

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mishbanya.effectivemobiletest2domain.courses.repository.ILinkOpener

class LinkOpenerImpl: ILinkOpener {
    override fun offerLinkOpen(link: String, context: Context) {
        if (link.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(link)
            context.startActivity(intent)
        }
    }
}