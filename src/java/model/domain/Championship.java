/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dalvo
 */
@Entity
@Table(name = "championships")
public class Championship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime startDate;

    @ManyToMany
    @JoinTable(
            name = "championship_team",
            joinColumns = @JoinColumn(name = "id_championship"),
            inverseJoinColumns = @JoinColumn(name = "id_team")
    )
    private List<Team> teams;

    @OneToMany(mappedBy = "championship")
    private List<Match> matches;

    public Championship() {
    }

}
