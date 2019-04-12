package pt.ipp.isep.dei.project.model.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.HouseGridRepository;
import pt.ipp.isep.dei.project.RoomRepository;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorList;

import java.util.Objects;

@Service
public class HouseService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HouseGridRepository houseGridRepository;

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
            house.addRoom(room);
            roomRepository.save(room);
        }
        for (HouseGridDTO houseGridDTO : houseDTO.getHouseGridDTOList()) {
            HouseGrid houseGrid = HouseGridMapper.mapToEntity(houseGridDTO);
            house.addGrid(houseGrid);
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

    public boolean addSensorToRoom(RoomSensor sensor, RoomId roomId){
        Room room = getRoomById(roomId);
        if(Objects.nonNull(room)){
            if(room.addSensorToListOfSensorsInRoom(sensor)){
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

    public void updateRepository(Room room) {
        roomRepository.save(room);
    }

    public RoomSensorList getAllSensors() {
        RoomSensorList roomSensorList = new RoomSensorList();
        for (Room room : roomRepository.findAll()) {
            roomSensorList.getListOfSensors().addAll(room.getSensorList().getListOfSensors());
        }
        return roomSensorList;
    }

}
