package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

public class RegularUser {

    GeographicalAreaTypeList geographicalAreaTypeList;
    GeographicalAreaList geographicalAreaList;
    SensorTypeList sensorTypeList;
    House house;
    SensorType sensorTypeTemperature;

    public RegularUser(GeographicalAreaTypeList geographicalAreaTypeList, GeographicalAreaList geographicalAreaList, SensorTypeList sensorTypeList, House house, SensorType sensorTypeTemperature) {
        this.geographicalAreaTypeList = geographicalAreaTypeList;
        this.geographicalAreaList = geographicalAreaList;
        this.sensorTypeList = sensorTypeList;
        this.house = house;
        this.sensorTypeTemperature = sensorTypeTemperature;
    }

    public void runRegularUserHouseArea() {
        int option = Menu.regularUserHouseAreaMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    GetTotalAndAverageRainfallAndCurrentTempHouseArea ui600 = new GetTotalAndAverageRainfallAndCurrentTempHouseArea(house);
                    ui600.run3();
                    break;
                case 2:
                    GetTotalAndAverageRainfallAndCurrentTempHouseArea ui620 = new GetTotalAndAverageRainfallAndCurrentTempHouseArea(house);
                    ui620.run();
                    break;
                case 3:
                    GetTotalAndAverageRainfallAndCurrentTempHouseArea ui623 = new GetTotalAndAverageRainfallAndCurrentTempHouseArea(house);
                    ui623.run2();
                    break;
            }
            option = Menu.regularUserHouseAreaMenu();
        }
    }

    public void runRegularUserRooms() {
        int option = Menu.regularUserRoomsMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    GetCurrentAndMaxTempRoom ui605 = new GetCurrentAndMaxTempRoom(house, sensorTypeTemperature);
                    ui605.run1();
                    break;
                case 2:
                    GetCurrentAndMaxTempRoom ui610 = new GetCurrentAndMaxTempRoom(house, sensorTypeTemperature);
                    ui610.run2();
                    break;
                case 3:
                    // JUST TESTING
                    EstimateEnergyOfWaterHeater ui752 = new EstimateEnergyOfWaterHeater(house);
                    ui752.run();
                    break;
            }
            option = Menu.regularUserRoomsMenu();
        }
    }

    public void runRegularUserOption() {
        int option = Menu.regularUserMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    runRegularUserHouseArea();
                    break;
                case 2:
                    runRegularUserRooms();
                    break;
            }
            option = Menu.regularUserMenu();
        }
    }

    public void runRegularUserPowerConsuptionManagement() {
        int option = Menu.regularUserPowerConsuptionManagement();
        if (option == 0) {
            return;
        }
        while (option != 0) {
            switch (option) {
                case 1:
                    GetNominalPowerOfARoom ui230 = new GetNominalPowerOfARoom(house);
                    ui230.run();
                    break;
            }
        }
    }
}