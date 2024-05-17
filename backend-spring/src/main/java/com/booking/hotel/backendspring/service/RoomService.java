package com.booking.hotel.backendspring.service;

import com.booking.hotel.backendspring.model.Reservation;
import com.booking.hotel.backendspring.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    List<Room> getRoomsOfHotel(Long hotelId);
    Reservation addReservationToRoom(Reservation reservation, Long roomId);
    Boolean deleteRoom(Long roomId);
}
