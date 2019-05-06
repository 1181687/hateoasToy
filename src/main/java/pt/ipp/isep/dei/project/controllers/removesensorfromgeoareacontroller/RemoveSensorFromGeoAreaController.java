package pt.ipp.isep.dei.project.controllers.removesensorfromgeoareacontroller;

import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorIdDTO;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.ArrayList;
import java.util.List;

public class RemoveSensorFromGeoAreaController {
    private GeographicalAreaService geographicalAreaService;
    private GeoAreaSensorService geoAreaSensorService;
    private GeographicalArea geographicalArea;

    /**
     * Constructor.
     *
     * @param geographicalAreaService Geographical area list to use.
     */
    public RemoveSensorFromGeoAreaController(GeographicalAreaService geographicalAreaService,GeoAreaSensorService geoAreaSensorService) {
        this.geographicalAreaService = geographicalAreaService;
        this.geoAreaSensorService = geoAreaSensorService;
    }

    /**
     * Method that outputs the list of GeoAreaDTOs based on the list of GeoAreas.
     *
     * @return List with GeographicalAreaDTOs ready to be sent to the UI.
     */
    public List<GeographicalAreaDTO> getGeographicalAreaDTO() {
        List<GeographicalAreaDTO> geoAreaDTOList = new ArrayList<>();
        for (GeographicalArea geoArea : geographicalAreaService.getGeoAreaList()) {
            GeographicalAreaDTO geoAreaDTO = GeographicalAreaMapper.mapToDTOwithSensors(geoArea);
            geoAreaDTOList.add(geoAreaDTO);
        }
        return geoAreaDTOList;
    }

    /**
     * Method that sets the GeoArea by its Id.
     *
     * @param geoAreaId Id of the GeoArea.
     */
    public void setGeoAreaById(String geoAreaId) {
        geographicalArea = geographicalAreaService.getGeoAreaById(geoAreaId);
    }

    /**
     * Method that outputs the list of SensorDTOs based on the list of Sensors in the GeoArea.
     *
     * @return List with SensorDTOs ready to be sent to the UI.
     */
    public List<GeoAreaSensorDTO> getSensorList() {
        GeoAreaIdDTO geoAreaIdDTO = GeoAreaIdMapper.mapToDTO(geographicalArea.getId());
        return geoAreaSensorService.getSensorsByGeoAreaId(geoAreaIdDTO);
    }

    /**
     * Method that removes a GeoAreaSensor from the GeoArea via its Id.
     *
     * @param sensorIdDTO Id of the GeoAreaSensor to be removed.
     */
    public boolean removeSensor(SensorIdDTO sensorIdDTO) {
        return geoAreaSensorService.removeSensor(sensorIdDTO);
    }
}
