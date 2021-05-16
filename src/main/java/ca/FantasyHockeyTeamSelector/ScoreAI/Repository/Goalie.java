package ca.FantasyHockeyTeamSelector.ScoreAI.Repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goalie {
    @Id
    private String name;

    private Long id;
    private Long age;
    
    @Builder.Default
    private Long statScore = 0L;

    @Lob
    private ArrayList<GoalieStats> stats;
}

