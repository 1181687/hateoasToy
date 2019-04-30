package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.HouseService;

public class RegularUser {

    GeoAreaTypeService geographicalAreaTypeService;
    GeographicalAreaService geographicalAreaService;
    SensorTypeList sensorTypeList;
    SensorTypeId sensorTypeTemperature = new SensorTypeId("Temperature");
    HouseService houseService;

    public RegularUser(GeoAreaTypeService geographicalTypeService, GeographicalAreaService geographicalAreaService, SensorTypeList sensorTypeList, HouseService houseService) {
        this.geographicalAreaTypeService = geographicalTypeService;
        this.geographicalAreaService = geographicalAreaService;
        this.sensorTypeList = sensorTypeList;
        this.houseService = houseService;

    }

    public void runRegularUserHouseArea() {
        int option = Menu.regularUserHouseAreaMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {

            switch (option) {
                case 1:
                    GetCurrentTemperatureHouseArea ui600 = new GetCurrentTemperatureHouseArea(houseService);
                    ui600.run();
                    break;
                case 2:
                    GetAverageRainfall ui620 = new GetAverageRainfall(houseService);
                    ui620.run();
                    break;
                case 3:
                    GetTotalRainfall ui623 = new GetTotalRainfall(houseService);
                    ui623.run();
                    break;
                case 4:
                    GetDayWithHighestTemperatureAmplitude ui633 = new GetDayWithHighestTemperatureAmplitude(houseService);
                    ui633.run();
                    break;
                case 5:
                    GetFirstHottestDayHouseArea ui631 = new GetFirstHottestDayHouseArea(houseService);
                    ui631.run();
                    break;
                case 6:
                    GetLastColdestDayHouseArea ui630 = new GetLastColdestDayHouseArea(houseService);
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
                    GetCurrentTemperatureRoom ui605 = new GetCurrentTemperatureRoom(houseService, sensorTypeTemperature);
                    ui605.run();
                    break;
                case 2:
                    GetMaxTemperatureRoom ui610 = new GetMaxTemperatureRoom(houseService, sensorTypeTemperature);
                    ui610.run();
                    break;
                case 3:
                    EstimateEnergyOfWaterHeater ui752 = new EstimateEnergyOfWaterHeater(houseService);
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
                GetNominalPowerOfARoom ui230 = new GetNominalPowerOfARoom(houseService);
                ui230.run();
            }
        }
    }
}