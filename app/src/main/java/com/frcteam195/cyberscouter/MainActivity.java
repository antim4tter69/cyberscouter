package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdmin1();

            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScouting();

            }
        });

        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncPictures();

            }
        });

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncData();

            }
        });

        button = (Button) findViewById(R.id.SwitchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOffline();

            }
        });
    }
    public void openAdmin1(){
    Intent intent = new Intent(this, Admin1.class);
    startActivity(intent);
    }

    public void openScouting(){
    }

    public void syncPictures(){
    }

    public void syncData(){
    }

    public void setOffline(){
    }

    public final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {}

        /* Inner class that defines the table contents */
        public class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "configuration";
            public static final String COLUMN_NAME_ROLE = "role";
            public static final String COLUMN_NAME_EVENT = "event";
            public static final String COLUMN_NAME_TABLET = "tablet number";
            public static final String COLUMN_NAME_LAYOUT = "field layout";
            public static final String COLUMN_NAME_CONNECT = "online/offline";

            private final String SQL_CREATE_ENTRIES =
                    "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                            FeedEntry._ID + " INTEGER PRIMARY KEY," +
                            FeedEntry.COLUMN_NAME_ROLE + " ROLE," +
                            FeedEntry.COLUMN_NAME_TABLET + " TABLET NUMBER," +
                            FeedEntry.COLUMN_NAME_LAYOUT + "FIELD LAYOUT," +
                            FeedEntry.COLUMN_NAME_CONNECT + "ONLINE/OFFLINE," +
                            FeedEntry.COLUMN_NAME_EVENT + " EVENT)";

            private static final String SQL_DELETE_ENTRIES =
                    "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


        }

        public class FeedReaderDbHelper extends SQLiteOpenHelper {
            // If you change the database schema, you must increment the database version.
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "FeedReader.db";

            public FeedReaderDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_ENTRIES);
            }
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // This database is only a cache for online data, so its upgrade policy is
                // to simply to discard the data and start over
                db.execSQL(SQL_DELETE_ENTRIES);
                onCreate(db);
            }
            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                onUpgrade(db, oldVersion, newVersion);
            }
            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);

            FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getContext());

            // Gets the data repository in write mode
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
values.put(FeedEntry.COLUMN_NAME_ROLE, role);
values.put(FeedEntry.COLUMN_NAME_EVENT, event);
values.put(FeedEntry.COLUMN_NAME_TABLET, tablet);
values.put(FeedEntry.COLUMN_NAME_LAYOUT, layout);
Values.put(FeedEntry.COLUMN_NAME_CONNECT, connect);

            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
// you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                    FeedEntry.COLUMN_NAME_ROLE,
                    FeedEntry.COLUMN_NAME_EVENT,
                    FeedEntry.COLUMN_NAME_TABLET,
                    FeedEntry.COLUMN_NAME_LAYOUT,
                    FeedEntry.COLUMN_NAME_CONNECT,
            };

            // Filter results WHERE "title" = 'My Title'
            String selection = FeedEntry.COLUMN_NAME_ROLE + " = ?";
            String[] selectionArgs = { "ROLE" };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    FeedEntry.COLUMN_NAME_EVENT + " DESC";

            Cursor cursor = db.query(
                    FeedEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );
            List itemIds = new ArrayList<>();
while(cursor.moveToNext()) {
                long itemId = cursor.getLong(
                        cursor.getColumnIndexOrThrow(FeedEntry._ID));
                itemIds.add(itemId);
            }
cursor.close();

            // Define 'where' part of query.
            String selection = FeedEntry.COLUMN_NAME_ROLE + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { "Role" };
            // Issue SQL statement.
            int deletedRows = db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);

            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            // New value for one column
            String title = "MyNewTitle";
            ContentValues values = new ContentValues();
values.put(FeedEntry.COLUMN_NAME_ROLE, role);

            // Which row to update, based on the title
            String selection = FeedEntry.COLUMN_NAME_ROLE + " LIKE ?";
            String[] selectionArgs = { "MyOldTitle" };

            int count = db.update(
                    FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);

        }

    }
    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}