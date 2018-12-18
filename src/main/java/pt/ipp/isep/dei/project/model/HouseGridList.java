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

    public HouseGrid getHouseGridFromASpecificPositionInTheList(int position){
        return mList.get(position);
    }
}
