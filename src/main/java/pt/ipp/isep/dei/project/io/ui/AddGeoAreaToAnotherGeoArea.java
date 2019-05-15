package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddGeoAreaToAnotherGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdDTO;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.List;

/**
 * US007 As an Administrator, I want to add an existing geographical area to another one
 * (e.g. add city of Porto to the district of Porto).
 */

public class AddGeoAreaToAnotherGeoArea {
    private AddGeoAreaToAnotherGeoAreaController controller;
    private List<GeoAreaIdDTO> geoAreaIdDTOList;

    public AddGeoAreaToAnotherGeoArea(GeographicalAreaService geoAreaService) {
        this.controller = new AddGeoAreaToAnotherGeoAreaController(geoAreaService);
        this.geoAreaIdDTOList = controller.getGeoAreaIdDTO();
    }

    public String getGeoAreaDTOListToString(boolean useCriteria) {
        StringBuilder geoAreaListContent = new StringBuilder();
        int numberOfGeoArea = 1;
        for (GeoAreaIdDTO geoAreaIdDto : geoAreaIdDTOList) {
            geoAreaListContent.append(numberOfGeoArea + " - Id: " + geoAreaIdDto.getId());
            geoAreaListContent.append(", Type: " + geoAreaIdDto.getGeoAreaType());
            geoAreaListContent.append(", Latitude: " + geoAreaIdDto.getLocationDTO().getLatitude());
            geoAreaListContent.append(", Longitude: " + geoAreaIdDto.getLocationDTO().getLongitude());
            if (useCriteria && !controller.isInsertedInNull(geoAreaIdDto)) {
                geoAreaListContent.append(", Inserted in: " + controller.getParentGeoAreaId(geoAreaIdDto).getId() + ", " + controller.getParentGeoAreaId(geoAreaIdDto).getGeoAreaType());
            }
            geoAreaListContent.append("\n");
            numberOfGeoArea++;
        }
        return geoAreaListContent.toString();
    }

    public void run() {
        String label1 = "Please choose the number that corresponds to the geographical area you wish to include in " +
                "another geographical area:\n" + getGeoAreaDTOListToString(true);
        int firstOption = InputValidator.getIntRange(label1, 1, geoAreaIdDTOList.size()) - 1;
        GeoAreaIdDTO mainGeoAreaIdDTO = geoAreaIdDTOList.get(firstOption);
        if (controller.isInsertedInNull(mainGeoAreaIdDTO)) {
            geoAreaIdDTOList.remove(geoAreaIdDTOList.get(firstOption));
            String label2 = ("Choose the number of the geographical area in which the previous geographical area is included.\n" +
                    getGeoAreaDTOListToString(false));
            int secondOption = InputValidator.getIntRange(label2, 1, geoAreaIdDTOList.size()) - 1;
            GeoAreaIdDTO parentGeoAreaIdDTO = geoAreaIdDTOList.get(secondOption);
            if (controller.addParentGeoAreaToMainGeoArea(mainGeoAreaIdDTO, parentGeoAreaIdDTO)) {
                System.out.println("Success!\n");
            } else
                System.out.println("It was not possible to insert the first selected geographical area in the second one. " +
                        "It happened because the geographical area you have chosen is already\nincluded in another area or because the first chosen geographical" +
                        "area includes the second one chosen. Try another geographical area.\n");
        }
    }
}