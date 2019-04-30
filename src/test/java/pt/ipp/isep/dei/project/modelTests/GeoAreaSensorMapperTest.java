package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GeoAreaSensorMapperTest {

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
        LocalDateTime startingDate = LocalDateTime.of(1991, 11, 2, 15, 25);
        LocationDTO location = new LocationDTO();
        location.setLatitude(123);
        location.setLongitude(345);
        location.setElevation(50);
        String units = "1/ms";

        SensorTypeId temperature = new SensorTypeId(typeName);
        Location locationSensor = new Location(123, 345, 50);

        GeoAreaSensor expectedResult = new GeoAreaSensor(new SensorId(id), name, startingDate, temperature, locationSensor, units);

        //Act
        GeoAreaSensorDTO sensorDTO = GeoAreaSensorMapper.mapToDTO(expectedResult);
        GeoAreaSensor result = GeoAreaSensorMapper.mapToEntity(sensorDTO);

        //Assert
        assertEquals(expectedResult, result);
        assertEquals("sensor1", sensorDTO.getName());
        assertEquals("1/ms", sensorDTO.getUnits());
        assertEquals(true, sensorDTO.getIsActive());
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
        SensorTypeId temperature = new SensorTypeId("Temperature");
        Location locationSensor = new Location(123, 345, 50);

        GeoAreaSensor expectedResult = new GeoAreaSensor(new SensorId(id), name, startingDateSensor, temperature, locationSensor, units);

        GeoAreaSensorDTO sensorDTO = GeoAreaSensorMapper.newSensorDTO();

        sensorDTO.setId(id);
        sensorDTO.setName(sensorDTO.getName());
        sensorDTO.setSensorType(typeName);
        sensorDTO.setStartingDate(startingDate);
        sensorDTO.setLocation(location);
        sensorDTO.setUnits(sensorDTO.getUnits());
        sensorDTO.setActive(sensorDTO.getIsActive());


        //Act

        GeoAreaSensor result = GeoAreaSensorMapper.mapToEntity(sensorDTO);
        //Assert
        assertEquals(expectedResult, result);
    }
}