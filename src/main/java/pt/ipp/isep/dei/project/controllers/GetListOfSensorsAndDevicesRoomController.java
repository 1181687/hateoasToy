package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorMapper;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.ArrayList;
import java.util.List;

public class GetListOfSensorsAndDevicesRoomController {

    private RoomAggregateService roomAggregateService;

    public GetListOfSensorsAndDevicesRoomController(RoomAggregateService roomAggregateService) {
        this.roomAggregateService = roomAggregateService;
    }


    public List<RoomDTO> getRoomDTOList() {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomAggregateService.getAllRooms()) {
            roomDTOList.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOList;
    }
    
    public List<RoomSensorDTO> getRoomSensorDTOList() {
        List<RoomSensorDTO> roomSensorDTOList = new ArrayList<>();
        for (RoomSensor roomSensor : roomAggregateService.getAllRoomSensors()) {
            roomSensorDTOList.add(RoomSensorMapper.mapToDTO(roomSensor));
        }
        return roomSensorDTOList;
    }


    /*    *//**
     * method that returns the method getDeviceListContentByPosition of the class House
     *
     * @param position
     * @return the device list content of a room by position
     *//*
    public String getDeviceListContent(int position) {
        return this.house.getDeviceListContentRoom(position);
    }

    *//**
     * method that returns the method deviceListEmpty of the Class House
     * @param position
     *//*
    public boolean isDeviceListEmpty(int position) {
        return this.house.isDeviceListEmpty(position);
    }*/


}
