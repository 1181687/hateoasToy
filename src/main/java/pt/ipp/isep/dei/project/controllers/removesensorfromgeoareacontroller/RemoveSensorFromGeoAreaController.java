package pt.ipp.isep.dei.project.controllers.removesensorfromgeoareacontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import java.util.ArrayList;
import java.util.List;

public class RemoveSensorFromGeoAreaController {
    @Autowired
    private GeoAreaAggregateService geoAreaAggregateService;
    private GeographicalArea geographicalArea;

    /**
     * Constructor.
     *
     * @param geoAreaAggregateService Geographical area list to use.
     */
    public RemoveSensorFromGeoAreaController(GeoAreaAggregateService geoAreaAggregateService) {
        this.geoAreaAggregateService = geoAreaAggregateService;
    }

    /**
     * Method that outputs the list of GeoAreaDTOs based on the list of GeoAreas.
     *
     * @return List with GeographicalAreaDTOs ready to be sent to the UI.
     */
    public List<GeographicalAreaDTO> getGeoAreaDTO() {
        List<GeographicalAreaDTO> geoAreaDTOList = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreaAggregateService.getAllGeoAreas()) {
            geoAreaDTOList.add(GeographicalAreaMapper.mapToDTO(geoArea));
        }
        return geoAreaDTOList;
    }

    public void setGeographicalArea(GeographicalAreaDTO geographicalAreaDTO){
        this.geographicalArea = GeographicalAreaMapper.mapToEntity(geographicalAreaDTO);
    }

    /**
     * Method that outputs the list of SensorDTOs based on the list of Sensors in the GeoArea.
     *
     * @return List with SensorDTOs ready to be sent to the UI.
     */
    public List<GeoAreaSensorDTO> getSensorList(GeographicalAreaDTO geographicalAreaDTO) {
        setGeographicalArea(geographicalAreaDTO);
        List<GeoAreaSensorDTO> sensorDTOList = new ArrayList<>();
        for (GeoAreaSensor sensor : geoAreaAggregateService.getSensorsFromGeoArea(geographicalArea.getId())) {
            sensorDTOList.add(GeoAreaSensorMapper.mapToDTO(sensor));
        }
        return sensorDTOList;
    }

    /**
     * Method that removes a GeoAreaSensor from the GeoArea via its Id.
     *
     * @param geoAreaSensorDTO Id of the GeoAreaSensor to be removed.
     */
    public boolean removeSensor(GeoAreaSensorDTO geoAreaSensorDTO) {
        return geoAreaAggregateService.removeSensor(GeoAreaSensorMapper.mapToEntity(geoAreaSensorDTO));
    }
}
