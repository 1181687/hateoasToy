package pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;

import java.util.ArrayList;
import java.util.List;

public class DeactivateSensorFromGeoAreaController {
    @Autowired
    private GeographicalAreaList geoAreaList;

    public DeactivateSensorFromGeoAreaController(GeographicalAreaList geoAreaList) {
        this.geoAreaList = geoAreaList;
    }

    public List<GeographicalAreaDTO> listOfGeographicalAreas() {
        List<GeographicalAreaDTO> dtoList = new ArrayList<>();
        for (GeographicalArea geoArea : this.geoAreaList.getGeoAreaList()) {
            dtoList.add(GeographicalAreaMapper.mapToDTOwithSensors(geoArea));
        }
        return dtoList;
    }

    public boolean deactivateSensor(GeoAreaSensorDTO sensorDTO) {
        GeoAreaSensor sensor = geoAreaList.getSensorById(sensorDTO.getId());
        if (sensor.deactivateDevice()) {
            geoAreaList.updateRepository();
            return true;
        }
        return false;
    }


}