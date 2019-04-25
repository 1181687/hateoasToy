package pt.ipp.isep.dei.project.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.AddNewGeographicalAreaTypeController;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AddNewGeographicalAreaTypeControllerTest {

    @Mock
    private GeoAreaTypeService geoAreaTypeService;

    private AddNewGeographicalAreaTypeController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AddNewGeographicalAreaTypeController(geoAreaTypeService);
    }

    @Test
    public void createGeoAreaType_ShouldReturnTrue() {

        String geoAreaTypeId = "geoAreaTypeId";

        when(this.geoAreaTypeService.createGeoAreaType(geoAreaTypeId)).thenReturn(true);

        assertTrue(controller.createGeoAreaType(geoAreaTypeId));
    }

    @Test
    public void createGeoAreaType_ShouldReturnFalse() {

        String geoAreaTypeId = "geoAreaTypeId";

        when(this.geoAreaTypeService.createGeoAreaType(geoAreaTypeId)).thenReturn(false);

        assertFalse(controller.createGeoAreaType(geoAreaTypeId));
    }


}
