package com.frcteam195.cyberscouter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class CyberScouterUsers {
    public CyberScouterUsers() {
    }

    private int userID;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String email;

    static CyberScouterUsers[] getUsers(Connection conn) {
        Vector<CyberScouterUsers> csuv = new Vector<CyberScouterUsers>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from " + CyberScouterContract.Users.TABLE_NAME + " order by " + CyberScouterContract.Users.COLUMN_NAME_USERID);
            while (rs.next()) {
                CyberScouterUsers csu = new CyberScouterUsers();
                csu.userID = rs.getInt(rs.findColumn(CyberScouterContract.Users.COLUMN_NAME_USERID));
                csu.firstName = rs.getString(rs.findColumn(CyberScouterContract.Users.COLUMN_NAME_FIRSTNAME));
                csu.lastName = rs.getString(rs.findColumn(CyberScouterContract.Users.COLUMN_NAME_LASTNAME));
                csu.cellPhone = rs.getString(rs.findColumn(CyberScouterContract.Users.COLUMN_NAME_CELLPHONE));
                csu.email = rs.getString(rs.findColumn(CyberScouterContract.Users.COLUMN_NAME_EMAIL));

                csuv.add(csu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (csuv.size() > 0) {
            CyberScouterUsers[] csuv2 = new CyberScouterUsers[csuv.size()];
            return csuv.toArray(csuv2);
        } else {
            return null;
        }
    }

    static String[] getUserNames(SQLiteDatabase db) {
        CyberScouterUsers[] csu = getLocalUsers(db);
        Vector<String> nameVector = new Vector<String>();
        if (null != csu) {
            for (int i = 0; i < csu.length; ++i) {
                String name = csu[i].getFirstName() + " "
                        + csu[i].getLastName();
                nameVector.add(name);
            }

        }

        if (0 < nameVector.size()) {
            String[] nv2 = new String[nameVector.size()];
            return nameVector.toArray(nv2);
        } else {
            return null;
        }
    }

    static CyberScouterUsers[] getLocalUsers(SQLiteDatabase db) {
        Cursor cursor = null;

        Vector<CyberScouterUsers> csuv = new Vector<CyberScouterUsers>();
        CyberScouterUsers[] csua = null;
        CyberScouterUsers csu = null;

        String[] projection = {
                CyberScouterContract.Users.COLUMN_NAME_USERID,
                CyberScouterContract.Users.COLUMN_NAME_FIRSTNAME,
                CyberScouterContract.Users.COLUMN_NAME_LASTNAME,
                CyberScouterContract.Users.COLUMN_NAME_CELLPHONE,
                CyberScouterContract.Users.COLUMN_NAME_EMAIL
        };

        String sortOrder =
                CyberScouterContract.Users.COLUMN_NAME_LASTNAME + " ASC, " + CyberScouterContract.Users.COLUMN_NAME_LASTNAME + " ASC";

        cursor = db.query(
                CyberScouterContract.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        if (0 < cursor.getCount()) {
            while (cursor.moveToNext()) {
                csu = new CyberScouterUsers();

                csu.userID = cursor.getInt(cursor.getColumnIndex(CyberScouterContract.Users.COLUMN_NAME_USERID));
                csu.firstName = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Users.COLUMN_NAME_FIRSTNAME));
                csu.lastName = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Users.COLUMN_NAME_LASTNAME));
                csu.cellPhone = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Users.COLUMN_NAME_CELLPHONE));
                csu.email = cursor.getString(cursor.getColumnIndex(CyberScouterContract.Users.COLUMN_NAME_EMAIL));

                csuv.add(csu);
            }

            csua = new CyberScouterUsers[csuv.size()];
            return(csuv.toArray(csua));
        }

        return csua;
    }

    static void setUsers(SQLiteDatabase db, CyberScouterUsers[] csua) {
        ContentValues values = new ContentValues();

        for(CyberScouterUsers user : csua) {
            values.put(CyberScouterContract.Users.COLUMN_NAME_USERID, user.getUserID());
            values.put(CyberScouterContract.Users.COLUMN_NAME_FIRSTNAME, user.getFirstName());
            values.put(CyberScouterContract.Users.COLUMN_NAME_LASTNAME, user.getLastName());
            values.put(CyberScouterContract.Users.COLUMN_NAME_CELLPHONE, user.getCellPhone());
            values.put(CyberScouterContract.Users.COLUMN_NAME_EMAIL, user.getEmail());

            long newRowId = db.insert(CyberScouterContract.Users.TABLE_NAME, null, values);
        }
    }

    static void deleteUsers(SQLiteDatabase db) {
        db.execSQL("DELETE from " + CyberScouterContract.Users.TABLE_NAME);
    }


    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getEmail() {
        return email;
    }
}
