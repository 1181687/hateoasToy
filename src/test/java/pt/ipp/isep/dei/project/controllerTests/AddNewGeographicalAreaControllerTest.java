package pt.ipp.isep.dei.project.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.AddNewGeographicalAreaController;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AddNewGeographicalAreaControllerTest {

    @Mock
    private GeoAreaService geoAreaService;

    private AddNewGeographicalAreaController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AddNewGeographicalAreaController(geoAreaService);
    }

    @Test
    public void isGeoAreaExistant_ShouldReturnTrue() {

        String geoAreaId;
        double latitude;
        double longitude;
        double elevation;
        String geoAreaTypeId;

        when(this.geoAreaService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId)).thenReturn(true);

        assertTrue(controller.isGeoAreaExistant());
    }


}
