package ca.FantasyHockeyTeamSelector.ScoreAI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalieRepository extends JpaRepository<Goalie, Long> {

}