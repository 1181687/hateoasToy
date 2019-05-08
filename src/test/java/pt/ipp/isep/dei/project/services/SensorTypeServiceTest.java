package pt.ipp.isep.dei.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.repositories.SensorTypeRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class SensorTypeServiceTest {

    @Mock
    private SensorTypeRepository sensorTypeRepository;

    @InjectMocks
    private SensorTypeService service;

    @BeforeEach
    public void StartUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSensorTypeList() {
        //Arrange
        SensorTypeId id1 = new SensorTypeId("Temperature");
        SensorType type1 = new SensorType(id1);

        SensorTypeId id2 = new SensorTypeId("Rainfall");
        SensorType type2 = new SensorType(id2);

        List<SensorType> expectedResult = Arrays.asList(type1,type2);

        when(sensorTypeRepository.findAll()).thenReturn(expectedResult);

        //Act
        List<SensorType> result = this.service.getSensorTypeList();

        assertEquals(expectedResult,result);

    }

    @Test
    public void addType_TypeDoesntExistInRepo_ShouldReturnTrue() {
        //Arrange
        SensorTypeId id1 = new SensorTypeId("Temperature");

        when(sensorTypeRepository.existsById(id1)).thenReturn(false);

        //Act
        boolean result = this.service.addType(id1);

        //Assert
        assertTrue(result);
    }

    @Test
    public void addType_TypeExistsInRepo_ShouldReturnFalse() {
        //Arrange
        SensorTypeId id1 = new SensorTypeId("Temperature");

        when(sensorTypeRepository.existsById(id1)).thenReturn(false);

        //Act
        boolean result = this.service.addType(id1);

        //Assert
        assertTrue(result);
    }

}