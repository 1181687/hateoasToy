package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.removesensorfromgeoareacontroller.RemoveSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorIdDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorIdMapper;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
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
    public RemoveSensorFromGeoArea(GeographicalAreaService geographicalAreaService, GeoAreaSensorService geoAreaSensorService) {
        controller = new RemoveSensorFromGeoAreaController(geographicalAreaService, geoAreaSensorService);
        geographicalAreaDTOS = controller.getGeographicalAreaDTO();
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
            GeoAreaIdDTO geoAreaId = positionToGeoAreaId(chosenGeoArea);
            sensorDTOS = controller.getSensorList(geoAreaId);
            if (sensorDTOS.isEmpty()) {
                System.out.println("\nThere are no sensors in the selected geographical area. Please create or import some.");
                continue;
            }
            String label2 = "\nPlease choose a sensor:\n" + sensorDTOsToString() + EXIT;
            int chosenSensor = InputValidator.getIntRange(label2, 0, sensorDTOS.size()) - 1;
            if (chosenSensor == -1) {
                continue;
            }
            SensorIdDTO sensorId = positionToSensorId(chosenSensor);
            String confirmation = InputValidator.confirmValidation("\nAll the data relative to the sensor will be removed too and can not be recovered anymore. Confirm? (Y/N)");
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
    private GeoAreaIdDTO positionToGeoAreaId(int uiId) {
        GeoAreaIdDTO geoAreaIdDTO = GeoAreaIdMapper.newDTO();
        geoAreaIdDTO.setId(geographicalAreaDTOS.get(uiId).getId());
        geoAreaIdDTO.setLocationDTO(geographicalAreaDTOS.get(uiId).getLocation());
        geoAreaIdDTO.setGeoAreaType(geographicalAreaDTOS.get(uiId).getType());
        return geoAreaIdDTO;
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
    private SensorIdDTO positionToSensorId(int uiId) {
        SensorIdDTO sensorIdDTO = SensorIdMapper.newDTO();
        sensorIdDTO.setId(sensorDTOS.get(uiId).getId());
        return sensorIdDTO;
    }
}
