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

            String label1 = "Please select the House Grid you want to add the Power Source: \n" + mController.getHouseGridListContent();
            //System.out.println("Please select the House Grid you want to add the Power Source:");
            //System.out.println(mController.getHouseGridListContent());
            int positionOfHouseGrid = InputValidator.getIntRange(label1,1,mController.houseGridListLength()) - 1;
            //read.nextLine();
            mController.getHouseGridFromListByPosition(positionOfHouseGrid);

            //System.out.println("Please insert the name of the new power source");
            String label2 ="Please insert the name of the new power source";
            //String powerSourceName = read.nextLine();
            String powerSourceName = InputValidator.getString(label2);

            //System.out.println("Please select the power source type");
            //System.out.println(mController.displayPowerSourceTypeList());
            String label3 = "Please select the power source type: \n" + mController.displayPowerSourceTypeList();
            int positionOfPowerSource = InputValidator.getIntRange(label3,1,mController.powerSourceTypeListLength()) -1;
            //read.nextLine();
            mController.getPowerSourceTypeFromListByPosition(positionOfPowerSource);

            //System.out.println("Is the power source rechargeable?");
            //System.out.println(mController.chooseRechargeableOption());
            String label4 ="Is the power source rechargeable? \n" + mController.chooseRechargeableOption();
            int rechargeableOption = InputValidator.getIntRange(label4,1,2);
            //read.nextLine();
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
