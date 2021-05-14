package ca.FantasyHockeyTeamSelector.ScoreAI;

import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ca.FantasyHockeyTeamSelector.ScoreAI.Repository.PlayerStats;
import ca.FantasyHockeyTeamSelector.ScoreAI.Repository.Player;
import ca.FantasyHockeyTeamSelector.ScoreAI.Repository.PlayerRepository;

@Service
@Transactional
public class PlayerScores {

    private PlayerRepository playerRepo;

    @Autowired
    public PlayerScores(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    private JSONObject getObjectFromURL(String urlString) {
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

    private int timeToPoints(String pte) {
        String[] parts = pte.split(":");
        int output = Integer.parseInt(parts[0]);
        return output;
    }

    public void updateSavedPlayerInfo() {
        JSONArray teamArr = getTeams();
        getPlayers(teamArr);
    }

    public JSONArray getTeams() {
        JSONObject teamJSON = getObjectFromURL("https://statsapi.web.nhl.com/api/v1/teams");
        JSONArray teamArr = (JSONArray) teamJSON.get("teams");
        return teamArr;
    }

    public void getPlayers(JSONArray teamArr) {
        for (int i = 0; i < teamArr.size(); i++) {
            JSONObject team = (JSONObject) teamArr.get(i);
            JSONObject rosterJSON = getObjectFromURL("https://statsapi.web.nhl.com/api/v1/teams/" + team.get("id") + "/roster");

            JSONArray rosterArr = (JSONArray) rosterJSON.get("roster");
            if (rosterArr == null) {
                return;
            }

            for (int j = 0; j < rosterArr.size(); j++) {
                JSONObject playerJSON = (JSONObject) ((JSONObject) rosterArr.get(j)).get("person");
                
                JSONObject personalStats = getObjectFromURL("https://statsapi.web.nhl.com/api/v1/people/" + playerJSON.get("id"));
                JSONObject personalStatsInfo = (JSONObject) ((JSONArray) personalStats.get("people")).get(0);

                if (!(((String) ((JSONObject) ((JSONObject) rosterArr.get(j)).get("position")).get("abbreviation")).equals("G"))) {
                    addPlayer(rosterArr, playerJSON, personalStatsInfo, j);
                } else {
                    addGoalie();
                }
            }
        }
    }

    public void addPlayer(JSONArray rosterArr, 
                          JSONObject playerJSON, 
                          JSONObject personalStatsInfo, 
                          int ind) 
    {

        Player player = Player.builder()
                              .name((String) personalStatsInfo.get("fullName"))
                              .id((Long) personalStatsInfo.get("id"))
                              .position((String) ((JSONObject) ((JSONObject) rosterArr.get(ind)).get("position")).get("abbreviation"))
                              .age((Long) personalStatsInfo.get("currentAge"))
                              .build();

        int highYear = 2021;
        int lowYear = 2020;

        ArrayList<PlayerStats> statsList = new ArrayList<PlayerStats>();   
        for (int i = 0; i < 1; i++) {
            JSONObject playingStats = getObjectFromURL("https://statsapi.web.nhl.com/api/v1/people/" + playerJSON.get("id") + "/stats?stats=statsSingleSeason&season=" + lowYear + "" + highYear + "");
            JSONArray playingStatsInfoArr = (JSONArray) ((JSONObject) ((JSONArray) playingStats.get("stats")).get(0)).get("splits");
            if (playingStatsInfoArr.size() != 0) {
                JSONObject playingStatsInfo = (JSONObject) ((JSONObject) playingStatsInfoArr.get(0)).get("stat");

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

                Long yearScore = stats.getPoints() * 6 + stats.getGoals() * 4 + stats.getAssists() * 2 + (Long) ((stats.getGames() / 82) * 200) +
                                    (Long) Math.round(stats.getShots() * 0.5) + (Long) Math.round(stats.getBlocked() * 0.5) + stats.getHits() - 
                                    (Long) Math.round(stats.getPim() * 0.5) + stats.getPlusMinus() + timeToPoints(stats.getTimeOnIcePerGame());

                if (player.getPosition().equals("C")) {
                    yearScore += stats.getFaceOffPct().longValue();
                }

                stats.setYearStatScore(yearScore);

                statsList.add(stats);
            }

            highYear -= 1;
            lowYear -= 1;
        }

        player.setStats(statsList);

        // FOR TESTING
        if (!player.getStats().isEmpty()) {
            player.setStatScore(player.getStats().get(0).getYearStatScore());
            System.out.println(player.getName() + ": " + player.getStatScore());
        }
        //

        playerRepo.save(player);
    }

    public void addGoalie() {

    }
}