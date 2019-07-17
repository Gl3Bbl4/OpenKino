package com.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HALL")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Width")
    private Integer width;

    @Column(name = "Height")
    private Integer heighgt;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "hall")
    private List<Place> places;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "hall")
    private List<Session> sessions;

    public Hall(){}


    public Long getId() {
        return id;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return heighgt;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer heighgt) {
        this.heighgt = heighgt;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
