package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class HouseGridList {
    private List<HouseGrid> mHouseGridsList;

    /**
     * constructor of the house grid list that receives a list of house grids.
     */
    public HouseGridList() {
        mHouseGridsList = new ArrayList<>();
    }

    /**
     * Get method.
     *
     * @return mHouseGridsList.
     */
    public List<HouseGrid> getmHouseGridsList() {
        return mHouseGridsList;
    }

    /**
     * Method that gets the house grid in a specific position in the list.
     *
     * @param position Specifies the position of the house grid in the list.
     * @return The respective house grid.
     */
    public HouseGrid getHouseGridByPosition(int position) {
        return mHouseGridsList.get(position);
    }

    /**
     * Method that shows the content of the house grids in the list.
     *
     * @return String with the required information.
     */
    public String getHouseGridListToString() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= mHouseGridsList.size(); i++) {
            content.append(i + " - Name: " + mHouseGridsList.get(i - 1).getName());
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * Method that displays the rooms in the house grid list
     *
     * @param position
     * @return rooms in the house grid
     */
    public String getRoomsInTheHouseGrid(int position) {
        return mHouseGridsList.get(position).getRoomListContent();
    }

    /**
     * Method that calls the method in HouseGrid that detaches a selected room from the HouseGridList.
     *
     * @param houseGridSelected Specified house grid in the list.
     * @param roomSelected      Specified room.
     */
    public boolean detachRoomInASpecificHouseGridInTheList(HouseGrid houseGridSelected, Room roomSelected) {
        int index = mHouseGridsList.indexOf(houseGridSelected);
        return mHouseGridsList.get(index).detachRoom(roomSelected);
    }

    /**
     * Method that asks the class HouseGrid to add a room to it's list.
     *
     * @param houseGridSelected Specified house grid in the list.
     * @param roomSelected      Specified room.
     */
    public void attachRoomInASpecificHouseGridInTheList(HouseGrid houseGridSelected, Room roomSelected) {
        int index = mHouseGridsList.indexOf(houseGridSelected);
        mHouseGridsList.get(index).attachRoom(roomSelected);
    }

    /**
     * Method that checks if the house grid's list is empty.
     *
     * @return True or false.
     */
    public boolean checkIfHouseGridListIsEmpty() {
        return mHouseGridsList.isEmpty();
    }

    /**
     * Method that creates a house grid.
     *
     * @param name Name of the grid.
     * @return New object of the class HouseGrid.
     */
    public HouseGrid createAHouseGrid(String name) {
        return new HouseGrid(name);
    }

    /**
     * Method that adds a house grid to the list.
     *
     * @param grid Specified grid.
     */
    public void addHouseGridToTheList(HouseGrid grid) {
        mHouseGridsList.add(grid);
    }

    /**
     * Method that checks if a room isn't already in a specific grid in the list.
     *
     * @param chosenGrid Specified house grid in the list.
     * @param room       Specified room.
     * @return True or false.
     */
    public boolean checkIfARoomIsAlreadyInAHouseGrid(HouseGrid chosenGrid, Room room) {
        int index = mHouseGridsList.indexOf(chosenGrid);
        return mHouseGridsList.get(index).checkIfRoomIsInHouseGrid(room);
    }

    /**
     * Method that asks for the grid where the room might already be connected.
     *
     * @param room Specified room.
     * @return Grid where the room is is connected to.
     */
    public HouseGrid getTheGridWhereTheRoomIsConnected(Room room) {
        for (HouseGrid houseGrid : mHouseGridsList) {
            if (houseGrid.checkIfRoomIsInHouseGrid(room)) {
                return houseGrid;
            }
        }
        return null;
    }

    /**
     * method that gets a List of all Devices in a house grid, by it position in a HouseGridList
     *
     * @param position position of the grid in the houseGridList
     * @return List <Device> in a grid
     */
    public DeviceList getAllDevicesListByPosition(int position) {
        return getmHouseGridsList().get(position).getAllDevicesList();
    }

    public String getNameByHGPosition(int position) {
        if (mHouseGridsList.isEmpty()) {
            return null;
        }
        return mHouseGridsList.get(position).getName();
    }
}