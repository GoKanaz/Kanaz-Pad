package com.gokanaz.kanazpad.utils

import android.content.Context
import com.gokanaz.kanazpad.data.model.Note
import io.noties.markwon.Markwon
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object HtmlExporter {
    
    fun exportNoteToHtml(context: Context, note: Note): File {
        val fileName = "${note.title.replace(" ", "_")}_${System.currentTimeMillis()}.html"
        val outputDir = File(context.getExternalFilesDir(null), "exports")
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
        
        val outputFile = File(outputDir, fileName)
        val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        
        val markwon = Markwon.create(context)
        val htmlContent = markwon.toMarkdown(note.content).toString()
        
        val html = """
            <!DOCTYPE html>
            <html lang="id">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <meta name="generator" content="Kanaz Pad">
                <meta name="author" content="@GoKanaz">
                <title>${note.title}</title>
                <style>
                    :root {
                        --primary: #FF6B35;
                        --secondary: #F7931E;
                        --dark: #1a1a2e;
                        --light: #ffffff;
                        --gray: #6c757d;
                    }
                    
                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                    }
                    
                    body {
                        font-family: 'Crimson Pro', 'Georgia', serif;
                        line-height: 1.8;
                        color: var(--dark);
                        max-width: 800px;
                        margin: 0 auto;
                        padding: 40px 20px;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    }
                    
                    .container {
                        background: var(--light);
                        padding: 60px;
                        border-radius: 12px;
                        box-shadow: 0 8px 24px rgba(0,0,0,0.2);
                    }
                    
                    h1, h2, h3, h4, h5, h6 {
                        font-family: 'Space Mono', monospace;
                        margin-top: 1.5em;
                        margin-bottom: 0.5em;
                        font-weight: 700;
                    }
                    
                    h1 {
                        font-size: 2.5em;
                        color: var(--primary);
                        border-bottom: 3px solid var(--primary);
                        padding-bottom: 0.3em;
                        margin-top: 0;
                    }
                    
                    h2 {
                        font-size: 2em;
                        color: var(--secondary);
                        border-bottom: 2px solid var(--secondary);
                        padding-bottom: 0.3em;
                    }
                    
                    h3 {
                        font-size: 1.5em;
                    }
                    
                    p {
                        margin-bottom: 1em;
                    }
                    
                    code {
                        font-family: 'JetBrains Mono', 'Courier New', monospace;
                        background: #f4f4f4;
                        padding: 2px 6px;
                        border-radius: 4px;
                        font-size: 0.9em;
                        color: #e83e8c;
                    }
                    
                    pre {
                        background: var(--dark);
                        color: var(--light);
                        padding: 16px;
                        border-radius: 8px;
                        overflow-x: auto;
                        margin: 1em 0;
                        border-left: 4px solid var(--primary);
                    }
                    
                    pre code {
                        background: none;
                        color: var(--light);
                        padding: 0;
                    }
                    
                    blockquote {
                        border-left: 4px solid var(--primary);
                        padding-left: 16px;
                        margin: 1em 0;
                        color: var(--gray);
                        font-style: italic;
                    }
                    
                    table {
                        width: 100%;
                        border-collapse: collapse;
                        margin: 1em 0;
                    }
                    
                    th, td {
                        border: 1px solid #e9ecef;
                        padding: 12px;
                        text-align: left;
                    }
                    
                    th {
                        background: var(--primary);
                        color: var(--light);
                        font-weight: 600;
                    }
                    
                    tr:nth-child(even) {
                        background: #f8f9fa;
                    }
                    
                    img {
                        max-width: 100%;
                        height: auto;
                        border-radius: 8px;
                        margin: 1em 0;
                        box-shadow: 0 4px 12px rgba(0,0,0,0.15);
                    }
                    
                    ul, ol {
                        margin-left: 2em;
                        margin-bottom: 1em;
                    }
                    
                    li {
                        margin-bottom: 0.5em;
                    }
                    
                    a {
                        color: var(--primary);
                        text-decoration: none;
                        border-bottom: 1px solid var(--primary);
                    }
                    
                    a:hover {
                        color: var(--secondary);
                        border-bottom-color: var(--secondary);
                    }
                    
                    .meta {
                        color: var(--gray);
                        font-size: 0.9em;
                        margin-bottom: 2em;
                        padding-bottom: 1em;
                        border-bottom: 1px solid #e9ecef;
                    }
                    
                    .footer {
                        margin-top: 3em;
                        padding-top: 1em;
                        border-top: 1px solid #e9ecef;
                        text-align: center;
                        color: var(--gray);
                        font-size: 0.85em;
                    }
                    
                    @media (max-width: 768px) {
                        .container {
                            padding: 30px 20px;
                        }
                        
                        body {
                            padding: 20px 10px;
                        }
                    }
                    
                    @media print {
                        body {
                            background: white;
                        }
                        
                        .container {
                            box-shadow: none;
                        }
                    }
                </style>
                <link href="https://fonts.googleapis.com/css2?family=Space+Mono:wght@700&family=Crimson+Pro:wght@300;400;600&family=JetBrains+Mono:wght@400;500&display=swap" rel="stylesheet">
            </head>
            <body>
                <div class="container">
                    <h1>${note.title}</h1>
                    
                    <div class="meta">
                        <p>
                            <strong>Created:</strong> ${dateFormat.format(note.createdAt)}<br>
                            <strong>Updated:</strong> ${dateFormat.format(note.updatedAt)}<br>
                            <strong>Words:</strong> ${note.getWordCount()} | 
                            <strong>Characters:</strong> ${note.getCharCount()} | 
                            <strong>Reading Time:</strong> ${note.getReadingTime()} min
                        </p>
                    </div>
                    
                    <div class="content">
                        ${htmlContent}
                    </div>
                    
                    <div class="footer">
                        <p>Generated by <strong>Kanaz Pad</strong> • @GoKanaz</p>
                        <p>${dateFormat.format(Date())}</p>
                    </div>
                </div>
            </body>
            </html>
        """.trimIndent()
        
        FileOutputStream(outputFile).use { it.write(html.toByteArray()) }
        
        return outputFile
    }
}
