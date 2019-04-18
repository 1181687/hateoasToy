package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.services.HouseGridService;
import pt.ipp.isep.dei.project.services.PowerSourceTypeService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HouseGridService houseGridService;

    @Autowired
    private PowerSourceTypeService sourceTypeService;

    @Autowired
    private SensorTypeService sensorTypeService;

    private Address address;



    public void configureHouseLocation(LocationDTO locationDTO) {


    }

    public Address getAddress(){
        return this.address;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public Location getLocation(){
        return this.address.getLocation();
    }

    public GeographicalArea getInsertedGeoArea(){
        return this.address.getInsertedGeoArea();
    }

    /**
     * method that get a list of sensor types
     *
     * @return a list of sensor types.
     */
    public List<SensorType> getSensorTypeList() {
        return sensorTypeService.getSensorTypeList();
    }

    public boolean isGridRepositoryEmpty() {
        return this.houseGridService.isGridRepositoryEmpty();
    }

    public List<HouseGrid> getAllGrids() {
        return this.houseGridService.getAllGrids();
    }

    public List<Room> getRoomsOfAHouseGrid(HouseGridId houseGridId){
        return roomService.getRoomsOfAHouseGrid(houseGridId);
    }

    public boolean detachRoomFromHouseGrid(RoomId roomId){
        return roomService.detachRoomFromHouseGrid(roomId);
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

    public boolean roomExists(String id){
        return this.roomService.isNameExistant(id);
    }

    public boolean gridExists(String id){
        return this.houseGridService.gridExists(id);
    }

    public boolean addGrid(String id){
        HouseGridId gridId = new HouseGridId(id);
        return this.houseGridService.createHouseGrid(gridId.getId());
    }

    public boolean addRoom(Room room){
       return this.roomService.addRoom(room);
    }
/*
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
