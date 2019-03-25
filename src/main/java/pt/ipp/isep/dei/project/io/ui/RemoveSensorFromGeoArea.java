package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.removesensorfromgeoareacontroller.RemoveSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;

import java.util.List;

public class RemoveSensorFromGeoArea {
    private RemoveSensorFromGeoAreaController controller;
    private List<GeographicalAreaDTO> geographicalAreaDTOS;
    private List<SensorDTO> sensorDTOS;
    private final String EXIT = "\r0 - Exit";

    /**
     * Constructor.
     *
     * @param geographicalAreaList List that contains all of the geographical areas in the system.
     */
    public RemoveSensorFromGeoArea(GeographicalAreaList geographicalAreaList) {
        controller = new RemoveSensorFromGeoAreaController(geographicalAreaList);
        geographicalAreaDTOS = controller.getGeographicalAreaList();
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
            String exit = "\r0 - Exit";
            String label = "\nPlease choose a geographical area:\n" + geoAreaDTOsToString() + exit;
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
            String label2 = "\nPlease choose a sensor:\n" + sensorDTOsToString() + exit;
            int chosenSensor = InputValidator.getIntRange(label2, 0, sensorDTOS.size()) - 1;
            if (chosenSensor == -1) {
                continue;
            }
            String sensorId = positionToSensorId(chosenSensor);
            String confirmation = InputValidator.confirmValidation("\nAll the date relative to the sensor will be removed too and can not be recovered anymore. Confirm? (Y/N)");
            if ("N".equalsIgnoreCase(confirmation)) {
                System.out.println("\nNo changes were made.");
                continue;
            }
            controller.removeSensor(sensorId);
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
     * Method that generates a list of SensorDTO.
     *
     * @return String representing the list.
     */
    private String sensorDTOsToString() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (SensorDTO sensorDTO : sensorDTOS) {
            content.append(numberInTheList + " - " + sensorDTO.getName() + "\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * Method that turns the UI's Id corresponding to a SensorDTO into the real Id of the SensorDTO.
     *
     * @param uiId Position in the list.
     * @return String corresponding to the Id of the SensorDTO.
     */
    private String positionToSensorId(int uiId) {
        return sensorDTOS.get(uiId).getId();
    }

    private void chooseGeoArea() {
        String exit = "\r0 - Exit";
        String label = "\nPlease choose a geographical area:\n" + geoAreaDTOsToString() + exit;
        int chosenGeoArea = InputValidator.getIntRange(label, 0, geographicalAreaDTOS.size()) - 1;
        if (chosenGeoArea == -1) {
            return;
        }
    }
}
