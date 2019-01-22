package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddPowerSourceToHouseGridController;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.PowerSourceTypeList;

/** US135 As an Administrator, I want to add a power source to a house grid, so that the
produced energy may be used by all devices in that grid. */

public class AddPowerSourceToHouseGrid {

    private AddPowerSourceToHouseGridController mController;

    public AddPowerSourceToHouseGrid(HouseGridList houseGridList, PowerSourceTypeList powerSourceTypeList) {
        this.mController = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);
    }

    public void run(){
        if (mController.isHouseGridListEmpty()) {
            System.out.println("There are no house grids in your house. Please insert a new house grid."+"\n");
        }
        else {

            String label1 = "Please select the House Grid you want to add the Power Source: \n" + mController.getHouseGridListToString();
            int positionOfHouseGrid = InputValidator.getIntRange(label1,1,mController.getHouseGridListSize()) - 1;
            mController.getHouseGridFromListByPosition(positionOfHouseGrid);

            String label2 ="Please insert the name of the new power source";
            String powerSourceName = InputValidator.getString(label2);

            String label3 = "Please select the power source type: \n" + mController.getPowerSourceTypeListToString();
            int positionOfPowerSource = InputValidator.getIntRange(label3, 1, mController.getPowerSourceTypeListSize()) - 1;
            mController.getPowerSourceTypeByPosition(positionOfPowerSource);

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
