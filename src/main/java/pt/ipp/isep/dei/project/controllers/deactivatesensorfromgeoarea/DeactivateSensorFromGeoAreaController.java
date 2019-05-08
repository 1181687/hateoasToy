package pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.ArrayList;
import java.util.List;

public class DeactivateSensorFromGeoAreaController {
    @Autowired
    private final GeographicalAreaService geoAreaService;
    @Autowired
    private final GeoAreaSensorService geoAreaSensorService;

    public DeactivateSensorFromGeoAreaController(GeographicalAreaService geoAreaService, GeoAreaSensorService geoAreaSensorService) {
        this.geoAreaService = geoAreaService;
        this.geoAreaSensorService = geoAreaSensorService;
    }

    public List<GeographicalAreaDTO> listOfGeographicalAreas() {
        List<GeographicalAreaDTO> dtoList = new ArrayList<>();
        for (GeographicalArea geoArea : this.geoAreaService.getGeoAreaList()) {
            dtoList.add(GeographicalAreaMapper.mapToDTOwithSensors(geoArea));
        }
        return dtoList;
    }

    public List<GeoAreaSensorDTO> listOfSensors(GeoAreaIdDTO geoAreaIdDTO) {
        return geoAreaSensorService.getSensorsByGeoAreaId(geoAreaIdDTO);
    }

    public boolean deactivateSensor(GeoAreaSensorDTO sensorDTO) {
        return geoAreaSensorService.deactivateSensor(sensorDTO);
    }
}