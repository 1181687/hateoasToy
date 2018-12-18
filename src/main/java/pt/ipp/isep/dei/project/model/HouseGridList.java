package pt.ipp.isep.dei.project.model;

import java.util.List;

public class HouseGridList {
    private List<HouseGrid> mList;

    public HouseGridList(List<HouseGrid> mList) {
        this.mList = mList;
    }

    public List<HouseGrid> getmList() {
        return mList;
    }

    public HouseGrid getHouseGridFromASpecificPositionInTheList(int position){
        return mList.get(position);
    }
}
