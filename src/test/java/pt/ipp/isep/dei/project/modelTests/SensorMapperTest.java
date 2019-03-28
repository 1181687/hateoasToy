package pt.ipp.isep.dei.project.modelTests;
/*
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SensorMapperTest {

    /**
     * this method map to DTO a GeographicalArea
     */
    @Test
    public void testMapToDTO_GeoAreaDTO() {
        //Arrange
        //Arrange
        String id = "S1";
        String name = "sensor1";
        String typeName = "Temperature";
        LocalDateTime startingDate = LocalDateTime.of(1991, 11, 2,15,25);
        LocationDTO location = new LocationDTO();
        location.setLatitude(123);
        location.setLongitude(345);
        location.setElevation(50);
        String units = "1/ms";

        SensorType temperature = new SensorType(typeName);
        Location locationSensor = new Location(123, 345, 50);

        Sensor expectedResult = new Sensor(id, name, startingDate, temperature, locationSensor, units);

        //Act
        SensorDTO sensorDTO = SensorMapper.mapToDTO(expectedResult);
        Sensor result = SensorMapper.mapToEntity(sensorDTO);

        //Assert
        assertEquals(expectedResult, result);
        assertEquals("sensor1", sensorDTO.getName());
        assertEquals("1/ms", sensorDTO.getUnits());
    }

    @Test
    public void testMapToEntity() {
        //Arrange
        //Arrange
        String id = "S1";
        String name = "sensor1";
        String typeName = "Temperature";
        LocalDate startingDate = LocalDate.of(1991, 11, 2);
        LocationDTO location = new LocationDTO();
        location.setLatitude(123);
        location.setLongitude(345);
        location.setElevation(50);
        String units = "1/ms";

        LocalDateTime startingDateSensor = LocalDateTime.of(1991, 11, 2, 21, 10, 25).truncatedTo(ChronoUnit.DAYS);
        SensorType temperature = new SensorType("Temperature");
        Location locationSensor = new Location(123, 345, 50);

        Sensor expectedResult = new Sensor(id, name, startingDateSensor, temperature, locationSensor, units);

        SensorDTO sensorDTO = SensorMapper.newSensorDTO();

        sensorDTO.setId(id);
        sensorDTO.setName(sensorDTO.getName());
        sensorDTO.setSensorType(typeName);
        sensorDTO.setStartingDate(startingDate);
        sensorDTO.setLocation(location);
        sensorDTO.setUnits(sensorDTO.getUnits());


        //Act

        Sensor result = SensorMapper.mapToEntity(sensorDTO);
        //Assert
        assertEquals(expectedResult, result);
    }
}*/