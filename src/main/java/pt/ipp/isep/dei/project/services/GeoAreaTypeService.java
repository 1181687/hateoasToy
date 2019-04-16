package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoAreaTypeService {
    @Autowired
    private GeoAreaTypeRepository geoAreaTypeRepository;
/*

    */
/**
     * constructor that receives a list of geographical areas type.
     *
     * @param geoAreaTypeList
     *//*

    public GeographicalAreaTypeList(List<GeographicalAreaType> geoAreaTypeList) {
        this.geoAreaTypeList = geoAreaTypeList;
    }

    */
/**
     * empty constructor of geographical areas type.
     *//*

    public GeographicalAreaTypeList() {
    }

    */
/**
     * method that get a geo area type list.
     *
     * @return a geographical areas types list
     *//*

    public List<GeographicalAreaType> getGeoAreaTypeList() {
        return this.geoAreaTypeList;
    }

    */
/**
     * method that add a type of geographical area.
     * @param geographicalAreaType
     * @return true if a geographicalAreaType is added. If not, return false.
     *//*

    public boolean addTypeOfGeoAreaToTheList(GeographicalAreaType geographicalAreaType) {
        if (!(this.geoAreaTypeList.contains(geographicalAreaType))) {
            this.geoAreaTypeList.add(geographicalAreaType);
            return true;
        }
        return false;
    }

    */
/**
     * method that create a new type of geo area.
     *
     * @param newType
     * @return a new type of geo area.
     *//*

    public GeographicalAreaType newTypeOfGeoArea(String newType) {
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(newType);
        return new GeographicalAreaType(geoAreaTypeId);
    }

    */
/**
     * method that get the list of geo area types
     * @return a list of geo area types.
     *//*

    */
/*public List<String> getListOfGeoAreaTypes() {
        List<String> listOfGeoAreaTypes = new ArrayList<>();
        for (GeographicalAreaType object : geoAreaTypeList) {
            listOfGeoAreaTypes.add(object.getStringOfTypeOfGeoArea());
        }
        return listOfGeoAreaTypes;
    }*/

    public boolean createGeoAreaType(GeographicalAreaType geographicalAreaType){
        if (!geoAreaTypeRepository.existsById(geographicalAreaType.getGeoAreaType())){
            geoAreaTypeRepository.save(new GeographicalAreaType(geographicalAreaType.getGeoAreaType()));
            return true;
        }
        return false;
    }

    public List<GeographicalAreaType> getListOfGeoAreaTypes(){
        List<GeographicalAreaType> geographicalAreaTypeList = new ArrayList<>();
        for (GeographicalAreaType geographicalAreaType : geoAreaTypeRepository.findAll()) {
            geographicalAreaTypeList.add(geographicalAreaType);
        }
        return geographicalAreaTypeList;
    }

}

