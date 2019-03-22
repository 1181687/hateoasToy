package pt.ipp.isep.dei.project.model;

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
}
