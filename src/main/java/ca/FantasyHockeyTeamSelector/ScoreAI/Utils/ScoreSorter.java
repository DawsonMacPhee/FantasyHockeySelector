package ca.FantasyHockeyTeamSelector.ScoreAI.Utils;

import java.util.Comparator;

import ca.FantasyHockeyTeamSelector.ScoreAI.Model.Player;

public class ScoreSorter implements Comparator<Player>{
    @Override
    public int compare(Player a, Player b) {
        return Math.toIntExact(a.getStatScore()) - Math.toIntExact(b.getStatScore());
    }
}