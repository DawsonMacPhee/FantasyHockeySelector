package ca.FantasyHockeyTeamSelector.Website;

import ca.FantasyHockeyTeamSelector.ScoreAI.PlayerScores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Controller
public class MainController {

    @Autowired
    private PlayerScores playerScores;

    @EventListener(ContextRefreshedEvent.class)
    public void onInit() {
        playerScores.updateSavedPlayerInfo();
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/stats")
    public String stats() {
        return "stats";
    }

    @GetMapping(value = "/fantasy")
    public String fantasy() {
        return "fantasy";
    }

}