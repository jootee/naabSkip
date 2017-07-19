package services.boj.com.hoc;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import services.boj.com.hoc.Utils.NotificationUtils;

/**
 * Created by Dosh on 4/2/2017.
 */

public class MyApplication extends Application {

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        //Put your code here.
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_LONG).show();


                    // register GCM registration complete receiver
                    LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mRegistrationBroadcastReceiver,
                            new IntentFilter(Config.REGISTRATION_COMPLETE));

                    // register new push message receiver
                    // by doing this, the activity will be notified each time a new message arrives
                    LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mRegistrationBroadcastReceiver,
                            new IntentFilter(Config.PUSH_NOTIFICATION));

                    // clear the notification area when the app is opened
                    NotificationUtils.clearNotifications(getApplicationContext());
                }
            }
        };




    }
}