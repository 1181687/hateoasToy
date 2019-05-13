package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.configurehouseinformationfromjsoncontroller.ConfigureHouseInformationFromJsonController;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.services.HouseService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigureHouseInformationFromJsonControllerTest {

    @Mock
    private HouseService houseService;

    private ConfigureHouseInformationFromJsonController controller;

    private ProjectFileReader reader;

    @BeforeEach
    public void StartUp() {

        MockitoAnnotations.initMocks(this);
        this.controller = new ConfigureHouseInformationFromJsonController(houseService);
    }

    @Test
    void getNumberOfNotImportedRooms() throws FileNotFoundException {
        // Arrange
        String path = "datasets/geoAreas/json/JSONfile.json";
        File file = new File(path);

        List<Object> resultJSON = reader.readFile(file);

        Object isepObj = resultJSON.get(0);


        int expectedResult = 15;

        // Act
        int result = controller.getNumberOfNotImportedRooms();

        // Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void getNumberOfNotImportedGrids() {


    }

    @Test
    void createReader() {


    }

    @Test
    void readFile() {


    }

    @Test
    void importHouseInformation() {


    }
}