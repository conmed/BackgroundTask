package com.sunny.BackgroundTask;
import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.annotations.androidmanifest.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
@DesignerComponent(version = 1,
        description = "An example extension for calling procedures in a background service",
        iconName = "https://res.cloudinary.com/andromedaviewflyvipul/image/upload/c_scale,h_20,w_20/v1571472765/ktvu4bapylsvnykoyhdm.png",
        nonVisible = true,
        category = ComponentCategory.EXTENSION)
@SimpleObject(external = true)
@UsesServices(services = {@ServiceElement(exported = "false",name = "com.sunny.BackgroundTask.BackgroundTask$BgService")})
public class BackgroundTask extends AndroidNonvisibleComponent{
    public Activity activity;
    public Context context;
    public BackgroundTask(ComponentContainer container) {
        super(container.$form());
        activity = container.$context();
        context = activity;
    }
    @SimpleFunction()
    public void StartBackgroundService(String procedureName){
        try {
            context.startService(new Intent(context,BgService.class)
            .putExtra("PROCEDURE_NAME",procedureName));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG);
        }
    }
    @SimpleFunction()
    public void StopService(){
        try {
            context.stopService(new Intent(context,BgService.class));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG);
        }
    }
    public static class BgService extends Service {
        public Context mContext;
        public NotificationManagerCompat notificationManager;
        public BgService(){
            super();
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            mContext = getApplicationContext();
            notificationManager = NotificationManagerCompat.from(mContext);
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext,mContext.getApplicationInfo().name);
            //builder.setContentTitle(title);
            builder.setContentText("Service started by user");
            builder.setAutoCancel(false);
            builder.setVisibility(1);
            builder.setPriority(1);
            notificationManager.notify(12345,builder.build());
            //Toast.makeText(context, "Service started by user.", Toast.LENGTH_LONG).show();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onDestroy() {
            //Toast.makeText(context, "Service stopped by user.", Toast.LENGTH_LONG).show();
            //notificationManager.cancel(12345);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext,mContext.getApplicationInfo().name);
            //builder.setContentTitle(title);
            builder.setContentText("Service stopped by user");
            builder.setAutoCancel(false);
            builder.setVisibility(1);
            builder.setPriority(1);
            notificationManager.notify(12345,builder.build());
            super.onDestroy();
        }
    }
}
