package com.booking.hotel.backendspring.repository;

import com.booking.hotel.backendspring.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
