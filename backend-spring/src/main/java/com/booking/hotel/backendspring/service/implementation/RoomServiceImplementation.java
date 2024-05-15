package com.booking.hotel.backendspring.service.implementation;

import com.booking.hotel.backendspring.model.Room;
import com.booking.hotel.backendspring.repository.RoomRepository;
import com.booking.hotel.backendspring.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomServiceImplementation implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
