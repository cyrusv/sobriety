package com.cyrusv.onedayatatime;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Date;


public class MainAppWidgetProvider extends AppWidgetProvider {

    public static final String WIDGET_IDS_KEY = "mywidgetproviderwidgetids";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra(WIDGET_IDS_KEY)) {
            int[] ids = intent.getExtras().getIntArray(WIDGET_IDS_KEY);
            this.onUpdate(context, AppWidgetManager.getInstance(context), ids);
        } else super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        update(context, appWidgetManager, appWidgetIds, null);


        Intent mainIntent = new Intent(context, MainActivity.class);

        mainIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        mainIntent.putExtra(MainAppWidgetProvider.WIDGET_IDS_KEY, appWidgetIds);

        mainIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        mainIntent.putExtra(MainAppWidgetProvider.WIDGET_IDS_KEY, appWidgetIds);
    }

    public void update(Context context, AppWidgetManager manager, int[] appWidgetIds, Object data) {

        for (int widgetId : appWidgetIds) {

            long startDate = context.getSharedPreferences("userSettings", Context.MODE_PRIVATE)
                    .getLong(context.getString(R.string.date_key), 0);
            long today = new Date().getTime();

            String days = Long.toString((today-startDate)/(60*60*24*1000));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget);
            remoteViews.setTextViewText(R.id.widget_number, days);

            Intent intent = new Intent(context, CountdownActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);


            manager.updateAppWidget(widgetId, remoteViews);
        }
    }

    public static void updateMyWidgets(Context context) {
        AppWidgetManager man = AppWidgetManager.getInstance(context);
        int[] ids = man.getAppWidgetIds(
                new ComponentName(context,MainAppWidgetProvider.class));
        Intent updateIntent = new Intent();
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        updateIntent.putExtra(MainAppWidgetProvider.WIDGET_IDS_KEY, ids);
        context.sendBroadcast(updateIntent);
    }
}
