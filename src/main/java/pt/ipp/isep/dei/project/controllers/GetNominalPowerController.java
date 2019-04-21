package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Measurable;
import pt.ipp.isep.dei.project.model.MeasurableList;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.ArrayList;
import java.util.List;

public class GetNominalPowerController {
    private House house;
    private Room chosenRoom;
    private HouseGrid selectedHouseGrid;
    private MeasurableList measurableList;

    private RoomAggregateService roomAggregateService;

    public GetNominalPowerController(RoomAggregateService roomAggregateService) {
        this.house = house;
        //this.measurableList = house.getNewMeasurableObjList();

    }

    public List<HouseGridDTO> getAllGrids(){
        List<HouseGrid> gridList = this.roomAggregateService.getAllGrids();
        List<HouseGridDTO> gridDTOS = new ArrayList<>();

        if(!gridList.isEmpty()){

            for (HouseGrid grid : gridList) {
                gridDTOS.add(HouseGridMapper.mapToDTO(grid));
            }
        }
        return gridDTOS;
    }

    /**
     * Calculates the nominal power of the selected housegrid.
     *
     * @return double
     */
    public double getHouseGridTotalNominalPower(String gridId) {
        return this.roomAggregateService.getGridNominalPower(new HouseGridId(gridId));
    }
/*
    *//**
     * method that returns the method isHouseGridListEmpty of the model class House
     *
     * @return true if housegrid grid list is empty or false if it is not empty
     *//*
    public boolean isGridListEmpty(){
        return this.house.isHouseGridListEmpty();
    }

    *//**
     * method that returns the method getHouseGridListContent of the model class House
     * @return housegrid grid list content
     *//*
    public String getHouseGridsListToString(){
        return this.house.getHouseGridListToString();
    }

    *//**
     * method that returns the method getHouseGridListSize of the model class House
     * @return an integer that matches the housegrid grid list size
     *//*
    public int getHouseGridListSize(){
        return this.house.getHouseGridListSize();
    }

    *//**
     * method that stores the chosen housegrid grid in Controller
     * @param position integer that matches the position of housegrid grid in housegrid grid list of housegrid
     *//*
    public void getHouseGridByPosition(int position){
        selectedHouseGrid = this.house.getHouseGridByPosition(position);
    }

    *//**
     * method that returns the method getRoomPosition of the model class House Grid
     * @param position integer that matches the position of room in room list of housegrid grid
     * @return Room that matches the chosen position
     *//*
    public Room getRoomOfHouseGridByPosition(int position){
        return selectedHouseGrid.getRoomByPosition(position);
    }

    *//**
     * method that returns the method getDeviceListToString of the model class House Grid
     * @param position integer that matches the position of room in room list of housegrid grid
     * @return device list content of the chosen room (position)
     *//*
    public String getDeviceListToString(int position){
        return selectedHouseGrid.getDeviceListContent(position);
    }

    *//**
     * method that returns the method getDeviceListSizeByRoomPosition of the model class House Grid
     * @param position integer that matches the position of room in room list of housegrid grid
     * @return integer that matches the device list size
     *//*
    public int getDeviceListSize(int position){
        return selectedHouseGrid.getDeviceListSizeByRoomPosition(position);
    }

    *//**
     * method that returns the method isRoomListEmpty of the model class House Grid
     * @return true if room list is empty or false if it is not empty
     *//*
    public boolean roomListOfHouseGridIsEmpty() {
        return selectedHouseGrid.isRoomListEmpty();
    }

    *//**
     * method that returns the method isEmpty of the model class House Grid
     * @param position integer that matches the position of room in room list of housegrid grid
     * @return true if device list of chosen room (position) is empty or false if it is not empty
     *//*
    public boolean deviceListIsEmpty(int position){
        return selectedHouseGrid.isDeviceListOfRoomEmpty(position);
    }

    *//**
     * method that returns the method getDeviceByRoomAndDevicePosition of the model class House Grid
     * @param pos1 integer that matches the room in room list of housegrid grid
     * @param pos2 integer that matches the device in device list of chosen room
     * @return device that matches the chosen position
     *//*
    public Device getDeviceListByPosition(int pos1, int pos2) {
        return selectedHouseGrid.getDeviceByRoomAndDevicePosition(pos1, pos2);
    }

    *//**
     * method that returns the method getNominalPower of model class MeasurableList
     * @return nominal power of objects in measurable list
     *//*
    public double getNominalPowerOfMeasurableObjects(){
        return measurableList.getNominalPower();
    }

    *//**
     * method that adds a measurable object (room or device)
     * @param measurable matches an object: room or device
     *//*
    public void addMeasurable(Measurable measurable) {
        measurableList.addMeasurable(measurable);
    }

    *//**
     * method that returns the method getRoomListContent of model class House Grid
     * @return the room list content
     *//*
    public String getRoomListInHouseGridToString() {
        return selectedHouseGrid.getRoomListContent();
    }

    *//**
     * method that returns the method getRoomListSize of model class House Grid
     * @return an integer that matches the size of room list
     *//*
    public int getRoomListInHouseGridSize() {
        return selectedHouseGrid.getRoomListSize();
    }

    *//**
     * method that returns the method isMeasurableInList of model class measurable list
     *
     * @param measurable matches an object: room or device
     * @return true if measurable list is empty and false if it is not
     *//*
    public boolean isMeasurableInList(Measurable measurable){
        return measurableList.checkIfMeasurableObjIsInList(measurable);
    }

    *//**
     * method that returns the method getMeasurableListToString of model class MeasurableList
     *
     * @return list (string)
     *//*
    public String getMeasurableListToString() {
        return measurableList.getListToString();
    }


    *//**
     * Method that returns the String builder with the list of rooms.
     *
     * @return A string with the list of rooms.
     *//*
    public String getListOfRooms() {
        return house.getRoomListContent();
    }

    *//**
     * Method that returns the length of the roomList.
     *
     * @return integer
     *//*
    public int getRoomListSize() {
        return house.houseRoomListSize();
    }

    *//**
     * Method that checks if the device list is empty, given a room from the list of rooms.
     *
     * @param option Chosen room from the list as an integer.
     * @return
     *//*
    public boolean isDeviceListEmpty(int option) {
        return house.isDeviceListEmpty(option);
    }

    *//**
     * Method that selects the room from the list and saves it in the controller.
     *
     * @param option Chosen room from the list as an integer.
     *//*
    public void getRoom(int option) {
        chosenRoom = house.getRoomOfTheRoomList(option);
    }

    *//**
     * Method that returns the nominal power of the room previously chosen.
     *
     * @return
     *//*
    public double getNominalPower() {
        return chosenRoom.getNominalPower();
    }*/

}