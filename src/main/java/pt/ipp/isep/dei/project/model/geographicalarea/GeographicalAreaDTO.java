package pt.ipp.isep.dei.project.model.geographicalarea;

public class GeographicalAreaDTO {

    private String id;
    private String description;
    private String geographicalAreaType;
    private double width;
    private double lenght;
    private double latitude;
    private double longitude;
    private double altitude;

    public GeographicalAreaDTO() {
        // empty
    }

    public String getId() {
        return id;
    }

    public void setId(String geoAreaName) {
        this.id = geoAreaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
