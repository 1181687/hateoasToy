package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea.DeactivateSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.util.ArrayList;
import java.util.List;

public class DeactivateSensorFromGeoArea {
    private static final String EXIT = "\r0 - Exit";
    private DeactivateSensorFromGeoAreaController ctrl;
    private List<GeoAreaSensorDTO> geoAreaSensorDTOS;
    private List<String> geoAreas;
    private String chosenGeoAreaId;

    public DeactivateSensorFromGeoArea(GeoAreaAggregateService geographicalAreaService) {
        this.ctrl = new DeactivateSensorFromGeoAreaController(geographicalAreaService);
        this.geoAreaSensorDTOS = ctrl.listOfActiveSensorsDTOs();
    }

    public void run() {
        if (geoAreaSensorDTOS.isEmpty()) {
            System.out.println("\nThere are no sensors in the system. Please create or import some.\n");
            return;
        }
        boolean flag = true;
        do {
            this.geoAreas = getActiveSensorsGeoAreas();
            String label = "\nFrom which geographical area do you want to deactivate a sensor: \n" + printListOfGeoAreas() + EXIT;
            int chosenGeoArea = InputValidator.getIntRange(label, 0, geoAreas.size()) - 1;
            if (chosenGeoArea == -1) {
                return;
            }
            this.chosenGeoAreaId = this.geoAreas.get(chosenGeoArea);
         /*   if (getListOfActiveSensors().isEmpty()) {
                System.out.println("\nThere are no active Sensors in " + geographicalAreaDTO.getId());
                continue;
            }*/
            String label2 = printListOfActiveSensors() + EXIT;
            int chosenSensor = InputValidator.getIntRange(label2, 0, this.geoAreaSensorDTOS.size()) - 1;
            if (chosenSensor == -1) {
                continue;
            }
            GeoAreaSensorDTO sensorDTO =this.geoAreaSensorDTOS.get(chosenSensor);
            deactivateSensor(sensorDTO);
        } while (flag);
    }


    private String printListOfGeoAreas() {
        List<String> geoAreas = getActiveSensorsGeoAreas();
        StringBuilder content = new StringBuilder();
        int iterator = 1;
        for (String geoArea : geoAreas) {
            content.append(iterator);
            content.append(" - ");
            content.append(geoArea);
            content.append( "\n");
            iterator++;
        }
        return content.toString();
    }

    private List<String> getActiveSensorsGeoAreas(){
        List<String> geoAreas = new ArrayList<>();

        for (GeoAreaSensorDTO sensorDTO : geoAreaSensorDTOS) {
            String geoAreaId = sensorDTO.getGeoAreaId();
            if(!geoAreas.contains(geoAreaId)){
                geoAreas.add(geoAreaId);
            }
        }
        return geoAreas;
    }

    private String printListOfActiveSensors() {
        if (this.geoAreaSensorDTOS.isEmpty()) {
            return ("There are no active Sensors in " + this.chosenGeoAreaId);
        }
        System.out.println("Which sensor of " + this.chosenGeoAreaId + " do you want to deactivate:");
        StringBuilder content = new StringBuilder();
        int iterator = 1;
        for (GeoAreaSensorDTO sensorDTO : geoAreaSensorDTOS) {
            content.append(iterator + " - " + sensorDTO.getId() + "\n");
            iterator++;
        }
        return content.toString();
    }

  /*  private List<GeoAreaSensorDTO> getListOfActiveSensors() {
        List<GeoAreaSensorDTO> activeList = new ArrayList<>();
        for (GeoAreaSensorDTO sensorDTO : geographicalAreaDTO.getSensors()) {
            if (sensorDTO.isActive()) {
                activeList.add(sensorDTO);
            }
        }
        return activeList;
    }*/

    private void deactivateSensor(GeoAreaSensorDTO sensorDTO) {
        String confirmation = InputValidator.confirmValidation("\nThe sensor is going to be deactivated. It can be reactivated on a later date.\nConfirm? (Y/N)");
        if ("N".equalsIgnoreCase(confirmation)) {
            System.out.println("\nNo changes were made.\n");
        } else {
            sensorDTO.setActive(false);
            if (ctrl.deactivateSensor(sensorDTO)) {
                System.out.println("The sensor is now deactivated.\n");
            } else {
                System.out.println("It was not possible to deactivate " + sensorDTO.getId() + "\n");
            }

        }
    }




}
