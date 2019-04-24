package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.DeviceReading;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeviceReadingTest {

    @Test
    void getValue() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        DeviceReading reading = new DeviceReading(20,timestamp);

        double expectedResult = 20.0;

        // Act
        double result = reading.getValue();

        // Assert
        assertEquals(expectedResult,result,0.0001);
    }

    @Test
    void getLocalDateTime() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        DeviceReading reading = new DeviceReading(20,timestamp);

        LocalDateTime expectedResult = timestamp;

        // Act
        LocalDateTime result = reading.getLocalDateTime();

        // Assert
        assertEquals(expectedResult,result);
    }
}