package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorList;
import pt.ipp.isep.dei.project.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project.repositories.HouseRepository;
import pt.ipp.isep.dei.project.repositories.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HouseService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HouseGridRepository houseGridRepository;

    @Autowired
    private GeographicalAreaService geographicalAreaService;

    @Autowired
    private HouseRepository houseRepository;

    private Address address;


    public boolean isGeoAreaRepositoryEmpty() {
        return this.geographicalAreaService.isGeoAreaRepositoryEmpty();
    }

    public List<GeographicalArea> getAllGeoAreas() {
        return this.geographicalAreaService.getAllGeoAreas();
    }

    public Address getAddress() {
        return this.address;
    }

    //method also used in house conf
    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return this.address.getLocation();
    }

    public void updateHouseWithRoomsAndGrids(HouseDTO houseDTO, House house) {
        Address houseAddress = AddressMapper.mapToEntity(houseDTO.getAddressDTO());
        if (Objects.isNull(houseAddress.getLocation())) {
            houseAddress.setLocation(house.getLocation());
        }
        if (Objects.isNull(houseAddress.getInsertedGeoArea())) {
            houseAddress.setInsertedGeoArea(house.getInsertedGeoArea());
        }
        house.setAddress(houseAddress);

        for (RoomDTO roomDTO : houseDTO.getRoomDTOList()) {
            Room room = RoomMapper.mapToEntity(roomDTO);
            roomRepository.save(room);
        }
        for (HouseGridDTO houseGridDTO : houseDTO.getHouseGridDTOList()) {
            HouseGrid houseGrid = HouseGridMapper.mapToEntity(houseGridDTO);
            houseGridRepository.save(houseGrid);
            for (Room room : houseGrid.getRoomList().getListOfRooms()) {
                addRoomToHouseGrid(houseGrid.getHouseGridName(), room);
            }

        }

    }

    public boolean addRoomToHouseGrid(HouseGridId houseGridId, Room room) {
        if (houseGridRepository.existsById(houseGridId)) {
            room.setHouseGridId(houseGridId);
            roomRepository.save(room);
            return true;
        }
        return false;

    }

    public boolean addSensorToRoom(RoomSensor sensor, RoomId roomId) {
        Room room = getRoomById(roomId);
        if (Objects.nonNull(room)) {
            if (room.addSensorToListOfSensorsInRoom(sensor)) {
                this.roomRepository.save(room);
                return true;
            }
        }
        return false;
    }

    public Room getRoomById(RoomId roomId) {
        if (this.roomRepository.findById(roomId).isPresent()) {
            return this.roomRepository.findById(roomId).get();
        }
        return null;
    }

    public boolean roomExists(RoomId roomId) {
        return roomRepository.existsById(roomId);
    }

    public boolean gridExists(HouseGridId gridId) {
        return houseGridRepository.existsById(gridId);
    }

    /*
        public Room getRoomWithRightSensor(String sensorId) {
            for (Room room : roomRepository.findAll()) {
                if (Objects.nonNull(room.getSensorById(sensorId))) {
                    return room;
                }
            }
            return null;
        }

        public RoomSensor getSensorById(String sensorId) {
            if (Objects.nonNull(getRoomWithRightSensor(sensorId))) {
                return getRoomWithRightSensor(sensorId).getSensorById(sensorId);
            }
            return null;
        }
    */
    public void updateRepository(List<Room> roomList) {
        roomRepository.saveAll(roomList);
    }

    public RoomSensorList getAllSensors() {
        RoomSensorList roomSensorList = new RoomSensorList();
        for (Room room : roomRepository.findAll()) {
            roomSensorList.getListOfSensors().addAll(room.getSensorList().getListOfSensors());
        }
        return roomSensorList;
    }

    public House getHouse(){
        List<House> houseList = new ArrayList<>();
        for (House house : houseRepository.findAll()) {
            houseList.add(house);
        }
        return houseList.get(0);
    }

    public void saveHouse(House house) {
        houseRepository.save(house);
    }
}
