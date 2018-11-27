package Sprint_0;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


class SensorTest {

    @Test
    void testaConstrutor() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Sensor s2 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        //Act
        boolean result = s1.equals(s2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testaConstrutorSensor() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        String expectedResult = "A123";
        //Act
        String result = s1.getmNomeSensor();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorData() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Date expectedResult = calendario.getTime();
        //Act
        Date result = s1.getmDataFuncionamento();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorTipo() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        TipoSensor expectedResult = tipoSensor;
        //Act
        TipoSensor result = s1.getmTipoSensor();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testaConstrutorSensorLocalizacao() {
        //Arrange
        Calendar calendario = new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1 = new Sensor("A123", dataFuncionamento, tipoSensor, locS1);
        Localizacao expectedResult = locS1;
        //Act
        Localizacao result = s1.getmLocalizacao();
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testarDistanciaLinear(){
        //Arrange
        Calendar calendario = new GregorianCalendar(2018, 11, 27);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123, 345, 50);
        Sensor s1= new Sensor("s1",dataFuncionamento, tipoSensor, locS1);

        Localizacao locS2 = new Localizacao(300, 425, 100);
        Sensor s2= new Sensor("s2",dataFuncionamento, tipoSensor, locS2);

        double expectedResult = 194.2395;

        double result = s1.distanciaLinearEntreDoisSensores(s1,s2);

        //Assert
        assertEquals(expectedResult, result, 0.0001);

    }
}