package ca.FantasyHockeyTeamSelector.ScoreAI.Utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Optional;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import org.springframework.stereotype.Service;

import ca.FantasyHockeyTeamSelector.ScoreAI.Repository.PlayerStats;
import ca.FantasyHockeyTeamSelector.ScoreAI.Repository.Player;
import ca.FantasyHockeyTeamSelector.ScoreAI.Repository.GoalieStats;
import ca.FantasyHockeyTeamSelector.ScoreAI.Repository.Goalie;

@Service
public class PlayerScoresUtils {
    
    public int timeToPoints(String pte) {
        if (pte.equals("")) {
            return 0;
        }

        String[] parts = pte.split(":");
        int output = Integer.parseInt(parts[0]);
        return output;
    }

    public Long calulateInternalPlayerScore(PlayerStats stats, Player player) {
        Long yearScore = stats.getPoints() * 6 + stats.getGoals() * 4 + stats.getAssists() * 2 + (Long) Math.round((stats.getGames() / 82.0) * 200.0) +
                        (Long) Math.round(stats.getShots() * 0.5) + (Long) Math.round(stats.getBlocked() * 0.5) + stats.getHits() - 
                        (Long) Math.round(stats.getPim() * 0.5) + stats.getPlusMinus() + timeToPoints(stats.getTimeOnIcePerGame());
    
        if (player.getPosition().equals("C")) {
            yearScore += stats.getFaceOffPct().longValue();
        }

        return yearScore;
    }

    public Long calulateInternalGoalieScore(GoalieStats stats, Goalie goalie) {
        Long yearScore = (Long) Math.round((stats.getWins() * stats.getSavePercentage() * stats.getGoalAgainstAverage()) * 6) 
                         + (Long) Math.round((stats.getSaves() / stats.getGames()) / 3.0)
                         + stats.getShutouts() * 3 + (Long) Math.round((stats.getEvenStrengthSavePercentage() + stats.getPowerPlaySavePercentage()) * 10.0);

        if (stats.getGames() < 30) {
            yearScore -= (Long) Math.round((30.0 - stats.getGames()) / 100.0 * yearScore);
        }

        return yearScore;
    }

    public PlayerStats setPlayerStats(JSONObject playingStatsInfo, Player player) {
        PlayerStats stats = PlayerStats.builder()
                                        .assists(Optional.ofNullable((Long) playingStatsInfo.get("assists")).orElse(0L))
                                        .blocked(Optional.ofNullable((Long) playingStatsInfo.get("blocked")).orElse(0L))
                                        .faceOffPct(Optional.ofNullable((Double) playingStatsInfo.get("faceOffPct")).orElse(0.0))
                                        .games(Optional.ofNullable((Long) playingStatsInfo.get("games")).orElse(0L))
                                        .goals(Optional.ofNullable((Long) playingStatsInfo.get("goals")).orElse(0L))
                                        .hits(Optional.ofNullable((Long) playingStatsInfo.get("hits")).orElse(0L))
                                        .pim(Optional.ofNullable((Long) playingStatsInfo.get("pim")).orElse(0L))
                                        .plusMinus(Optional.ofNullable((Long) playingStatsInfo.get("plusMinus")).orElse(0L))
                                        .points(Optional.ofNullable((Long) playingStatsInfo.get("points")).orElse(0L))
                                        .powerPlayTimeOnIcePerGame(Optional.ofNullable((String) playingStatsInfo.get("powerPlayTimeOnIcePerGame")).orElse(""))
                                        .shotPct(Optional.ofNullable((Double) playingStatsInfo.get("shotPct")).orElse(0.0))
                                        .shots(Optional.ofNullable((Long) playingStatsInfo.get("shots")).orElse(0L))
                                        .timeOnIcePerGame(Optional.ofNullable((String) playingStatsInfo.get("timeOnIcePerGame")).orElse(""))
                                        .build();
    
        stats.setYearStatScore(calulateInternalPlayerScore(stats, player));

        return stats;
    }

    public GoalieStats setGoalieStats(JSONObject playingStatsInfo, Goalie goalie) {
        GoalieStats stats = GoalieStats.builder()
                                        .wins(Optional.ofNullable((Long) playingStatsInfo.get("wins")).orElse(0L))
                                        .evenStrengthSavePercentage(Optional.ofNullable((Double) playingStatsInfo.get("evenStrengthSavePercentage")).orElse(0.0))
                                        .losses(Optional.ofNullable((Long) playingStatsInfo.get("losses")).orElse(0L))
                                        .powerPlaySaves(Optional.ofNullable((Long) playingStatsInfo.get("powerPlaySaves")).orElse(0L))
                                        .goalAgainstAverage(Optional.ofNullable((Double) playingStatsInfo.get("goalAgainstAverage")).orElse(0.0))
                                        .gamesStarted(Optional.ofNullable((Long) playingStatsInfo.get("gamesStarted")).orElse(0L))
                                        .shutouts(Optional.ofNullable((Long) playingStatsInfo.get("shutouts")).orElse(0L))
                                        .saves(Optional.ofNullable((Long) playingStatsInfo.get("saves")).orElse(0L))
                                        .savePercentage(Optional.ofNullable((Double) playingStatsInfo.get("savePercentage")).orElse(0.0))
                                        .powerPlayShots(Optional.ofNullable((Long) playingStatsInfo.get("powerPlayShots")).orElse(0L))
                                        .powerPlaySavePercentage(Optional.ofNullable((Double) playingStatsInfo.get("powerPlaySavePercentage")).orElse(0.0))
                                        .games(Optional.ofNullable((Long) playingStatsInfo.get("games")).orElse(0L))
                                        .goalsAgainst(Optional.ofNullable((Long) playingStatsInfo.get("goalsAgainst")).orElse(0L))
                                        .shotsAgainst(Optional.ofNullable((Long) playingStatsInfo.get("shotsAgainst")).orElse(0L))
                                        .build();
    
        stats.setYearStatScore(calulateInternalGoalieScore(stats, goalie));

        return stats;
    }

    public JSONObject getObjectFromURL(String urlString) {
        JSONObject json = new JSONObject();

        try {
            URL url = new URL(urlString);

            HttpURLConnection connReq = (HttpURLConnection) url.openConnection();
            connReq.setRequestMethod("GET");
            connReq.connect();

            int responsecode = connReq.getResponseCode();

            if (responsecode == 200) {
                String jsonString = "";
                Scanner scan = new Scanner(url.openStream());
                while(scan.hasNext()) {
                    jsonString += scan.nextLine();
                }
                scan.close();

                JSONParser parse = new JSONParser();
                json = (JSONObject) parse.parse(jsonString);
            } else {
                System.out.println("FAILURE");
            }
        } catch (Exception e) {

        }

        return json;
    }
}