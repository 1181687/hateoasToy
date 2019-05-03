package pt.ipp.isep.dei.project.controllers.configurehouseinformationfromjsoncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.house.HouseDTO;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConfigureHouseInformationFromJsonController {

    @Autowired
    private HouseService houseService;
    private List<Object> houseObjects;
    private int numberOfNotImportedRooms;
    private int numberOfNotImportedGrids;
    private ProjectFileReader reader;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ConfigureHouseInformationFromJsonController(HouseService houseService) {
        this.houseService = houseService;
    }


    public int getNumberOfNotImportedRooms() {
        return this.numberOfNotImportedRooms;
    }

    public int getNumberOfNotImportedGrids() {
        return numberOfNotImportedGrids;
    }

    /**
     * receives the String Path (json) and creates the respective reader (json)
     * and saves it in controller private attribute reader
     *
     * @param path String path of the file to import
     */
    public void createReader(String path) {
        this.reader = Utils.createReader(path);
    }

    /**
     * receives a FileReader and reads
     *
     * @param file
     * @return
     */
    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        createReader(path);
        this.houseObjects = this.reader.readFile(file);
        return houseObjects;
    }

    public boolean importHouseInformation() {
        boolean imported = false;
        HouseDTO houseDTO = (HouseDTO) houseObjects.get(0);
        checkForInvalidRoomInfo(houseDTO.getRoomDTOList());
        checkForInvalidGridInfo(houseDTO);
        if (!(numberOfNotImportedGrids == houseDTO.getHouseGridDTOList().size()
                && numberOfNotImportedRooms == houseDTO.getRoomDTOList().size())) {
            houseService.updateHouseWithRoomsAndGrids(houseDTO);
            imported = true;
        }
        return imported;
    }

    private void checkForInvalidRoomInfo(List<RoomDTO> dtos) {
        for (Iterator<RoomDTO> roomDTOIterator = dtos.iterator(); roomDTOIterator.hasNext(); ) {
            RoomDTO roomDTO = roomDTOIterator.next();
            RoomId roomId = new RoomId(roomDTO.getRoomId());
            double height = roomDTO.getHeight();
            double length = roomDTO.getLength();
            double width = roomDTO.getWidth();
            if (Objects.isNull(roomId.getId())) {
                numberOfNotImportedRooms++;
                LOGGER.log(Level.WARNING, "Room was not imported because it has a null id.");
                roomDTOIterator.remove();
                continue;
            }
            if (this.houseService.roomExists(roomId)) {
                numberOfNotImportedRooms++;
                String invalidInfo = "id: " + roomId + ".";
                LOGGER.log(Level.WARNING, "Room was not imported because " + roomId.getId() + " already exists: " + invalidInfo);
                roomDTOIterator.remove();
                continue;
            }
            if (Double.isNaN(length) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(length, 0.0) ||
                    Double.isNaN(width) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(width, 0.0) ||
                    Double.isNaN(height) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(height, 0.0)) {
                numberOfNotImportedRooms++;
                String invalidInfo = "id: " + roomDTO.getRoomId() + ".";
                LOGGER.log(Level.WARNING, "Room was not imported because " + roomId.getId() + " does not have valid dimensions " + invalidInfo);
                roomDTOIterator.remove();
            }
        }
    }

    private void checkForInvalidGridInfo(HouseDTO houseDTO) {
        List<HouseGridDTO> houseGridDTOS = houseDTO.getHouseGridDTOList();
        List<RoomDTO> roomDTOS = houseDTO.getRoomDTOList();
        for (Iterator<HouseGridDTO> houseGridDTOIterator = houseGridDTOS.iterator(); houseGridDTOIterator.hasNext(); ) {
            HouseGridDTO houseGridDTO = houseGridDTOIterator.next();
            HouseGridId houseGridId = new HouseGridId(houseGridDTO.getName());
            if (Objects.isNull(houseGridId.getHousegridId())) {
                numberOfNotImportedGrids++;
                LOGGER.log(Level.WARNING, "House grid was not imported because it has a null id.");
                houseGridDTOIterator.remove();
                continue;
            }
            if (this.houseService.gridExists(houseGridId)) {
                numberOfNotImportedGrids++;
                String invalidInfo = "id: " + houseGridId + ".";
                LOGGER.log(Level.WARNING, "House grid was not imported because " + houseGridId.getHousegridId() + " already exists: " + invalidInfo);
                houseGridDTOIterator.remove();
            }
            houseGridDTO.getRoomDTOS().removeIf(roomDTO -> !roomDTOS.contains(roomDTO));
        }
    }
}


