/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author dalvo
 */
@Entity
@Table(name = "matchs")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_championship", nullable = false)
    private Championship championship;

    @ManyToOne
    @JoinColumn(name = "id_first_team", nullable = false)
    private Team firstTeam;

    @ManyToOne
    @JoinColumn(name = "id_second_team", nullable = false)
    private Team secondTeam;
    
    @Column(name = "finished", nullable = false)
    private Boolean finished;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "match")
    @OrderBy("match.goals.goalRound, goalMinute DESC")
    private List<Goal> goals;

    public Match() {
    }

    public Match(Championship championship, Team firstTeam, Team secondTeam) {
        this.championship = championship;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.finished = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    
}
