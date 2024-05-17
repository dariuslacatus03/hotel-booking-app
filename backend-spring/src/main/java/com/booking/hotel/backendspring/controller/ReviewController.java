package com.booking.hotel.backendspring.controller;

import com.booking.hotel.backendspring.dtos.ReviewDTO;
import com.booking.hotel.backendspring.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/reviews")
@CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping(path = "/{hotelId}/get")
    public ResponseEntity<List<ReviewDTO>> getReviewsOfHotel(@PathVariable Long hotelId)
    {
        return ResponseEntity.ok(ReviewDTO.convertListToDTO(reviewService.getReviewsOfHotel(hotelId)));
    }
}
