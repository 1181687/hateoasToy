package pt.ipp.isep.dei.project.model;

public class GeographicalAreaDTO {

    private String geoAreaName;
    private String geographicalAreaType;
    private double width;
    private double lenght;
    private double latitude;
    private double longitude;
    private double altitude;

    public GeographicalAreaDTO() {
        // empty
    }

    public String getGeoAreaName() {
        return geoAreaName;
    }

    public void setGeoAreaName(String geoAreaName) {
        this.geoAreaName = geoAreaName;
    }

    public String getGeographicalAreaType() {
        return geographicalAreaType;
    }

    public void setGeographicalAreaType(String geographicalAreaType) {
        this.geographicalAreaType = geographicalAreaType;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
