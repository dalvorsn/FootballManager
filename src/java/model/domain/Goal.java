/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dalvo
 */
@Entity
@Table(name = "goals")
public class Goal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_match", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "id_team", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "id_player", nullable = false)
    private Player player;

    @Column(name = "goal_round", nullable = false)
    private Integer goalRound;
    
    @Column(name = "goal_minute", nullable = false)
    private Integer goalMinute;

    public Goal() {
    }

    public Goal(Match match, Team team, Player player, Integer goalRound, Integer goalMinute) {
        this.match = match;
        this.team = team;
        this.player = player;
        this.goalRound = goalRound;
        this.goalMinute = goalMinute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getGoalRound() {
        return goalRound;
    }

    public void setGoalRound(Integer goalRound) {
        this.goalRound = goalRound;
    }

    public Integer getGoalMinute() {
        return goalMinute;
    }

    public void setGoalMinute(Integer goalMinute) {
        this.goalMinute = goalMinute;
    }
    
}
