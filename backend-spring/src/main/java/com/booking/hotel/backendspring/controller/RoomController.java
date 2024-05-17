package com.booking.hotel.backendspring.controller;

import com.booking.hotel.backendspring.dtos.ReservationDTO;
import com.booking.hotel.backendspring.dtos.RoomDTO;
import com.booking.hotel.backendspring.model.Reservation;
import com.booking.hotel.backendspring.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/rooms")
@CrossOrigin
public class RoomController {
    private final RoomService roomService;

    //testing purposes
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(RoomDTO.convertListToDTO(roomService.getAllRooms()));
    }

    @GetMapping("/{hotelId}/get")
    public ResponseEntity<List<RoomDTO>> getRoomsOfHotel(@PathVariable Long hotelId)
    {
        return ResponseEntity.ok(RoomDTO.convertListToDTO(roomService.getRoomsOfHotel(hotelId)));
    }

    @PostMapping(path = "/{roomId}/add-reservation")
    public ResponseEntity<?> addReservationToRoom(@RequestBody Reservation reservation, @PathVariable Long roomId)
    {
        try{
            return ResponseEntity.ok(ReservationDTO.convertToDTO(roomService.addReservationToRoom(reservation, roomId)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{roomId}/delete")
    public ResponseEntity<Boolean> deleteRoom(@PathVariable Long roomId)
    {
        return ResponseEntity.ok(roomService.deleteRoom(roomId));
    }
}
