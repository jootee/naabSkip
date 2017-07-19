package services.boj.com.hoc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Startup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
            setContentView(R.layout.activity_startup);
            Intent intent = new Intent(Startup.this, MainActivity.class);
            startActivity(intent);

        }else{
            //no connection
            Toast toast = Toast.makeText(Startup.this, "No Internet Connection....Try Again", Toast.LENGTH_LONG);
            toast.show();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();
                      System.exit(1);
                }
            }, 6000);
          //
        }

    }
}
