package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.utils.Utils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RoomSensor implements Sensor {

    @Id
    private String id;
    private String sensorName;
    private LocalDateTime startingDate;

    @ElementCollection
    @CollectionTable(name = "Reading",
            joinColumns = @JoinColumn(name = "SENSOR_ID"))
    private List<Reading> listOfReadings = new ArrayList<>();

    @Transient
    private SensorType sensorType;

    private String units;

    @Transient
    private boolean isActive;

    public RoomSensor(String id, String sensorName, LocalDateTime startingDate, SensorType sensorType, String units) {
        this.id = id;
        this.sensorName = sensorName;
        this.startingDate = startingDate;
        this.sensorType = sensorType;
        this.units = units;
        this.isActive = true;
    }

    protected RoomSensor() {
        // empty
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public double getMaximumValueOfDay(LocalDate date) {
        if (!getDailyMeasurement(date).isEmpty()) {
            double maximumValueOfDay = getDailyMeasurement(date).get(0).getValue();
            for (Reading reading : getDailyMeasurement(date)) {
                if (!Utils.isFirstDoubleBiggerThanSecondOne(maximumValueOfDay, reading.getValue()) && !Utils.isSameDouble(maximumValueOfDay, reading.getValue())) {
                    maximumValueOfDay = reading.getValue();
                }
            }
            return maximumValueOfDay;
        }
        return Double.NaN;
    }

    public List<Reading> getDailyMeasurement(LocalDate date) {
        List<Reading> daylyReadings = new ArrayList<>();
        for (Reading registo : listOfReadings) {
            LocalDate secondDate = registo.getDateTime().toLocalDate();

            if (checkIfDaysAreEqual(date, secondDate) && (!Double.isNaN(registo.getValue()))) {
                daylyReadings.add(registo);
            }
        }
        return daylyReadings;

    }

    public boolean checkIfDaysAreEqual(LocalDate firstDate, LocalDate secondDate) {
        return (firstDate.isEqual(secondDate));
    }

    public boolean isMeasurementListEmpty() {
        return listOfReadings.isEmpty();
    }

    public boolean sensorTypeEqualsSensorType(SensorType type) {
        String tipoDoSensorPedido = type.getType();
        return (this.getSensorType().getType().equals(tipoDoSensorPedido));
    }

    public Reading getLastMeasurement() {
        if (listOfReadings.isEmpty()) {
            return null;
        }
        Reading reading = listOfReadings.get(0);
        for (int i = (listOfReadings.size() - 1); i > 0; i--) {
            if (!(Double.isNaN(listOfReadings.get(i).getValue()))) {
                return listOfReadings.get(i);
            }
        }
        return reading;
    }

    public boolean addReading(Reading reading) {
        if (!listOfReadings.contains(reading)) {
            return this.listOfReadings.add(reading);
        }
        return false;
    }
}
