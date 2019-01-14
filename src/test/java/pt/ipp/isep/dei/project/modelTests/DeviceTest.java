package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceTest {

    @Test
    void getDeviceNameTest() {
        //Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double luminousFlux = 10.0;
        DeviceSpecs deviceSpecs1 = new Lamp(luminousFlux);
        double nominalPower1 = 1.0;
        Device dev1 = new Device("Lamp1", room, deviceSpecs1, nominalPower1);
        String expectedResult = "Lamp1";

        //Act
        String result = dev1.getmName();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNominalPower() {
        //Arrange
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double luminousFlux = 10.0;
        double energyConsumption1 = 20.0;
        DeviceSpecs deviceSpecs1 = new Lamp("Lamp", luminousFlux, energyConsumption1);
        double nominalPower1 = 20.0;
        Device dev1 = new Device("Lamp1", room, deviceSpecs1, nominalPower1);
        double expectedResult = 20.0;

        //Act
        double result = dev1.getNominalPower();

        //Assert
        assertEquals(result, expectedResult);
    }
}
