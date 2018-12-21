package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimensions;
import static org.junit.jupiter.api.Assertions.*;

public class DimensionsTest {

    @Test
    public void getAndSetHeightOfTheRoomTest() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmHeight(3.0);
        Double expectResult = 3.0;

        //act
        Double result = dimensions.getmHeight();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }


    @Test
    public void getAndSetHeightOfTheRoomTestNoChange() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmHeight(2.8);
        Double expectResult = 2.8;

        //act
        Double result = dimensions.getmHeight();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetLengthOfTheRoomTest() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmLength(2.6);
        Double expectResult = 2.6;

        //act
        Double result = dimensions.getmLength();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetLengthOfTheRoomTestNoChange() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 1.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmLength(1.5);
        Double expectResult = 1.5;

        //act
        Double result = dimensions.getmLength();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetWidthOfTheRoomTest() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 2.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmWidth(2.3);
        Double expectResult = 2.3;

        //act
        Double result = dimensions.getmWidth();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }

    @Test
    public void getAndSetWidthOfTheRoomNoChange() {
        //arrange
        Double height = 2.8;
        Double length = 1.5;
        Double width = 2.5;
        Dimensions dimensions = new Dimensions(height, length, width);

        dimensions.setmWidth(2.5);
        Double expectResult = 2.5;

        //act
        Double result = dimensions.getmWidth();

        //assert
        assertEquals(expectResult, result, 0.0001);
    }
}