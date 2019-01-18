package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Program;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProgramTest {

    @Test
    void testarEqualsSameObject() {
        //Arrange
        String name = "Program 1";
        double duration = 5;
        double energyConsumption = 200;
        Program program = new Program(name, duration, energyConsumption);
        boolean expectedResult = true;
        //Act
        boolean result = program.equals(program);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testEqualsOneObjectDontBelong() {
        //Arrange
        String name = "Program 1";
        double duration = 5;
        double energyConsumption = 200;
        Program program = new Program(name, duration, energyConsumption);
        Object objeto = new Object();
        boolean expectedResult = false;
        //Act
        boolean result = program.equals(objeto);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsTwoPrograms() {
        //Arrange
        String name = "Program 1";
        double duration = 5;
        double energyConsumption = 200;
        Program program = new Program(name, duration, energyConsumption);

        String name1 = "Program 1";
        double duration1 = 5;
        double energyConsumption1 = 200;
        Program program1 = new Program(name1, duration1, energyConsumption1);

        boolean expectedResult = true;
        //Act
        boolean result = program.equals(program1);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsTwoDifferentPrograms() {
        //Arrange
        String name = "Program 2";
        double duration = 6;
        double energyConsumption = 201;
        Program program = new Program(name, duration, energyConsumption);

        String name1 = "Program 1";
        double duration1 = 5;
        double energyConsumption1 = 200;
        Program program1 = new Program(name1, duration1, energyConsumption1);

        boolean expectedResult = false;
        //Act
        boolean result = program.equals(program1);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testhashCodeTrue() {
        //Arrange
        String name = "Program 1";
        double duration = 6;
        double energyConsumption = 200;

        int hash1 = Objects.hash(name, duration, energyConsumption);
        int hash2 = Objects.hash(name, duration, energyConsumption);

        // Act
        int result = hash1;
        int expectedResult = hash2;
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testhashCodeFalse() {
        //Arrange
        String name = "Program 1";
        double duration = 6;
        double energyConsumption = 200;
        double energyConsumption1 = 201;

        int hash1 = Objects.hash(name, duration, energyConsumption);
        int hash2 = Objects.hash(name, duration, energyConsumption1);

        // Act
        int result = hash1;
        int expectedResult = hash2;
        // Assert
        assertNotEquals(expectedResult, result);
    }
}