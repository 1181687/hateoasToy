package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddGeoAreaToAnotherGeoAreaController;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;

/**
 * US007 As an Administrator, I want to add an existing geographical area to another one
 * (e.g. add city of Porto to the district of Porto).
 */

public class AddGeoAreaToAnotherGeoArea {
    private AddGeoAreaToAnotherGeoAreaController ctrl;

    public AddGeoAreaToAnotherGeoArea(GeographicalAreaList lista) {
        this.ctrl = new AddGeoAreaToAnotherGeoAreaController(lista);
    }

    public void run() {
        String label1 = "Choose the number that corresponds to the geographical area you wish to include in another geographical area.";
        System.out.println(ctrl.getListToString(true));
        int firstOption = InputValidator.getIntRange(label1, 1, ctrl.getListSize());
        int positionOfFirstOption = firstOption - 1;
        if (ctrl.isGeoAreaInsertedinAnotherArea(ctrl.getGeoAreaInTheList(positionOfFirstOption))) {
            System.out.println("Choose the number of the geographical area in which the previous geographical area is included.");
            ctrl.removeGeoArea(ctrl.getGeoAreaInTheList(positionOfFirstOption));
            System.out.println(ctrl.getListToString(true));
            int secondOption = InputValidator.getIntRange(label1, 1, ctrl.getListSize());
            int positionOfSecondOption = secondOption - 1;
            ctrl.getGeoAreaInTheList(positionOfSecondOption).setInsertedIn(ctrl.getGeoAreaInTheList(positionOfSecondOption));
            System.out.println("Success!");
            ctrl.addGeoAreaInASpecificPosition(positionOfSecondOption, ctrl.getGeoAreaInTheList(positionOfSecondOption));
        } else
            System.out.println("The geographical area you have chosen is already included in another area. Try another geographical area.");
    }
}