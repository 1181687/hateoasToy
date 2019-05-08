package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.Objects;

public class LocationDTO {
    private double latitude;
    private double longitude;
    private double elevation;

    /**
     * constructor of Location that receives a latitude, a longitude and an elevation.
     */
    public LocationDTO() {
        //Intentionally empty
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationDTO)) {
            return false;
        }
        LocationDTO locationDTO = (LocationDTO) obj;
        return Utils.isSameDouble(this.latitude, locationDTO.getLatitude()) && Utils.isSameDouble(this.longitude, locationDTO.getLongitude()) && Utils.isSameDouble(this.elevation, locationDTO.getElevation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.latitude, this.longitude, this.elevation);
    }
}
