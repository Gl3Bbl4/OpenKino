package com.dao;

import com.models.Place;
import com.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceDao extends JpaRepository<Place,Long> {
    @Query(value = "select place from Place as place " +
            "join place.hall as hall where " +
            "hall.id = :id")
    List<Place> getFindbyIdHall(@Param("id") Long id);
    @Query(value = "select place from Place as place " +
            "join place.hall as hall where " +
            "hall.id = :id and place.reservation is null")
    List<Place> getFindbyIdHallPlacesnotReservation(@Param("id") Long id);
    @Query(value = "select place from Place as place " +
            "join place.hall as hall where " +
            "hall.id = :id and place.reservation is not null")
    List<Place> getFindbyIdHallPlacesisReservation(@Param("id") Long id);
    @Query(value = "select place from Place as place " +
            "join place.hall as hall where " +
            "hall.id = :idH and place.number = :idP")
    Place findPlaceByNumberFromHall(@Param("idH") Long idH, @Param("idP") Integer idP);

    @Query(value = "select place from Place as place " +
            "join place.hall as hall join place.reservation as reservation" +
            " where hall.id = :idH and reservation.id = :idR")
    List<Place> findPlaceByHallAndReservation(@Param("idH") Long idH, @Param("idR") Long idR);

}
