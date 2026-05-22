package com.example.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.data.EsmaDataProvider
import kotlin.random.Random

class DailyReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            scheduleDailyReminder(context)
            return
        }
        showNotification(context)
    }

    companion object {
        const val CHANNEL_ID = "daily_esma_channel"
        const val NOTIFICATION_ID = 9912

        fun showNotification(context: Context) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Günlük Esma-ül Hüsna Hatırlatıcı",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Her gün bir Esma-ül Hüsna ismi ve anlamını hatırlatır."
                }
                notificationManager.createNotificationChannel(channel)
            }

            // Select a random name for the daily reminder
            val randomIndex = Random.nextInt(EsmaDataProvider.ALL_NAMES.size)
            val name = EsmaDataProvider.ALL_NAMES[randomIndex]

            // Clicking opens the app to this specific name
            val mainIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra("NAME_ID", name.id)
            }

            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                mainIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // Use simple high-contrast standard Compass icon for spiritual direction
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_compass)
                .setContentTitle("Günün Esma-ül Hüsna'sı: ${name.name}")
                .setContentText(name.meaning)
                .setStyle(NotificationCompat.BigTextStyle().bigText("${name.meaning}\n\nOkumak ve tefekkür etmek için tıklayın."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }

        fun scheduleDailyReminder(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? android.app.AlarmManager ?: return
            val intent = Intent(context, DailyReminderReceiver::class.java).apply {
                action = "com.example.ACTION_DAILY_REMINDER"
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                101,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // Trigger daily. Next run is 24 hours from now
            val triggerAt = System.currentTimeMillis() + 24 * 60 * 60 * 1000L
            
            try {
                alarmManager.setInexactRepeating(
                    android.app.AlarmManager.RTC_WAKEUP,
                    triggerAt,
                    android.app.AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            } catch (e: Exception) {
                // Fail gracefully if system denies inexact repeats
            }
        }
    }
}
