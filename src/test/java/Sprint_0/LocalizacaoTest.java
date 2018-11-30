package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizacaoTest {

    @Test
    void testarDistanciaDuasLocalizacoes() {
        double lat1 = 41.1496;
        double lon1 = -8.6109;
        double alt1 = 97;
        double lat2 = 52.3740;
        double lon2 = 4.8897;
        double alt2 = 13;
        Localizacao local1 = new Localizacao(lat1, lon1, alt1);
        Localizacao local2 = new Localizacao(lat2, lon2, alt2);

        //Localizacao local2 = new Localizacao(lat2, -16.9, 0);
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
        Localizacao local1 = new Localizacao(lat1, lon1, alt1);
        Localizacao local2 = new Localizacao(lat2, lon2, alt2);

        //Localizacao local2 = new Localizacao(lat2, -16.9, 0);
        double expectedResult = Double.NaN;
        double result = local1.distanciaDuasLocalizacoes(local2);
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLatitude() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Localizacao local = new Localizacao(latitude, longitude, altitude);
        local.setmLatitude(-45.6109);
        double expectedResult = -45.6109;
        //act
        double result = local.getmLatitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLatitudeForaIntervalo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Localizacao local = new Localizacao(latitude, longitude, altitude);
        local.setmLatitude(-100.6109);
        double expectedResult = Double.NaN;
        //act
        double result = local.getmLatitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLongitude() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Localizacao local = new Localizacao(latitude, longitude, altitude);
        local.setmLongitude(-100.6109);
        double expectedResult = -100.6109;
        //act
        double result = local.getmLongitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }

    @Test
    void testarAlteracaoLongitudeForaIntervalo() {
        //arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Localizacao local = new Localizacao(latitude, longitude, altitude);
        local.setmLongitude(200.198);
        double expectedResult = Double.NaN;
        //act
        double result = local.getmLongitude();
        //assert
        assertEquals(expectedResult,result,0.001);
    }
    @Test
    void testarEqualsSameObjectTrue (){
        //Arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Localizacao local1 = new Localizacao(latitude, longitude, altitude);
        boolean expectedResult = true;
        //Act
        boolean result = local1.equals(local1);
        //Assert
        assertEquals(expectedResult, result);
    }
    @Test
    void testarEqualsTwoEqualObjects (){
        //Arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Localizacao local1 = new Localizacao(latitude, longitude, altitude);

        double latitude2 = 41.1496;
        double longitude2 = -8.6109;
        double altitude2 = 97;
        Localizacao local2 = new Localizacao(latitude2, longitude2, altitude2);

        boolean expectedResult = true;
        //Act
        boolean result = local1.equals(local2);
        //Assert
        assertEquals(expectedResult, result);
    }
    @Test
    void testarEqualsTwoDifferentObjects (){
        //Arrange
        double latitude = 41.1496;
        double longitude = -8.6109;
        double altitude = 97;
        Localizacao local1 = new Localizacao(latitude, longitude, altitude);

        double latitude2 = 41.1496;
        double longitude2 = -8.6109;
        double altitude2 = 100;
        Localizacao local2 = new Localizacao(latitude2, longitude2, altitude2);

        boolean expectedResult = false;
        //Act
        boolean result = local1.equals(local2);
        //Assert
        assertEquals(expectedResult, result);
    }
    @Test
    void testarEqualsTwoDifferentObjectsAgain (){
        //Arrange
        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Cidade");
        Localizacao local = new Localizacao(41.1496, -8.6109, 97);
        RetanguloArea area = new RetanguloArea(10, 4);
        AreaGeografica ag1 = new AreaGeografica(nomeAG, tipo, local, area);

        double latitude2 = 41.1496;
        double longitude2 = -8.6109;
        double altitude2 = 97;
        Localizacao local2 = new Localizacao(latitude2, longitude2, altitude2);

        boolean expectedResult = false;
        //Act
        boolean result = local2.equals(ag1);
        //Assert
        assertEquals(expectedResult, result);
    }

}
