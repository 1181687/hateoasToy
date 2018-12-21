package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US135Controller;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.PowerSourceTypeList;

import java.util.Scanner;

public class US135UI {

    private US135Controller mController;

    public US135UI(HouseGridList houseGridList, PowerSourceTypeList powerSourceTypeList) {
        this.mController = new US135Controller(houseGridList,powerSourceTypeList);
    }

    public void run(){
        if(mController.checkIfHouseGridListIsEmpty()){
            System.out.println("There are no house grids in your house. Please insert a new house grid."+"\n");
        }
        else {
            Scanner read = new Scanner(System.in);

            System.out.println("Please select the House Grid you want to add the Power Source:");
            System.out.println(mController.getHouseGridListContent());
            int positionOfHouseGrid = read.nextInt() - 1;
            read.nextLine();
            mController.getHouseGridFromListByPosition(positionOfHouseGrid);

            System.out.println("Please insert the name of the new power source");
            String powerSourceName = read.nextLine();

            System.out.println("Please select the power source type");
            System.out.println(mController.displayPowerSourceTypeList());
            int positionOfPowerSource = read.nextInt()-1;
            read.nextLine();
            mController.getPowerSourceTypeFromListByPosition(positionOfPowerSource);

            System.out.println("Is the power source rechargeable?");
            System.out.println(mController.chooseRechargeableOption());
            int rechargeableOption = read.nextInt();
            read.nextLine();
            boolean isRechargeable = mController.isRechargeable(rechargeableOption);

            boolean isPowerSourceAdded = mController.createAndAddPowerSourceToHouseGrid(powerSourceName,isRechargeable);

            if (isPowerSourceAdded) {
                System.out.println("Success! The Power Source was added to the House Grid");
            } else {
                System.out.println("The Power Source was not added to the House Grid");
            }
        }
    }
}
