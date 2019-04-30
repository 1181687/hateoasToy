package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.ConfigureHouseLocationController;
import pt.ipp.isep.dei.project.services.HouseService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ConfigureHouseLocationControllerTest {


    @Mock
    private HouseService houseService;

    private ConfigureHouseLocationController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ConfigureHouseLocationController(houseService);
    }

    @Test
    public void isGeoAreaRepositoryEmpty_ShouldReturnTrue() {

        when(this.houseService.isGeoAreaRepositoryEmpty()).thenReturn(true);

        boolean result = controller.isGeoAreaRepositoryEmpty();

        assertTrue(result);
    }

    @Test
    public void isGeoAreaRepositoryEmpty_ShouldReturnFalse() {

        when(this.houseService.isGeoAreaRepositoryEmpty()).thenReturn(false);

        boolean result = controller.isGeoAreaRepositoryEmpty();

        assertFalse(result);
    }

}
