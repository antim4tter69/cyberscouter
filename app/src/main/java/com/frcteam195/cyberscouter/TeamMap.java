package com.frcteam195.cyberscouter;

public class TeamMap {

    public static int getNumberForTeam(String teamName) {
        int ret = -1;

        if(teamName.equals("Red 1"))
            ret = 1;
        else if(teamName.equals("Red 2"))
            ret = 2;
        else if(teamName.equals("Red 3"))
            ret = 3;
        else if(teamName.equals("Blue 1"))
            ret = 4;
        else if(teamName.equals("Blue 2"))
            ret = 5;
        else if(teamName.equals("Blue 3"))
            ret = 6;
        else
            ret = -1;

        return ret;
    }
}
