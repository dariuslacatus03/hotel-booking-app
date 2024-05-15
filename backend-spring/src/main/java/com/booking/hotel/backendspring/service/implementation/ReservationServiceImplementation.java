package com.booking.hotel.backendspring.service.implementation;

import com.booking.hotel.backendspring.model.Reservation;
import com.booking.hotel.backendspring.model.Room;
import com.booking.hotel.backendspring.repository.ReservationRepository;
import com.booking.hotel.backendspring.repository.RoomRepository;
import com.booking.hotel.backendspring.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationServiceImplementation implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsOfRoom(Long roomId){
        List<Reservation> reservationsOfRoom = new ArrayList<>();
        for (Reservation reservation : reservationRepository.findAll())
        {
            if (roomId.equals(reservation.getRoom().getId()))
                reservationsOfRoom.add(reservation);
        }
        return reservationsOfRoom;
    }

    @Override
    public Reservation addReservation(Reservation reservation, Long roomId) {
        if (reservation.getStartDate().isAfter(reservation.getEndDate()))
        {
            throw new InvalidParameterException("Invalid date");
        }
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isEmpty())
        {
            throw new EntityNotFoundException("Room not found");
        }
        else
        {
            Room roomEntity = roomOptional.get();
            boolean validReservation = isValidReservation(reservation.getStartDate(), reservation.getEndDate(), roomEntity);
            if (validReservation)
            {
                reservation.setRoom(roomEntity);
                roomEntity.getRoomReservations().add(reservation);
                roomRepository.save(roomEntity);
                reservationRepository.save(reservation);
            }
            else
            {
                throw new InvalidParameterException("Invalid date");
            }
        }
        return reservation;
    }
    private static boolean isValidReservation(LocalDateTime start, LocalDateTime end, Room roomEntity) {
        boolean validReservation = true;
        for (Reservation reservation : roomEntity.getRoomReservations())
        {
            if (reservation.getStartDate().isBefore(start) && reservation.getEndDate().isAfter(start))
                validReservation = false;
            if (reservation.getStartDate().isAfter(start) && reservation.getStartDate().isBefore(end))
                validReservation = false;
        }
        return validReservation;
    }

    @Override
    public Boolean deleteReservation(Long reservationId) {
        if (reservationRepository.findById(reservationId).isEmpty())
            return Boolean.FALSE;
        reservationRepository.deleteById(reservationId);
        return Boolean.TRUE;
    }
}
