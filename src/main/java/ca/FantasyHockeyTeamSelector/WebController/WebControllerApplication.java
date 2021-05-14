package ca.FantasyHockeyTeamSelector.WebController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("ca.FantasyHockeyTeamSelector.ScoreAI.Repository")
@EntityScan("ca.FantasyHockeyTeamSelector.ScoreAI.Repository")
@ComponentScan("ca.FantasyHockeyTeamSelector.ScoreAI.PlayerStats")
public class WebControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebControllerApplication.class, args);
	}

}
