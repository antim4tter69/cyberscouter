package com.frcteam195.cyberscouter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class NamePickerDialog extends DialogFragment {
    private CharSequence[] g_names = null;

    public interface NamePickerDialogListener {
        public void onNameSelected(String name);
    }

    NamePickerDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            CyberScouterDbHelper mDbHelper = new CyberScouterDbHelper(getActivity());
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            CyberScouterConfig cfg = CyberScouterConfig.getConfig(db);

            if(!cfg.isOffline()) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                (new getUserNamesTask()).execute(null, null, null);

                while (null == g_names) {
                    sleep(10);
                }
                builder.setTitle(R.string.NamePickerTitle)
                        .setItems(g_names, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lv = ((AlertDialog)dialog).getListView();
                                String tmp = (String)lv.getItemAtPosition(which);
                                mListener.onNameSelected(tmp);
                            }
                        });
                return builder.create();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NamePickerDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement NamePickerDialogListener");
        }
    }

    private class getUserNamesTask extends AsyncTask<Void, Void, Void> {
        final private static String serverAddress = "frcteam195test.cmdlvflptajw.us-east-1.rds.amazonaws.com";
        final private static String dbName = "CyberScouter";
        final private static String username = "admin";
        final private static String password = "Einstein195";

        @Override
        protected Void doInBackground(Void... arg) {

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                        + serverAddress + "/" + dbName, username, password);

                CyberScouterUsers csu = new CyberScouterUsers();
                g_names = csu.getUserNames(conn);

                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}