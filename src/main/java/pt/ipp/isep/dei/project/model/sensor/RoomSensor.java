package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.roles.Root;
import pt.ipp.isep.dei.project.utils.Utils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class RoomSensor implements Root {
    @EmbeddedId
    private SensorId id;
    private String sensorName;
    private LocalDateTime startingDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ROOM_READING",
            joinColumns = @JoinColumn(name = "SENSOR_ID"))
    private List<Reading> readings = new ArrayList<>();

    @Embedded
    private SensorTypeId sensorTypeId;
    private String units;

    @Embedded
    private SensorState isActive;

    @Embedded
    @JoinColumn(name = "ROOM_ID")
    private RoomId roomId;

    public RoomSensor(SensorId id, String sensorName, LocalDateTime startingDate, SensorTypeId sensorTypeId, String units) {
        this.id = id;
        this.sensorName = sensorName;
        this.startingDate = startingDate;
        this.sensorTypeId = sensorTypeId;
        this.units = units;
        this.isActive = new SensorState();
    }

    public RoomSensor(SensorId id, String sensorName, LocalDateTime startingDate, SensorTypeId sensorTypeId, String units, RoomId roomId) {
        this.id = id;
        this.sensorName = sensorName;
        this.startingDate = startingDate;
        this.sensorTypeId = sensorTypeId;
        this.units = units;
        this.isActive = new SensorState();
        this.roomId = roomId;
    }

    protected RoomSensor() {
        // empty
    }

    public SensorId getId() {
        return id;
    }

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public SensorTypeId getSensorType() {
        return sensorTypeId;
    }

    public boolean isActive() {
        return isActive.isActive();
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
        List<Reading> daylyRoomReadings = new ArrayList<>();
        for (Reading registo : this.readings) {
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
        return this.readings.isEmpty();
    }

    public boolean sensorTypeEqualsSensorType(SensorTypeId typeId) {
        String tipoDoSensorPedido = typeId.getSensorTypeId();
        return (this.getSensorType().equals(tipoDoSensorPedido));
    }

    public Reading getLastMeasurement() {
        if (this.readings.isEmpty()) {
            return null;
        }
        Reading reading = this.readings.get(0);
        for (int i = (this.readings.size() - 1); i > 0; i--) {
            if (!(Double.isNaN(this.readings.get(i).getValue()))) {
                return this.readings.get(i);
            }
        }
        return reading;
    }

    public boolean addRoomReading(Reading reading) {
        if (!this.readingExistsBySensorIdLocalDateTime(reading)) {
            return this.readings.add(reading);
        }
        return false;
    }

    public boolean readingExistsBySensorIdLocalDateTime(Reading reading) {
        if (!this.readings.isEmpty()) {
            for (Reading reading1 : this.readings) {
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
        return this.id.equals(sensor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public List<Reading> getReadingsBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Reading> readingsBetweenDates = new ArrayList<>();
        for (Reading reading : this.readings) {
            if ((reading.getDateTime().toLocalDate().isEqual(startDate) || reading.getDateTime().toLocalDate().isAfter(startDate)) && (reading.getDateTime().toLocalDate().isEqual(endDate) || reading.getDateTime().toLocalDate().isBefore(endDate))) {
                readingsBetweenDates.add(reading);
            }
        }
        return readingsBetweenDates;
    }

}
