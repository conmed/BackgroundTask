package com.sunny.BackgroundTask;
//package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesServices;
import com.google.appinventor.components.annotations.androidmanifest.ServiceElement;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(version = 1,
        description = "An extension to run tasks in background",
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
    public void CreateComponent(int id,String componentName){
        
    }
    @SimpleFunction()
    public void StopService(){

    }
    public static class BgService extends JobService {
        public AActivity activity;
        public CContainer container;
        public FForm form;
        public NotificationManagerCompat notificationManager;
        public BgService(){
            activity = new AActivity(this);
            form = new FForm(this);
            container = new CContainer(this);
        }

        @Override
        public boolean onStartJob(JobParameters jobParameters) {
            return true;
        }

        @Override
        public boolean onStopJob(JobParameters jobParameters) {
            return true;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return START_NOT_STICKY;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }
        public void GotEvent(Component component,String eventName, Object[] args){

        }
    }
    public static class CContainer implements ComponentContainer{
        private final BgService bgService;
        public CContainer(BgService bgService){
            this.bgService = bgService;
        }
        @Override
        public Activity $context() {
            return bgService.activity;
        }

        @Override
        public Form $form() {
            return bgService.form;
        }

        @Override
        public void $add(AndroidViewComponent component) {

        }

        @Override
        public void setChildWidth(AndroidViewComponent component, int width) {

        }

        @Override
        public void setChildHeight(AndroidViewComponent component, int height) {

        }

        @Override
        public int Width() {
            return 0;
        }

        @Override
        public int Height() {
            return 0;
        }
    }
    public static class FForm extends Form{
        private final BgService bgService;
        public FForm(BgService bgService){
            this.bgService = bgService;
        }

        @Override
        public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
            bgService.GotEvent(component,eventName,args);
            return true;
        }

        @Override
        public void dispatchGenericEvent(Component component, String eventName, boolean notAlreadyHandled, Object[] args) {
            bgService.GotEvent(component,eventName,args);
        }
    }
    public static class AActivity extends Activity{
        public AActivity(BgService bgService){}
        /*public void attachToMain(Context context){
            attachBaseContext(context);
        }*/
    }
}
