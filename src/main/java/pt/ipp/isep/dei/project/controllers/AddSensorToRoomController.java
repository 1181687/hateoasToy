package pt.ipp.isep.dei.project.controllers;


import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.ArrayList;
import java.util.List;

public class AddSensorToRoomController {

    private RoomAggregateService roomAggregateService;

    public AddSensorToRoomController(RoomAggregateService roomService) {
        this.roomAggregateService = roomService;
    }

    public List<RoomDTO> getRoomDTOList() {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomAggregateService.getAllRooms()) {
            roomDTOList.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOList;
    }

    public List<SensorTypeDTO> getSensorTypeList() {
        List<SensorTypeDTO> sensorTypeDTOList = new ArrayList<>();
        for (SensorType sensorType : roomAggregateService.getSensorTypeList()) {
            sensorTypeDTOList.add(SensorTypeMapper.mapToDto(sensorType));
        }
        return sensorTypeDTOList;
    }

    public void addSensorToRoom(RoomSensorDTO roomSensorDTO) {
        RoomSensorMapper.mapToEntity(roomSensorDTO);
    }
}
