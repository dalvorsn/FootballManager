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
public class TopScorerDTO implements Serializable {
    private Long id;
    private String name;
    private Long teamId;
    private String teamName;
    private String teamLogo;
    private Long goals;

    public TopScorerDTO(Long id, String name, Long teamId, String teamName, String teamLogo, Long goals) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.goals = goals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public Long getGoals() {
        return goals;
    }

    public void setGoals(Long goals) {
        this.goals = goals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
    
}
