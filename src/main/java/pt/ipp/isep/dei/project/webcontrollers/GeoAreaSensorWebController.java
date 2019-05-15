package pt.ipp.isep.dei.project.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GeoAreaSensorWebController {
    @Autowired
    private GeographicalAreaService geographicalAreaService;
    @Autowired
    private SensorTypeService sensorTypeService;
    @Autowired
    private GeoAreaSensorService geoAreaSensorService;

    @GetMapping("/geoareas")
    public List<GeographicalAreaDTO> getGeographicalAreaDTOList() {
        List<GeographicalArea> geoAreaList = this.geographicalAreaService.getGeoAreaList();
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreaList) {
            GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTOwithSensors(geoArea);
            geographicalAreaDTOList.add(geographicalAreaDTO);
        }
        return geographicalAreaDTOList;
    }

    @GetMapping("/SensorType")
    public List<SensorTypeDTO> getSensorTypeDTOList() {
        List<SensorType> sensorTypeList = this.sensorTypeService.getSensorTypeList();
        List<SensorTypeDTO> sensorTypeDTOList = new ArrayList<>();
        for (SensorType sensorType : sensorTypeList) {
            SensorTypeDTO sensorTypeDTO = SensorTypeMapper.mapToDto(sensorType);
            sensorTypeDTOList.add(sensorTypeDTO);
        }
        return sensorTypeDTOList;
    }

    @PostMapping("/geoareasensors")
    public ResponseEntity<?> addGeoAreaSensor(@RequestBody GeoAreaSensorDTO geoAreaSensorDTO) {
        if (this.geoAreaSensorService.addGeoAreaSensor(GeoAreaSensorMapper.mapToEntity(geoAreaSensorDTO))){
            return new ResponseEntity<>("Sensor created", HttpStatus.OK);
        }
        return new ResponseEntity<>("The Sensor was not created",HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/GeoAreaSensor")
    public List<GeoAreaSensorDTO> getGeoAreaSensors() {
        return this.geoAreaSensorService.getGeoAreaSensors();
    }


}
