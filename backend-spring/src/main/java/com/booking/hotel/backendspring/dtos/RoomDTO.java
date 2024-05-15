package com.booking.hotel.backendspring.dtos;

import com.booking.hotel.backendspring.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private int roomNumber;
    private int type;
    private double price;
    private boolean available;

    public static RoomDTO convertToDTO(Room roomToConvert)
    {
        return new RoomDTO(roomToConvert.getId(), roomToConvert.getRoom_number(), roomToConvert.getType(),
                roomToConvert.getPrice(), roomToConvert.is_available());
    }

    public static List<RoomDTO> convertListToDTO(List<Room> roomsToConvert)
    {
        List<RoomDTO> allRoomsDTO = new ArrayList<>();
        for (Room room : roomsToConvert)
        {
            allRoomsDTO.add(RoomDTO.convertToDTO(room));
        }
        return allRoomsDTO;
    }
}
