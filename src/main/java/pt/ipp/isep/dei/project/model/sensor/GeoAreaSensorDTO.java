package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeoAreaSensorDTO {
    private String id;
    private String name;
    private LocalDate startingDate;
    private String sensorType;
    private LocationDTO location;
    private String units;
    private boolean isActive = true;
    private GeoAreaIdDTO geoAreaIdDTO;
    private List<ReadingDTO> readingDTOs = new ArrayList<>();

    /**
     * Constructor.
     */
    public GeoAreaSensorDTO() {
        //intentionally empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method that returns the name of a sensor.
     *
     * @return String corresponding to the name of the sensor.
     */
    public String getName() {
        return name;
    }

    /**
     * Method that sets the name of a sensor.
     *
     * @param name Name to be used.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that returns the date in which a sensor has started working.
     *
     * @return LocalDateTime corresponding to the starting date.
     */
    public LocalDate getStartingDate() {
        return startingDate;
    }

    /**
     * Method that sets the starting date.
     *
     * @param startingDate Starting date do be used.
     */
    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * Method that returns the type of a sensor.
     *
     * @return SensorType.
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * Method that sets the type of a sensor.
     *
     * @param sensorType Type to be used.
     */
    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    /**
     * Method that gets the location of a sensor.
     *
     * @return Location.
     */
    public LocationDTO getLocation() {
        return this.location;
    }

    /**
     * Method that sets the location of a sensor.
     *
     * @param location Location to be used.
     */
    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public GeoAreaIdDTO getGeographicalAreaId() {
        return this.geoAreaIdDTO;
    }

    public void setGeographicalAreaId(GeoAreaIdDTO geoAreaIdDTO) {
        this.geoAreaIdDTO = geoAreaIdDTO;
    }

    public List<ReadingDTO> getReadingDTOs() {
        return this.readingDTOs;
    }

    public void setReadingDTOs(List<ReadingDTO> readingDTOs) {
        this.readingDTOs = readingDTOs;
    }

    public boolean addReadingDTO(ReadingDTO readingDTO) {
        if (!this.readingDTOs.contains(readingDTO)) {
            this.readingDTOs.add(readingDTO);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeoAreaSensorDTO)) {
            return false;
        }
        GeoAreaSensorDTO geoAreaSensorDTO = (GeoAreaSensorDTO) obj;
        return this.id.equals(geoAreaSensorDTO.getId()) && this.sensorType.equals(geoAreaSensorDTO.getSensorType()) && this.location.equals(geoAreaSensorDTO.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.location, this.sensorType);
    }
}
