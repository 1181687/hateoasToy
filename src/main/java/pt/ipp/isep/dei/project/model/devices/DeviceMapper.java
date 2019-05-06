package pt.ipp.isep.dei.project.model.devices;

import pt.ipp.isep.dei.project.model.house.RoomMapper;

public final class DeviceMapper {

    private DeviceMapper() {
        // Empty
    }

    public static DeviceDTO mapToDTO(Device device) {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setName(device.getName());
        deviceDTO.setDeviceType(device.getType());
        deviceDTO.setLocation(RoomMapper.mapToDTO(device.getLocation()));
        deviceDTO.setNominalPower(device.getNominalPower());
        return deviceDTO;
    }
}
