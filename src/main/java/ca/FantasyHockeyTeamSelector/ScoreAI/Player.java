package ca.FantasyHockeyTeamSelector.ScoreAI;

import java.util.ArrayList;

public class Player {
    public String name;
    public Long id;
    public String position;
    public Long age;
    public Long statScore = 0L;

    ArrayList<PlayerStats> stats = new ArrayList<PlayerStats>();
}