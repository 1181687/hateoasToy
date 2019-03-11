package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.ReadingDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SensorDTO {
    private String sensorName;
    private LocalDateTime startingDate;
    private List<ReadingDTO> listOfReadings = new ArrayList<>();
    private SensorType sensorType;
    private Location location;

    /**
     * Constructor.
     */
    public SensorDTO() {
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
    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    /**
     * Method that sets the starting date.
     *
     * @param startingDate Starting date do be used.
     */
    public void setStartingDate(LocalDateTime startingDate) {
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
    public SensorType getSensorType() {
        return sensorType;
    }

    /**
     * Method that sets the type of a sensor.
     *
     * @param sensorType Type to be used.
     */
    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    /**
     * Method that gets the location of a sensor.
     *
     * @return Location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Method that sets the location of a sensor.
     *
     * @param location Location to be used.
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
