package com.models;




import com.configuration.SecurityConfig;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;

import java.awt.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
@Table(name = "Session")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Session implements Serializable {
    public static class View{
        public static class Public{}
        public static class Internal extends Public {}

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Public.class)
    private Long id;

    @JsonSerialize(using = SecurityConfig.LocalDateTimeSerializer.class)
    @JsonDeserialize(using = SecurityConfig.LocalDateTimeDeserializer.class)
    @Column(name = "START")
    @JsonView(View.Public.class)
    private LocalDateTime start;

    @JsonSerialize(using = SecurityConfig.LocalDateTimeSerializer.class)
    @JsonDeserialize(using = SecurityConfig.LocalDateTimeDeserializer.class)
    @Column(name = "END")
    @JsonView(View.Public.class)
    private LocalDateTime end;

    @Column(name = "PRICE")
    @JsonView(View.Public.class)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "ID_FILM")
    @JsonView(View.Internal.class)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "ID_HALL")
    @JsonView(View.Internal.class)
    private Hall hall;

    @OneToMany
    @JoinColumn(name = "ID_PLACE")
   @JsonView(View.Public.class)
    private List<Place> places;

    @Transient
    @JsonView(View.Internal.class)
    private List<Point> placesIsBroken;



    public Session() {
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDateTime getStart() {
        return start;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

}
