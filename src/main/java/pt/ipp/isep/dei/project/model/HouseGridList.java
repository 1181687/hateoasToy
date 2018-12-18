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
}
