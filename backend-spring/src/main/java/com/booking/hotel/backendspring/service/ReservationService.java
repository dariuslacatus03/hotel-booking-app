package com.booking.hotel.backendspring.service;

import com.booking.hotel.backendspring.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();
    Reservation addReservation(Reservation reservation, Long roomId);
    Boolean deleteReservation(Long reservationId);
}
