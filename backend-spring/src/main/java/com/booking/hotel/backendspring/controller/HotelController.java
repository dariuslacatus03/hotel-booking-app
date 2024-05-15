package com.booking.hotel.backendspring.controller;

import com.booking.hotel.backendspring.dtos.HotelDTO;
import com.booking.hotel.backendspring.dtos.RoomDTO;
import com.booking.hotel.backendspring.model.Hotel;
import com.booking.hotel.backendspring.model.Room;
import com.booking.hotel.backendspring.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/hotels")
public class HotelController {
    private final HotelService hotelService;

    @PostMapping(path = "/add-hotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotelToAdd)
    {
        return ResponseEntity.ok(hotelService.addHotel(hotelToAdd));
    }

    @PostMapping(path = "/{hotelId}/add-room")
    public ResponseEntity<RoomDTO> addRoomToHotel(@PathVariable Long hotelId, @RequestBody Room roomToAdd)
    {
        return ResponseEntity.ok(RoomDTO.convertToDTO(hotelService.addRoomToHotel(hotelId, roomToAdd)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HotelDTO>> getAllHotels()
    {
        return ResponseEntity.ok(HotelDTO.convertListToDTO(hotelService.getAllHotels()));
    }

    @GetMapping("/radius")
    public ResponseEntity<List<Hotel>> getHotelsInRadius(@RequestParam("radius") double radius) {
        //TO DO
        return null;
    }
}
