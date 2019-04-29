package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.removesensorfromgeoareacontroller.RemoveSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.List;

public class RemoveSensorFromGeoArea {
    private RemoveSensorFromGeoAreaController controller;
    private List<GeographicalAreaDTO> geographicalAreaDTOS;
    private List<GeoAreaSensorDTO> sensorDTOS;
    private final static String EXIT = "\r0 - Exit";

    /**
     * Constructor.
     *
     * @param geographicalAreaService List that contains all of the geographical areas in the system.
     */
    public RemoveSensorFromGeoArea(GeographicalAreaService geographicalAreaService) {
        controller = new RemoveSensorFromGeoAreaController(geographicalAreaService);
        geographicalAreaDTOS = controller.getGeographicalAreaService();
    }

    /**
     * RUN!
     */
    public void run() {
        if (geographicalAreaDTOS.isEmpty()) {
            System.out.println("\nThere are no geographical areas in the system. Please create or import some.\n");
            return;
        }
        boolean flag = true;
        do {
            String label = "\nPlease choose a geographical area:\n" + geoAreaDTOsToString() + EXIT;
            int chosenGeoArea = InputValidator.getIntRange(label, 0, geographicalAreaDTOS.size()) - 1;
            if (chosenGeoArea == -1) {
                return;
            }
            String geoAreaId = positionToGeoAreaId(chosenGeoArea);
            controller.setGeoAreaById(geoAreaId);
            sensorDTOS = controller.getSensorList();
            if (sensorDTOS.isEmpty()) {
                System.out.println("\nThere are no sensors in the selected geographical area. Please create or import some.");
                continue;
            }
            String label2 = "\nPlease choose a sensor:\n" + sensorDTOsToString() + EXIT;
            int chosenSensor = InputValidator.getIntRange(label2, 0, sensorDTOS.size()) - 1;
            if (chosenSensor == -1) {
                continue;
            }
            String sensorId = positionToSensorId(chosenSensor);
            String confirmation = InputValidator.confirmValidation("\nAll the data relative to the sensor will be removed too and can not be recovered anymore. Confirm? (Y/N)");
            if ("N".equalsIgnoreCase(confirmation)) {
                System.out.println("\nNo changes were made.");
                continue;
            }
            //controller.removeSensor(sensorId);
            flag = false;
        } while (flag);
        System.out.println("\nThe sensor was removed with success!\n");
    }

    /**
     * Method that generates a list of GeographicalAreaDTO.
     *
     * @return String representing the list.
     */
    private String geoAreaDTOsToString() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (GeographicalAreaDTO geoAreaDTO : geographicalAreaDTOS) {
            content.append(numberInTheList + " - " + geoAreaDTO.getDescription() + "\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * Method that turns the UI's Id corresponding to a GeographicalAreaDTO into the real Id of the GeographicalAreaDTO.
     *
     * @param uiId Position in the list.
     * @return String corresponding to the Id of the GeographicalAreaDTO.
     */
    private String positionToGeoAreaId(int uiId) {
        return geographicalAreaDTOS.get(uiId).getId();
    }

    /**
     * Method that generates a list of GeoAreaSensorDTO.
     *
     * @return String representing the list.
     */
    private String sensorDTOsToString() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (GeoAreaSensorDTO sensorDTO : sensorDTOS) {
            content.append(numberInTheList + " - " + sensorDTO.getName() + "\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * Method that turns the UI's Id corresponding to a GeoAreaSensorDTO into the real Id of the GeoAreaSensorDTO.
     *
     * @param uiId Position in the list.
     * @return String corresponding to the Id of the GeoAreaSensorDTO.
     */
    private String positionToSensorId(int uiId) {
        return sensorDTOS.get(uiId).getId();
    }
}
