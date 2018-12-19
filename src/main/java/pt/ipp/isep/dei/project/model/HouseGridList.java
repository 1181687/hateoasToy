package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class HouseGridList {
    private List<HouseGrid> mList = new ArrayList<>();

    public HouseGridList() {
    }

    public List<HouseGrid> getmList() {
        return mList;
    }


    /**
     * Method that get the house grid in a specific position in the list.
     *
     * @param position Specifies the position of the house grid in the list.
     * @return The respective house grid.
     */
    public HouseGrid getHouseGridFromASpecificPositionInTheList(int position){
        return mList.get(position);
    }

    /**
     * Method that asks to the class HouseGrid to add a room to it's list.
     *
     * @param houseGridSelected Specified house grid in the list.
     * @param roomSelected      Specified room.
     */
    public void attachRoomInASpecificHouseGridInTheList(HouseGrid houseGridSelected, Room roomSelected) {
        int index = mList.indexOf(houseGridSelected);
        mList.get(index).attachRoomInTheHouseGridRoomList(roomSelected);
    }
}
