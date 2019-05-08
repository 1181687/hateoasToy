package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.house.AddressDTO;
import pt.ipp.isep.dei.project.model.house.AddressMapper;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.ArrayList;
import java.util.List;

public class ConfigureHouseLocationController {

    private HouseService houseService;
    private House house;
    private GeographicalAreaService geographicalAreaService;

    public ConfigureHouseLocationController(House house, HouseService houseService, GeographicalAreaService geographicalAreaService) {
        this.houseService = houseService;
        this.house = house;
        this.geographicalAreaService = geographicalAreaService;
    }

    public void configureHouseLocation(AddressDTO addressDTO) {
        house.setAddress(AddressMapper.mapToEntity(addressDTO));
        houseService.saveHouse(house);
    }


    public boolean isGeoAreaRepositoryEmpty() {
        return this.geographicalAreaService.isGeoAreaRepositoryEmpty();
    }

    public List<GeographicalAreaDTO> getGeoAreaList() {
        List<GeographicalArea> geoAreas = this.geographicalAreaService.getAllGeoAreas();
        List<GeographicalAreaDTO> geoAreaDTOS = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreas) {
            GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(geoArea);
            geoAreaDTOS.add(geographicalAreaDTO);
        }
        return geoAreaDTOS;
    }
}
