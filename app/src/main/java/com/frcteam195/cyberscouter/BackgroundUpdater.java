package com.frcteam195.cyberscouter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class BackgroundUpdater extends Service {
    boolean keepRunning;

    public BackgroundUpdater() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
        keepRunning = true;

        Thread thread = new Thread(new updateRunner());
        thread.start();

        return(START_NOT_STICKY);
    }

    @Override
    public void onDestroy() {
        keepRunning = false;

    }

    private class updateRunner implements Runnable {

        @Override
        public void run() {
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            int cnt = 0;
            while(keepRunning) {
                try {
                    cnt++;
                    Thread.sleep(20000);
                    Toast.makeText(getApplicationContext(), "Count of loops is " + cnt, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }

    }
}
