package com.frcteam195.cyberscouter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class CyberScouterContract {

    private CyberScouterContract(){}

    public static class ConfigEntry implements BaseColumns {
        public static final String TABLE_NAME = "config";
        public static final String COLUMN_NAME_ROLE = "role";
        public static final String COLUMN_NAME_EVENT = "event";
        public static final String COLUMN_NAME_TABLET_NUM = "tablet_num";
    }

}
