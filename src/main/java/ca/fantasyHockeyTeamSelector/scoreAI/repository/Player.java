package ca.fantasyHockeyTeamSelector.scoreAI.repository;

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
public class Player {
    @Id
    private Long playerId;

    private String name;
    private String position;
    private Long age;
    
    @Builder.Default
    private Long statScore = 0L;

    @Lob
    private ArrayList<PlayerStats> stats;
}