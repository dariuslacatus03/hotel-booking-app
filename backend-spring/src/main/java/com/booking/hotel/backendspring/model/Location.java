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
        // Earth's average meridional radius in meters
        double meridionalRadius = 6367449;

        // Earth's equatorial radius in meters
        double equatorialRadius = 6378137;

        // Convert latitude and longitude from degrees to radians
        double latitudeRadians = Math.toRadians(latitude);

        // Calculate meters per degree for latitude and longitude
        double metersPerLatitude = (Math.PI * meridionalRadius * Math.cos(latitudeRadians)) / 180;
        double metersPerLongitude = (Math.PI * equatorialRadius * Math.cos(latitudeRadians)) / 180;

        // Convert latitude and longitude to meters
        double latitudeToMeters = latitude * metersPerLatitude;
        double longitudeToMeters = longitude * metersPerLongitude;

        return new double[] {latitudeToMeters, longitudeToMeters};
    }

}
