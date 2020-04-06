/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dalvo
 */
@Entity
@Table(name = "teams")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "logo_url", length = 255, nullable = true)
    private String logoUrl;

    @ManyToOne
    @JoinColumn(name = "id_owner", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    public Team() {
    }

}
