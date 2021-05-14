package ca.FantasyHockeyTeamSelector.ScoreAI.Repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStats {
    public Long assists;
    public Long blocked;
    public Double faceOffPct;
    public Long games;
    public Long goals;
    public Long hits;
    public Long pim;
    public Long plusMinus;
    public Long points;
    public String powerPlayTimeOnIce;
    public String powerPlayTimeOnIcePerGame;
    public Double shotPct;
    public Long shots;
    public String timeOnIce;
    public String timeOnIcePerGame;

    @Builder.Default
    public Long yearStatScore = 0L;
}