package com.gokanaz.kanazpad.utils

import android.content.Context
import android.widget.TextView
import io.noties.markwon.Markwon
import io.noties.markwon.SoftBreakAddsNewLinePlugin
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.ImagesPlugin

object MarkdownRenderer {
    
    private var markwonInstance: Markwon? = null
    
    fun getMarkwon(context: Context): Markwon {
        if (markwonInstance == null) {
            markwonInstance = Markwon.builder(context)
                .usePlugin(ImagesPlugin.create())
                .usePlugin(TablePlugin.create(context))
                .usePlugin(HtmlPlugin.create())
                .usePlugin(SoftBreakAddsNewLinePlugin.create())
                .build()
        }
        return markwonInstance!!
    }
    
    fun renderMarkdown(context: Context, textView: TextView, markdown: String) {
        val markwon = getMarkwon(context)
        markwon.setMarkdown(textView, markdown)
    }
    
    fun parseMarkdownToHtml(context: Context, markdown: String): String {
        val markwon = getMarkwon(context)
        return markwon.toMarkdown(markdown).toString()
    }
}
