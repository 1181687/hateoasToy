package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea.DeactivateSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;

import java.util.List;

public class DeactivateSensorFromGeoArea {
    private DeactivateSensorFromGeoAreaController ctrl;
    private static final String EXIT = "\r0 - Exit";
    private List<GeographicalAreaDTO> geographicalAreaDTOS;
    private GeographicalAreaDTO geographicalAreaDTO;

    public DeactivateSensorFromGeoArea(GeographicalAreaList geographicalAreaList) {
        this.ctrl = new DeactivateSensorFromGeoAreaController(geographicalAreaList);
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
            String label2 = printListOfSensors() + EXIT;
            int chosenSensor = InputValidator.getIntRange(label2, 0, geographicalAreaDTO.getSensors().size()) - 1;
            if (chosenSensor == -1) {
                continue;
            }
            SensorDTO sensorDTO = geographicalAreaDTO.getSensors().get(chosenSensor);
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

    private String printListOfSensors() {
        System.out.println("Which sensor of " + geographicalAreaDTO.getId() + " do you want to deactivate:\n");
        StringBuilder content = new StringBuilder();
        int iterator = 1;
        for (SensorDTO sensorDTO : geographicalAreaDTO.getSensors()) {
            if (sensorDTO.isActive()) {
                content.append(iterator + " - " + sensorDTO.getId() + "\n");
                iterator++;
            }
        }
        return content.toString();
    }

    private boolean deactivateSensor(SensorDTO sensorDTO) {
        String confirmation = InputValidator.confirmValidation("\nThe sensor is going to be deactivated. It can be reactivated on a later date.\nConfirm? (Y/N)");
        if ("N".equalsIgnoreCase(confirmation)) {
            System.out.println("\nNo changes were made.\n");
            return true;
        } else {
            sensorDTO.setActive(false);
            if(ctrl.deactivateSensor(sensorDTO)){
                System.out.println("The sensor is now deactivated.\n");
            }
            else {
                System.out.println("It was not possible to deactivate "+sensorDTO.getId()+"\n");
            }

        }
        return false;
    }


}
