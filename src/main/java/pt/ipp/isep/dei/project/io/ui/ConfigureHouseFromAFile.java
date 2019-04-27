package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.configurehouseinformationfromjsoncontroller.ConfigureHouseInformationFromJsonController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseDTO;
import pt.ipp.isep.dei.project.services.HouseService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

public class ConfigureHouseFromAFile {

    private ConfigureHouseInformationFromJsonController controller;

    public ConfigureHouseFromAFile(House house, HouseService houseService) {
        this.controller = new ConfigureHouseInformationFromJsonController(house, houseService);
    }

    public void run() throws FileNotFoundException {

        // Write the path
        String path = InputValidator.getString("Please specify the name of the file to import.");
        File file = new File(path);
        if (!isValidFormat(path)) {
            System.out.println("\nERROR: Please insert a valid format.\n");
            return;
        }

        if (!file.exists()) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        List<Object> dtoList = controller.readFile(file, path);
        if (Objects.isNull(dtoList) || dtoList.isEmpty()) {
            System.out.println("\nThe information on the file is not valid to be imported.\n");
            return;
        }

        // Content of the choosen file
        String confirmOptions = "\n This is the content of the chosen file: \n";

        StringBuilder content = new StringBuilder();
        content.append(confirmOptions);
        content.append("\n");
        for (Object house : dtoList) {
            HouseDTO houseDTO = (HouseDTO) house;
            String id = houseDTO.getAddressDTO().getCompleteAddress();
            int numberOfRooms = houseDTO.getRoomDTOList().size();
            int numberOfGrids = houseDTO.getHouseGridDTOList().size();
            content.append(" > ");
            content.append(id);
            content.append("\n");
            content.append(" - Number of rooms: ");
            content.append(numberOfRooms);
            content.append("\n");
            content.append(" - Number of Grids: ");
            content.append(numberOfGrids);
            content.append("\n");
        }

        System.out.println(content);


        // Import confirmation
        String importConfirmation = InputValidator.confirmValidation("Do you want to configure the House with this information? (Y/N)");
        if ("Y".equalsIgnoreCase(importConfirmation)) {

            try {
                if (controller.importHouseInformation()) {
                    int notImportedRooms = controller.getNumberOfNotImportedRooms();
                    int notImportedGrids = controller.getNumberOfNotImportedGrids();
                    if (notImportedRooms > 0 || notImportedGrids > 0) {
                        System.out.println("\nThe file was partially imported/not imported.");
                    }
                    if (notImportedRooms > 0) {
                        System.out.println("There were " + notImportedRooms + " rooms that were not imported, due to invalid information.\n");
                        return;
                    }

                    if (notImportedGrids > 0) {
                        System.out.println("There were " + notImportedGrids + " grids that were not imported, due to invalid information.\n");
                        return;
                    }
                    System.out.println("\n The House was configured with success.\n");

                } else {
                    System.out.println("The House is already configured.\n");
                }

            } catch (Exception e) {
                System.out.println("\nSorry! The file doesn't contain valid information. It was not possible to import it.\n");
            }

        } else {
            System.out.println("The House was not configured. \n");
        }
    }

    private boolean isValidFormat(String fileName) {
        return fileName.endsWith(".json");
    }
}


