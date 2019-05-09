package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddGeoAreaToAnotherGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.List;

/**
 * US007 As an Administrator, I want to add an existing geographical area to another one
 * (e.g. add city of Porto to the district of Porto).
 */

public class AddGeoAreaToAnotherGeoArea {
    private AddGeoAreaToAnotherGeoAreaController controller;
    private List<GeographicalAreaDTO> geoAreaDTOList;

    public AddGeoAreaToAnotherGeoArea(GeographicalAreaService geoAreaService) {
        this.controller = new AddGeoAreaToAnotherGeoAreaController(geoAreaService);
        this.geoAreaDTOList = controller.getGeoAreaDTO();
    }

    public String getGeoAreaDTOListToString(boolean useCriteria) {
        StringBuilder geoAreaListContent = new StringBuilder();
        int numberOfGeoArea = 1;
        for (GeographicalAreaDTO geoAreaDto:geoAreaDTOList) {
            geoAreaListContent.append(numberOfGeoArea + " - Id: " + geoAreaDto.getId());
            geoAreaListContent.append(", Type: " + geoAreaDto.getType());
            geoAreaListContent.append(", Latitude: " + geoAreaDto.getLatitude());
            geoAreaListContent.append(", Longitude: " + geoAreaDto.getLongitude());
            if (useCriteria && !(controller.checkIfGeoAreaDoesntHaveAnInsertedArea(geoAreaDto))) {
                geoAreaListContent.append(", Inserted in: " + geoAreaDto.getParentGeoArea().getId()+", "+ geoAreaDto.getParentGeoArea().getType());
            }
            geoAreaListContent.append("\n");
            numberOfGeoArea++;
        }
        return geoAreaListContent.toString();
    }

    public void run() {
        String label1 = "Please choose the number that corresponds to the geographical area you wish to include in " +
                "another geographical area:\n" + getGeoAreaDTOListToString(true);
        int firstOption = InputValidator.getIntRange(label1, 1, geoAreaDTOList.size())-1;
        GeographicalAreaDTO mainGeoAreaDTO = geoAreaDTOList.get(firstOption);
        if (controller.checkIfGeoAreaDoesntHaveAnInsertedArea(mainGeoAreaDTO)) {
            geoAreaDTOList.remove(geoAreaDTOList.get(firstOption));
            String label2 = ("Choose the number of the geographical area in which the previous geographical area is included.\n"+
                    getGeoAreaDTOListToString(false));
            int secondOption = InputValidator.getIntRange(label2, 1, geoAreaDTOList.size())-1;
            GeographicalAreaDTO parentGeoAreaDTO = geoAreaDTOList.get(secondOption);
            //controller.addParentGeoAreaToMainGeoArea(mainGeoAreaDTO, parentGeoAreaDTO);
            mainGeoAreaDTO.setParentGeoArea(parentGeoAreaDTO);
            controller.saveGeoArea(mainGeoAreaDTO);
            //geoAreaDTOList=controller.getGeoAreaDTO();
            //firstGeoAreaDTO.setParentGeoArea(geoAreaDTOList.get(secondOption));
            System.out.println("Success!\n");
            //controller.addGeoAreaInASpecificPosition(positionOfSecondOption, controller.getGeoAreaInTheList(positionOfSecondOption));
        } else
            System.out.println("The geographical area you have chosen is already included in another area. Try another geographical area.");
    }
}