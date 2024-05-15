package com.booking.hotel.backendspring.service;

import com.booking.hotel.backendspring.model.Hotel;
import com.booking.hotel.backendspring.model.Location;
import com.booking.hotel.backendspring.model.Room;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();

    List<Room> getRoomsOfHotel(Long hotelId);

    //function made for testing purposes
    Hotel addHotel(Hotel hotelToAdd);

    Room addRoomToHotel(Long hotelId, Room roomToAdd);

    List<Hotel> getHotelsInRadius(double radius, Location currentLocation);
}
