package com.booking.hotel.backendspring.dtos;

import com.booking.hotel.backendspring.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String reviewText;
    private int rating;

    public static ReviewDTO convertToDTO(Review reviewToConvert)
    {
        return new ReviewDTO(reviewToConvert.getId(), reviewToConvert.getReviewText(), reviewToConvert.getRating());
    }

    public static List<ReviewDTO> convertListToDTO(List<Review> listToConvert)
    {
        List<ReviewDTO> allReviewsDTO = new ArrayList<>();
        for (Review review : listToConvert)
        {
            allReviewsDTO.add(ReviewDTO.convertToDTO(review));
        }
        return allReviewsDTO;
    }
}
