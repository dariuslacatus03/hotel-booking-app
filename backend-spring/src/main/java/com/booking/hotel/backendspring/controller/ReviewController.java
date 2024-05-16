package com.booking.hotel.backendspring.controller;

import com.booking.hotel.backendspring.dtos.ReviewDTO;
import com.booking.hotel.backendspring.model.Review;
import com.booking.hotel.backendspring.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/reviews")
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(path = "/{hotelId}/add-review")
    public ResponseEntity<?> addReviewToHotel(@RequestBody Review reviewToAdd, @PathVariable Long hotelId)
    {
        try{
            return ResponseEntity.ok(ReviewDTO.convertToDTO(reviewService.addReviewToHotel(hotelId, reviewToAdd)));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = "/{hotelId}/get")
    public ResponseEntity<List<ReviewDTO>> getReviewsOfHotel(@PathVariable Long hotelId)
    {
        return ResponseEntity.ok(ReviewDTO.convertListToDTO(reviewService.getReviewsOfHotel(hotelId)));
    }
}
