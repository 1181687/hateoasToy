package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.time.LocalDate;
import java.util.List;

public class GetCurrentAndMaxTempRoomController {

    private final SensorTypeId temperature;
    private final RoomSensorService roomSensorService;
    private final RoomService roomService;


    /**
     * constructor that receives a House and a SensorType
     *
     * @param typeId SensorType received
     */
    public GetCurrentAndMaxTempRoomController(SensorTypeId typeId, RoomSensorService sensorService, RoomService rService) {
        this.temperature = typeId;
        this.roomSensorService = sensorService;
        this.roomService = rService;
    }

    public SensorTypeId getType() {
        return this.temperature;
    }

    public ReadingDTO getLatestMeasurementOfRoomSensor(String roomIdString) {
        RoomId roomId = new RoomId(roomIdString);
        return this.roomSensorService.getLastMeasurement(roomId, temperature);
    }

    public List<RoomDTO> getRoomDTOList(){
        return this.roomService.getAllRoomsDTO();
    }

    public double getMaximumTemperatureOfRoomInGivenDay(String roomIdString, LocalDate date) {
        RoomId roomId = new RoomId(roomIdString);
        return this.roomSensorService.getMaxMeasurementValueOfADay(roomId, temperature, date);
    }
}