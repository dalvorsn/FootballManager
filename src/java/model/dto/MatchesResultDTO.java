/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.io.Serializable;

/**
 *
 * @author Luciano
 */
public class MatchesResultDTO implements Serializable {
    private Integer round;
    
    private Long firstTeamId;
    private String firstTeamLogo;
    private String firstTeamName;
    private Integer firstTeamGoals;
    
    private Long secondTeamId;
    private String secondTeamName;
    private String secondTeamLogo;
    private Integer secondTeamGoals;
    
    private boolean finished;

    public MatchesResultDTO() {
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getFirstTeamLogo() {
        return firstTeamLogo;
    }

    public void setFirstTeamLogo(String firstTeamLogo) {
        this.firstTeamLogo = firstTeamLogo;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public void setFirstTeamName(String firstTeamName) {
        this.firstTeamName = firstTeamName;
    }

    public Integer getFirstTeamGoals() {
        return firstTeamGoals;
    }

    public void setFirstTeamGoals(Integer firstTeamGoals) {
        this.firstTeamGoals = firstTeamGoals;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public void setSecondTeamName(String secondTeamName) {
        this.secondTeamName = secondTeamName;
    }

    public String getSecondTeamLogo() {
        return secondTeamLogo;
    }

    public void setSecondTeamLogo(String secondTeamLogo) {
        this.secondTeamLogo = secondTeamLogo;
    }

    public Integer getSecondTeamGoals() {
        return secondTeamGoals;
    }

    public void setSecondTeamGoals(Integer secondTeamGoals) {
        this.secondTeamGoals = secondTeamGoals;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Long getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(Long firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public Long getSecondTeamId() {
        return secondTeamId;
    }

    public void setSecondTeamId(Long secondTeamId) {
        this.secondTeamId = secondTeamId;
    }
    
    
}
