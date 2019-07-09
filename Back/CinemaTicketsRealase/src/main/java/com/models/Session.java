package com.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="ID")
    private Long id;
    @Column(name = "Name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "ID_FILM", unique = true, nullable = false, updatable = false)
    private Film film;
    public Session(){}
}