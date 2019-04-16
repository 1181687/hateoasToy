package pt.ipp.isep.dei.project.model.house.powersource;

public final class PowerSourceTypeMapper {

    protected PowerSourceTypeMapper() {
        //empty
    }

    public static PowerSourceTypeDTO mapToDTO(PowerSourceType powerSourceType) {
        PowerSourceTypeDTO dto = new PowerSourceTypeDTO();
        dto.setType(powerSourceType.getTypeOfPowerSource());
        return dto;
    }
}
