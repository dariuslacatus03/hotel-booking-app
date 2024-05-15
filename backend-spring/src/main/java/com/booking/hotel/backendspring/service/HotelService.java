package com.booking.hotel.backendspring.service;

import com.booking.hotel.backendspring.model.Hotel;
import com.booking.hotel.backendspring.model.Room;

import java.util.List;

public interface HotelService {
    //function made for testing purposes
    Hotel addHotel(Hotel hotelToAdd);

    Room addRoomToHotel(Long hotelId, Room roomToAdd);

    List<Hotel> getAllHotels();

    List<Hotel> getHotelsInRadius(double radius);
}
