package ca.fantasyHockeyTeamSelector.scoreAI.repository;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStats implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long assists;
    private Long blocked;
    private Double faceOffPct;
    private Long games;
    private Long goals;
    private Long hits;
    private Long pim;
    private Long plusMinus;
    private Long points;
    private String powerPlayTimeOnIce;
    private String powerPlayTimeOnIcePerGame;
    private Double shotPct;
    private Long shots;
    private String timeOnIce;
    private String timeOnIcePerGame;

    @Builder.Default
    private Long yearStatScore = 0L;
}