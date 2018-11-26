package Sprint_0;

import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


class SensorTest {

    @Test
    void testaConstrutorSensor(){
        //Arrange
        Calendar calendario= new GregorianCalendar(1991, 11, 2);
        Date dataFuncionamento = calendario.getTime();
        TipoSensor tipoSensor = new TipoSensor("Temperatura");
        Localizacao locS1 = new Localizacao(123,345,50);
        Sensor s1 = new Sensor("A123", dataFuncionamento,tipoSensor,locS1);
        //Act
        String expectedResult=s1.getmNomeSensor();
        //Assert

    }

}