package pt.ipp.isep.dei.project.model.house;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.LocationMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;

import java.util.Objects;

public class AddressMapper {


    /**
     * Constructor.
     */
    protected AddressMapper() {
        // empty
    }

    /**
     * Method that creates a new AddressDTO.
     *
     * @return AddressDTO.
     */
    public static AddressDTO newAddressDTO() {
        return new AddressDTO();
    }


    /**
     * Method that creates an AddressDTO based on a set of information.
     *
     * @return AddressDTO.
     */
    public static AddressDTO newDTO() {

        AddressDTO addressDTO = new AddressDTO();
        return addressDTO;
    }


    /**
     * Method that turns an AddressDTO into an address.
     *
     * @param addressDTO to be used.
     * @return address with the required information.
     */
    public static Address mapToEntity(AddressDTO addressDTO) {
        if (Objects.isNull(addressDTO)) {
            return null;
        }
        Location location = null;
        if (Objects.nonNull(addressDTO.getLocation())) {
            location = LocationMapper.mapToEntity(addressDTO.getLocation());
        }
        GeographicalArea insertedGeoArea = null;
        if (Objects.nonNull(addressDTO.getInsertedGeoArea())) {
            insertedGeoArea = GeographicalAreaMapper.mapToEntity(addressDTO.getInsertedGeoArea());
        }
        return new Address(addressDTO.getCompleteAddress(), location, insertedGeoArea);
    }
}

