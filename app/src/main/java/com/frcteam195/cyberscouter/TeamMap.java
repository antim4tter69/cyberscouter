package com.frcteam195.cyberscouter;

public class TeamMap {

    public static String getNumberForTeam(CyberScouterMatches csm, String teamName) {
        String ret = "unknown";

        if(teamName.equals("Blue 1"))
            ret = csm.getBlueTeam1();
        else if(teamName.equals("Blue 2"))
            ret = csm.getBlueTeam2();
        else if(teamName.equals("Blue 3"))
            ret = csm.getBlueTeam3();
        else if(teamName.equals("Red 1"))
            ret = csm.getRedTeam1();
        else if(teamName.equals("Red 2"))
            ret = csm.getRedTeam2();
        else if(teamName.equals("Red 3"))
            ret = csm.getRedTeam3();
        else
            ret = "unknown";

        return ret;
    }
}
