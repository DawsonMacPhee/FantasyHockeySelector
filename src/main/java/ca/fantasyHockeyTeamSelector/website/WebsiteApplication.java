package ca.fantasyHockeyTeamSelector.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("ca.FantasyHockeyTeamSelector.ScoreAI.Repository")
@EntityScan("ca.FantasyHockeyTeamSelector.ScoreAI.Repository")
@ComponentScan("ca.FantasyHockeyTeamSelector")
public class WebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}

}
