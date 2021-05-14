package ca.FantasyHockeyTeamSelector.ScoreAI.Repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    private String name;
    private Long id;
    private String position;
    private Long age;
    
    @Builder.Default
    private Long statScore = 0L;

    private ArrayList<PlayerStats> stats;
}