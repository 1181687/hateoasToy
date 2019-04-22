package pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea;

import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.util.ArrayList;
import java.util.List;

public class DeactivateSensorFromGeoAreaController {

    private GeoAreaAggregateService geoAreaService;

    public DeactivateSensorFromGeoAreaController(GeoAreaAggregateService geoAreaService) {
        this.geoAreaService = geoAreaService;
    }

    public List<GeoAreaSensorDTO> listOfActiveSensorsDTOs() {
        List<GeoAreaSensorDTO> dtoList = new ArrayList<>();
        for (GeoAreaSensor sensor : this.geoAreaService.getActiveSensors()) {
            dtoList.add(GeoAreaSensorMapper.mapToDTO(sensor));
        }
        return dtoList;
    }

    public boolean deactivateSensor(GeoAreaSensorDTO sensorDTO) {
        GeoAreaSensorId sensorId = new GeoAreaSensorId(sensorDTO.getId());
        if (this.geoAreaService.deactivateSensor(sensorId)) {
            return true;
        }
        return false;
    }


}