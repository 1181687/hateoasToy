package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US135Controller;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.PowerSourceTypeList;


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

            String label1 = "Please select the House Grid you want to add the Power Source: \n" + mController.getHouseGridListContent();
            int positionOfHouseGrid = InputValidator.getIntRange(label1,1,mController.houseGridListLength()) - 1;
            mController.getHouseGridFromListByPosition(positionOfHouseGrid);

            String label2 ="Please insert the name of the new power source";
            String powerSourceName = InputValidator.getString(label2);

            String label3 = "Please select the power source type: \n" + mController.getPowerSourceTypeListContent();
            int positionOfPowerSource = InputValidator.getIntRange(label3,1,mController.powerSourceTypeListLength()) -1;
            mController.getPowerSourceTypeFromListByPosition(positionOfPowerSource);

            String label4 ="Is the power source rechargeable? \n" + mController.chooseRechargeableOption();
            int rechargeableOption = InputValidator.getIntRange(label4,1,2);
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
