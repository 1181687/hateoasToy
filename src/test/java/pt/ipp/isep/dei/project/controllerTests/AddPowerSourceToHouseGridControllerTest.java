package pt.ipp.isep.dei.project.controllerTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.AddPowerSourceToHouseGridController;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeDTO;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeMapper;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;

public class AddPowerSourceToHouseGridControllerTest {

    @Mock
    private HouseService houseService;

    private AddPowerSourceToHouseGridController controller;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        this.controller = new AddPowerSourceToHouseGridController(houseService);
    }

    @Test
    public void isHouseGridRepositoryEmpty_WithEmptyRepository_ShouldReturnTrue() {

        when(this.houseService.isGridRepositoryEmpty()).thenReturn(true);

        assertTrue(controller.isHouseGridRepositoryEmpty());
    }

    @Test
    public void isHouseGridRepositoryEmpty_RepositoryIsNotEmpty_ShouldReturnFalse() {


        when(houseService.isGridRepositoryEmpty()).thenReturn(false);

        assertFalse(controller.isHouseGridRepositoryEmpty());
    }

    @Test
    public void newPowerSource_PowerSourceDoesntExistInRepository_ShouldReturnTrue(){

        //Arrange
        String gridId = "main";
        String id = "Power Source 1";
        String type = "Battery";
        when(houseService.newPowerSource(id,type,gridId)).thenReturn(true);

        //Act
        boolean result = controller.newPowerSource(id,type,gridId);

        //Assert
        assertTrue(result);
    }

    @Test
    public void newPowerSource_PowerSourceAlreadyExistInRepository_ShouldReturnFalse(){

        //Arrange
        String gridId = "main";

        String id = "Power Source 1";
        String type = "Battery";
        houseService.newPowerSource(id,type,gridId);
        when(houseService.newPowerSource(id,type,gridId)).thenReturn(false);

        //Act
        boolean result = controller.newPowerSource(id,type,gridId);

        //Assert
        assertFalse(result);
    }

    @Test
    public void getGridList_ShouldReturnAListOfDTOs() {
        HouseGridId id = new HouseGridId("main");
        HouseGrid grid = new HouseGrid(id);
        List<HouseGrid> list =new ArrayList<>();
        list.add(grid);

        when(houseService.getAllGrids()).thenReturn(list);

        List<HouseGridDTO> dtoList = new ArrayList<>();
        HouseGridDTO dto = HouseGridMapper.mapToDTO(grid);
        dtoList.add(dto);

        List<HouseGridDTO> result = controller.getGridList();

        assertEquals(dtoList.get(0).getId(),result.get(0).getId());
    }

    @Test
    public void getPowerSourceTypeListShouldReturnAListOfPowerSourceTypeDTOs(){
        //Arrange
        PowerSourceTypeId id = new PowerSourceTypeId("battery");
        PowerSourceType type = new PowerSourceType(id);
        List<PowerSourceType> listOfTypes = new ArrayList<>();
        listOfTypes.add(type);
        when(houseService.getAllPowerSourceTypes()).thenReturn(listOfTypes);
        List<PowerSourceTypeDTO> dtoList= new ArrayList<>();
        dtoList.add(PowerSourceTypeMapper.mapToDTO(type));
        //Act
        List<PowerSourceTypeDTO> result = controller.getPowerSourceTypeList();
        //Assert
        assertEquals(dtoList.get(0).getType(),result.get(0).getType());
    }

}
