package com.booking.hotel.backendspring.repository;

import com.booking.hotel.backendspring.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
