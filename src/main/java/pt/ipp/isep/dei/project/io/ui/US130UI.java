package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US130Controller;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;

import java.util.Scanner;

public class US130UI {
    private US130Controller mController;

    public US130UI(HouseGridList houseGridList) {
        this.mController = new US130Controller(houseGridList);
    }

    public void run(){
        System.out.println("Please insert the name of the House Grid you want to create.");
        Scanner read = new Scanner(System.in);
        String nameHG = read.nextLine();
        HouseGrid houseGridCreated = mController.createANewHouseGrid(nameHG);
        mController.addHouseGridToTheListOfHouseGrids(houseGridCreated);
        System.out.println("Your House Grid was succesfully created! \n" );
    }

}
