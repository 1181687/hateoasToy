package pt.ipp.isep.dei.project.model;

public class GeographicalAreaMapping {

    public GeographicalAreaMapping() {
        // empty
    }

    public static GeographicalAreaDTO mapToAreaGeoDTO(GeographicalArea geographicalArea) {
        GeographicalAreaDTO geographicalAreaDTO = new GeographicalAreaDTO();
        geographicalAreaDTO.setGeoAreaName(geographicalArea.getNameOfGeoArea());
        geographicalAreaDTO.setGeographicalAreaType(geographicalArea.getGeoAreaType());
        geographicalAreaDTO.setInsertedIn(geographicalArea.getInsertedIn());
        geographicalAreaDTO.setLocation(geographicalArea.getLocation());
        geographicalAreaDTO.setAreaShape(geographicalArea.getAreaShape());
        return geographicalAreaDTO;
    }

}
