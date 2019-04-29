package pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.ArrayList;
import java.util.List;

public class DeactivateSensorFromGeoAreaController {
    @Autowired
    private GeographicalAreaService geoAreaService;

    public DeactivateSensorFromGeoAreaController(GeographicalAreaService geoAreaService) {
        this.geoAreaService = geoAreaService;
    }

    public List<GeographicalAreaDTO> listOfGeographicalAreas() {
        List<GeographicalAreaDTO> dtoList = new ArrayList<>();
        for (GeographicalArea geoArea : this.geoAreaService.getGeoAreaList()) {
            dtoList.add(GeographicalAreaMapper.mapToDTOwithSensors(geoArea));
        }
        return dtoList;
    }

    public boolean deactivateSensor(GeoAreaSensorDTO sensorDTO) {
        GeoAreaSensor sensor = geoAreaService.getSensorById(sensorDTO.getId());
        if (sensor.deactivateDevice()) {
            geoAreaService.updateRepository();
            return true;
        }
        return false;
    }
}