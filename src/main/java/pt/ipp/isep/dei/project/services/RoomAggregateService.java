package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.RoomAggregateRepository;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoomAggregateService {

    @Autowired
    private RoomAggregateRepository roomAggregateRepository;

    @Autowired
    private HouseGridAggregateService houseGridAggregateService;


    /**
     * method that get the String content Name and Location of all devices in the list,
     * grouped by device type.
     *
     * @return String with Device Name and Location grouped by Type.
     */
    public String getContentNameLocationOrderedByType(List<Device> deviceList) {
        StringBuilder content = new StringBuilder();
        Map<String, List<Device>> byDeviceType = deviceList.stream()
                .collect(Collectors.groupingBy(Device::getType));


        for (Map.Entry<String, List<Device>> entry : byDeviceType.entrySet()) {
            content.append(entry.getKey());
            content.append("\n");
            for (Device dev : entry.getValue()) {

                content.append("- Device Name: ");
                content.append(dev.getName());
                content.append(", Location: ");
                content.append(dev.getLocation().getRoomId());
                content.append(".\n");
            }
            content.append("\n");
        }
        return content.toString();
    }


    /**
     * method that get the String content Name and Location of all devices in the list, of a given housegrid,
     * and grouped by device type.
     *
     * @return String with devices Names and Location grouped by Type.
     */
    public String getDeviceListContentNameTypeLocationByGrid(String houseGridId) {
        List<Device> deviceList = this.getDeviceListByHouseGridById(houseGridId);
        return getContentNameLocationOrderedByType(deviceList);
    }


    public List<Room> getRoomListByHouseGrid(String id) {
        HouseGridId houseGridId = new HouseGridId(id);
        return roomAggregateRepository.findAllByHouseGridIdEquals(houseGridId);
    }

    /**
     * method that gets a List of all devices in a room of the HouseGrid
     *
     * @param
     * @return List <Device>
     */
    public List<Device> getDeviceListByRoomOfGridById(String houseGridId) {

        List<Device> deviceListOfRoomOfHGrid = new ArrayList<>();
        for (Room room : this.getRoomListByHouseGrid(houseGridId)) {
            deviceListOfRoomOfHGrid = room.getDeviceList();
        }
        return deviceListOfRoomOfHGrid;
    }

    public List<Device> getDeviceListByHouseGridById(String houseGridId) {
        List<Device> deviceList = new ArrayList<>();
        for (Device device : this.getDeviceListByRoomOfGridById(houseGridId)) {
            deviceList.add(device);
        }
        return deviceList;
    }

    public List<HouseGrid> getAllGrids() {
        return houseGridAggregateService.getAllGrids();
    }

    /**
     * Method that searches for a grid by its Id. If it exists in the repo, the grid is returned, if not, null is returned.
     *
     * @param id Id to be used.
     * @return HouseGrid or null.
     */
    public HouseGrid getGridById(HouseGridId id) {
        return houseGridAggregateService.getGridById(id);
    }

    public boolean isHouseGridListEmpty() {
        return this.houseGridAggregateService.numberOfHouseGridsInRepository();
    }
}
