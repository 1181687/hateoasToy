package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US135Controller;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.PowerSource;

import java.util.Scanner;

public class US135UI {

    private US135Controller mController;

    public US135UI(HouseGridList houseGridList, PowerSource powerSource) {
        this.mController = new US135Controller(houseGridList, powerSource);
    }

    public void run(){
        if(mController.checkIfHouseGridListIsEmpty()){
            System.out.println("There are no house grids in your house. Please insert a new house grid."+"\n");
        }
        else {
            System.out.println("Please select the House Grid you want to add the Power Source:");
            System.out.println(mController.getHouseGridListContent());
            Scanner read = new Scanner(System.in);
            int position = read.nextInt() - 1;
            read.nextLine();
            HouseGrid selectedHouseGrid = mController.getHouseGridFromListByPosition(position);
            boolean isPowerSourceAdded = mController.addPowerSourceToHouseGrid(selectedHouseGrid);

            if (isPowerSourceAdded) {
                System.out.println("Success! The Power Source was added to the House Grid");
            } else {
                System.out.println("The Power Source was not added to the House Grid");
            }
        }
    }
}
