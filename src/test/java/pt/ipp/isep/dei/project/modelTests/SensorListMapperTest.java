package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SensorListMapperTest {

    @Test
    public void testEntityToMap() {
        //Arrange
        //Sensor s1
        LocalDateTime startingDate = LocalDateTime.of(1991, 11, 2, 21, 10, 25);
        SensorType temperature = new SensorType("Temperature");
        Location location = new Location(123, 345, 50);
        Sensor s1 = new Sensor("R003", "A123", startingDate, temperature, location, "l/m2");

        //Sensor s2
        LocalDateTime startingDate2 = LocalDateTime.of(1991, 11, 3, 21, 10, 25);
        SensorType temperature2 = new SensorType("Temperature");
        Location location2 = new Location(123, 345, 50);
        Sensor s2 = new Sensor("R002", "A323", startingDate, temperature, location, "l/m2");


        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s1);


        SensorDTOList sensorDTOList = SensorListMapper.newSensorDTOList();

        SensorList expectedResult = SensorListMapper.mapToEntity(sensorDTOList);
        //Act
        SensorDTOList outraCoisa = SensorListMapper.mapToDTO(sensorList);
        SensorList result = SensorListMapper.mapToEntity(outraCoisa);

        //Assert
        assertEquals(expectedResult, result);

    }
}
