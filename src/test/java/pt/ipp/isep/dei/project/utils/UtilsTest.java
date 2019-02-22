package pt.ipp.isep.dei.project.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void isSameDouble() {
        // arrange
        double value1 = 1.1;
        double value2 = 1.1;

        // act
        boolean result = Utils.isSameDouble(value1, value2);

        // assert
        assertTrue(result);
    }

    @Test
    void isSameDoubleFalse() {
        // arrange
        double value1 = 1.1;
        double value2 = 1.0;

        // act
        boolean result = Utils.isSameDouble(value1, value2);

        // assert
        assertFalse(result);
    }

    @Test
    void testIfConstructorException() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //arrange
        Constructor<Utils> constructor = Utils.class.getDeclaredConstructor();
        //Assert
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void roundTest() {
        // Arrange
        double valueToBeRounded = 3.7654;
        int decimalPlaces = 0;

        double expectedResult = 4.0;

        // Act
        double result = Utils.round(valueToBeRounded, decimalPlaces);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void roundTestIllegalArgumentException() {
        // Arrange
        double valueToBeRounded = 3.7654;
        int decimalPlaces = -1;

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                Utils.round(valueToBeRounded, decimalPlaces)
        );
        assertEquals("Please insert a positive value.", exception.getMessage());
    }

    @Test
    void testGetMeteringPeriod() {
        //Arrange
        int expectedResult = 15;

        //Act
        int result = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testMeteringPeriodWrongKey() {
        //Arrange
        String expectedResult = "Wrong Key";
        //Act
        String result = Utils.readConfigFile("Configuration.properties", "Cenas");
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void testMeteringPeriodWrongFile() {
        //Arrange
        String expectedResult = "There is no file with that filename.";
        //Act
        String result = Utils.readConfigFile("Dummy.properties", "dummyname");
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void testGetMeteringPeriodDevice() {
        //Arrange
        int expectedResult = 15;

        int result = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void testGetListOfDeviceTypes() {
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Fridge");
        expectedResult.add("Lamp");
        expectedResult.add("DishWasher");
        expectedResult.add("WashingMachine");
        expectedResult.add("ElectricWaterHeater");

        List<String> result = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        assertEquals(expectedResult, result);
    }


    @Test
    void testGetDataSeries() {
        //Arrange

        // Readings Instantiation
        LocalDateTime time0 = LocalDateTime.of(2019, 01, 24, 00, 00, 00);
        LocalDateTime time1 = LocalDateTime.of(2019, 01, 24, 8, 00, 00);
        LocalDateTime time2 = LocalDateTime.of(2019, 01, 24, 16, 00, 00);

        Map<LocalDateTime, Double> mapToTest = new TreeMap<>();

        mapToTest.put(time0, 3.0);
        mapToTest.put(time1, 5.0);
        mapToTest.put(time2, 7.0);

        String expectedResult = "Date/hour: 2019-01-24 00:00, Energy Consumption: 3.0 kWh\n" +
                "Date/hour: 2019-01-24 08:00, Energy Consumption: 5.0 kWh\n" +
                "Date/hour: 2019-01-24 16:00, Energy Consumption: 7.0 kWh\n";

        //Act
        String result = Utils.getDataSeriesToString(mapToTest);

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testGetDataSeriesEmptyMap() {
        //Arrange
        Map<LocalDateTime, Double> mapToTest = new TreeMap<>();

        String expectedResult = "";

        //Act
        String result = Utils.getDataSeriesToString(mapToTest);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void isSameNumberTrueTest() {
        // Act
        boolean result = Utils.isSameNumber(1.1, 1.1);

        // assert
        assertTrue(result);
    }

    @Test
    void isSameNumberFalseTest() {
        // Act
        boolean result = Utils.isSameNumber(1.1, 1.0);

        // Assert
        assertFalse(result);
    }

    @Test
    void isFirstDoubleBiggerThanSecondOneTestTrue() {
        // arrange
        double value1 = 1.2;
        double value2 = 1.1;

        // act
        boolean result = Utils.isFirstDoubleBiggerThanSecondOne(value1, value2);

        // assert
        assertTrue(result);
    }

    @Test
    void isFirstDoubleBiggerThanSecondOneTestFalse() {
        // arrange
        double value1 = 1.1;
        double value2 = 1.1;

        // act
        boolean result = Utils.isFirstDoubleBiggerThanSecondOne(value1, value2);

        // assert
        assertFalse(result);
    }

    @Test
    void isFirstDoubleBiggerThanSecondOneTestFalse2() {
        // arrange
        double value1 = 1.1;
        double value2 = 1.2;

        // act
        boolean result = Utils.isFirstDoubleBiggerThanSecondOne(value1, value2);

        // assert
        assertFalse(result);
    }
}