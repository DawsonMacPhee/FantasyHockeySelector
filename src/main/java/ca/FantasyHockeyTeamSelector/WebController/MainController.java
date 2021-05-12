package ca.FantasyHockeyTeamSelector.WebController;

import ca.FantasyHockeyTeamSelector.ScoreAI.PlayerScores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.ContextRefreshedEvent;

@RestController
public class MainController {

    @EventListener(ContextRefreshedEvent.class)
    public void onInit() {
        PlayerScores score = new PlayerScores();
    }

}