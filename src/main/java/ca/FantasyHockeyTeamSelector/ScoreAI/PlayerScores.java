package ca.fantasyHockeyTeamSelector.scoreAI;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ca.fantasyHockeyTeamSelector.scoreAI.repository.PlayerStats;
import ca.fantasyHockeyTeamSelector.scoreAI.repository.GoalieStats;
import ca.fantasyHockeyTeamSelector.scoreAI.repository.Player;
import ca.fantasyHockeyTeamSelector.scoreAI.repository.PlayerRepository;
import ca.fantasyHockeyTeamSelector.scoreAI.repository.Goalie;
import ca.fantasyHockeyTeamSelector.scoreAI.repository.GoalieRepository;
import ca.fantasyHockeyTeamSelector.scoreAI.utils.PlayerScoresUtils;

@Service
@Transactional
public class PlayerScores {

    private PlayerRepository playerRepo;
    private GoalieRepository goalieRepo;
    private PlayerScoresUtils utils;

    @Autowired
    public PlayerScores(PlayerRepository playerRepo, GoalieRepository goalieRepo, PlayerScoresUtils utils) {
        this.playerRepo = playerRepo;
        this.goalieRepo = goalieRepo;
        this.utils = utils;
    }

    public void updateSavedPlayerInfo() {
        JSONArray teamArr = getTeams();
        getPlayersByTeam(teamArr);
    }

    public JSONArray getTeams() {
        JSONObject teamJSON = utils.getObjectFromURL("https://statsapi.web.nhl.com/api/v1/teams");
        JSONArray teamArr = (JSONArray) teamJSON.get("teams");
        return teamArr;
    }

    public void getPlayersByTeam(JSONArray teamArr) {
        for (int i = 0; i < teamArr.size(); i++) {
            JSONObject team = (JSONObject) teamArr.get(i);
            JSONObject rosterJSON = utils.getObjectFromURL("https://statsapi.web.nhl.com/api/v1/teams/" + team.get("id") + "/roster");

            JSONArray rosterArr = (JSONArray) rosterJSON.get("roster");
            if (rosterArr != null) {
                addRosterToDB(rosterArr);
            }
        }
    }

    public void addRosterToDB(JSONArray rosterArr) {
        for (int j = 0; j < rosterArr.size(); j++) {
            JSONObject playerJSON = (JSONObject) ((JSONObject) rosterArr.get(j)).get("person");
            
            JSONObject personalStats = utils.getObjectFromURL("https://statsapi.web.nhl.com/api/v1/people/" + playerJSON.get("id"));
            JSONObject personalStatsInfo = (JSONObject) ((JSONArray) personalStats.get("people")).get(0);

            if (!(((String) ((JSONObject) ((JSONObject) rosterArr.get(j)).get("position")).get("abbreviation")).equals("G"))) {
                addPlayerToDB(rosterArr, playerJSON, personalStatsInfo, j);
            } else {
                addGoalieToDB(rosterArr, playerJSON, personalStatsInfo, j);
            }
        }
    }

    public void addPlayerToDB(JSONArray rosterArr, 
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
            JSONObject playingStats = utils.getObjectFromURL("https://statsapi.web.nhl.com/api/v1/people/" + playerJSON.get("id") + "/stats?stats=statsSingleSeason&season=" + lowYear + "" + highYear + "");
            JSONArray playingStatsInfoArr = (JSONArray) ((JSONObject) ((JSONArray) playingStats.get("stats")).get(0)).get("splits");
            if (playingStatsInfoArr.size() != 0) {
                JSONObject playingStatsInfo = (JSONObject) ((JSONObject) playingStatsInfoArr.get(0)).get("stat");
                statsList.add(utils.setPlayerStats(playingStatsInfo, player));
            }

            highYear -= 1;
            lowYear -= 1;
        }

        player.setStats(statsList);

        // FOR TESTING
        if (!player.getStats().isEmpty()) {
            player.setStatScore(player.getStats().get(0).getYearStatScore());
            //System.out.println(player.getName() + ": " + player.getStatScore());
        }
        //

        playerRepo.save(player);
    }

    public void addGoalieToDB(JSONArray rosterArr, 
                              JSONObject playerJSON, 
                              JSONObject personalStatsInfo, 
                              int ind)
    {
        Goalie goalie = Goalie.builder()
                              .name((String) personalStatsInfo.get("fullName"))
                              .id((Long) personalStatsInfo.get("id"))
                              .age((Long) personalStatsInfo.get("currentAge"))
                              .build();

        int highYear = 2021;
        int lowYear = 2020;

        ArrayList<GoalieStats> statsList = new ArrayList<GoalieStats>();   
        for (int i = 0; i < 1; i++) {
            JSONObject playingStats = utils.getObjectFromURL("https://statsapi.web.nhl.com/api/v1/people/" + playerJSON.get("id") + "/stats?stats=statsSingleSeason&season=" + lowYear + "" + highYear + "");
            JSONArray playingStatsInfoArr = (JSONArray) ((JSONObject) ((JSONArray) playingStats.get("stats")).get(0)).get("splits");
            if (playingStatsInfoArr.size() != 0) {
                JSONObject playingStatsInfo = (JSONObject) ((JSONObject) playingStatsInfoArr.get(0)).get("stat");
                statsList.add(utils.setGoalieStats(playingStatsInfo, goalie));
            }

            highYear -= 1;
            lowYear -= 1;
        }

        goalie.setStats(statsList);

        // FOR TESTING
        if (!goalie.getStats().isEmpty()) {
            goalie.setStatScore(goalie.getStats().get(0).getYearStatScore());
            //System.out.println(goalie.getName() + ": " + goalie.getStatScore());
        }
        //

        goalieRepo.save(goalie);
    }
}