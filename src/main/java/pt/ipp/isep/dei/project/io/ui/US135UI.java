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

            boolean isPowerSourceAdded = mController.createAndAddPowerSourceToHouseGrid(powerSourceName);

            if (isPowerSourceAdded) {
                String houseGridName = mController.getHouseGridName();
                System.out.println("Success! The power source "+ powerSourceName +" was added to the house grid " + houseGridName + ".");
                String label4 = "Do you want to see the list of power sources added to this house grid? (y/n)";
                String answer = InputValidator.confirmValidation(label4);
                if ("y".equals(answer) || "Y".equals(answer)){
                    System.out.println(mController.listPowerSourcesConnectedToHouseGrid());
                }
            } else {
                System.out.println("The Power Source was not added to the House Grid");
            }
        }
    }
}
