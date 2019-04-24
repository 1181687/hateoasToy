package pt.ipp.isep.dei.project.controllerTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.HouseGridAggregateRepository;
import pt.ipp.isep.dei.project.HouseGridRepository;
import pt.ipp.isep.dei.project.controllers.AddPowerSourceToHouseGridController;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.services.HouseGridAggregateService;
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
    public void isHouseGridRepositoryEmpty_ShouldReturnTrue() {

        when(this.houseService.isGridRepositoryEmpty()).thenReturn(true);

        assertTrue(controller.isHouseGridRepositoryEmpty());
    }

    @Test
    public void isHouseGridRepositoryEmpty_ShouldReturnFalse() {

        houseService.addGrid("main");

        when(houseService.isGridRepositoryEmpty()).thenReturn(false);

        assertFalse(controller.isHouseGridRepositoryEmpty());
    }

/*    @Test
    public void getGridList_ShouldReturnAListOfDTOs() {
        houseService.addGrid("main");
        HouseGridId id = new HouseGridId("main");
        HouseGrid grid = new HouseGrid(id);
        List<HouseGrid> list =new ArrayList<>();
        list.add(grid);
        when(houseService.getAllGrids()).thenReturn(list);

        List<HouseGridDTO> dtoList = new ArrayList<>();
        HouseGridDTO dto = HouseGridMapper.mapToDTO(grid);
        dtoList.add(dto);

        List<HouseGridDTO> result = controller.getGridList();

        assertEquals(dtoList,result);
    }*/

}
