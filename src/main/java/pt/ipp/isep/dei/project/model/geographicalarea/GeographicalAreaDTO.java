package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.LocationMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;

import java.util.ArrayList;
import java.util.List;

public class GeographicalAreaDTO {

    private String id;
    private String description;
    private String type;
    private double width;
    private double length;
    private double latitude;
    private double longitude;
    private double elevation;
    private GeographicalAreaDTO parentGeoArea;
    private List<GeoAreaSensorDTO> sensors = new ArrayList<>();

    public GeographicalAreaDTO() {
        // empty
    }

    public String getId() {
        return id;
    }

    public GeoAreaIdDTO getGeoAreaIdDTO() {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(this.latitude);
        locationDTO.setLongitude(this.longitude);
        locationDTO.setElevation(this.elevation);
        GeoAreaIdDTO geoAreaIdDTO = new GeoAreaIdDTO();
        geoAreaIdDTO.setId(this.id);
        geoAreaIdDTO.setLocationDTO(locationDTO);
        geoAreaIdDTO.setGeoAreaType(this.type);
        return geoAreaIdDTO;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
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

    public List<GeoAreaSensorDTO> getSensors() {
        return sensors;
    }

    public void addSensor(GeoAreaSensorDTO sensor) {
        this.sensors.add(sensor);
    }

    public LocationDTO getLocation() {
        LocationDTO location = LocationMapper.newLocationDTO();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setElevation(elevation);
        return location;
    }

    public void addAllSensors(List<GeoAreaSensor> sensorList) {
        for (GeoAreaSensor sensor : sensorList) {
            this.sensors.add(GeoAreaSensorMapper.mapToDTO(sensor));
        }
    }

    public void setParentGeoArea(GeographicalAreaDTO geographicalAreaDTO){
        this.parentGeoArea = geographicalAreaDTO;
    }

    public GeographicalAreaDTO getParentGeoArea(){
        return this.parentGeoArea;
    }
}
