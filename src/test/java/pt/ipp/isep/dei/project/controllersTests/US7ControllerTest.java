package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US7Controller;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class US7ControllerTest {

    @Test
    void testeConteudoListaController() {

        ListaAG listaDeAGs = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        ag2.setmAreaInseridaEm(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        US7Controller ctrl = new US7Controller(listaDeAGs);

        String expectResult = "1 - Nome: Porto, Tipo: Cidade, Latitude: 41.1496, Longitude: -8.6109\n2 - Nome: Rua do Bonfim, Tipo: Rua, Latitude: 41.1496, Longitude: -8.6109, Inserido Em: Cidade Porto\n";

        //Act
        String result = ctrl.getConteudoLista(true);

        //Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testarGetAreaGeograficaNaListaApresentada() {
        //Arrange
        ListaAG lista = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        lista.adicionarAreaGeoALista(ag1);
        lista.adicionarAreaGeoALista(ag2);

        US7Controller ctrl = new US7Controller(lista);

        int opçãoSeleccionada =1;

        GeographicArea expectedResult = ag2;

        //Act
        GeographicArea resultado = ctrl.getAGNaListaApresentada(opçãoSeleccionada);

        //Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoNAOTem () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicArea ag2 = new GeographicArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicArea ag3 = new GeographicArea(nomeAG3, tipo3, local3, area3);

        ag2.setmAreaInseridaEm(ag1);

        US7Controller ctrl = new US7Controller(listaAG);

        boolean expectedResult = true;

        //Act

        boolean result = ctrl.verSeAGTemAreaInseridaVazia(ag3);

        //Assert

        assertEquals(expectedResult, result);

    }

    @Test
    public void testarSeAGNaoTemAreaInseridaQuandoTem () {
        //Arrange
        ListaAG listaAG = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG = "Porto";
        TipoAreaGeo tipo = new TipoAreaGeo("Distrito");
        Location local = new Location(41.1496, -8.6109, 97);
        RectangleArea area = new RectangleArea(10, 10, local);
        GeographicArea ag2 = new GeographicArea(nomeAG, tipo, local, area);

        String nomeAG3 = "Sul";
        TipoAreaGeo tipo3 = new TipoAreaGeo("Região");
        Location local3 = new Location(41.1496, -8.6109, 97);
        RectangleArea area3 = new RectangleArea(10, 10, local);
        GeographicArea ag3 = new GeographicArea(nomeAG3, tipo3, local3, area3);

        ag2.setmAreaInseridaEm(ag1);

        US7Controller ctrl = new US7Controller(listaAG);

        boolean expectedResult = false;

        //Act

        boolean result = ctrl.verSeAGTemAreaInseridaVazia(ag2);

        //Assert

        assertEquals(expectedResult, result);
    }


    @Test
    public void testaAdicionarAreaGeoAListaNumaPosicaoEspecifica() {
        //Arrange
        ListaAG listaDeAGs = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.adicionarAreaGeoALista(ag1);

        US7Controller ctrl = new US7Controller(listaDeAGs);

        ctrl.adicionarAGListaPosicaoEspecifica(0,ag2);

        GeographicArea expectedResult = ag2;

        //Act
        GeographicArea result = listaDeAGs.getmListaAG().get(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarRemoverUmaAreaGeoAListaDeAreasGeo() {
        //Arrange
        ListaAG listaDeAGs = new ListaAG();

        String nomeAG1 = "Porto";
        TipoAreaGeo tipo1 = new TipoAreaGeo("Cidade");
        Location local1 = new Location(41.1496, -8.6109, 97);
        RectangleArea area1 = new RectangleArea(10, 10, local1);
        GeographicArea ag1 = new GeographicArea(nomeAG1, tipo1, local1, area1);

        String nomeAG2 = "Rua do Bonfim";
        TipoAreaGeo tipo2 = new TipoAreaGeo("Rua");
        Location local2 = new Location(41.1496, -8.6109, 97);
        RectangleArea area2 = new RectangleArea(10, 10, local1);
        GeographicArea ag2 = new GeographicArea(nomeAG2, tipo2, local2, area2);

        listaDeAGs.adicionarAreaGeoALista(ag1);
        listaDeAGs.adicionarAreaGeoALista(ag2);

        US7Controller ctrl = new US7Controller(listaDeAGs);

        ctrl.removerAGLista(ag1);

        GeographicArea expectedResult = ag2;

        //Act
        GeographicArea result = listaDeAGs.getmListaAG().get(0);

        //Assert
        assertEquals(expectedResult, result);
    }
}