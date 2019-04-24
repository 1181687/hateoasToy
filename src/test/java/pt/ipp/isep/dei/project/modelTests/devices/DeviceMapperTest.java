package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceDTO;
import pt.ipp.isep.dei.project.model.devices.DeviceMapper;
import pt.ipp.isep.dei.project.model.devices.washingmachine.WashingMachineType;

import static org.junit.jupiter.api.Assertions.*;

class DeviceMapperTest {

    @Test
    void mapToDTO() {
        // Arrange
        WashingMachineType washingMachineType = new WashingMachineType();
        Device washingMachine = washingMachineType.createDevice("WM1");
        washingMachine.setAttributesDevType("Capacity", 40);
        washingMachine.setAttributesDevType("Duration", 1);
        washingMachine.setAttributesDevType("Energy Consumption", 1);
        washingMachine.setAttributesDevType("Nominal Power", 1000);

        DeviceDTO expectedResult = DeviceMapper.mapToDTO(washingMachine);
        // Act
        Device result = washingMachine;

        // Assert
        assertEquals(expectedResult.getName(),result.getName());
        assertEquals(expectedResult.getDeviceType(),result.getType());
        assertEquals(expectedResult.getNominalPower(),result.getNominalPower());
    }
}