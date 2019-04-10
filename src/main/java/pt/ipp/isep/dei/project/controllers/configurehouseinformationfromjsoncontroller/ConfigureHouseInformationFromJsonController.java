package pt.ipp.isep.dei.project.controllers.configurehouseinformationfromjsoncontroller;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseDTO;
import pt.ipp.isep.dei.project.model.house.HouseMapper;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class ConfigureHouseInformationFromJsonController {

    private House house;
    private List<Object> houseObjects;
    private ProjectFileReader reader;


    public ConfigureHouseInformationFromJsonController(House house) {
        this.house = house;
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

    /**
     * This method imports the house object to be imported
     *
     * @param
     * @return boolean
     */
    public boolean importHouseInformation() {
        boolean imported = false;
        HouseDTO houseDTO = (HouseDTO) houseObjects.get(0);
        house = HouseMapper.mapToEntity(houseDTO, house);
        writeAddressToFile(house.getAddress());
        if (Objects.nonNull(house)) {
            imported = true;
        }
        return imported;
    }

    public void writeAddressToFile(Address address) {
        Properties prop = new Properties();
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;

        try {
            fileIn = new FileInputStream("Configuration.properties");
            prop.load(fileIn);

            prop.setProperty("completeAddress", address.getCompleteAddress());

            Location houseLocation = address.getLocation();
            if (Objects.nonNull(houseLocation)) {
                prop.setProperty("latitude", houseLocation.getLatitude().toString());
                prop.setProperty("longitude", houseLocation.getLongitude().toString());
                prop.setProperty("altitude", houseLocation.getElevation().toString());
            }

            GeographicalArea insertedGeoArea = address.getInsertedGeoArea();
            if (Objects.nonNull(insertedGeoArea)) {
                prop.setProperty("id", insertedGeoArea.getId().toString());
            }

            fileOut = new FileOutputStream("Configuration.properties");

            prop.store(fileOut, null);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(fileOut)) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


