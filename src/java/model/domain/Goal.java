/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.time.LocalTime;
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

    @Column(name = "goal_time", nullable = false, columnDefinition = "TIME")
    private LocalTime goalTime;

    public Goal() {
    }

}