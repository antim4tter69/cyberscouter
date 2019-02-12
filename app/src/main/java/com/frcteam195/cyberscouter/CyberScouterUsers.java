package com.frcteam195.cyberscouter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class CyberScouterUsers {
    public CyberScouterUsers() {}

    private int userID;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String email;

    public CyberScouterUsers[] getUsers(Connection conn) {
        Vector<CyberScouterUsers> csuv = new Vector<CyberScouterUsers>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Users order by UserID");
            while(rs.next()) {
                CyberScouterUsers csu = new CyberScouterUsers();
                csu.userID = rs.getInt(rs.findColumn("UserID"));
                csu.firstName = rs.getString(rs.findColumn("FirstName"));
                csu.lastName = rs.getString(rs.findColumn("LastName"));
                csu.cellPhone = rs.getString(rs.findColumn("CellPhone"));
                csu.email = rs.getString(rs.findColumn("Email"));

                csuv.add(csu);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        if(csuv.size() > 0) {
            CyberScouterUsers[] csuv2 = new CyberScouterUsers[csuv.size()];
            return csuv.toArray(csuv2);
        } else {
            return null;
        }
    }

    public String[] getUserNames(Connection conn) {
        CyberScouterUsers[] csu = getUsers(conn);
        Vector<String>nameVector = new Vector<String>();
        if(null != csu) {
            for(int i=0 ; i<csu.length ; ++i) {
                String name = csu[i].getFirstName() + " "
                        + csu[i].getLastName();
                nameVector.add(name);
            }

        }

        if(0 < nameVector.size()) {
            String[] nv2 = new String[nameVector.size()];
            return nameVector.toArray(nv2);
        } else {
            return null;
        }
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
