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

    public Match() {
    }

}
