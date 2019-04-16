package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HouseGridService houseGridService;

    @Autowired
    private PowerSourceTypeService sourceTypeService;

    public void configureHouseLocation(LocationDTO locationDTO) {


    }

    public boolean isGridRepositoryEmpty() {
        return this.houseGridService.isGridRepositoryEmpty();
    }

    public List<HouseGrid> getAllGrids() {
        return this.houseGridService.getAllGrids();
    }

    /**
     * Method that searches for a grid by its Id. If it exists in the repo, the grid is returned, if not, null is returned.
     *
     * @param id Id to be used.
     * @return HouseGrid or null.
     */
    public HouseGrid getGridById(HouseGridId id) {
        return houseGridService.getGridById(id);
    }

    /**
     * Method that returns all the rooms in the house repo.
     *
     * @return List of Room.
     */
    public List<Room> getAllRooms() {
        return this.roomService.getAllRooms();
    }

    /**
     * Method that searches for a room by its Id. If it exists in the repo, the room is returned, if not, null is returned.
     *
     * @param id Id to be used.
     * @return Room or null.
     */
    public Room getRoomById(RoomId id) {
        return roomService.getRoomById(id);
    }

    /**
     * Method that stores a room in the db. If it already exists, it updates it.
     *
     * @param room Room to be stored.
     */
    public void updateRoomRepository(Room room) {
        roomService.updateRoomRepository(room);
    }

    public List<PowerSourceType> getAllPowerSourceTypes() {
        return this.sourceTypeService.getAllPowerSourceTypes();
    }

    public boolean newPowerSource(String powerSourceId, String typeId, String gridId) {
        return this.houseGridService.newPowerSource(powerSourceId, typeId, gridId);
    }

  /*  public void updateHouseWithRoomsAndGrids(HouseDTO houseDTO, House house) {
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
                addRoomToHouseGrid(houseGrid.getHouseGridId(), room);
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
*/
  /* public boolean addSensorToRoom(RoomSensor sensor, RoomId roomId){
        Room room = getRoomById(roomId);
        if(Objects.nonNull(room)){
            if(room.addSensorToListOfSensorsInRoom(sensor)){
                this.roomRepository.save(room);
                return true;
            }
        }
        return false;
    }*/

/*    public Room getRoomById(RoomId roomId) {
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


   public RoomSensorService getAllSensors() {


   /* public RoomSensorService getAllSensors() {
        RoomSensorService roomSensorList = new RoomSensorService();
        for (Room room : roomRepository.findAll()) {
            roomSensorList.getListOfSensors().addAll(room.getSensorList().getListOfSensors());
        }
        return roomSensorList;
    }*/

}
