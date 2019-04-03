package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.controllers.GetListOfTypeOfGeoAreaController;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
@SpringJUnitConfig(GetListOfTypeOfGeoAreaControllerTest.Config.class)
public class GetListOfTypeOfGeoAreaControllerTest {

    @Autowired
    private GeographicalAreaList geographicalAreaList;
    private GeographicalAreaTypeList geographicalAreaTypeList;
    private GeographicalArea ag;
    private GeographicalAreaType type;
    private GetListOfTypeOfGeoAreaController controller;

    @BeforeEach
    public void StartUp() {
        //Geographical Area & Geographical Area Type
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        this.type = new GeographicalAreaType("Urban area");
        this.ag = new GeographicalArea("ISEP", "Campus do ISEP", type, location, areaShape);

        // Geo Area Type List & Geo Area List
        this.geographicalAreaTypeList = new GeographicalAreaTypeList();
        this.controller = new GetListOfTypeOfGeoAreaController(geographicalAreaList, geographicalAreaTypeList);
    }

    @Configuration
    static class Config {
    }

    @Test
    public void testarGetListaAGPorTipo() {
        //Arrange
        String nameType = "Urban area";
        this.geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type);
        this.geographicalAreaList.addGeoArea(ag);

        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("Campus do ISEP"));

        //Act
        List<String> result = controller.getListaAGPorTipo(nameType);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarGetListaDosTiposDeAG() {
        //Arrange
        geographicalAreaTypeList.addTypeOfGeoAreaToTheList(type);
        List<String> expectedResult = Arrays.asList("Urban area");

        //Act
        List<String> result = controller.getListaDosTiposDeAG();

        //Assert
        assertEquals(expectedResult, result);
    }
}
