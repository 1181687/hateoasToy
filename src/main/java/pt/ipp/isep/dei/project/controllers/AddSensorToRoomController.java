package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.ArrayList;
import java.util.List;

public class AddSensorToRoomController {

    private HouseService houseService;

    public AddSensorToRoomController(HouseService houseService) {
        this.houseService = houseService;
    }

    public List<RoomDTO> getRoomDTOList() {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : houseService.getAllRooms()) {
            roomDTOList.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOList;
    }

    /**
     * This method display the rooms of the housegrid
     * @return the RoomList.
     */
    /*public String getRoomListContent() {
        return this.roomList.getRoomListContent();
    }

    /**
     * This method give de position of the room in the rooms list.
     * @param position of the room in the list of rooms.
     */
    /*public void getRoomByIndex (int position) {
        selectedRoom = this.roomList.getRoomFromPosition(position);
    }

    /**
     * This method display the list of sensors type in the sensors type list.
     * @return the sensor type list.
     */
    /*public String displayListOfSensorsType () {
        return this.sensorTypeList.getSensorTypeListToString();
    }*/

/**
 * This method get de position of the sensor type in the list of sensors type.
 *
 * @param position of sensors type in the sensors type list.
 * <p>
 * This method create and add a sensor to the list of sensors in the room.
 * @param name of the new sensor.
 * @return a new sensor added in the list of sensors in the room.
 * <p>
 * Method that checks if a room isn't already in the list of rooms.
 * @return true or false.
 * <p>
 * This method create and add a sensor to the list of sensors in the room.
 * @param name of the new sensor.
 * @return a new sensor added in the list of sensors in the room.
 * <p>
 * Method that checks if a room isn't already in the list of rooms.
 * @return true or false.
 */
    /*public void getSensorTypeByIndex (int position) {
        sensorType = this.sensorTypeList.getSensorTypeByPosition(position);
    }

    /**
     * This method get the location of the housegrid.
     */
    /*public void getLocationOfTheHouse () {
        locationOfTheHouse = house.getLocation();
    }

    /*    *//**
 * This method create and add a sensor to the list of sensors in the room.
 * @param name of the new sensor.
 * @return a new sensor added in the list of sensors in the room.
 *//*
    public boolean createAndAddSensorToTheList(String id, String name, String units) {
        GeoAreaSensor newSensor = selectedRoom.getSensorList().newSensor(id, name, sensorType, locationOfTheHouse, units);
        return selectedRoom.addSensorToListOfSensorsInRoom(newSensor);
    }*/

/**
 * Method that checks if a room isn't already in the list of rooms.
 * @return true or false.
 */
    /*public boolean isRoomListEmpty() {
        return roomList.isEmpty();
    }

    /**
     * This method check if the list of Sensors Type is empty.
     * @return true or false.
     */
    /*public boolean isSensorTypeListEmpty() {
        return sensorTypeList.isEmpty();
    }*/
}
