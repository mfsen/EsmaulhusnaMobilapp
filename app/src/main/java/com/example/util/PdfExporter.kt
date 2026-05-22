package com.example.util

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.data.EsmaNameModel
import java.io.File
import java.io.FileOutputStream

object PdfExporter {
    fun exportNotesToPdf(context: Context, names: List<EsmaNameModel>) {
        // Filter elements containing valid notes
        val notesList = names.filter { it.note.isNotBlank() }
        if (notesList.isEmpty()) {
            Toast.makeText(context, "Dışa aktaracak kayıtlı notunuz bulunmamaktadır.", Toast.LENGTH_SHORT).show()
            return
        }

        val pdfDocument = PdfDocument()
        
        // standard page dimensions at 72dpi
        val pageWidth = 595
        val pageHeight = 842

        // Setup custom visual paints
        val titlePaint = Paint().apply {
            color = Color.rgb(18, 92, 80) // Spiritual Calm Deep Emerald
            textSize = 20f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }
        
        val subtitlePaint = Paint().apply {
            color = Color.rgb(100, 110, 105)
            textSize = 11f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
            isAntiAlias = true
        }

        val numberingPaint = Paint().apply {
            color = Color.rgb(180, 140, 60) // Calm Gold Accent
            textSize = 13f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }

        val namePaint = Paint().apply {
            color = Color.rgb(18, 92, 80)
            textSize = 14f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }

        val arabicPaint = Paint().apply {
            color = Color.rgb(20, 30, 25)
            textSize = 15f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }

        val labelPaint = Paint().apply {
            color = Color.rgb(100, 110, 105)
            textSize = 10f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }

        val textPaint = Paint().apply {
            color = Color.rgb(40, 45, 42)
            textSize = 11f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            isAntiAlias = true
        }

        val dividerPaint = Paint().apply {
            color = Color.rgb(230, 235, 232)
            strokeWidth = 1f
        }

        var pageNumber = 1
        var myPageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
        var myPage = pdfDocument.startPage(myPageInfo)
        var canvas: Canvas = myPage.canvas

        // Header Branding
        canvas.drawText("Esma-ül Hüsna Tefekkür Notlarım", 40f, 60f, titlePaint)
        canvas.drawText("Yaratıcının güzel isimleri üzerine aldığım kişisel notlar ve düşüncelerim.", 40f, 80f, subtitlePaint)
        canvas.drawLine(40f, 95f, (pageWidth - 40).toFloat(), 95f, dividerPaint)

        var yPos = 130f
        val xMargin = 40f
        val contentWidth = pageWidth - (2 * xMargin)

        for (item in notesList) {
            // Check remaining space
            if (yPos > pageHeight - 160f) {
                pdfDocument.finishPage(myPage)
                
                pageNumber++
                myPageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
                myPage = pdfDocument.startPage(myPageInfo)
                canvas = myPage.canvas
                
                // Draw Continuation Header
                canvas.drawText("Esma-ül Hüsna Tefekkür Notlarım (Devamı)", 40f, 50f, titlePaint)
                canvas.drawLine(40f, 65f, (pageWidth - 40).toFloat(), 65f, dividerPaint)
                yPos = 100f
            }

            // Draw ID circle or simple Gold Prefix
            val idText = "#${item.details.id}"
            canvas.drawText(idText, xMargin, yPos, numberingPaint)
            
            // Draw Turkish name and Arabic Calligraphy
            val mainText = " ${item.details.name} - ${item.details.pronunciation}"
            canvas.drawText(mainText, xMargin + 35f, yPos, namePaint)
            
            // Draw Arabic right-aligned on the same line if possible
            val arabText = item.details.arabic
            val arabWidth = arabicPaint.measureText(arabText)
            canvas.drawText(arabText, pageWidth - xMargin - arabWidth, yPos, arabicPaint)
            
            yPos += 20f

            // Draw Label
            canvas.drawText("TEFEKKÜR NOTUM:", xMargin, yPos, labelPaint)
            yPos += 16f

            // Clean wrap-text implementation for notes
            val words = item.note.split(" ", "\n")
            var line = ""
            for (word in words) {
                if (word.isBlank()) continue
                val testLine = if (line.isEmpty()) word else "$line $word"
                val testLineWidth = textPaint.measureText(testLine)
                if (testLineWidth > contentWidth) {
                    canvas.drawText(line, xMargin, yPos, textPaint)
                    yPos += 16f
                    line = word
                } else {
                    line = testLine
                }
            }
            if (line.isNotEmpty()) {
                canvas.drawText(line, xMargin, yPos, textPaint)
                yPos += 16f
            }

            // Draw fine separation line between entries
            yPos += 12f
            canvas.drawLine(xMargin, yPos, (pageWidth - xMargin), yPos, dividerPaint)
            yPos += 24f
        }

        pdfDocument.finishPage(myPage)

        val pdfFile = File(context.cacheDir, "Esmaul_Husna_Tefekkur_Notlarim.pdf")
        try {
            val fos = FileOutputStream(pdfFile)
            pdfDocument.writeTo(fos)
            pdfDocument.close()
            fos.close()

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                pdfFile
            )

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/pdf"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, "Esma-ül Hüsna Notlarım")
                putExtra(Intent.EXTRA_TEXT, "Esma-ül Hüsna uygulaması üzerinden kaydettiğim tefekkür notlarımı paylaşıyorum. Selametle.")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(shareIntent, "PDF Notlarını Paylaş"))

        } catch (e: Exception) {
            Toast.makeText(context, "PDF paylaşılamadı: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }
}
