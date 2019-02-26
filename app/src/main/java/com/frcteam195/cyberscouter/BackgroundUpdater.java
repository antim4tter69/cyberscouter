package com.frcteam195.cyberscouter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import java.nio.channels.ClosedByInterruptException;
import java.util.Locale;

public class BackgroundUpdater extends Service {
    boolean keepRunning;
    Thread thread;

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

        if(null == thread) {
            thread = new Thread(new updateRunner());
            thread.start();
        }

        return(START_NOT_STICKY);
    }

    @Override
    public void onDestroy() {
        keepRunning = false;
        thread.interrupt();
    }

    private class updateRunner implements Runnable {

        @Override
        public void run() {
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            int cnt = 0;
            while(keepRunning) {
                cnt++;
                try {
                    CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(getApplicationContext());
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

                    if(null != cfg) {
                        if(-1 != TeamMap.getNumberForTeam(cfg.getRole())) {
                            int l_allianceStationID = TeamMap.getNumberForTeam(cfg.getRole());
                            CyberScouterMatchScouting[] csmsa = CyberScouterMatchScouting.getMatchesReadyToUpload(db, cfg.getEvent_id(), l_allianceStationID);
                            if(null != csmsa) {
                                for (CyberScouterMatchScouting csms : csmsa) {
                                    // Send record information to the SQL Server database
                                    // Set the status of the match locally
                                    CyberScouterMatchScouting.updateMatchUploadStatus(db, csms.getMatchScoutingID(), UploadStatus.UPLOADED);
                                    popToast(String.format(Locale.getDefault(), "Match %d was uploaded successfully.", csms.getMatchScoutingID()));
                                }
                            } else {
                                popToast(String.format(Locale.getDefault(), "Loop #%d no matches to upload.", cnt));
                            }
                        }
                    }

                    Thread.sleep(20000);
                } catch (InterruptedException ie) {
//                } catch (ClosedByInterruptException cbie) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }

    }

    private void popToast(final String msg) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
