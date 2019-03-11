package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.ReadingDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SensorDTO {
    private String id;
    private String sensorName;
    private LocalDate startingDate;
    private List<ReadingDTO> listOfReadings = new ArrayList<>();
    private String sensorType;
    private LocationDTO location;
    private String units;

    /**
     * Constructor.
     */
    public SensorDTO() {
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
        return sensorName;
    }

    /**
     * Method that sets the name of a sensor.
     *
     * @param name Name to be used.
     */
    public void setName(String name) {
        this.sensorName = name;
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
     * Method that returns the list of readings in a sensor.
     *
     * @return List of readings.
     */
    public List<ReadingDTO> getListOfReadings() {
        return listOfReadings;
    }

    /**
     * Method that adds a reading to a sensor.
     *
     * @param readingDTO Reading to be used.
     */
    public void addReadingDTO(ReadingDTO readingDTO) {
        this.listOfReadings.add(readingDTO);
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
        return location;
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
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
