package ca.FantasyHockeyTeamSelector.WebController;

import ca.FantasyHockeyTeamSelector.ScoreAI.PlayerScores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MainController {

    @Autowired
    private PlayerScores playerScores;

    @EventListener(ContextRefreshedEvent.class)
    public void onInit() {
        System.out.println("HERE");
        playerScores.updateSavedPlayerInfo();
    }

}