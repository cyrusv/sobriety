package com.cyrusv.onedayatatime;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Date;


public class MainAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        long startDate = context.getSharedPreferences("userSettings", Context.MODE_PRIVATE)
                .getLong(context.getString(R.string.date_key), 0);
        long today = new Date().getTime();

        String days = Long.toString((today-startDate)/(60*60*24*1000));


        Intent intent = new Intent(context, CountdownActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget);
        remoteViews.setTextViewText(R.id.widget_number, days);
        remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

}
