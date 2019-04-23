package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.house.RoomId;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
public class RoomSensor {
    @EmbeddedId
    private RoomSensorId id;
    private String sensorName;
    private LocalDateTime startingDate;

    @Embedded
    private SensorTypeId sensorTypeId;

    private String units;

    @Embedded
    private SensorState isActive;

    @Embedded
    @JoinColumn(name = "room_id")
    private RoomId roomId;

    public RoomSensor(RoomSensorId id, String sensorName, LocalDateTime startingDate, SensorTypeId sensorTypeId, String units, RoomId roomId) {
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

    public RoomSensorId getId() {
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

    public String getSensorName() {
        return sensorName;
    }

    public SensorTypeId getSensorTypeId() {
        return sensorTypeId;
    }

    public String getUnits() {
        return units;
    }

    public SensorState getIsActive() {
        return isActive;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    /*public double getMaximumValueOfDay(LocalDate date) {
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
        String tipoDoSensorPedido = type.getSensorType();
        return (this.getSensorType().getSensorType().equals(tipoDoSensorPedido));
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
    public int hashCode() {
        return Objects.hash(this.id);
    }*/
}
