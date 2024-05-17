package com.booking.hotel.backendspring.service;

import com.booking.hotel.backendspring.model.Hotel;
import com.booking.hotel.backendspring.model.Location;
import com.booking.hotel.backendspring.model.Review;
import com.booking.hotel.backendspring.model.Room;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();

    Hotel addHotel(Hotel hotelToAdd);

    Room addRoomToHotel(Long hotelId, Room roomToAdd);

    Review addReviewToHotel(Long hotelId, Review reviewToAdd);

    List<Hotel> getHotelsInRadius(double radius, Location currentLocation);

    Boolean deleteHotel(Long hotelId);
}
