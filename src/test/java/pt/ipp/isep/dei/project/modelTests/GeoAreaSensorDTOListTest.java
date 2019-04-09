package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorDTOList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoAreaSensorDTOListTest {

    @Test
    public void testGetList_ReturnsEquals() {
        // Arrange
        GeoAreaSensorDTO sensorDTO = new GeoAreaSensorDTO();
        SensorDTOList sensorDTOList = new SensorDTOList();
        sensorDTOList.addSensorDTO(sensorDTO);

        List<GeoAreaSensorDTO> expectedResult = new ArrayList<>();

        expectedResult.add(sensorDTO);
        // Act
        List<GeoAreaSensorDTO> result = sensorDTOList.getList();
        // Assert
        assertEquals(expectedResult, result);

    }
}