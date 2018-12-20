package pt.ipp.isep.dei.project.controllers;


import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;

import java.util.Scanner;


public class US130Controller {

    private HouseGridList mHouseGridList;

    public US130Controller(HouseGridList houseGridList) {
        this.mHouseGridList = houseGridList;
    }

    public HouseGrid createANewHouseGrid (String nameOfHG){
       return mHouseGridList.createAHouseGrid(nameOfHG);
    }





}
