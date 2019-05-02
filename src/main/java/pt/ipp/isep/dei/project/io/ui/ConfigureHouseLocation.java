package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.ConfigureHouseLocationController;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.house.AddressDTO;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.List;


public class ConfigureHouseLocation {

    /**
     * US101 As an Administrator, I want to configure the location of the house
     */


    private ConfigureHouseLocationController controller;

    private List<GeographicalAreaDTO> geoAreaDTOS;


    public ConfigureHouseLocation(House house, HouseService houseService, GeographicalAreaService geographicalAreaService) {
        this.controller = new ConfigureHouseLocationController(house, houseService, geographicalAreaService);
        this.geoAreaDTOS = this.controller.getGeoAreaList();
    }


    /**
     * method that configures a new House location
     */
    public void run() {

        AddressDTO addressDTO = new AddressDTO();

        if (controller.isGeoAreaRepositoryEmpty()) {
            System.out.println("\nThere's no geographical areas in the system. Please create or import some.\n");
            return;
        }

        String label1 = "Introduce the complete address of the House.";
        String completeAddress = InputValidator.getString(label1);
        addressDTO.setCompleteAddress(completeAddress);

        String label2 = "Introduce the latitude of the House Location (valid numbers between -90 and 90).";
        double latitude = InputValidator.getDoubleRange(label2, -90, 90);

        String label3 = "Introduce the longitude of the House Location (valid numbers between -180 and 180).";
        double longitude = InputValidator.getDoubleRange(label3, -180, 180);

        String label4 = "Introduce the altitude of the House Location.";
        double elevation = InputValidator.getDouble(label4);


        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(latitude);
        locationDTO.setLongitude(longitude);
        locationDTO.setElevation(elevation);
        addressDTO.setLocation(locationDTO);

        String listOfGeoAreaDTO = this.getGeoAreaListToString();
        String label5 = "In which geographical area is the House included?\n" + listOfGeoAreaDTO;
        int uiIDGeoArea = InputValidator.getIntRange(label5, 1, this.getGeoAreaListSize()) - 1;

        GeographicalAreaDTO geographicalAreaDTO = this.geoAreaDTOS.get(uiIDGeoArea);
        addressDTO.setInsertedGeoArea(geographicalAreaDTO);


        controller.configureHouseLocation(addressDTO);


        StringBuilder content = new StringBuilder();
        content.append("The House was configured with success!\n");
        System.out.println(content.toString());
    }


    /**
     * get method
     *
     * @return the content of the geo area list
     */
    private String getGeoAreaListToString() {
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (GeographicalAreaDTO geoArea : this.geoAreaDTOS) {
            content.append(number);
            content.append(" - ");
            content.append(geoArea.getId());
            content.append("\n");
            number++;
        }
        return content.toString();
    }


    /**
     * get method
     *
     * @return the size of the geo area list
     */
    private int getGeoAreaListSize() {
        return this.geoAreaDTOS.size();
    }
}

