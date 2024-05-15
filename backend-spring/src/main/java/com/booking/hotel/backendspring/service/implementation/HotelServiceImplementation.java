package com.booking.hotel.backendspring.service.implementation;

import com.booking.hotel.backendspring.model.Hotel;
import com.booking.hotel.backendspring.model.Room;
import com.booking.hotel.backendspring.repository.HotelRepository;
import com.booking.hotel.backendspring.repository.RoomRepository;
import com.booking.hotel.backendspring.service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HotelServiceImplementation implements HotelService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    @Override
    public Hotel addHotel(Hotel hotelToAdd) {
        return hotelRepository.save(hotelToAdd);
    }

    @Override
    public Room addRoomToHotel(Long hotelId, Room roomToAdd) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        hotel.ifPresentOrElse(
                hotelEntity -> {
                    roomToAdd.setHotel(hotelEntity);
                    hotelEntity.getRooms().add(roomToAdd);
                    hotelRepository.save(hotelEntity);
                    roomRepository.save(roomToAdd);
                },
                () -> {
                    throw new EntityNotFoundException("Hotel not found with ID: " + hotelId);
                });
        return roomToAdd;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> getHotelsInRadius(double radius) {
        //TO DO
        return null;
    }
}
