package com.booking.hotel.backendspring.service.implementation;

import com.booking.hotel.backendspring.model.Review;
import com.booking.hotel.backendspring.repository.ReviewRepository;
import com.booking.hotel.backendspring.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviewsOfHotel(Long hotelId) {
        List<Review> reviewsOfHotel = new ArrayList<>();
        for(Review review : reviewRepository.findAll())
        {
            if (hotelId.equals(review.getHotel().getId()))
                reviewsOfHotel.add(review);
        }
        return reviewsOfHotel;
    }

}
