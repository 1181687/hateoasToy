package pt.ipp.isep.dei.project.controllers.deactivatesensorfromgeoarea;

import pt.ipp.isep.dei.project.services.GeoAreaService;

public class DeactivateSensorFromGeoAreaController {

    private GeoAreaService geoAreaList;

    public DeactivateSensorFromGeoAreaController(GeoAreaService geoAreaList) {
        this.geoAreaList = geoAreaList;
    }

 /*   public List<GeographicalAreaDTO> listOfGeographicalAreas() {
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
    }*/


}