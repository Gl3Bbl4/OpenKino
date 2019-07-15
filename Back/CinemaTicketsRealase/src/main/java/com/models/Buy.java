package com.models;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BUY")
public class Buy {
    @Id
    @GeneratedValue
    private Long id;

    @Column( name = "DATE")
    private Date date;

    @Column(name = "PRICE")
    private Double price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "buy")
    private List<Reservation> reservations;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_CARD")
    private Card card;

    public Buy(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}