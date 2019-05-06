package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.time.LocalDate;
import java.util.List;

/**
 * US610 As a Regular User, I want to get the maximum temperature in a room in a given day,
 * in order to check if heating/cooling in that room was effective.
 */

public class GetMaxTemperatureRoom {


    private GetCurrentAndMaxTempRoomController controller;
    private List<RoomDTO> allRoomsDTOList;

    /**
     * constructor that receives a House and a SensorTypeList
     *
     * @param typeId     SensorTypeId
     * @param sensorService RoomSensorService
     * @param rService RoomService
     */
    public GetMaxTemperatureRoom(SensorTypeId typeId, RoomSensorService sensorService, RoomService rService){
        this.controller = new GetCurrentAndMaxTempRoomController(typeId, sensorService, rService);
        this.allRoomsDTOList=controller.getRoomDTOList();
    }

    public String getRoomDTOListToString(){
        StringBuilder roomListContent = new StringBuilder();
        int numberOfRoom = 1;
        for (RoomDTO roomDto:allRoomsDTOList) {
            roomListContent.append (numberOfRoom+" - Id: "+roomDto.getRoomId()+"\n");
            numberOfRoom++;
        }
        return roomListContent.toString();
    }

    public void run() {
        if (allRoomsDTOList.isEmpty()) {
            System.out.println("Sorry! There are no rooms available.\n");
            return;
        }
        String label0 = "Please, choose the room you would like to get the maximum temperature:\n"+getRoomDTOListToString()+"\n";
        int option = InputValidator.getIntRange(label0, 1, allRoomsDTOList.size())-1;
        RoomDTO selectedRoom = allRoomsDTOList.get(option);
        String roomDTOId = selectedRoom.getRoomId();
        String label1 = "Please insert the date when you want to get the maximum temperature (yyyy-MM-dd):";
        LocalDate dateLD = InputValidator.getStringDate(label1);
        double temp = controller.getMaximumTemperatureOfRoomInGivenDay(roomDTOId, dateLD);
        if (Double.isNaN(temp)) {
            System.out.println("Sorry! There are no temperature values available.\n");
            return;
        }

        System.out.println("The maximum temperature is " + temp + "ºC.\n");
    }
}