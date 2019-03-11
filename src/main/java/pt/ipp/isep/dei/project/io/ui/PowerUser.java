package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.house.House;

public class PowerUser {
    House house;

    public PowerUser(House house) {
        this.house = house;
    }

    public void runPowerUserMenu() {
        int option = Menu.powerUserMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {
            switch (option) {
                case 1:
                    runPowerUserHouseConfig();
                    break;
                case 2:
                    runPowerUserEnergyConsumptionManagement();
                    break;
            }
            option = Menu.powerUserMenu();
        }
    }

    public void runPowerUserHouseConfig() {
        int option = Menu.powerUserHouseMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {
            switch (option) {
                case 1:
                    GetDevicesInHouseGrid ui160 = new GetDevicesInHouseGrid(house);
                    ui160.run();
                    break;
                case 2:
                    GetNominalPowerOfGrid ui172 = new GetNominalPowerOfGrid(house);
                    ui172.run();
                    break;
                case 3:
                    GetNominalPowerOfARoom ui230 = new GetNominalPowerOfARoom(house);
                    ui230.run();
                    break;
                case 4:
                    DeactivateDeviceFromRoom ui222 = new DeactivateDeviceFromRoom(house);
                    ui222.run();
                    break;
            }
            option = Menu.powerUserHouseMenu();
        }
    }

    public void runPowerUserEnergyConsumptionManagement() {
        int option = Menu.powerUserEnergyConsumtionMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {
            switch (option) {
                case 1:
                    GetNominalPowerRoomsDevices ui705 = new GetNominalPowerRoomsDevices(house);
                    ui705.run();
                    break;
                case 2:
                    EstimateEnergyOfWaterHeater ui752 = new EstimateEnergyOfWaterHeater(house);
                    ui752.run();
                    break;
                case 3:
                    GetEnergyConsumptionOfRoom ui721 = new GetEnergyConsumptionOfRoom(house);
                    ui721.run();
                    break;
                case 4:
                    GetEnergyConsumptionOfAGrid ui722 = new GetEnergyConsumptionOfAGrid(house);
                    ui722.run();
                    break;
                case 5:
                    GetEnergyConsumptionDevice ui720 = new GetEnergyConsumptionDevice(house);
                    ui720.run();
                    break;
                case 6:
                    GetEnergyConsumptionDataSeries ui730 = new GetEnergyConsumptionDataSeries(house);
                    ui730.run();
                    break;

            }
            option = Menu.powerUserEnergyConsumtionMenu();
        }
    }
}

