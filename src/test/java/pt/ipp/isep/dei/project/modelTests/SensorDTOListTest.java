package pt.ipp.isep.dei.project.modelTests;

import org.junit.Test;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorDTOList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SensorDTOListTest {

    @Test
    public void testGetList_ReturnsEquals() {
        // Arrange
        SensorDTO sensorDTO = new SensorDTO();
        SensorDTOList sensorDTOList = new SensorDTOList();
        sensorDTOList.addSensorDTO(sensorDTO);

        List<SensorDTO> expectedResult = new ArrayList<>();

        expectedResult.add(sensorDTO);
        // Act
        List<SensorDTO> result = sensorDTOList.getList();
        // Assert
        assertEquals(expectedResult, result);

    }
}