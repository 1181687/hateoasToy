package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;
import java.util.Map;

public class GetEnergyConsumptionDataSeriesController {

    private House mHouse;
    private HouseGrid mSelectedHouseGrid;
    private Room mSelectedRoom;
    private Device mSelectedDevice;

    public GetEnergyConsumptionDataSeriesController(House mHouse) {
        this.mHouse = mHouse;
    }

    public String getHouseGridListToString(){
        return mHouse.getHouseGridListContent();
    }

    public String getRoomListToString(){
        return mHouse.getRoomListContent();
    }

    public String getDeviceListToString(){
        return mHouse.getAllDevicesToString();
    }

    public boolean houseGridListIsEmpty(){
        return mHouse.isHouseGridListEmpty();
    }

    public void getHouseGridByPosition(int position){
        mSelectedHouseGrid=mHouse.getHouseGridByPosition(position);
    }

    public void getRoomByPosition(int position){
        mSelectedRoom=mHouse.getRoomOfTheRoomList(position);
    }

    public void getDeviceByPosition(int position){
        mSelectedDevice = mHouse.getDeviceByPosition(position);
    }

    public String getHouseGridDataSeriesToString(LocalDateTime startDate, LocalDateTime endDate){
        Map<LocalDateTime,Double> map = mSelectedHouseGrid.getDataSeries(startDate,endDate);
        return mHouse.getDataSeriesToString(map);
    }

    public String getRoomDataSeriesToString(LocalDateTime startDate, LocalDateTime endDate){
        Map<LocalDateTime,Double> map = mSelectedRoom.getDataSeries(startDate,endDate);
        return mHouse.getDataSeriesToString(map);
    }

    public String getDeviceDataSeriesToString(LocalDateTime startDate, LocalDateTime endDate){
        Map<LocalDateTime,Double> map = mSelectedDevice.getDataSeries(startDate,endDate);
        return mHouse.getDataSeriesToString(map);
    }
}
