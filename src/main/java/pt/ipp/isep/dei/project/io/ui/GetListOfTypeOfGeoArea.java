package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfTypeOfGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeDTO;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import java.util.List;

public class GetListOfTypeOfGeoArea {

    /**
     * US004 As an Administrator, I want to get a list of existing geographical areas of a given type.
     */
    private GetListOfTypeOfGeoAreaController controller;


    public GetListOfTypeOfGeoArea(GeoAreaService geoAreaService) {
        this.controller = new GetListOfTypeOfGeoAreaController(geoAreaService);
    }

    public void run() {
        String exitMenu = "0 - Return to the previous menu";
        List<GeographicalAreaTypeDTO> geographicalAreaTypeDTOS = controller.getListOfGeoAreaTypes();
        if (geographicalAreaTypeDTOS.isEmpty()) {
            System.out.println("There are no geographical area types defined. Please create a new type.");
            return;
        }
        System.out.println("Choose the Geographical Area type you wish to see: ");
        int listIterator = 1;
        for (GeographicalAreaTypeDTO geographicalAreaTypeDTO : geographicalAreaTypeDTOS) {
            System.out.println(listIterator + " - " + geographicalAreaTypeDTO.getGeoAreaType());
            listIterator++;
        }
        System.out.println(exitMenu);

        int option = InputValidator.getIntRange("\nChoose an option", 1, geographicalAreaTypeDTOS.size()) - 1;

        if (option == -1) {
            return;
        }

        String chosenType = geographicalAreaTypeDTOS.get(option).getGeoAreaType();

        List<GeographicalAreaDTO> geoAreaByType = controller.getListOfGeographicalAreasByType(chosenType);

        if (geoAreaByType.isEmpty()) {
            System.out.println("No Geographical Areas of the chosen type were found.");
        } else {
            if (geoAreaByType.size() == 1) {
                System.out.println("The Geographical Area of the type " + chosenType + " is:");
            }
            if (geoAreaByType.size() > 1) {
                System.out.println("The Geographical Areas of the type " + chosenType + " are:");
            }
            for (int i = 0; i < geoAreaByType.size(); i++) {
                System.out.println(geoAreaByType.get(i));
            }
            System.out.println();
        }
    }
}
