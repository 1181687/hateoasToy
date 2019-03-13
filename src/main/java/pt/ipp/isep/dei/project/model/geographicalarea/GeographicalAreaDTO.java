package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.sensor.SensorDTO;

import java.util.ArrayList;
import java.util.List;

public class GeographicalAreaDTO {

    private String id;
    private String sensorName;
    private String geographicalAreaType;
    private double width;
    private double lenght;
    private double latitude;
    private double longitude;
    private double altitude;
    private List<SensorDTO> sensors = new ArrayList<>();

    public GeographicalAreaDTO() {
        // empty
    }

    public String getId() {
        return id;
    }

    public void setId(String geoAreaName) {
        this.id = geoAreaName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
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

    public void setLength(double lenght) {
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

    public List<SensorDTO> getSensors() {
        return sensors;
    }

    public void addSensor(SensorDTO sensor) {
        this.sensors.add(sensor);
    }
}
