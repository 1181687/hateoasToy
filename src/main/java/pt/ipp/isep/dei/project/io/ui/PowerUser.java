package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.House;

public class PowerUser {
    House mhouse;

    public PowerUser(House house) {
        this.mhouse = house;
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
                    GetDevicesInHouseGrid ui160 = new GetDevicesInHouseGrid(mhouse);
                    ui160.run();
                    break;
                case 2:
                    GetNominalPowerOfGrid ui172 = new GetNominalPowerOfGrid(mhouse);
                    ui172.run();
                    break;
                case 3:
                    GetNominalPowerOfARoom ui230 = new GetNominalPowerOfARoom(mhouse);
                    ui230.run();
                    break;
                case 4:
                    DeativateDeviceFromRoom ui222 = new DeativateDeviceFromRoom(mhouse);
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
                    GetNominalPowerRoomsDevices ui705 = new GetNominalPowerRoomsDevices(mhouse);
                    ui705.run();
                    break;
                case 2:
                    EstimateEnergyOfWaterHeater ui752 = new EstimateEnergyOfWaterHeater(mhouse);
                    ui752.run();
                    break;
                case 3:
                    GetEnergyConsumptionOfRoom ui721 = new GetEnergyConsumptionOfRoom(mhouse);
                    ui721.run();
                    break;
                case 4:
                    GetEnergyConsumptionOfAGrid ui722 = new GetEnergyConsumptionOfAGrid(mhouse);
                    ui722.run();
                    break;
            }
            option = Menu.powerUserEnergyConsumtionMenu();
        }
    }
}

