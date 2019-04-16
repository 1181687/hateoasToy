package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddPowerSourceToHouseGridController;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeDTO;

import java.util.List;

/**
 * US135 As an Administrator, I want to add a power source to a housegrid grid, so that the
 * produced energy may be used by all devices in that grid.
 */

public class AddPowerSourceToHouseGrid {

    private AddPowerSourceToHouseGridController controller;
    private List<HouseGridDTO> houseGridDTOS;
    private List<PowerSourceTypeDTO> powerSourceTypeDTOS;

    public AddPowerSourceToHouseGrid(HouseService houseService) {
        this.controller = new AddPowerSourceToHouseGridController(houseService);
    }

    public void run() {
        if (controller.isHouseGridRepositoryEmpty()) {
            System.out.println("There are no grids in your house. Please insert a new grid. \n");
        } else {

            String label1 = "Please select the grid to which you want to add the Power Source: \n" + getGridListToString();
            int positionOfHouseGrid = InputValidator.getIntRange(label1, 1, getGridListSize()) - 1;

            String gridId = getGridDTOByPosition(positionOfHouseGrid).getId();

            String label3 = "Please select the power source type: \n" + getPowerSourceTypeListToString();
            int positionOfPowerSource = InputValidator.getIntRange(label3, 1, getPowerSourceTypeListSize()) - 1;

            String powerSourceTypeId = getPowerSourceTypeByPosition(positionOfPowerSource).getType();

            String powerSourceId;
            boolean isPowerSourceAdded = false;
            do {

                String label2 = "Please insert the name of the new power source";
                powerSourceId = InputValidator.getString(label2);

                if (controller.newPowerSource(powerSourceId, powerSourceTypeId, gridId)) {
                    isPowerSourceAdded = true;
                } else {
                    System.out.println("Name already exists. Please write a new one.");
                    isPowerSourceAdded = false;
                }

            } while (!isPowerSourceAdded);

            System.out.println("Success! The power source " + powerSourceId + " was added to the house grid " + gridId + ".");

        }
    }

    private String getGridListToString() {
        this.houseGridDTOS = this.controller.getGridList();
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (HouseGridDTO grid : this.houseGridDTOS) {
            content.append(number);
            content.append(" - ");
            content.append(grid.getId());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private String getPowerSourceTypeListToString() {
        this.powerSourceTypeDTOS = this.controller.getPowerSourceTypeList();
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (PowerSourceTypeDTO typeDTO : this.powerSourceTypeDTOS) {
            content.append(number);
            content.append(" - ");
            content.append(typeDTO.getType());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private int getGridListSize() {
        return this.houseGridDTOS.size();
    }

    private int getPowerSourceTypeListSize() {
        return this.powerSourceTypeDTOS.size();
    }

    private HouseGridDTO getGridDTOByPosition(int position) {
        return this.houseGridDTOS.get(position);
    }

    private PowerSourceTypeDTO getPowerSourceTypeByPosition(int position) {
        return this.powerSourceTypeDTOS.get(position);
    }
}


