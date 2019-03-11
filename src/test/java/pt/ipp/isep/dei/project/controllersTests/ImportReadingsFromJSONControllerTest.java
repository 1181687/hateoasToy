package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.importReadingsFromJSONController.ImportReadingsFromJSONController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ImportReadingsFromJSONControllerTest {

    @Test
    public void readGeoAreaJson() {
        GeographicalAreaList geoList = new GeographicalAreaList();
        ImportReadingsFromJSONController ctrl = new ImportReadingsFromJSONController(geoList);

        List<GeographicalAreaDTO> result = ctrl.readGeoAreaJson("JSONfile.json");

        String expectedResult = "cenas";

        assertEquals(expectedResult, result);
    }

    @Test
    public void Import() {
        GeographicalAreaList geoList = new GeographicalAreaList();
        ImportReadingsFromJSONController ctrl = new ImportReadingsFromJSONController(geoList);

        List<GeographicalAreaDTO> import1 = ctrl.readGeoAreaJson("JSONfile.json");

        boolean result = ctrl.importGeographicalArea(import1);

        assertTrue(result);
    }
}