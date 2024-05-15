package com.booking.hotel.backendspring.dtos;

import com.booking.hotel.backendspring.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long room_id;

    public static ReservationDTO convertToDTO(Reservation reservationToConvert)
    {
        return new ReservationDTO(reservationToConvert.getId(), reservationToConvert.getStartDate(),
                reservationToConvert.getEndDate(), reservationToConvert.getRoom().getId());
    }

    public static List<ReservationDTO> convertListToDTO(List<Reservation> reservationsToConvert)
    {
        List<ReservationDTO> allReservationsDTO = new ArrayList<>();
        for (Reservation reservation : reservationsToConvert)
        {
            allReservationsDTO.add(ReservationDTO.convertToDTO(reservation));
        }
        return allReservationsDTO;
    }
}
