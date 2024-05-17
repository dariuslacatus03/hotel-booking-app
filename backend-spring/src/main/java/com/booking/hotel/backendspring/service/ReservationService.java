package com.booking.hotel.backendspring.service;

import com.booking.hotel.backendspring.model.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsOfRoom(Long roomId);
    Boolean deleteReservation(Long reservationId);
}
