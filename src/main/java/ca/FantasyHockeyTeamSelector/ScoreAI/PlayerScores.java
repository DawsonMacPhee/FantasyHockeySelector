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

public class PlayerScores {
    private ArrayList<Player> c = new ArrayList<Player>();
    private ArrayList<Player> lw = new ArrayList<Player>();
    private ArrayList<Player> rw = new ArrayList<Player>();
    private ArrayList<Player> d = new ArrayList<Player>();
    private ArrayList<Player> g = new ArrayList<Player>();

    public PlayerScores() {
        updateSavedPlayerInfo();

        c.sort(new ScoreSorter());
        Collections.reverse(c);
        lw.sort(new ScoreSorter());
        Collections.reverse(lw);
        rw.sort(new ScoreSorter());
        Collections.reverse(rw);
        d.sort(new ScoreSorter());
        Collections.reverse(d);

        System.out.println();
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(c.get(i).name + ": " + c.get(i).statScore);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(lw.get(i).name + ": " + lw.get(i).statScore);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(rw.get(i).name + ": " + rw.get(i).statScore);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(d.get(i).name + ": " + d.get(i).statScore);
        }
    }

    public void updateSavedPlayerInfo() {
        JSONArray teamArr = getTeams();
        getPlayers(teamArr);
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

    public int timeToPoints(String pte) {
        String[] parts = pte.split(":");
        int output = Integer.parseInt(parts[0]);
        return output;
    }

    public void addPlayer(JSONArray rosterArr, JSONObject playerJSON, JSONObject personalStatsInfo, int ind) {
        Player player = new Player();
        player.name = (String) personalStatsInfo.get("fullName");
        player.id = (Long) personalStatsInfo.get("id");
        player.position = (String) ((JSONObject) ((JSONObject) rosterArr.get(ind)).get("position")).get("abbreviation");
        player.age = (Long) personalStatsInfo.get("currentAge");

        int highYear = 2021;
        int lowYear = 2020;

        for (int i = 0; i < 1; i++) {
            JSONObject playingStats = getObjectFromURL("https://statsapi.web.nhl.com/api/v1/people/" + playerJSON.get("id") + "/stats?stats=statsSingleSeason&season=" + lowYear + "" + highYear + "");
            JSONArray playingStatsInfoArr = (JSONArray) ((JSONObject) ((JSONArray) playingStats.get("stats")).get(0)).get("splits");
            if (playingStatsInfoArr.size() != 0) {
                JSONObject playingStatsInfo = (JSONObject) ((JSONObject) playingStatsInfoArr.get(0)).get("stat");

                PlayerStats stats = new PlayerStats();
                stats.assists = (Long) playingStatsInfo.get("assists");
                stats.blocked = (Long) playingStatsInfo.get("blocked");
                stats.faceOffPct = (Double) playingStatsInfo.get("faceOffPct");
                stats.games = (Long) playingStatsInfo.get("games");
                stats.goals = (Long) playingStatsInfo.get("goals");
                stats.hits = (Long) playingStatsInfo.get("hits");
                stats.pim = (Long) playingStatsInfo.get("pim");
                stats.plusMinus = (Long) playingStatsInfo.get("plusMinus");
                stats.points = (Long) playingStatsInfo.get("points");
                stats.powerPlayTimeOnIcePerGame = (String) playingStatsInfo.get("powerPlayTimeOnIcePerGame");
                stats.shotPct = (Double) playingStatsInfo.get("shotPct");
                stats.shots = (Long) playingStatsInfo.get("shots");
                stats.timeOnIcePerGame = (String) playingStatsInfo.get("timeOnIcePerGame");

                stats.yearStatScore = stats.points * 6 + stats.goals * 4 + stats.assists * 2 + (Long) ((stats.games / 82) * 200);
                stats.yearStatScore += (Long) Math.round(stats.shots * 0.5) + (Long) Math.round(stats.blocked * 0.5) + stats.hits - (Long) Math.round(stats.pim * 0.5) + stats.plusMinus + timeToPoints(stats.timeOnIcePerGame);

                if (player.position.equals("C")) {
                    stats.yearStatScore += stats.faceOffPct.longValue();
                }

                player.stats.add(stats);
            }

            highYear -= 1;
            lowYear -= 1;
        }

        // FOR TESTING
        if (!player.stats.isEmpty()) {
            player.statScore = player.stats.get(0).yearStatScore;
            System.out.println(player.name + ": " + player.statScore);
        }

        if (player.position.equals("C")) {
            c.add(player);
        } else if (player.position.equals("LW")) {
            lw.add(player);
        } else if (player.position.equals("RW")) {
            rw.add(player);
        } else if (player.position.equals("D")) {
            d.add(player);
        }
    }

    public void addGoalie() {

    }
}