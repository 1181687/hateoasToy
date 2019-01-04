package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class HouseGridList {
    private List<HouseGrid> mList;

    /**
     * Constructor method.
     */
    public HouseGridList() {
        mList = new ArrayList<>();
    }

    /**
     * Get method.
     * @return mList.
     */
    public List<HouseGrid> getmList() {
        return mList;
    }

    /**
     * Method that gets the house grid in a specific position in the list.
     * @param position Specifies the position of the house grid in the list.
     * @return The respective house grid.
     */
    public HouseGrid getHouseGridFromASpecificPositionInTheList(int position) {
        return mList.get(position);
    }

    /**
     * Method that shows the content of the house grids in the list.
     * @return String with the required information.
     */
    public String getContentOfTheHouseGridsInTheList() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= mList.size(); i++) {
            content.append(i + " - Name: " + mList.get(i - 1).getmHouseGridName());
            content.append("\n");
        }
        return content.toString();
    }

    public String displayRoomsInTheHouseGrid(int position) {
        return mList.get(position).displayRoomsAttachedToHouseGrid();
    }

    /**
     * Method that calls the method in HouseGrid that detaches a selected room from the HouseGridList.
     * @param houseGridSelected Specified house grid in the list.
     * @param roomSelected      Specified room.
     */
    public boolean detachRoomInASpecificHouseGridInTheList(HouseGrid houseGridSelected, Room roomSelected) {
        int index = mList.indexOf(houseGridSelected);
        return mList.get(index).detachRoomFromHouseGrid(roomSelected);
    }

    /**
     * Method that asks the class HouseGrid to add a room to it's list.
     * @param houseGridSelected Specified house grid in the list.
     * @param roomSelected      Specified room.
     */
    public void attachRoomInASpecificHouseGridInTheList(HouseGrid houseGridSelected, Room roomSelected) {
        int index = mList.indexOf(houseGridSelected);
        mList.get(index).attachRoomInTheHouseGridRoomList(roomSelected);
    }

    /**
     * Method that checks if the house grid's list is empty.
     * @return True or false.
     */
    public boolean checkIfHouseGridListIsEmpty (){
        return mList.isEmpty();
    }

    /**
     * Method that creates a house grid.
     * @param name Name of the grid.
     * @return New object of the class HouseGrid.
     */
    public HouseGrid createAHouseGrid (String name){
        return new HouseGrid(name);
    }

    /**
     * Method that adds a house grid to the list.
     * @param grid Specified grid.
     */
    public void addHouseGridToTheList(HouseGrid grid) {
        mList.add(grid);
    }

    /**
     * Method that checks if a room isn't already in a specific grid in the list.
     * @param chosenGrid Specified house grid in the list.
     * @param room Specified room.
     * @return True or false.
     */
    public boolean checkIfARoomIsAlreadyInAHouseGridOfTheList(HouseGrid chosenGrid, Room room) {
        int index = mList.indexOf(chosenGrid);
        return mList.get(index).checkIfARoomIsAlreadyInTheGrid(room);
    }
}