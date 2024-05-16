package com.booking.hotel.backendspring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Location {
    double latitude;
    double longitude;

    public static double[] toMeters(double latitude, double longitude)
    {
        double meridionalRadius = 6367449;
        double equatorialRadius = 6378137;

        double latitudeRadians = Math.toRadians(latitude);

        double metersPerLatitude = (Math.PI * meridionalRadius * Math.cos(latitudeRadians)) / 180;
        double metersPerLongitude = (Math.PI * equatorialRadius * Math.cos(latitudeRadians)) / 180;

        double latitudeToMeters = latitude * metersPerLatitude;
        double longitudeToMeters = longitude * metersPerLongitude;

        return new double[] {latitudeToMeters, longitudeToMeters};
    }

}
