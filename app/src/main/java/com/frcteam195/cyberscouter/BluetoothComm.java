package com.frcteam195.cyberscouter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;


public class BluetoothComm {
    private final static String _serviceUuid = "c3252081-b20b-46df-a9f8-1c3722eadbef";
    private final static String _serviceName = "Team195Pi";
    private final static String _errorJson = "{'result': 'failed', 'msg': 'bluetooth command failed!'}";


    static private String sendCommand(BluetoothAdapter _bluetoothAdapter, String json) {
        String resp = _errorJson;

        try {

            Set<BluetoothDevice> pairedDevices = _bluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress();
                    if (deviceName.equals(_serviceName)) {
                        BluetoothSocket mmSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(_serviceUuid));
                        mmSocket.connect();
                        OutputStream mmOutputStream = mmSocket.getOutputStream();
                        InputStream mmInputStream = mmSocket.getInputStream();
                        mmOutputStream.write(json.getBytes());
                        Thread.sleep(1);
                        byte[] ibytes = new byte[mmInputStream.available()];
                        int length = ibytes.length;
                        while(ibytes.length == 0) {
                            Thread.sleep(10);
                            ibytes = new byte[mmInputStream.available()];
                        }
                        mmInputStream.read(ibytes);
                        resp = new String(ibytes);


                        mmSocket.close();

                        break;
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        return resp;
    }

    static public String getConfig(BluetoothAdapter _bluetoothAdapter, String clientId) {
        String returnJson = _errorJson;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cmd", "get-config");
            jsonObject.put("payload", clientId);

            if(FakeBluetoothServer.bUseFakeBluetoothServer) {
                returnJson = FakeBluetoothServer.getResponse("get-config");
            } else {
                returnJson = BluetoothComm.sendCommand(_bluetoothAdapter, jsonObject.toString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
     return(returnJson);
    }
}
