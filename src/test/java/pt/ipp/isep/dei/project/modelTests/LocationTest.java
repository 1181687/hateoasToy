package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    @Test
    void testarDistanciaDuasLocalizacoes() {
        double lat1 = 41.1496;
        double lon1 = -8.6109;
        double alt1 = 97;
        double lat2 = 52.3740;
        double lon2 = 4.8897;
        double alt2 = 13;
        Location local1 = new Location(lat1, lon1, alt1);
        Location local2 = new Location(lat2, lon2, alt2);

        //Location local2 = new Location(lat2, -16.9, 0);
        double expectedResult = 1611770.043;
        double result = local1.distanciaDuasLocalizacoes(local2);
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarDistanciaDuasLocalizacoesForaInvervalo() {
        double lat1 = 41.1496;
        double lon1 = -200.6109;
        double alt1 = 97;
        double lat2 = 52.3740;
        double lon2 = 4.8897;
        double alt2 = 13;
        Location local1 = new Location(lat1, lon1, alt1);
        Location local2 = new Location(lat2, lon2, alt2);

        double expectedResult = Double.NaN;
        double result = local1.distanciaDuasLocalizacoes(local2);
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLatitudeDentroIntervaloLimitePositivo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local = new Location(latitude, longitude, altitude);
        local.setmLatitude(90);
        double expectedResult = 90;
        //act
        double result = local.getmLatitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLatitudeDentroIntervaloLimiteNegativo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local = new Location(latitude, longitude, altitude);
        local.setmLatitude(-90);
        double expectedResult = -90;
        //act
        double result = local.getmLatitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLatitudeForaIntervaloNegativo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local = new Location(latitude, longitude, altitude);
        local.setmLatitude(-91);
        double expectedResult = Double.NaN;
        //act
        double result = local.getmLatitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLatitudeForaIntervaloPositivo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local = new Location(latitude, longitude, altitude);
        local.setmLatitude(91);
        double expectedResult = Double.NaN;
        //act
        double result = local.getmLatitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLongitudeDentroIntervaloLimitePositivo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local = new Location(latitude, longitude, altitude);
        local.setmLongitude(180);
        double expectedResult = 180;
        //act
        double result = local.getmLongitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLongitudeDentroIntervaloLimiteNegativo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local = new Location(latitude, longitude, altitude);
        local.setmLongitude(-180);
        double expectedResult = -180;
        //act
        double result = local.getmLongitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLongitudeForaIntervaloPositivo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local = new Location(latitude, longitude, altitude);
        local.setmLongitude(181);
        double expectedResult = Double.NaN;
        //act
        double result = local.getmLongitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLongitudeForaIntervaloNegativo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local = new Location(latitude, longitude, altitude);
        local.setmLongitude(-181);
        double expectedResult = Double.NaN;
        //act
        double result = local.getmLongitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarEqualsComOMesmoObjetoDaClasseLocalizacao(){
        //Arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local1 = new Location(latitude, longitude, altitude);
        boolean expectedResult = true;
        //Act
        boolean result = local1.equals(local1);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsComUmObjetoQueNaoEDaClasseLocalizacao(){
        //Arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local1 = new Location(latitude, longitude, altitude);
        Object objeto = new Object();
        boolean expectedResult = false;
        //Act
        boolean result = local1.equals(objeto);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsComDuasLocalizacoesIguais(){
        //Arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local1 = new Location(latitude, longitude, altitude);

        double latitude2 = 41.1496;
        double longitude2 = -8.6109;
        double altitude2 = 97;
        Location local2 = new Location(latitude2, longitude2, altitude2);

        boolean expectedResult = true;
        //Act
        boolean result = local1.equals(local2);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarEqualsComDuasLocalizacoesDiferentes(){
        //Arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Location local1 = new Location(latitude, longitude, altitude);

        double latitude2 = 41.1497;
        double longitude2 = -8.6109;
        double altitude2 = 97;
        Location local2 = new Location(latitude2, longitude2, altitude2);

        boolean expectedResult = false;
        //Act
        boolean result = local1.equals(local2);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testarHashCode () {
        // Arrange
        Location local = new Location(41.1496, -8.6109, 97);
        int expectedResult = 1;
        // Act
        int result = local.hashCode();
        // Assert
        assertEquals(expectedResult, result);
    }

}