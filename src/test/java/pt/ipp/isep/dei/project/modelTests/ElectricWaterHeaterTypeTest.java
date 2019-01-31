package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectricWaterHeaterTypeTest {


    @Test
    public void testCreateDevice() {
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        String name = "EWH Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", 2, dim);

        double hotWaterTemp = 50;
        double maximumVolume = 150;
        double performanceRatio = 0.9;
        double nominalPower = 100;
        ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);

        Device expectedResult = new Device(name, room, electricWaterHeaterSpecs);

        Device result = electricWaterHeaterType.createDevice(name, room);

        assertEquals(expectedResult, result);

    }
}
