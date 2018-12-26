package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTest {
    @Test
    public void testAddRoomToHouse(){
        RoomList rList = new RoomList();
        Dimensions dim = new Dimensions(4,4,4);
        SensorList list = new SensorList();
        Room room = new Room("F5", 1, dim, list);
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(rList, gridlist, adr);

        boolean result = house.addRoomToHouse(room);
        assertTrue(result);
    }

    @Test
    public void testAddRoomToHouseFalse(){
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location local = new Location(10,10,10);
        Address adr = new Address("5000", local);
        House house = new House(rList, gridlist, adr);

        boolean result = house.addRoomToHouse(null);
        assertFalse(result);
    }

    @Test
    public void getAverageRainfallInTheAreaTest() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar House
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location location = new Location(10, 10, 10);
        Address adr = new Address("5000", location);
        House house = new House(rList, gridlist, adr, ag);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        // Sensor0
        Calendar calendarioDaMedicao01 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataHoraDaMedicao01 = calendarioDaMedicao01.getTime();

        Calendar calendarioDaMedicao02 = new GregorianCalendar(2018, 11, 3, 17, 24, 00);
        Date dataHoraDaMedicao02 = calendarioDaMedicao02.getTime();

        Medicao medicao01 = new Medicao(23, dataHoraDaMedicao01);
        Medicao medicao02 = new Medicao(30, dataHoraDaMedicao02);

        s0.adicionarMedicaoALista(medicao01);
        s0.adicionarMedicaoALista(medicao02);

        //Sensor1
        Calendar calendarioDaMedicao11 = new GregorianCalendar(2018, 11, 4, 15, 20, 00);
        Date dataHoraDaMedicao11 = calendarioDaMedicao11.getTime();

        Calendar calendarioDaMedicao12 = new GregorianCalendar(2018, 11, 5, 17, 24, 00);
        Date dataHoraDaMedicao12 = calendarioDaMedicao12.getTime();

        Medicao medicao11 = new Medicao(22, dataHoraDaMedicao11);
        Medicao medicao12 = new Medicao(25, dataHoraDaMedicao12);
        Medicao medicao13 = new Medicao(20, dataHoraDaMedicao12);

        s1.adicionarMedicaoALista(medicao11);
        s1.adicionarMedicaoALista(medicao12);
        s1.adicionarMedicaoALista(medicao13);

        Calendar startDate = new GregorianCalendar(2018, 11, 1, 15, 20, 00);
        Date startDate1 = startDate.getTime();
        Calendar endDate = new GregorianCalendar(2018, 11, 6, 17, 24, 00);
        Date endDate1 = endDate.getTime();

        double expectedResult = 24.375;

        TipoSensor searchType = new TipoSensor("Rainfall");

        //Act
        double result = house.getAverageDailyMeasurementOfHouseArea(searchType, startDate1, endDate1);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAverageRainfallInTheAreaTestNoReadings() {
        //arrange
        //Instanciar AG
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Location local = new Location(42.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicalArea ag = new GeographicalArea(nomeAG, tipo, local, area);

        //Instanciar House
        RoomList rList = new RoomList();
        HouseGridList gridlist = new HouseGridList();
        Location location = new Location(10, 10, 10);
        Address adr = new Address("5000", location);
        House house = new House(rList, gridlist, adr, ag);

        //Instanciar Sensor
        Calendar calendario0 = new GregorianCalendar(2018, 11, 2, 15, 20, 00);
        Date dataFuncionamento0 = calendario0.getTime();
        TipoSensor tipoSensor0 = new TipoSensor("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        Sensor s0 = new Sensor("A123", dataFuncionamento0, tipoSensor0, locS0);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s0);

        Calendar calendario1 = new GregorianCalendar(2018, 11, 5, 15, 20, 00);
        Date dataFuncionamento1 = calendario1.getTime();
        TipoSensor tipoSensor1 = new TipoSensor("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        Sensor s1 = new Sensor("A123", dataFuncionamento1, tipoSensor1, locS1);
        ag.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(s1);

        Calendar startDate = new GregorianCalendar(2018, 11, 1, 15, 20, 00);
        Date startDate1 = startDate.getTime();
        Calendar endDate = new GregorianCalendar(2018, 11, 6, 17, 24, 00);
        Date endDate1 = endDate.getTime();

        double expectedResult = 0;

        TipoSensor searchType = new TipoSensor("Rainfall");

        //Act
        double result = house.getAverageDailyMeasurementOfHouseArea(searchType, startDate1, endDate1);

        //Assert
        assertEquals(expectedResult, result);
    }
}
