package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea.DeactivateSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.util.ArrayList;
import java.util.List;

public class DeactivateSensorFromGeoArea {
    private static final String EXIT = "\r0 - Exit";
    private DeactivateSensorFromGeoAreaController ctrl;
    private List<GeoAreaSensorDTO> geoAreaSensorDTOS;
    private List<GeoAreaIdDTO> geoAreas;
    private GeoAreaIdDTO chosenGeoAreaId;

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
            String label2 = printActiveSensorsInChosenGeoArea() + EXIT;
            int chosenSensor = InputValidator.getIntRange(label2, 0, this.geoAreaSensorDTOS.size()) - 1;
            if (chosenSensor == -1) {
                continue;
            }
            GeoAreaSensorDTO sensorDTO =this.geoAreaSensorDTOS.get(chosenSensor);
            deactivateSensor(sensorDTO);
        } while (flag);
    }


    private String printListOfGeoAreas() {
        List<GeoAreaIdDTO> geoAreas = getActiveSensorsGeoAreas();
        StringBuilder content = new StringBuilder();
        int iterator = 1;
        for (GeoAreaIdDTO geoArea : geoAreas) {
            content.append(iterator);
            content.append(" - ");
            content.append(" Id: ");
            content.append(geoArea.getId());
            content.append(" Type: ");
            content.append(geoArea.getGeoAreaType());
            content.append( "\n");
            iterator++;
        }
        return content.toString();
    }

    private List<GeoAreaIdDTO> getActiveSensorsGeoAreas(){
        List<GeoAreaIdDTO> geoAreas = new ArrayList<>();

        for (GeoAreaSensorDTO sensorDTO : geoAreaSensorDTOS) {
            GeoAreaIdDTO geoAreaId = sensorDTO.getParentGeoArea();
            if(!geoAreas.contains(geoAreaId)){
                geoAreas.add(geoAreaId);
            }
        }
        return geoAreas;
    }

    private String printActiveSensorsInChosenGeoArea() {
        if (this.geoAreaSensorDTOS.isEmpty()) {
            return ("There are no active Sensors in " + this.chosenGeoAreaId);
        }
        System.out.println("Which sensor of " + this.chosenGeoAreaId.getId() + " do you want to deactivate:");
        List<GeoAreaSensorDTO> sensors = getSensorsInChosenGeoArea();
        StringBuilder content = new StringBuilder();
        int iterator = 1;
        for (GeoAreaSensorDTO sensorDTO : sensors) {
            content.append(iterator + " - " + sensorDTO.getId() + "\n");
            iterator++;
        }
        return content.toString();
    }

    private void deactivateSensor(GeoAreaSensorDTO sensorDTO) {
        String confirmation = InputValidator.confirmValidation("\nThe sensor is going to be deactivated. It can be reactivated on a later date.\nConfirm? (Y/N)");
        if ("N".equalsIgnoreCase(confirmation)) {
            System.out.println("\nNo changes were made.\n");
        } else {
            if (ctrl.deactivateSensor(sensorDTO)) {
                this.geoAreaSensorDTOS = ctrl.listOfActiveSensorsDTOs();
                System.out.println("The sensor is now deactivated.\n");
            } else {
                System.out.println("It was not possible to deactivate " + sensorDTO.getId() + "\n");
            }

        }
    }

    private List<GeoAreaSensorDTO> getSensorsInChosenGeoArea(){
        List<GeoAreaSensorDTO> sensorDTOS = new ArrayList<>();
        for (GeoAreaSensorDTO sensorDTO : geoAreaSensorDTOS) {
            if(sensorDTO.getParentGeoArea().equals(chosenGeoAreaId)){
                sensorDTOS.add(sensorDTO);
            }
        }
        return sensorDTOS;
    }


}
