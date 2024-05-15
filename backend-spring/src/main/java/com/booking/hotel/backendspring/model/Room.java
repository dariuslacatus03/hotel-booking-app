package com.booking.hotel.backendspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    private int room_number;
    private int type;
    private double price;
    private boolean is_available;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

}
