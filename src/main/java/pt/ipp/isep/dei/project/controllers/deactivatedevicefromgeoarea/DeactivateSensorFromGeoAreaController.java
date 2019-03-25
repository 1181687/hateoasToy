package pt.ipp.isep.dei.project.controllers.deactivatedevicefromgeoarea;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;

import java.util.ArrayList;
import java.util.List;

public class DeactivateSensorFromGeoAreaController {
    private GeographicalAreaList geoAreaList;
    private GeographicalArea geoArea;
    private GeographicalAreaDTO geoAreaDTO;

    public DeactivateSensorFromGeoAreaController(GeographicalAreaList geoAreaList) {
        this.geoAreaList = geoAreaList;
    }

    public List<GeographicalAreaDTO> listOfGeographicalAreas() {
        List<GeographicalAreaDTO> geoAreaList = new ArrayList<>();
        for (GeographicalArea geoArea : this.geoAreaList.getGeoAreaList()) {
            geoAreaList.add(GeographicalAreaMapper.mapToDTO(geoArea));
        }
        return geoAreaList;
    }

}
