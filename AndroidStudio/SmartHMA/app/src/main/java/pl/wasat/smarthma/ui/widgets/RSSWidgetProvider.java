package pl.wasat.smarthma.ui.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import pl.wasat.smarthma.R;

public class RSSWidgetProvider extends AppWidgetProvider {

    // String to be sent on Broadcast as soon as Data is Fetched
    // should be included on RSSWidgetProvider manifest intent action
    // to be recognized by this RSSWidgetProvider to receive broadcast
    public static final String DATA_FETCHED = "com.wordpress.laaptu.DATA_FETCHED";

    /*
     * this method is called every 30 mins as specified on widgetinfo.xml this
     * method is also called on every phone reboot from this method nothing is
     * updated right now but instead RetmoteFetchService class is called this
     * service will fetch data,and send broadcast to RSSWidgetProvider this
     * broadcast will be received by RSSWidgetProvider onReceive which in turn
     * updates the widget
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for (int appWidgetId : appWidgetIds) {
            Intent serviceIntent = new Intent(context, RemoteFetchService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    appWidgetId);
            context.startService(serviceIntent);

            // Register an onClickListener for the Refresh button
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);

            Intent clickIntent = new Intent(context, RSSWidgetProvider.class);
            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_refresh, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private RemoteViews updateWidgetListView(Context context, int appWidgetId) {

        // which layout to show on widget
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);

        // RemoteViews Service needed to provide adapter for ListView
        Intent svcIntent = new Intent(context, WidgetService.class);
        // passing app widget id to that RemoteViews Service
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        // setting a unique Uri to the intent
        // don't know its purpose to me right now
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        // setting adapter to listview of the widget
        remoteViews.setRemoteAdapter(appWidgetId, R.id.listViewWidget,
                svcIntent);
        // setting an empty view in case of no data
        remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);
        return remoteViews;
    }

    /*
     * It receives the broadcast as per the action set on intent filters on
     * Manifest.xml once data is fetched from RemotePostService,it sends
     * broadcast and RSSWidgetProvider notifies to change the data the data change
     * right now happens on ListProvider as it takes RemoteFetchService
     * listItemList as data
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(DATA_FETCHED)) {
            int appWidgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            AppWidgetManager appWidgetManager = AppWidgetManager
                    .getInstance(context);
            RemoteViews remoteViews = updateWidgetListView(context, appWidgetId);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }

    }

}