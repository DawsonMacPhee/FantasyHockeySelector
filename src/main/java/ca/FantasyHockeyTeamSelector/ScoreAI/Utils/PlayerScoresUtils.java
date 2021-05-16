package ca.FantasyHockeyTeamSelector.ScoreAI.Utils;

import java.util.ArrayList;
import java.util.Scanner;
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
        String[] parts = pte.split(":");
        int output = Integer.parseInt(parts[0]);
        return output;
    }

    public Long calulateInternalPlayerScore(PlayerStats stats, Player player) {
        Long yearScore = stats.getPoints() * 6 + stats.getGoals() * 4 + stats.getAssists() * 2 + (Long) ((stats.getGames() / 82) * 200) +
                        (Long) Math.round(stats.getShots() * 0.5) + (Long) Math.round(stats.getBlocked() * 0.5) + stats.getHits() - 
                        (Long) Math.round(stats.getPim() * 0.5) + stats.getPlusMinus() + timeToPoints(stats.getTimeOnIcePerGame());
    
        if (player.getPosition().equals("C")) {
            yearScore += stats.getFaceOffPct().longValue();
        }

        return yearScore;
    }

    public Long calulateInternalGoalieScore(GoalieStats stats, Goalie goalie) {
        return stats.getSaves();
    }

    public PlayerStats setPlayerStats(JSONObject playingStatsInfo, Player player) {
        PlayerStats stats = PlayerStats.builder()
                                        .assists((Long) playingStatsInfo.get("assists"))
                                        .blocked((Long) playingStatsInfo.get("blocked"))
                                        .faceOffPct((Double) playingStatsInfo.get("faceOffPct"))
                                        .games((Long) playingStatsInfo.get("games"))
                                        .goals((Long) playingStatsInfo.get("goals"))
                                        .hits((Long) playingStatsInfo.get("hits"))
                                        .pim((Long) playingStatsInfo.get("pim"))
                                        .plusMinus((Long) playingStatsInfo.get("plusMinus"))
                                        .points((Long) playingStatsInfo.get("points"))
                                        .powerPlayTimeOnIcePerGame((String) playingStatsInfo.get("powerPlayTimeOnIcePerGame"))
                                        .shotPct((Double) playingStatsInfo.get("shotPct"))
                                        .shots((Long) playingStatsInfo.get("shots"))
                                        .timeOnIcePerGame((String) playingStatsInfo.get("timeOnIcePerGame"))
                                        .build();
    
        stats.setYearStatScore(calulateInternalPlayerScore(stats, player));

        return stats;
    }

    public GoalieStats setGoalieStats(JSONObject playingStatsInfo, Goalie goalie) {
        GoalieStats stats = GoalieStats.builder()
                                        .wins((Long) playingStatsInfo.get("wins"))
                                        .evenStrengthSavePercentage((Double) playingStatsInfo.get("evenStrengthSavePercentage"))
                                        .shortHandedShots((Long) playingStatsInfo.get("shortHandedShots"))
                                        .shortHandedSaves((Long) playingStatsInfo.get("shortHandedSaves"))
                                        .ot((Long) playingStatsInfo.get("ot"))
                                        .evenSaves((Long) playingStatsInfo.get("evenSaves"))
                                        .evenShots((Long) playingStatsInfo.get("evenShots"))
                                        .losses((Long) playingStatsInfo.get("losses"))
                                        .powerPlaySaves((Long) playingStatsInfo.get("powerPlaySaves"))
                                        .goalAgainstAverage((Double) playingStatsInfo.get("goalAgainstAverage"))
                                        .gamesStarted((Long) playingStatsInfo.get("gamesStarted"))
                                        .timeOnIcePerGame((String) playingStatsInfo.get("timeOnIcePerGame"))
                                        .shutouts((Long) playingStatsInfo.get("shutouts"))
                                        .saves((Long) playingStatsInfo.get("saves"))
                                        .savePercentage((Double) playingStatsInfo.get("savePercentage"))
                                        .timeOnIce((String) playingStatsInfo.get("timeOnIce"))
                                        .ties((Long) playingStatsInfo.get("ties"))
                                        .powerPlayShots((Long) playingStatsInfo.get("powerPlayShots"))
                                        .powerPlaySavePercentage((Double) playingStatsInfo.get("powerPlaySavePercentage"))
                                        .games((Long) playingStatsInfo.get("games"))
                                        .goalsAgainst((Long) playingStatsInfo.get("goalsAgainst"))
                                        .shortHandedSavePercentage((Double) playingStatsInfo.get("shortHandedSavePercentage"))
                                        .shotsAgainst((Long) playingStatsInfo.get("shotsAgainst"))
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