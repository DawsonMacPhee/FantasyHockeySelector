package ca.FantasyHockeyTeamSelector.ScoreAI;

import java.util.Comparator;

public class ScoreSorter implements Comparator<Player>{
    @Override
    public int compare(Player a, Player b) {
        return Math.toIntExact(a.statScore) - Math.toIntExact(b.statScore);
    }
}