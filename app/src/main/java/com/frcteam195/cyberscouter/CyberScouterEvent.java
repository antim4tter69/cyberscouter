package com.frcteam195.cyberscouter;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class CyberScouterEvent {

    private Integer EventID;
    private String EventName;
    private String EventLocation;
    private String StartDate;
    private String EndDate;
    private boolean currentEvent;

    public CyberScouterEvent() {

    }

    public CyberScouterEvent getCurrentEvent(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Events where CurrentEvent = 1");
            while(rs.next()) {
                this.EventID = rs.getInt(rs.findColumn("EventID"));
                this.EventName = rs.getString(rs.findColumn("EventName"));
                this.EventLocation = rs.getString(rs.findColumn("EventLocation"));
                this.StartDate = rs.getString(rs.findColumn("StartDate"));
                this.EndDate = rs.getString(rs.findColumn("EndDate"));
                this.currentEvent = rs.getBoolean(rs.findColumn("CurrentEvent"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public Integer getEventID() {
        return EventID;
    }

    public String getEventName() {
        return EventName;
    }

    public String getEventLocation() {
        return EventLocation;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public boolean isCurrentEvent() {
        return currentEvent;
    }

}
