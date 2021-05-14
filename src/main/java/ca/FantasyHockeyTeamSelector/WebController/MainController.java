package ca.FantasyHockeyTeamSelector.WebController;

import ca.FantasyHockeyTeamSelector.ScoreAI.PlayerScores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Component;

@RestController
public class MainController {

    @Autowired
    private PlayerScores playerScores;

    @EventListener(ContextRefreshedEvent.class)
    public void onInit() {
        playerScores.updateSavedPlayerInfo();
    }

}