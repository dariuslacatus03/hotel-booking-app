package com.booking.hotel.backendspring.service.implementation;

import com.booking.hotel.backendspring.model.Hotel;
import com.booking.hotel.backendspring.model.Review;
import com.booking.hotel.backendspring.repository.HotelRepository;
import com.booking.hotel.backendspring.repository.ReviewRepository;
import com.booking.hotel.backendspring.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final HotelRepository hotelRepository;
    @Override
    public Review addReviewToHotel(Long hotelId, Review reviewToAdd) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isEmpty())
        {
            throw new RuntimeException("Hotel does not exist");
        }
        Hotel hotelEntity = hotelOptional.get();
        reviewToAdd.setHotel(hotelEntity);
        hotelEntity.getReviews().add(reviewToAdd);
        hotelRepository.save(hotelEntity);
        return reviewRepository.save(reviewToAdd);
    }

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
