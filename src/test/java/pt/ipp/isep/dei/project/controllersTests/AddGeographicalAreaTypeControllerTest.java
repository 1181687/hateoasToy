package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.AddGeoAreaTypeController;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AddGeographicalAreaTypeControllerTest {

    @Mock
    private GeoAreaTypeService geoAreaTypeService;

    private AddGeoAreaTypeController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AddGeoAreaTypeController(geoAreaTypeService);
    }

    @Test
    public void createGeoAreaType_ShouldReturnTrue() {

        String geoAreaTypeId = "geoAreaTypeId";

        when(this.geoAreaTypeService.createGeoAreaType(geoAreaTypeId)).thenReturn(true);

        boolean result = controller.createGeoAreaType(geoAreaTypeId);

        assertTrue(result);
    }

    @Test
    public void createGeoAreaType_ShouldReturnFalse() {

        String geoAreaTypeId = "geoAreaTypeId";

        when(this.geoAreaTypeService.createGeoAreaType(geoAreaTypeId)).thenReturn(false);

        boolean result = controller.createGeoAreaType(geoAreaTypeId);

        assertFalse(result);
    }

}
