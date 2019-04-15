package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.Map;

public class GetEnergyConsumptionDataSeriesController {

    private static final String NO_VALID_VALUES = "No valid values found for that period.\n";
    private House house;
    private HouseGrid selectedHouseGrid;
    private Room selectedRoom;
    private Device selectedDevice;

    public GetEnergyConsumptionDataSeriesController(House house) {
        this.house = house;
    }

    public String getHouseGridListToString() {
        return house.getHouseGridListToString();
    }

    public String getRoomListToString() {
        return house.getRoomListContent();
    }

    public String getDeviceListToString() {
        return house.getAllDevicesToString();
    }

    public boolean houseGridListIsEmpty() {
        return house.isHouseGridListEmpty();
    }

    public void getHouseGridByPosition(int position) {
        selectedHouseGrid = house.getHouseGridByPosition(position);
    }

    public void getRoomByPosition(int position) {
        selectedRoom = house.getRoomOfTheRoomList(position);
    }

    public void getDeviceByPosition(int position) {
        selectedDevice = house.getDeviceByPosition(position);
    }

    public String getHouseGridDataSeriesToString(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = selectedHouseGrid.getDataSeries(startDate, endDate);
        if (map.isEmpty()) {
            return NO_VALID_VALUES;
        }
        return Utils.getDataSeriesToString(map);
    }

    public String getRoomDataSeriesToString(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = selectedRoom.getDataSeries(startDate, endDate);
        if (map.isEmpty()) {
            return NO_VALID_VALUES;
        }
        return Utils.getDataSeriesToString(map);
    }

    public String getDeviceDataSeriesToString(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = selectedDevice.getDataSeries(startDate, endDate);
        if (map.isEmpty()) {
            return NO_VALID_VALUES;
        }
        return Utils.getDataSeriesToString(map);
    }

    public int getHouseGridListSize() {
        return house.getHouseGridListSize();
    }

    public int getRoomListSize() {
        return house.getRoomListSize();
    }

    public int getDeviceListSize() {
        return house.getDeviceSize();
    }

    public boolean roomListIsEmpty() {
        return house.roomListIsEmpty();
    }

    public boolean deviceListIsEmpty() {
        return house.isDeviceListOfAllRoomsEmpty();
    }
}
