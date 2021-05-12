package ca.FantasyHockeyTeamSelector.ScoreAI.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String name;
    private Long id;
    private String position;
    private Long age;
    
    @Builder.Default
    private Long statScore = 0L;

    private ArrayList<PlayerStats> stats;
}