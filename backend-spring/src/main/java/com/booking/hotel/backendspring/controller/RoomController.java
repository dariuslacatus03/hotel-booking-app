package com.booking.hotel.backendspring.controller;

import com.booking.hotel.backendspring.dtos.RoomDTO;
import com.booking.hotel.backendspring.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(RoomDTO.convertListToDTO(roomService.getAllRooms()));
    }
}
