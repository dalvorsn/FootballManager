/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 *
 * @author dalvo
 */
public class ChampionshipDTO implements Serializable {
    private Long id;
    private String name;
    private Long subscribedTeams;
    private Timestamp startDate;
    private Integer max;
    private Boolean canSubscribe;

    public ChampionshipDTO(Long id, String name, Long subscribedTeams, Timestamp startDate, Integer max) {
        this.id = id;
        this.name = name;
        this.subscribedTeams = subscribedTeams;
        this.startDate = startDate;
        this.max = max;
        this.canSubscribe = false;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubscribedTeams() {
        return subscribedTeams;
    }

    public void setSubscribedTeams(Long subscribedTeams) {
        this.subscribedTeams = subscribedTeams;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean getCanSubscribe() {
        return canSubscribe;
    }

    public void setCanSubscribe(Boolean canSubscribe) {
        this.canSubscribe = canSubscribe;
    }
    
    
}
