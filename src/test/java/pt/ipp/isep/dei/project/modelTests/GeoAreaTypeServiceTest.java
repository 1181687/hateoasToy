package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.repositories.GeoAreaTypeRepository;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GeoAreaTypeServiceTest {

    @Mock
    private GeoAreaTypeRepository geoAreaTypeRepository;
    @InjectMocks
    private GeoAreaTypeService service;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getListOfGeoAreaTypes() {
        //Arrange
        GeoAreaTypeId id1 = new GeoAreaTypeId("City");
        GeographicalAreaType geoAreaType1 = new GeographicalAreaType(id1);

        GeoAreaTypeId id2 = new GeoAreaTypeId("Forest");
        GeographicalAreaType geoAreaType2 = new GeographicalAreaType(id2);

        List<GeographicalAreaType> expectedResult = Arrays.asList(geoAreaType1, geoAreaType2);

        when(this.geoAreaTypeRepository.findAll()).thenReturn(expectedResult);

        //Act
        List<GeographicalAreaType> result = this.service.getListOfGeoAreaTypes();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListOfGeoAreaTypesToString() {
        //Arrange
        GeoAreaTypeId id1 = new GeoAreaTypeId("City");
        GeographicalAreaType geoAreaType1 = new GeographicalAreaType(id1);

        GeoAreaTypeId id2 = new GeoAreaTypeId("Forest");
        GeographicalAreaType geoAreaType2 = new GeographicalAreaType(id2);

        List<GeographicalAreaType> types = Arrays.asList(geoAreaType1, geoAreaType2);

        when(this.geoAreaTypeRepository.findAll()).thenReturn(types);

        List<String> expectedResult = Arrays.asList(id1.getTypeId(), id2.getTypeId());

        //Act
        List<String> result = this.service.getListOfGeoAreaTypesToString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void newTypeOfGeoArea() {
        //Arrange
        String typeName = "City";
        GeoAreaTypeId id1 = new GeoAreaTypeId(typeName);
        GeographicalAreaType expectedResult = new GeographicalAreaType(id1);
        //Act
        GeographicalAreaType result = this.service.newTypeOfGeoArea(typeName);
        //Assert
        assertEquals(expectedResult, result);
    }

}
