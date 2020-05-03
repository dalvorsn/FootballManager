/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @Column(name = "name", nullable = false, length = 60 )
    private String name;
    
    @Column(name = "max_subscribers", nullable = false)
    private Integer maxSubscribers;

    @Column(name = "start_date", nullable = true, columnDefinition = "DATETIME")
    private Timestamp startDate;

    @OneToMany(mappedBy = "championship")
    private List<ChampionshipSubscribe> subscribes;

    @OneToMany(mappedBy = "championship")
    private List<Match> matches;

    public Championship() {
    }

    public Championship(String name, Integer maxSubscribers) {
        this.name = name;
        this.maxSubscribers = maxSubscribers;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public List<ChampionshipSubscribe> getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(List<ChampionshipSubscribe> subscribes) {
        this.subscribes = subscribes;
    }

    public Integer getMaxSubscribers() {
        return maxSubscribers;
    }

    public void setMaxSubscribers(Integer maxSubscribers) {
        this.maxSubscribers = maxSubscribers;
    }
}
