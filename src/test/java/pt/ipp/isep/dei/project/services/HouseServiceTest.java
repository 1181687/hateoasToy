package pt.ipp.isep.dei.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project.repositories.HouseRepository;
import pt.ipp.isep.dei.project.repositories.RoomRepository;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class HouseServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HouseGridRepository houseGridRepository;

    @Mock
    private GeographicalAreaService geographicalAreaService;

    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private HouseService service;

    private static final String CONFIG_PROPERTIES = "Configuration.properties";


    @BeforeEach
    public void StartUp(){
        MockitoAnnotations.initMocks(this);

    }


    @Test
    void setAndGetAddress() {
        //Arrange
        GeoAreaTypeId typeId = new GeoAreaTypeId("City");
        GeographicalAreaType type = new GeographicalAreaType(typeId);
        Location location1 = new Location(25.6,42.3,74.8);
        AreaShape areaShape1 = new AreaShape(25,24);
        GeographicalArea geoArea1 = new GeographicalArea("Porto","Porto",type,location1,areaShape1);

        Location location2 = new Location(26.6,42.3,74.8);

        Address expectedResult = new Address("Rua do Marco",location2,geoArea1);

        //Act
        this.service.setAddress(expectedResult);
        Address result = this.service.getAddress();

        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void getLocation() {
        //Arrange
        GeoAreaTypeId typeId = new GeoAreaTypeId("City");
        GeographicalAreaType type = new GeographicalAreaType(typeId);
        Location location1 = new Location(25.6,42.3,74.8);
        AreaShape areaShape1 = new AreaShape(25,24);
        GeographicalArea geoArea1 = new GeographicalArea("Porto","Porto",type,location1,areaShape1);

        Location expectedResult = new Location(26.6,42.3,74.8);

        Address address = new Address("Rua do Marco",expectedResult,geoArea1);
        this.service.setAddress(address);

        //Act
        Location result = this.service.getLocation();

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void addRoomToHouseGrid_WhenGridExists_ShouldReturnTrue() {
        //Arrange
        Dimension dimension = new Dimension(2,3,2);
        Room room = new Room("Bathroom","Bathroom",1,dimension);
        HouseGridId gridId = new HouseGridId("main grid");

        when(houseGridRepository.existsById(gridId)).thenReturn(true);

        //Act
        boolean result = this.service.addRoomToHouseGrid(gridId,room);
        //Assert
        assertTrue(result);
    }

    @Test
    void addRoomToHouseGrid_WhenGridDoesntExists_ShouldReturnFalse() {
        //Arrange
        Dimension dimension = new Dimension(2,3,2);
        Room room = new Room("Bathroom","Bathroom",1,dimension);
        HouseGridId gridId = new HouseGridId("main grid");

        when(houseGridRepository.existsById(gridId)).thenReturn(false);

        //Act
        boolean result = this.service.addRoomToHouseGrid(gridId,room);
        //Assert
        assertFalse(result);
    }

    @Test
    void roomExists_ShouldReturnTrue() {
        //Arrange
        RoomId roomId = new RoomId("Bathroom");
        when(roomRepository.existsById(roomId)).thenReturn(true);

        //Act
        boolean result = this.service.roomExists(roomId);

        //Assert
        assertTrue(result);
    }

    @Test
    void roomExists_ShouldReturnFalse() {
        //Arrange
        RoomId roomId = new RoomId("Bathroom");
        when(roomRepository.existsById(roomId)).thenReturn(false);

        //Act
        boolean result = this.service.roomExists(roomId);

        //Assert
        assertFalse(result);
    }

    @Test
    void gridExists_ShouldReturnTrue() {
        //Arrange
        HouseGridId id = new HouseGridId("main grid");
        when(houseGridRepository.existsById(id)).thenReturn(true);
        //Act
        boolean result = this.service.gridExists(id);
        //Assert
        assertTrue(result);
    }

    @Test
    void gridExists_ShouldReturnFalse() {
        //Arrange
        HouseGridId id = new HouseGridId("main grid");
        when(houseGridRepository.existsById(id)).thenReturn(false);
        //Act
        boolean result = this.service.gridExists(id);
        //Assert
        assertFalse(result);
    }

    @Test
    void getHouse_whenHouseRepoHasAHouse_ShouldReturnTheHouse() {
        //Arrange
        GeoAreaTypeId typeId = new GeoAreaTypeId("City");
        GeographicalAreaType type = new GeographicalAreaType(typeId);
        Location location1 = new Location(25.6,42.3,74.8);
        AreaShape areaShape1 = new AreaShape(25,24);
        GeographicalArea geoArea1 = new GeographicalArea("Porto","Porto",type,location1,areaShape1);

        Location location2 = new Location(26.6,42.3,74.8);

        Address address = new Address("Rua do Marco",location2,geoArea1);

        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);

        List<House> houseList = Collections.singletonList(house);
        when(houseRepository.count()).thenReturn(1L);
        when(houseRepository.findAll()).thenReturn(houseList);

        //Act
        House result = this.service.getHouse();

        //Assert
        assertEquals(house,result);
    }

    @Test
    public void getHouse_whenHouseIsEmpty_ShouldReturnNull() {

        when(houseRepository.count()).thenReturn(0L);
        House result = this.service.getHouse();
        assertEquals(null,result);
    }

    @Test
    void saveHouse() {
        GeoAreaTypeId typeId = new GeoAreaTypeId("City");
        GeographicalAreaType type = new GeographicalAreaType(typeId);
        Location location1 = new Location(25.6,42.3,74.8);
        AreaShape areaShape1 = new AreaShape(25,24);
        GeographicalArea geoArea1 = new GeographicalArea("Porto","Porto",type,location1,areaShape1);

        Location location2 = new Location(26.6,42.3,74.8);

        Address address = new Address("Rua do Marco",location2,geoArea1);

        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);

        when(houseRepository.save(house)).thenReturn(house);
        when(houseRepository.count()).thenReturn(1L);
        List<House> houseList = Collections.singletonList(house);

        when(houseRepository.findAll()).thenReturn(houseList);

        this.service.saveHouse(house);
        House result = this.service.getHouse();

        assertEquals(house,result);
    }

    @Test
    public void updateHouseWithRoomsAndGrids_WithNullLocationAndParentGeoArea(){

        GeoAreaTypeId typeId = new GeoAreaTypeId("City");
        GeographicalAreaType type = new GeographicalAreaType(typeId);
        Location location1 = new Location(25.6,42.3,74.8);
        AreaShape areaShape1 = new AreaShape(25,24);
        GeographicalArea geoArea1 = new GeographicalArea("Porto","Porto",type,location1,areaShape1);

        Location location2 = new Location(26.6,42.3,74.8);

        Address address = new Address("Rua do Marco",location2,geoArea1);

        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);

        Dimension dimension = new Dimension(2,3,2);
        Room room = new Room("Bathroom","Bathroom",1,dimension);
        List<RoomDTO> roomDTOS = Arrays.asList(RoomMapper.mapToDTO(room));

        HouseGridId gridId = new HouseGridId("main grid");
        HouseGrid grid = new HouseGrid(gridId);
        grid.addRoom(room);

        HouseGridDTO gridDTO = new HouseGridDTO();
        gridDTO.setName(gridId.getHousegridId());
        gridDTO.addRoomDTO(RoomMapper.mapToDTO(room));
        List<HouseGridDTO> gridDTOS = Arrays.asList(gridDTO);

        AddressDTO addressDTO = AddressMapper.newAddressDTO();
        addressDTO.setCompleteAddress("Rua do Marco");

        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setAddressDTO(addressDTO);
        houseDTO.setRoomDTOList(roomDTOS);
        houseDTO.setHouseGridDTOList(gridDTOS);

        doNothing().when(houseRepository).deleteAll();
        when(houseRepository.save(house)).thenReturn(house);
        when(roomRepository.save(room)).thenReturn(room);
        when(houseGridRepository.save(grid)).thenReturn(grid);

        List<House> houseList = Collections.singletonList(house);
        when(houseRepository.count()).thenReturn(1L);
        when(houseRepository.findAll()).thenReturn(houseList);

        this.service.updateHouseWithRoomsAndGrids(houseDTO);

        House result = this.service.getHouse();

        assertEquals(house,result);

    }

    @Test
    public void updateHouseWithRoomsAndGrids(){

        GeoAreaTypeId typeId = new GeoAreaTypeId("City");
        GeographicalAreaType type = new GeographicalAreaType(typeId);
        Location location1 = new Location(25.6,42.3,74.8);
        AreaShape areaShape1 = new AreaShape(25,24);
        GeographicalArea geoArea1 = new GeographicalArea("Porto","Porto",type,location1,areaShape1);

        Location location2 = new Location(26.6,42.3,74.8);

        Address address = new Address("Rua do Marco",location2,geoArea1);

        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);

        Dimension dimension = new Dimension(2,3,2);
        Room room = new Room("Bathroom","Bathroom",1,dimension);
        List<RoomDTO> roomDTOS = Arrays.asList(RoomMapper.mapToDTO(room));

        HouseGridId gridId = new HouseGridId("main grid");
        HouseGrid grid = new HouseGrid(gridId);
        grid.addRoom(room);

        HouseGridDTO gridDTO = new HouseGridDTO();
        gridDTO.setName(gridId.getHousegridId());
        gridDTO.addRoomDTO(RoomMapper.mapToDTO(room));
        List<HouseGridDTO> gridDTOS = Arrays.asList(gridDTO);

        AddressDTO addressDTO = AddressMapper.mapToDTO(address);

        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setAddressDTO(addressDTO);
        houseDTO.setRoomDTOList(roomDTOS);
        houseDTO.setHouseGridDTOList(gridDTOS);

        doNothing().when(houseRepository).deleteAll();
        when(houseRepository.save(house)).thenReturn(house);
        when(roomRepository.save(room)).thenReturn(room);
        when(houseGridRepository.save(grid)).thenReturn(grid);

        List<House> houseList = Collections.singletonList(house);
        when(houseRepository.count()).thenReturn(1L);
        when(houseRepository.findAll()).thenReturn(houseList);

        this.service.updateHouseWithRoomsAndGrids(houseDTO);

        House result = this.service.getHouse();

        assertEquals(house,result);

    }
}