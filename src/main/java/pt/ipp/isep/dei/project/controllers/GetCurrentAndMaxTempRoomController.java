package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.services.RoomSensorService;

public class GetCurrentAndMaxTempRoomController {

    private RoomSensorService roomSensorService;
    private SensorType sensorType;

    /**
     * constructor that receives a RoomSensorService and a sensor type
     *
     * @param roomSensorService
     * @param type
     */
    public GetCurrentAndMaxTempRoomController(RoomSensorService roomSensorService, SensorType type) {
        this.roomSensorService = roomSensorService;
        this.sensorType = type;
    }

    public SensorType getType() {
        return sensorType;
    }

    /**
     * receives a Room name, and gets the latest Reading according to the sensorType
     * passed on the constructor
     *
     * @return latest measurement
     */
/*
    public RoomReading getLatestMeasurementByRoomName(RoomId roomId) {
        return this.roomSensorService.
                .getLatestMeasurementBySensorType(roomId, sensorType);
    }


    /**
     * gets a formatted string with information from the rooms that are available
     *
     * @return a String with room's name, housegrid floor, height, length and width:
     */
/*
    public String getRoomListContent() {
        return this.house.getRoomListContent();
    }

    /**
     * gets the lenght of roomList
     *
     * @return lenght of roomList
     */

/*
    public int getRoomListSize() {
        return this.house.getRoomListSize();
    }
/*
    /**
     * gets the room name, by a specific position
     *
     * @param position integer position number of the room
     * @return a String with the name of room
     */

/*
    public String getRoomNameByPos(int position) {
        return this.house.getRoomNameByPosition(position);
    }

    /**
     * @param name receives the string name of the room
     * @param type receives the type of sensor (temperature)
     * @param date receives a given day
     * @return the maximum temperature of that room in the choosen day
     */

/*
    public double getMaximumTemperatureOfRoomInGivenDay(String name, SensorType type, LocalDate date) {
        return this.house.getMaximumTemperatureOfRoomInSpecificDay(name, type, date);
    }
*/
}
