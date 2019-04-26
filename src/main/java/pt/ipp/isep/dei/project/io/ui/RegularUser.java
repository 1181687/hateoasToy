package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;

public class RegularUser {

    GeographicalAreaTypeList geographicalAreaTypeList;
    GeographicalAreaService geographicalAreaService;
    SensorTypeList sensorTypeList;
    House house;
    SensorType sensorTypeTemperature = new SensorType("Temperature");

    public RegularUser(GeographicalAreaTypeList geographicalAreaTypeList, GeographicalAreaService geographicalAreaService, SensorTypeList sensorTypeList, House house) {
        this.geographicalAreaTypeList = geographicalAreaTypeList;
        this.geographicalAreaService = geographicalAreaService;
        this.sensorTypeList = sensorTypeList;
        this.house = house;
    }

    public void runRegularUserHouseArea() {
        int option = Menu.regularUserHouseAreaMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    GetCurrentTemperatureHouseArea ui600 = new GetCurrentTemperatureHouseArea(house);
                    ui600.run();
                    break;
                case 2:
                    GetAverageRainfall ui620 = new GetAverageRainfall(house);
                    ui620.run();
                    break;
                case 3:
                    GetTotalRainfall ui623 = new GetTotalRainfall(house);
                    ui623.run();
                    break;
                case 4:
                    GetDayWithHighestTemperatureAmplitude ui633 = new GetDayWithHighestTemperatureAmplitude(house);
                    ui633.run();
                    break;
                case 5:
                    GetFirstHottestDayHouseArea ui631 = new GetFirstHottestDayHouseArea(house);
                    ui631.run();
                    break;
                case 6:
                    GetLastColdestDayHouseArea ui630 = new GetLastColdestDayHouseArea(house);
                    ui630.run();
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
                    GetCurrentTemperatureRoom ui605 = new GetCurrentTemperatureRoom(house, sensorTypeTemperature);
                    ui605.run();
                    break;
                case 2:
                    GetMaxTemperatureRoom ui610 = new GetMaxTemperatureRoom(house, sensorTypeTemperature);
                    ui610.run();
                    break;
                case 3:
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
            if (option == 1) {
                runRegularUserHouseArea();
            }
            if (option == 2) {
                runRegularUserRooms();
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
            if (option == 1) {
                GetNominalPowerOfARoom ui230 = new GetNominalPowerOfARoom(house);
                ui230.run();
            }
        }
    }
}