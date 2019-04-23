package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.List;

public class GetNominalPowerOfGrid {

    private GetNominalPowerController controller;
    private List<HouseGridDTO> gridDTOS;

    public GetNominalPowerOfGrid(RoomAggregateService roomAggregateService) {
        this.controller = new GetNominalPowerController(roomAggregateService);
    }

    public void run() {
        this.gridDTOS = controller.getAllGrids();
        String exit = "0- Exit";
        if (isGridListEmpty()) {
            System.out.println("There are no grids in the house. Please, add one");
        } else {
            String label1 = "Please select a House Grid to see its total nominal power: \n" + getGridsToString() + exit;
            int gridListLength = numberOfGrids();
            int position = InputValidator.getIntRange(label1, 0, gridListLength)-1;
            if (position == -1) {
                return;
            }
            HouseGridDTO chosenGrid = this.gridDTOS.get(position);
            //controller.getHouseGridByPosition(position);

            double nominalPower = controller.getHouseGridTotalNominalPower(chosenGrid.getId());
            System.out.println("The total nominal power of the selected grid is " + nominalPower + "kW.");

        }
    }

    private boolean isGridListEmpty(){
        return this.gridDTOS.isEmpty();
    }

    private int numberOfGrids(){
        return this.gridDTOS.size();
    }

    private String getGridsToString(){
        StringBuilder content = new StringBuilder();
        int iterator = 1;

        for (HouseGridDTO gridDTO : gridDTOS) {
            content.append(iterator);
            content.append(" - ");
            content.append(gridDTO.getId());
            content.append("\n");
        }
        return content.toString();
    }
}
