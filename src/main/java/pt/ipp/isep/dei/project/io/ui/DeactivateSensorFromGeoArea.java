package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.deactivatedevicefromgeoarea.DeactivateSensorFromGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;

import java.util.List;

public class DeactivateSensorFromGeoArea {
    private DeactivateSensorFromGeoAreaController ctrl;

    public DeactivateSensorFromGeoArea(GeographicalAreaList geographicalAreaList) {
        this.ctrl = new DeactivateSensorFromGeoAreaController(geographicalAreaList);
    }

    public void run() {
        List<GeographicalAreaDTO> geographicalAreaDTOS = ctrl.listOfGeographicalAreas();
        for (GeographicalAreaDTO geoAreaDTO : geographicalAreaDTOS) {
            System.out.println(geoAreaDTO.getId());
        }
    }

}
