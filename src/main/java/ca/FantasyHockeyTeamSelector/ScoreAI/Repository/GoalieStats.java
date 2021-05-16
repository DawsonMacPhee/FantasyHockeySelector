package ca.FantasyHockeyTeamSelector.ScoreAI.Repository;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoalieStats implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long wins;
    private Double evenStrengthSavePercentage;
    private Long shortHandedShots;
    private Long shortHandedSaves;
    private Long ot;
    private Long evenSaves;
    private Long evenShots;
    private Long losses;
    private Long powerPlaySaves;
    private Double goalAgainstAverage;
    private Long gamesStarted;
    private String timeOnIcePerGame;
    private Long shutouts;
    private Long saves;
    private Double savePercentage;
    private String timeOnIce;
    private Long ties;
    private Long powerPlayShots;
    private Double powerPlaySavePercentage;
    private Long games;
    private Long goalsAgainst;
    private Double shortHandedSavePercentage;
    private Long shotsAgainst;

    @Builder.Default
    public Long yearStatScore = 0L;
}