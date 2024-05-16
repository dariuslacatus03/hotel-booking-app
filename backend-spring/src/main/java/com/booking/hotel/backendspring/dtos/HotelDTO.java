package com.booking.hotel.backendspring.dtos;

import com.booking.hotel.backendspring.model.Hotel;
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
public class HotelDTO {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer numberOfRooms;

    public static HotelDTO convertToDTO(Hotel hotelToConvert)
    {
        return new HotelDTO(hotelToConvert.getId(), hotelToConvert.getName(), hotelToConvert.getLatitude(),
                hotelToConvert.getLongitude(), hotelToConvert.getRooms().size());
    }

    public static List<HotelDTO> convertListToDTO(List<Hotel> hotelsToConvert)
    {
        List<HotelDTO> allHotelsDTO = new ArrayList<>();
        for (Hotel hotel : hotelsToConvert)
        {
            allHotelsDTO.add(HotelDTO.convertToDTO(hotel));
        }
        return allHotelsDTO;
    }
}
