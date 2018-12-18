package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US149Controller;
import pt.ipp.isep.dei.project.model.HouseGridList;

public class US149UI {
    private HouseGridList mListOfHouseGrids;

    public US149UI(HouseGridList ListOfHouseGrids) {
    }

    public String getListOfHouseGrides (){
        return mListOfHouseGrids.getContentOfHouseGrid();
    }


}
