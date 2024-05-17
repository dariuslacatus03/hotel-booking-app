package com.booking.hotel.backendspring.service.implementation;

import com.booking.hotel.backendspring.model.Reservation;
import com.booking.hotel.backendspring.repository.ReservationRepository;
import com.booking.hotel.backendspring.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationServiceImplementation implements ReservationService {
    private final ReservationRepository reservationRepository;
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsOfRoom(Long roomId){
        List<Reservation> reservationsOfRoom = new ArrayList<>();
        for (Reservation reservation : reservationRepository.findAll())
        {
            if (reservation.getEndDate().isBefore(LocalDateTime.now()))
            {
                reservationRepository.deleteById(reservation.getId());
            }
            else if (roomId.equals(reservation.getRoom().getId()))
                reservationsOfRoom.add(reservation);
        }
        return reservationsOfRoom;
    }

    @Override
    public Boolean deleteReservation(Long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isEmpty())
            return Boolean.FALSE;
        Reservation reservationEntity = reservationOptional.get();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(currentDateTime, reservationEntity.getStartDate());
        long hours = duration.toHours();
        if (hours > 0 && hours < 24)
        {
            throw new RuntimeException("Reservations cannot be canceled 24 hours before start date");
        }
        reservationRepository.deleteById(reservationId);
        return Boolean.TRUE;
    }
}
