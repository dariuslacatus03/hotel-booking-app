package com.booking.hotel.backendspring.service;

import com.booking.hotel.backendspring.model.Review;

import java.util.List;

public interface ReviewService {
    Review addReviewToHotel(Long hotelId, Review reviewToAdd);
    List<Review> getReviewsOfHotel(Long hotelId);
}
