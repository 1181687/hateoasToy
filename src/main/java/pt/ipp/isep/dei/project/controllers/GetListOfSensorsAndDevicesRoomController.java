package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceDTO;
import pt.ipp.isep.dei.project.model.devices.DeviceMapper;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorMapper;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.util.ArrayList;
import java.util.List;

public class GetListOfSensorsAndDevicesRoomController {

    private RoomService roomService;
    private RoomSensorService sensorService;

    public GetListOfSensorsAndDevicesRoomController(RoomService roomAggregateService, RoomSensorService roomSensorService) {
        this.roomService = roomAggregateService;
        this.sensorService = roomSensorService;
    }


    public List<RoomDTO> getRoomDTOList() {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomService.getAllRooms()) {
            roomDTOList.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOList;
    }

    public List<RoomSensorDTO> getRoomSensorDTOList(String roomId) {
        RoomId id = new RoomId(roomId);
        List<RoomSensorDTO> roomSensorDTOList = new ArrayList<>();
        for (RoomSensor roomSensor : sensorService.getAllSensorsOfRoom(id)) {
            roomSensorDTOList.add(RoomSensorMapper.mapToDTO(roomSensor));
        }
        return roomSensorDTOList;
    }

    public List<DeviceDTO> getDeviceDTOList(String id) {
        List<Device> devices = roomService.getAllDevicesOfRoom(id);
        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        if (!devices.isEmpty()) {
            for (Device device : devices) {
                deviceDTOList.add(DeviceMapper.mapToDTO(device));
            }
        }
        return deviceDTOList;
    }

}
