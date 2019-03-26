package pt.ipp.isep.dei.project.controllers.deactivatedevicefromgeoarea;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;

import java.util.ArrayList;
import java.util.List;

public class DeactivateSensorFromGeoAreaController {
    private GeographicalAreaList geoAreaList;

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

    public boolean deactivateDevice(SensorDTO sensorDTO) {
        Sensor sensor = geoAreaList.getSensorById(sensorDTO.getId());
        sensor.setActive(sensorDTO.isActive());
        return true;
    }

}