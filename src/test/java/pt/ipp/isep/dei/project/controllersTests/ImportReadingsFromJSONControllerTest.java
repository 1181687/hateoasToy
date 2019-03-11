package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.importReadingsFromJSONController.ImportReadingsFromJSONController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ImportReadingsFromJSONControllerTest {

    @Test
    public void readGeoAreaJson() {
        ImportReadingsFromJSONController ctrl = new ImportReadingsFromJSONController();

        List<GeographicalAreaDTO> result = ctrl.readGeoAreaJson("JSONfile.json");

        String expectedResult = "cenas";

        assertEquals(expectedResult, result);
    }
}