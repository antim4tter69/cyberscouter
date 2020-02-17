package com.frcteam195.cyberscouter;

import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.OutputStream;
import java.nio.channels.ClosedByInterruptException;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class BackgroundUpdater extends Service {
    boolean keepRunning;
    Thread thread;
    BluetoothDevice mmDevice;
    BluetoothSocket mmSocket;
    BluetoothAdapter _bluetoothAdapter;

    private final String _serviceUuid = "c3252081-b20b-46df-a9f8-1c3722eadbef";
    private final String _serviceName = "Team195Pi";

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

        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        _bluetoothAdapter = bluetoothManager.getAdapter();

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
            int cnt = 0;
            while(keepRunning) {
                cnt++;
                try {
//                    CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(getApplicationContext());
//                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
//                    CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);
//
//                    if(null != cfg) {
//                        if(-1 != TeamMap.getNumberForTeam(cfg.getRole())) {
//                            int l_allianceStationID = TeamMap.getNumberForTeam(cfg.getRole());
//                            CyberScouterMatchScouting[] csmsa = CyberScouterMatchScouting.getMatchesReadyToUpload(db, cfg.getEvent_id(), l_allianceStationID);
//                            if(null != csmsa) {
//                                for (CyberScouterMatchScouting csms : csmsa) {
//                                    // Send record information to the SQL Server database
//                                    // Set the status of the match locally
//                                    CyberScouterMatchScouting.updateMatchUploadStatus(db, csms.getMatchScoutingID(), UploadStatus.UPLOADED);
//                                    popToast(String.format(Locale.getDefault(), "Match %d was uploaded successfully.", csms.getMatchScoutingID()));
//                                }
//                            } else {
//                                popToast(String.format(Locale.getDefault(), "Loop #%d no matches to upload.", cnt));
//                            }
//                        }
//                    }
//                    popToast(Settings.Secure.getString(getContentResolver(), "bluetooth_name"));
//                    CyberScouterMatchScouting csms = new CyberScouterMatchScouting();
//                    csms.setMatchScoutingID(11111);
//                    csms.setEventID(22222);
//                    csms.setMatchID(33333);
//                    csms.setComputerID(44444);
//                    csms.setScouterID(55555);
//                    String jsonCsms = csms.toJSON();
//
//                    sendToRfcommServer(jsonCsms);

                    int color = Color.GREEN;
                    if(FakeBluetoothServer.bUseFakeBluetoothServer)
                        color = ContextCompat.getColor(getApplicationContext(), R.color.amber);
                    if(BluetoothComm.bLastBTCommFailed())
                        color = Color.RED;
                    Intent i = new Intent(BluetoothComm.ONLINE_STATUS_UPDATED_FILTER);
                    i.putExtra("onlinestatus", color);
                    getApplicationContext().sendBroadcast(i);

                    Thread.sleep(20000);
                } catch (InterruptedException ie) {
//                } catch (ClosedByInterruptException cbie) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }

        private void sendToRfcommServer(String msg){
            String deviceName = null;
            String deviceHardwareAddress = null;
            boolean found = false;

            Set<BluetoothDevice> pairedDevices = _bluetoothAdapter.getBondedDevices();
            if(pairedDevices.size() > 0){
                for(BluetoothDevice device : pairedDevices) {
                    deviceName = device.getName();
                    deviceHardwareAddress = device.getAddress();
                    if(deviceName.equals(_serviceName)) {
                        found = true;
                        mmDevice = device;
                        break;
                    }
                }
            }

            if(!found) {
                popToast(String.format("Scan failed to find device %s", _serviceName));
                return;
            }

            byte etx = 0x03;
            try {
                mmSocket = mmDevice.createRfcommSocketToServiceRecord(UUID.fromString(_serviceUuid));
                mmSocket.connect();
                OutputStream mmOutputStream = mmSocket.getOutputStream();
                mmOutputStream.write(msg.getBytes());
                Thread.sleep(1);
                mmOutputStream.write(etx);
                Thread.sleep(100);
//                mmOutputStream.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
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
