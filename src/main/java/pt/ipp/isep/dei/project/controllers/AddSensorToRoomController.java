package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeMapper;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.util.ArrayList;
import java.util.List;


public class AddSensorToRoomController {

    private final SensorTypeService sensorTypeService;
    private final RoomService roomService;
    private final RoomSensorService roomSensorService;

    public AddSensorToRoomController(SensorTypeService sensorTypeService, RoomService roomService, RoomSensorService roomSensorService) {
        this.sensorTypeService = sensorTypeService;
        this.roomService = roomService;
        this.roomSensorService = roomSensorService;
    }

    /**
     * This method display the rooms of the housegrid
     *
     * @return the RoomList.
     */
    public List<RoomDTO> getRoomListContent() {
        return this.roomService.getAllRoomsDTO();
    }

    /**
     * This method display the list of sensors type in the sensors type list.
     *
     * @return the sensor type list.
     */
    public List<SensorTypeDTO> getSensorTypes() {
        List<SensorTypeDTO> sensorTypeDTOS = new ArrayList<>();
        for (SensorType type : this.sensorTypeService.getSensorTypeList()) {
            sensorTypeDTOS.add(SensorTypeMapper.mapToDto(type));
        }
        return sensorTypeDTOS;
    }

    /**
     * This method create and add a sensor to the list of sensors in the room.
     *
     * @param roomSensorDTO DTO object of the sensor to create
     * @return a new sensor added to the RoomSensor Repository.
     */
    public boolean createAndAddSensorToTheList(RoomSensorDTO roomSensorDTO) {
        return roomSensorService.newSensor(roomSensorDTO);
    }
}
