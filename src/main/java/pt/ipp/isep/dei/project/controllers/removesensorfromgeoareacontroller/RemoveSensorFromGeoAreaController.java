package pt.ipp.isep.dei.project.controllers.removesensorfromgeoareacontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;

import java.util.ArrayList;
import java.util.List;

public class RemoveSensorFromGeoAreaController {
    @Autowired
    private GeographicalAreaList geographicalAreaList;
    private GeographicalArea geographicalArea;

    /**
     * Constructor.
     *
     * @param geographicalAreaList Geographical area list to use.
     */
    public RemoveSensorFromGeoAreaController(GeographicalAreaList geographicalAreaList) {
        this.geographicalAreaList = geographicalAreaList;
    }

    /**
     * Method that outputs the list of GeoAreaDTOs based on the list of GeoAreas.
     *
     * @return List with GeographicalAreaDTOs ready to be sent to the UI.
     */
    public List<GeographicalAreaDTO> getGeographicalAreaList() {
        List<GeographicalAreaDTO> geoAreaDTOList = new ArrayList<>();
        for (GeographicalArea geoArea : geographicalAreaList.getGeoAreaList()) {
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
        geographicalArea = geographicalAreaList.getGeoAreaById(geoAreaId);
    }

    /**
     * Method that outputs the list of SensorDTOs based on the list of Sensors in the GeoArea.
     *
     * @return List with SensorDTOs ready to be sent to the UI.
     */
    public List<GeoAreaSensorDTO> getSensorList() {
        List<GeoAreaSensorDTO> sensorDTOList = new ArrayList<>();
        for (GeoAreaSensor sensor : geographicalArea.getSensorListInTheGeographicArea().getListOfSensors()) {
            GeoAreaSensorDTO sensorDTO = GeoAreaSensorMapper.mapToDTO(sensor);
            sensorDTOList.add(sensorDTO);
        }
        return sensorDTOList;
    }

    /**
     * Method that removes a GeoAreaSensor from the GeoArea via its Id.
     *
     * @param sensorId Id of the GeoAreaSensor to be removed.
     */
    public boolean removeSensor(String sensorId) {
        if(geographicalArea.removeSensorById(sensorId)){
            this.geographicalAreaList.updateRepository();
            return true;
        }
        return false;
    }
}
