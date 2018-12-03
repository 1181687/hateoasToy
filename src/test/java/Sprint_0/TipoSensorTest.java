package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoSensorTest {

    @Test
    void TestaGetTipoSensor() {
        //Arrange
        TipoSensor tipo0 = new TipoSensor("Temperatura");
        String expectedResult = "Temperatura";
        //Act
        String result = tipo0.getmTipo();
        //Assert
        assertEquals(result,expectedResult);
    }

}