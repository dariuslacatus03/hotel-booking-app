package com.booking.hotel.backendspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Room> rooms;
}
