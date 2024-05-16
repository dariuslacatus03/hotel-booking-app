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
            throw new InvalidParameterException("Start date cannot be after end date");
        }
        if (reservation.getStartDate().isEqual(reservation.getEndDate()))
        {
            throw new InvalidParameterException("Start date cannot be equal to end date");
        }
        LocalDate currentDate = LocalDate.now();
        if (reservation.getStartDate().isBefore(currentDate.atStartOfDay())) {
            throw new InvalidParameterException("Start date cannot be before current date");
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
                throw new InvalidParameterException("Your reservation intersects another one");
            }
        }
        return reservation;
    }
    private static boolean isValidReservation(LocalDateTime start, LocalDateTime end, Room roomEntity) {
        for (Reservation reservation : roomEntity.getRoomReservations())
        {
            if (reservation.getStartDate().isEqual(start) || reservation.getEndDate().isEqual(end))
                return false;
            if (reservation.getStartDate().isBefore(start) && reservation.getEndDate().isAfter(start))
                return false;
            if (reservation.getStartDate().isAfter(start) && reservation.getStartDate().isBefore(end))
                return false;
        }
        return true;
    }

    @Override
    public Boolean deleteReservation(Long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isEmpty())
            return Boolean.FALSE;
        Reservation reservationEntity = reservationOptional.get();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(reservationEntity.getStartDate(), currentDateTime);
        long hours = duration.toHours();
        if (hours < 12)
        {
            throw new RuntimeException("Reservations cannot be canceled 12 hours before start date");
        }
        reservationRepository.deleteById(reservationId);
        return Boolean.TRUE;
    }
}
