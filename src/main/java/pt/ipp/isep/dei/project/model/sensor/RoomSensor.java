package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.RoomReading;
import pt.ipp.isep.dei.project.utils.Utils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class RoomSensor {
    @Id
    private String id;
    private String sensorName;
    private LocalDateTime startingDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "RoomReading",
            joinColumns = @JoinColumn(name = "SENSOR_ID"))
    private List<RoomReading> listOfRoomReadings = new ArrayList<>();

    @Embedded
    private SensorType sensorType;

    private String units;

    @Embedded
    private SensorState isActive;

    public RoomSensor(String id, String sensorName, LocalDateTime startingDate, SensorType sensorType, String units) {
        this.id = id;
        this.sensorName = sensorName;
        this.startingDate = startingDate;
        this.sensorType = sensorType;
        this.units = units;
        this.isActive = new SensorState();
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

    public boolean isActive() {
        return isActive.isActive();
    }

    public double getMaximumValueOfDay(LocalDate date) {
        if (!getDailyMeasurement(date).isEmpty()) {
            double maximumValueOfDay = getDailyMeasurement(date).get(0).getValue();
            for (RoomReading reading : getDailyMeasurement(date)) {
                if (!Utils.isFirstDoubleBiggerThanSecondOne(maximumValueOfDay, reading.getValue()) && !Utils.isSameDouble(maximumValueOfDay, reading.getValue())) {
                    maximumValueOfDay = reading.getValue();
                }
            }
            return maximumValueOfDay;
        }
        return Double.NaN;
    }

    public List<RoomReading> getDailyMeasurement(LocalDate date) {
        List<RoomReading> daylyRoomReadings = new ArrayList<>();
        for (RoomReading registo : listOfRoomReadings) {
            LocalDate secondDate = registo.getDateTime().toLocalDate();

            if (checkIfDaysAreEqual(date, secondDate) && (!Double.isNaN(registo.getValue()))) {
                daylyRoomReadings.add(registo);
            }
        }
        return daylyRoomReadings;

    }

    public boolean checkIfDaysAreEqual(LocalDate firstDate, LocalDate secondDate) {
        return (firstDate.isEqual(secondDate));
    }

    public boolean isMeasurementListEmpty() {
        return listOfRoomReadings.isEmpty();
    }

    public boolean sensorTypeEqualsSensorType(SensorType type) {
        String tipoDoSensorPedido = type.getType();
        return (this.getSensorType().getType().equals(tipoDoSensorPedido));
    }

    public RoomReading getLastMeasurement() {
        if (listOfRoomReadings.isEmpty()) {
            return null;
        }
        RoomReading reading = listOfRoomReadings.get(0);
        for (int i = (listOfRoomReadings.size() - 1); i > 0; i--) {
            if (!(Double.isNaN(listOfRoomReadings.get(i).getValue()))) {
                return listOfRoomReadings.get(i);
            }
        }
        return reading;
    }

    public boolean addRoomReading(RoomReading reading) {
        if (!this.readingExistsBySensorIdLocalDateTime(reading)) {
            return this.listOfRoomReadings.add(reading);
        }
        return false;
    }

    public boolean readingExistsBySensorIdLocalDateTime(RoomReading reading) {
        if (!listOfRoomReadings.isEmpty()) {
            for (RoomReading reading1 : listOfRoomReadings) {
                if (reading1.getDateTime().equals(reading.getDateTime())) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof RoomSensor)) {
            return false;
        }
        RoomSensor sensor = (RoomSensor) object;
        return this.id.equalsIgnoreCase(sensor.id);
    }
    @Override
    public int hashCode (){
        return Objects.hash(this.id);
    }
}
