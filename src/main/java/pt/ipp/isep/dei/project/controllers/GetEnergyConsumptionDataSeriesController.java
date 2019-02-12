package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.Map;

public class GetEnergyConsumptionDataSeriesController {

    private House mHouse;
    private HouseGrid mSelectedHouseGrid;
    private Room mSelectedRoom;
    private Device mSelectedDevice;
    private static final String NO_VALID_VALUES = "No valid values found for that period.\n";

    public GetEnergyConsumptionDataSeriesController(House mHouse) {
        this.mHouse = mHouse;
    }

    public String getHouseGridListToString() {
        return mHouse.getHouseGridListToString();
    }

    public String getRoomListToString() {
        return mHouse.getRoomListContent();
    }

    public String getDeviceListToString() {
        return mHouse.getAllDevicesToString();
    }

    public boolean houseGridListIsEmpty() {
        return mHouse.isHouseGridListEmpty();
    }

    public void getHouseGridByPosition(int position) {
        mSelectedHouseGrid = mHouse.getHouseGridByPosition(position);
    }

    public void getRoomByPosition(int position) {
        mSelectedRoom = mHouse.getRoomOfTheRoomList(position);
    }

    public void getDeviceByPosition(int position) {
        mSelectedDevice = mHouse.getDeviceByPosition(position);
    }

    public String getHouseGridDataSeriesToString(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = mSelectedHouseGrid.getDataSeries(startDate, endDate);
        if (map.isEmpty()) {
            return NO_VALID_VALUES;
        }
        return Utils.getDataSeriesToString(map);
    }

    public String getRoomDataSeriesToString(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = mSelectedRoom.getDataSeries(startDate, endDate);
        if (map.isEmpty()) {
            return NO_VALID_VALUES;
        }
        return Utils.getDataSeriesToString(map);
    }

    public String getDeviceDataSeriesToString(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = mSelectedDevice.getDataSeries(startDate, endDate);
        if (map.isEmpty()) {
            return NO_VALID_VALUES;
        }
        return Utils.getDataSeriesToString(map);
    }

    public int getHouseGridListSize() {
        return mHouse.getHouseGridListSize();
    }

    public int getRoomListSize() {
        return mHouse.getRoomListSize();
    }

    public int getDeviceListSize() {
        return mHouse.getDeviceSize();
    }

    public boolean roomListIsEmpty() {
        return mHouse.roomListIsEmpty();
    }

    public boolean deviceListIsEmpty() {
        return mHouse.isDeviceListOfAllRoomsEmpty();
    }
}
