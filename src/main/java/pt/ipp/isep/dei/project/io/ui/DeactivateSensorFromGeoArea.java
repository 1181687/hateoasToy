package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea.DeactivateSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.ArrayList;
import java.util.List;

public class DeactivateSensorFromGeoArea {
    private DeactivateSensorFromGeoAreaController ctrl;
    private static final String EXIT = "\r0 - Exit";
    private List<GeographicalAreaDTO> geographicalAreaDTOS;
    private GeographicalAreaDTO geographicalAreaDTO;
    private List<GeoAreaSensorDTO> geoAreaSensorDTOList;

    public DeactivateSensorFromGeoArea(GeographicalAreaService geographicalAreaService, GeoAreaSensorService geoAreaSensorService) {
        this.ctrl = new DeactivateSensorFromGeoAreaController(geographicalAreaService,geoAreaSensorService);
        this.geographicalAreaDTOS = ctrl.listOfGeographicalAreas();
    }

    public void run() {
        if (geographicalAreaDTOS.isEmpty()) {
            System.out.println("\nThere are no geographical areas in the system. Please create or import some.\n");
            return;
        }
        boolean flag = true;
        do {
            String label = "\nFrom which geographical area do you want to deactivate a sensor: \n" + printListOfGeoAreas() + EXIT;
            int chosenGeoArea = InputValidator.getIntRange(label, 0, geographicalAreaDTOS.size()) - 1;
            if (chosenGeoArea == -1) {
                return;
            }
            geographicalAreaDTO = geographicalAreaDTOS.get(chosenGeoArea);
            GeoAreaIdDTO geoAreaId = positionToGeoAreaId(chosenGeoArea);
            geoAreaSensorDTOList = ctrl.listOfSensors(geoAreaId);
            if (getListOfActiveSensors().isEmpty()) {
                System.out.println("\nThere are no active Sensors in " + geographicalAreaDTO.getId());
                continue;
            }
            String label2 = printListOfActiveSensors() + EXIT;
            int chosenSensor = InputValidator.getIntRange(label2, 0, getListOfActiveSensors().size()) - 1;
            if (chosenSensor == -1) {
                continue;
            }
            GeoAreaSensorDTO sensorDTO = getListOfActiveSensors().get(chosenSensor);
            deactivateSensor(sensorDTO);
        } while (flag);
    }

    private String printListOfGeoAreas() {
        StringBuilder content = new StringBuilder();
        int iterator = 1;
        for (GeographicalAreaDTO geoAreaDTO : ctrl.listOfGeographicalAreas()) {
            content.append(iterator + " - " + geoAreaDTO.getId() + "\n");
            iterator++;
        }
        return content.toString();
    }

    private String printListOfActiveSensors() {
        if (getListOfActiveSensors().isEmpty()) {
            return ("There are no active Sensors in " + geographicalAreaDTO.getId());
        }
        System.out.println("Which sensor of " + geographicalAreaDTO.getId() + " do you want to deactivate:");
        StringBuilder content = new StringBuilder();
        int iterator = 1;
        for (GeoAreaSensorDTO sensorDTO : getListOfActiveSensors()) {
            content.append(iterator + " - " + sensorDTO.getId() + "\n");
            iterator++;
        }
        return content.toString();
    }

    private List<GeoAreaSensorDTO> getListOfActiveSensors() {
        List<GeoAreaSensorDTO> activeList = new ArrayList<>();
        for (GeoAreaSensorDTO sensorDTO : geoAreaSensorDTOList) {
            if (sensorDTO.getIsActive()) {
                activeList.add(sensorDTO);
            }
        }
        return activeList;
    }

    private boolean deactivateSensor(GeoAreaSensorDTO sensorDTO) {
        String confirmation = InputValidator.confirmValidation("\nThe sensor is going to be deactivated. It can be reactivated on a later date.\nConfirm? (Y/N)");
        if ("N".equalsIgnoreCase(confirmation)) {
            System.out.println("\nNo changes were made.\n");
            return true;
        } else {
            sensorDTO.setActive(false);
            if (ctrl.deactivateSensor(sensorDTO)) {
                System.out.println("The sensor is now deactivated.\n");
            } else {
                System.out.println("It was not possible to deactivate " + sensorDTO.getId() + "\n");
            }

        }
        return false;
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


}
