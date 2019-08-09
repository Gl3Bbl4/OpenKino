package com.service;

import com.models.Hall;
import com.models.Place;

import java.util.List;

public interface HallService {
    Long saveHall(Hall hall);
     Hall findHallbyId(Long id);
     Long updateHall(Hall hall);
     void deleteHall(Long id);
    Integer getSize(Long id);
    Long savePlace(Place place, Long id, Long idR, Long idB);
    Long updatePlace(Place place);
    void deletePlace(Long id);
    Place  findPlaceByNumberFromHall(Long idHall, Integer number);
    List<Place> getPlaces(Long idHall);
    Place getPlace(Long idHall, Long idPlaces);
    List<Place> getPlacesNotReservation(Long id);
     List<Place> getPlacesReservation(Long id);

}
