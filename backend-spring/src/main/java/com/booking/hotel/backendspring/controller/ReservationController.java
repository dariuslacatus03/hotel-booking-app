package com.booking.hotel.backendspring.controller;

import com.booking.hotel.backendspring.dtos.ReservationDTO;
import com.booking.hotel.backendspring.model.Reservation;
import com.booking.hotel.backendspring.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDTO>> getAllReservations()
    {
        return ResponseEntity.ok(ReservationDTO.convertListToDTO(reservationService.getAllReservations()));
    }

    @PostMapping(path = "/{roomId}/add-reservation")
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody Reservation reservation, @PathVariable Long roomId)
    {
        return ResponseEntity.ok(ReservationDTO.convertToDTO(reservationService.addReservation(reservation, roomId)));
    }

    @DeleteMapping(path = "/delete/{reservationId}")
    public ResponseEntity<Boolean> deleteReservation(@PathVariable Long reservationId)
    {
        return ResponseEntity.ok(reservationService.deleteReservation(reservationId));
    }
}