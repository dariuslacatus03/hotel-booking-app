package com.booking.hotel.backendspring.controller;

import com.booking.hotel.backendspring.dtos.HotelDTO;
import com.booking.hotel.backendspring.dtos.ReviewDTO;
import com.booking.hotel.backendspring.dtos.RoomDTO;
import com.booking.hotel.backendspring.model.*;
import com.booking.hotel.backendspring.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/hotels")
@CrossOrigin
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/all")
    public ResponseEntity<List<HotelDTO>> getAllHotels()
    {
        return ResponseEntity.ok(HotelDTO.convertListToDTO(hotelService.getAllHotels()));
    }

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

    @PostMapping(path = "/{hotelId}/add-review")
    public ResponseEntity<?> addReviewToHotel(@RequestBody Review reviewToAdd, @PathVariable Long hotelId)
    {
        try{
            return ResponseEntity.ok(ReviewDTO.convertToDTO(hotelService.addReviewToHotel(hotelId, reviewToAdd)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/radius")
    public ResponseEntity<List<HotelDTO>> getHotelsInRadius(@RequestParam("radius") double radius,
                                                            @RequestParam("latitude") double latitude,
                                                            @RequestParam("longitude") double longitude) {
        Location currentLocation = new Location(latitude, longitude);
        return ResponseEntity.ok(HotelDTO.convertListToDTO(hotelService.getHotelsInRadius(radius, currentLocation)));
    }

    @DeleteMapping("/{hotelId}/delete")
    public ResponseEntity<?> deleteHotel(@PathVariable Long hotelId)
    {
        return ResponseEntity.ok(hotelService.deleteHotel(hotelId));
    }
}
