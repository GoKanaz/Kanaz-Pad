package com.gokanaz.kanazpad.utils

import android.content.Context
import android.widget.TextView
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

object MarkdownRenderer {
    
    private val parser = Parser.builder().build()
    private val renderer = HtmlRenderer.builder().build()
    
    fun renderMarkdown(context: Context, textView: TextView, markdown: String) {
        val html = parseMarkdownToHtml(context, markdown)
        textView.text = android.text.Html.fromHtml(html, android.text.Html.FROM_HTML_MODE_LEGACY)
    }
    
    fun parseMarkdownToHtml(context: Context, markdown: String): String {
        val document = parser.parse(markdown)
        return renderer.render(document)
    }
}
