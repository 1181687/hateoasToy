package sprint0.ControllersTests;

import org.junit.jupiter.api.Test;
import sprint0.Model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US6ControllerTest {

    @Test
    public void testarAdicaoDeDoisSensoresAAreaGeografica () {
        //Arrange

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Localizacao local1 = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area1 = new RetanguloArea(10, 10, local1);
        AreaGeografica ag1 = new AreaGeografica(nomeAG1, tipo1, local1, area1);

        Calendar cal = new GregorianCalendar();
        Date data = cal.getTime();
        TipoSensor tipo = new TipoSensor("Hum");
        Localizacao local = new Localizacao(45, 45, 45);
        Sensor s1 = new Sensor("fe",data,tipo,local);
        Sensor s2 = new Sensor ("fe",data,tipo,local);
        ag1.getmListaSensor().add(s1);
        boolean resultado = ag1.adicionarSensorAListaDeSensores(s2);

        assertFalse(resultado);
    }

}