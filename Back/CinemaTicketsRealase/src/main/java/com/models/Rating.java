package com.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RATING")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @Column(name = "Name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "ID_FILM", unique = true, nullable = false, updatable = false)
    private Film film;
    public Rating(){}
}
