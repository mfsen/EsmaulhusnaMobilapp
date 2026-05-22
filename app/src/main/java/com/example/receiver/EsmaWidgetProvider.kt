package com.example.receiver

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import com.example.MainActivity
import com.example.R
import com.example.data.EsmaDataProvider
import com.example.data.EsmaName
import kotlin.random.Random

class EsmaWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Perform update for each active widget
        for (appWidgetId in appWidgetIds) {
            // Pick a new random/hourly Esma when the system updates the widget
            selectNewEsmaId(context, appWidgetId)
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_REFRESH) {
            val appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )
            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                // Select a new Esma-ul Husna on manual click
                selectNewEsmaId(context, appWidgetId)
                val appWidgetManager = AppWidgetManager.getInstance(context)
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }
    }

    private fun selectNewEsmaId(context: Context, appWidgetId: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val currentId = prefs.getInt(KEY_CURRENT_ESMA_ID_PREFIX + appWidgetId, -1)

        val names = EsmaDataProvider.ALL_NAMES
        if (names.isNotEmpty()) {
            var nextEsma = names[Random.nextInt(names.size)]
            // Try to pick a different one if possible
            if (nextEsma.id == currentId && names.size > 1) {
                var newIndex = Random.nextInt(names.size)
                while (names[newIndex].id == currentId) {
                    newIndex = Random.nextInt(names.size)
                }
                nextEsma = names[newIndex]
            }

            prefs.edit()
                .putInt(KEY_CURRENT_ESMA_ID_PREFIX + appWidgetId, nextEsma.id)
                .putInt(KEY_GLOBAL_LAST_ESMA_ID, nextEsma.id)
                .apply()
        }
    }

    companion object {
        const val ACTION_REFRESH = "com.example.action.WIDGET_REFRESH"
        private const val PREFS_NAME = "esma_widget_prefs"
        private const val KEY_CURRENT_ESMA_ID_PREFIX = "current_esma_id_"
        private const val KEY_GLOBAL_LAST_ESMA_ID = "current_esma_id"

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            var esmaId = prefs.getInt(KEY_CURRENT_ESMA_ID_PREFIX + appWidgetId, -1)
            
            if (esmaId == -1) {
                esmaId = prefs.getInt(KEY_GLOBAL_LAST_ESMA_ID, -1)
            }

            val names = EsmaDataProvider.ALL_NAMES
            var esma = names.find { it.id == esmaId }
            
            if (esma == null && names.isNotEmpty()) {
                esma = names[Random.nextInt(names.size)]
                prefs.edit()
                    .putInt(KEY_CURRENT_ESMA_ID_PREFIX + appWidgetId, esma.id)
                    .putInt(KEY_GLOBAL_LAST_ESMA_ID, esma.id)
                    .apply()
            }

            if (esma == null) return

            val views = RemoteViews(context.packageName, R.layout.esma_widget)

            // Setup Widget texts
            views.setTextViewText(R.id.widget_arabic_text, esma.arabic)
            views.setTextViewText(R.id.widget_name_text, esma.name)
            views.setTextViewText(R.id.widget_meaning_text, esma.meaning)

            // Setup PendingIntent to launch MainActivity with the selected Esma ID
            val mainIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtra("NAME_ID", esma.id)
            }
            
            val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }

            val mainPendingIntent = PendingIntent.getActivity(
                context,
                appWidgetId, // Unique request code per widget instance
                mainIntent,
                pendingIntentFlags
            )
            views.setOnClickPendingIntent(R.id.widget_container, mainPendingIntent)

            // Setup PendingIntent for the Refresh Button broadcast action
            val refreshIntent = Intent(context, EsmaWidgetProvider::class.java).apply {
                action = ACTION_REFRESH
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            }
            
            val refreshPendingIntent = PendingIntent.getBroadcast(
                context,
                appWidgetId + 20000, // Safe offset to prevent interference
                refreshIntent,
                pendingIntentFlags
            )
            views.setOnClickPendingIntent(R.id.widget_btn_refresh, refreshPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
