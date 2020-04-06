/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import model.domain.e.ESoccerPosition;

/**
 *
 * @author dalvo
 */
@Entity
@Table(name = "players")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "position", nullable = false)
    @Enumerated(EnumType.STRING)
    private ESoccerPosition position;

    @Column(name = "age", nullable = false)
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "id_team", nullable = true)
    private Team team;

    public Player() {
    }

}
