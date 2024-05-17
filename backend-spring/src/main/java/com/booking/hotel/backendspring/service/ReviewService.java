package com.booking.hotel.backendspring.service;

import com.booking.hotel.backendspring.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsOfHotel(Long hotelId);

}
