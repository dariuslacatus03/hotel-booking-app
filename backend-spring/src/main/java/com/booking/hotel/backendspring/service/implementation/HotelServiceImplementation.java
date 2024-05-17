package com.booking.hotel.backendspring.service.implementation;

import com.booking.hotel.backendspring.model.*;
import com.booking.hotel.backendspring.repository.HotelRepository;
import com.booking.hotel.backendspring.repository.ReviewRepository;
import com.booking.hotel.backendspring.repository.RoomRepository;
import com.booking.hotel.backendspring.service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HotelServiceImplementation implements HotelService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel addHotel(Hotel hotelToAdd) {
        return hotelRepository.save(hotelToAdd);
    }

    @Override
    public Room addRoomToHotel(Long hotelId, Room roomToAdd) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isEmpty())
        {
            throw new EntityNotFoundException("Hotel not found");
        }
        else
        {
            Hotel hotelEntity = hotelOptional.get();
            roomToAdd.setHotel(hotelEntity);
            hotelEntity.getRooms().add(roomToAdd);
            hotelRepository.save(hotelEntity);
            roomRepository.save(roomToAdd);
        }
        return roomToAdd;
    }

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
    public List<Hotel> getHotelsInRadius(double radius, Location currentLocation) {
        List<Hotel> hotelsInRadius = new ArrayList<>();

        double[] currentLocationMeters = Location.toMeters(currentLocation.getLatitude(), currentLocation.getLongitude());
        double currentLatitudeMeters = currentLocationMeters[0];
        double currentLongitudeMeters = currentLocationMeters[1];

        for (Hotel hotel : hotelRepository.findAll()) {
            double[] hotelLocationMeters = Location.toMeters(hotel.getLatitude(), hotel.getLongitude());
            double hotelLatitudeMeters = hotelLocationMeters[0];
            double hotelLongitudeMeters = hotelLocationMeters[1];

            double distance = Math.sqrt(
                            Math.pow(currentLatitudeMeters - hotelLatitudeMeters, 2) +
                            Math.pow(currentLongitudeMeters - hotelLongitudeMeters, 2)
            );

            if (distance <= radius * 1000) {
                hotelsInRadius.add(hotel);
            }
        }
        return hotelsInRadius;
    }

    @Override
    public Boolean deleteHotel(Long hotelId) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isEmpty())
            return Boolean.FALSE;
        hotelRepository.deleteById(hotelId);
        return Boolean.TRUE;
    }
}
