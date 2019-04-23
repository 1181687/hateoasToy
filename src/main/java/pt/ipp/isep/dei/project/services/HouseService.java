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

import java.util.List;
import java.util.Objects;

@Service
public class HouseService {

    @Autowired
    private RoomAggregateService roomAggregateService;

    @Autowired
    private HouseGridAggregateService houseGridAggregateService;

    @Autowired
    private PowerSourceTypeService sourceTypeService;



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


    public boolean isGridRepositoryEmpty() {
        return this.houseGridAggregateService.isGridRepositoryEmpty();
    }

    public List<HouseGrid> getAllGrids() {
        return this.houseGridAggregateService.getAllGrids();
    }

    public List<Room> getRoomsOfAHouseGrid(HouseGridId houseGridId) {
        return roomAggregateService.getRoomsOfAHouseGrid(houseGridId);
    }

    public boolean detachRoomFromHouseGrid(RoomId roomId) {
        return roomAggregateService.detachRoomFromHouseGrid(roomId);
    }



    /**
     * Method that returns all the rooms in the house repo.
     *
     * @return List of Room.
     */
    public List<Room> getAllRooms() {
        return this.roomAggregateService.getAllRooms();
    }

    /**
     * Method that searches for a room by its Id. If it exists in the repo, the room is returned, if not, null is returned.
     *
     * @param id Id to be used.
     * @return Room or null.
     */
    public Room getRoomById(RoomId id) {
        return roomAggregateService.getRoomById(id);
    }

    /**
     * Method that stores a room in the db. If it already exists, it updates it.
     *
     * @param room Room to be stored.
     */
    public void updateRoomRepository(Room room) {
        roomAggregateService.updateRoom(room);
    }

    public List<PowerSourceType> getAllPowerSourceTypes() {
        return this.sourceTypeService.getAllPowerSourceTypes();
    }

    public boolean newPowerSource(String powerSourceId, String typeId, String gridId) {
        return this.houseGridAggregateService.newPowerSource(powerSourceId, typeId, gridId);
    }

    public boolean roomExists(String id) {
        RoomId roomId = new RoomId(id);
        return this.roomAggregateService.roomExists(roomId);
    }

    public boolean gridExists(String id) {
        HouseGridId gridId = new HouseGridId(id);
        return (Objects.nonNull(this.houseGridAggregateService.getGridById(gridId)));
    }

    public boolean addGrid(String id) {
        HouseGridId gridId = new HouseGridId(id);
        return this.houseGridAggregateService.createHouseGrid(gridId.getId());
    }

    public boolean createRoom(String roomId, String description, int houseFloor, double length, double width, double height) {
        RoomId roomId1 = new RoomId(roomId);
        return this.roomAggregateService.createRoom(roomId1, description, houseFloor, length, width, height);
    }

    /**
     * Calculates the nominal power of the selected housegrid.
     *
     * @return double
     */
    public double getGridNominalPower(String gridId) {
        return this.roomAggregateService.getGridNominalPower(new HouseGridId(gridId));
    }

    public boolean roomDeviceListIsEmpty(String id) {
        return this.roomAggregateService.roomDeviceListIsEmpty(new RoomId(id));
    }

    public double getRoomNominalPower(String id) {
        return this.roomAggregateService.getRoomNominalPower(new RoomId(id));
    }

    public String getDeviceListContentNameTypeLocationByGrid(String id) {
        return this.roomAggregateService.getDeviceListContentNameTypeLocationByGrid(id);
    }

    /**
     * Method that checks if the housegrid grid's list is empty.
     *
     * @return True or false.
     */
    public boolean isHouseGridListEmpty() {
        return this.houseGridAggregateService.isGridRepositoryEmpty();
    }

    /**
     * method that checks if there are no devices in the RoomList
     *
     * @return true if there aren't devices. False if there are devices
     */
    public boolean getDeviceListByRoomOfGridById(String houseGridId) {
        return this.roomAggregateService.getDeviceListByRoomOfGridById(houseGridId).isEmpty();
    }

    public String getGridNameById(String id) {
        HouseGridId houseGridId = new HouseGridId(id);
        return this.houseGridAggregateService.getGridById(houseGridId).getHouseGridId().getId();
    }

    public HouseGrid getGridById(String id) {
        HouseGridId houseGridId = new HouseGridId(id);
        return this.houseGridAggregateService.getGridById(houseGridId);
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
        Room room = getRoomSensorById(roomId);
        if(Objects.nonNull(room)){
            if(room.addSensorToListOfSensorsInRoom(sensor)){
                this.roomRepository.save(room);
                return true;
            }
        }
        return false;
    }*/

/*    public Room getRoomSensorById(RoomId roomId) {
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
