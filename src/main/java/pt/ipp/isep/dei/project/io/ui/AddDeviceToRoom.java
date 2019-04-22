package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddDeviceToRoomController;
import pt.ipp.isep.dei.project.model.devices.DeviceDTO;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.List;


public class AddDeviceToRoom {

    private AddDeviceToRoomController controller;
    private List<RoomDTO> roomDTOList;
    private RoomDTO selectedRoom;
    private List<String> deviceTypes;

    public AddDeviceToRoom(RoomAggregateService roomAggregateService) {
        this.controller = new AddDeviceToRoomController(roomAggregateService);
        this.roomDTOList = controller.getRoomListContent();
        this.deviceTypes = controller.getDeviceTypeListToString();
    }

    public void run() {
        boolean flag = true;
        int indexSelectedRoom = -1;
        while (flag) {
            //SELECT A ROOM
            String exit = "\r0 - Exit";
            if (roomDTOList.size() != 0) {
                String label2 = "Please choose a room to attach a new device: \n" + getRoomDTOListToString() + exit;
                indexSelectedRoom = InputValidator.getIntRange(label2, 0, roomDTOList.size()) - 1;
                if (indexSelectedRoom == -1) {
                    return;
                }
                selectedRoom = roomDTOList.get(indexSelectedRoom);
                controller.getRoom(selectedRoom.getId());
            } else {
                System.out.println("There are no rooms available. Please create one\n");
            }
            //SELECT A DEVICE TYPE
            String label0 = "Please select the Device Type: \n" + getDeviceTypeListToString() + "\r0 - Exit";
            int selectedType = InputValidator.getIntRange(label0, 0, controller.getNumberOfDeviceTypes())-1;
            if (selectedType == -1) {
                continue;
            }
            //CREATE A DEVICE
            String selectedDeviceType = deviceTypes.get(selectedType);
            createDevice(selectedDeviceType);
            flag = false;
        }
        //SHOW DEVICE LIST IN THE ROOM - Y/N?
        showUpdatedDeviceListOfRoom(selectedRoom);
    }

    private String getRoomDTOListToString() {
        List<RoomDTO> getRoomListDTO = roomDTOList;
        StringBuilder content = new StringBuilder();
        int listOrderByNumber = 1;
        for (RoomDTO roomDTO : getRoomListDTO) {
            content.append(listOrderByNumber);
            content.append(" - Id: ");
            content.append(roomDTO.getId());
            content.append(", Description: ");
            content.append(roomDTO.getDescription()+"\n");
            listOrderByNumber++;
        }
        return content.toString();
    }

    private String getDeviceTypeListToString(){
        StringBuilder content = new StringBuilder();
        int listOrderByNumber = 1;
        for (String type : deviceTypes) {
            content.append(listOrderByNumber);
            content.append(" - ");
            content.append(type+"\n");
            listOrderByNumber++;
        }
        return content.toString();
    }

    private void createDevice(String selectedType) {

        String option = selectedType;

        String exit = "Exit";

        while (!option.equals(exit)) {
            switch (option) {
                case "Fridge":
                    //CREATION OF A FRIGDE
                    creationOfFridge();
                    option = exit;

                    break;

                case "Lamp":
                    //CREATION OF A LAMP
                    creationOfLamp();
                    option = exit;

                    break;

                case "DishWasher":
                    //CREATION OF A DISH WASHER
                    creationOfDishWasher();
                    option = exit;

                    break;
                case "WashingMachine":
                    //CREATION OF A WASHING MACHINE
                    creationOfWashingMachine();

                    option = exit;
                    break;
                case "ElectricWaterHeater":
                    //CREATION OF A ELECTRIC WATER HEATER
                    creationOfElectricWaterHeater();

                    option = exit;
                    break;
                case "Kettle":
                    //CREATION OF A WATER KETTLE
                    creationOfKettle();

                    option = exit;
                    break;
                case "ElectricOven":
                    //CREATION OF A ELECTRIC OVEN
                    creationOfElectricOven();

                    option = exit;
                    break;
                case "Freezer":
                    //CREATION OF A FREEZER
                    creationOfFreezer();

                    option = exit;
                    break;
                case "MicrowaveOven":
                    //CREATION OF A MICROWAVE OVEN
                    creationOfMicrowaveOven();

                    option = exit;
                    break;
                case "Stove":
                    //CREATION OF A STOVE
                    creationOfStove();

                    option = exit;
                    break;
                case "Fan":
                    //CREATION OF A FAN
                    creationOfFan();

                    option = exit;
                    break;
                case "Television":
                    //CREATION OF A TELEVISION
                    creationOfTelevision();

                    option = exit;
                    break;
                case "WallTowelHeater":
                    //CREATION OF A WALL TOWEL HEATER
                    creationOfWallTowelHeater();

                    option = exit;
                    break;
                case "WineCooler":
                    //CREATION OF A WINE COOLER
                    creationOfWineCooler();

                    option = exit;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void creationOfFan() {
        String label77 = "What is the name of the fan?";
        String fanDeviceName = InputValidator.getString(label77);
        String label78 = "What is the fan nominal power (kW)?";
        double fanNominalPower = InputValidator.getDoublePos(label78);

        controller.createNewFan(fanDeviceName, fanNominalPower);

        if (controller.isProgrammable()) {
            creationOfTimeVariablePrograms();
        }

        System.out.println("The Fan was successfully created and added to the selected room.");
    }

    private void creationOfMicrowaveOven() {
        String label01 = "What is the name of the microwave?";
        String microwaveDeviceName = InputValidator.getString(label01);
        String label02 = "What is the microwave nominal power (kW)?";
        double microwaveNominalPower = InputValidator.getDoublePos(label02);

        controller.createNewMicroWaveOven(microwaveDeviceName, microwaveNominalPower);

        if (controller.isProgrammable()) {
            creationOfTimeVariablePrograms();
        }

        System.out.println("The Microwave oven was successfully created and added to the selected room.");
    }

    private void creationOfElectricOven() {
        String label01 = "What is the name of the electric oven?";
        String ElectricOvenDeviceName = InputValidator.getString(label01);
        String label02 = "What is the electric oven nominal power (kW)?";
        double ElectricOvenNominalPower = InputValidator.getDoublePos(label02);
        String label03 = "What is the electric powered on time (s)?";
        double ElectricOvenTime = InputValidator.getDoublePos(label03);

        controller.createNewElectricOven(ElectricOvenDeviceName, ElectricOvenNominalPower,ElectricOvenTime);

        if (controller.isProgrammable()) {
            creationOfTimeVariablePrograms();
        }

        System.out.println("The Electric oven was successfully created and added to the selected room.");
    }

    private void creationOfStove() {
        String label01 = "What is the name of the stove?";
        String stoveDeviceName = InputValidator.getString(label01);
        String label02 = "What is the stove nominal power (kW)?";
        double stoveNominalPower = InputValidator.getDoublePos(label02);

        controller.createNewStove(stoveDeviceName, stoveNominalPower);

        if (controller.isProgrammable()) {
            creationOfTimeVariablePrograms();
        }

        System.out.println("The Fan was successfully created and added to the selected room.");
    }

    private void creationOfKettle() {
        String label01 = "What is the name of the kettle?";
        String ketleDeviceName = InputValidator.getString(label01);
        String label02 = "What is the kettle nominal power (kW)?";
        double kettleNominalPower = InputValidator.getDoublePos(label02);
        String label03 = "What is the maximum volume of water (L)?";
        double kettleMaximumVolumeOfWater = InputValidator.getDoublePos(label03);
        String label04 = "What is the temperature of the cold water (ºC)?";
        double kettleTemperatureColdWater = InputValidator.getDoublePos(label04);
        String label05 = "What is the volume of water to heat (L)?";
        double kettleVolumeOfWaterToHeat = InputValidator.getDoublePos(label05);
        String label55 = "What is the performance ratio (tipically 0.9)?";
        double kettlePerformanceRatio = InputValidator.getDoublePos(label55);

        controller.createNewKettle(ketleDeviceName, kettleNominalPower,kettleMaximumVolumeOfWater,kettleTemperatureColdWater,kettleVolumeOfWaterToHeat,kettlePerformanceRatio);

        System.out.println("The Kettle was successfully created and added to the selected room.");
    }
    
    private void creationOfFridge() {
        String label11 = "What is the name of the fridge?";
        String fridgeDeviceName = InputValidator.getString(label11);
        String label12 = "What is the fridge nominal power (kW)?";
        double fridgeNominalPower = InputValidator.getDoublePos(label12);
        String label13 = "What is the freezer capacity (l)?";
        double freezerCapacity = InputValidator.getDoublePos(label13);
        String label14 = "What is the refrigerator capacity (l)?";
        double refrigeratorCapacity = InputValidator.getDoublePos(label14);
        String label15 = "What is the annual energy consumption (kWh)?";
        double annualEnergyConsumption = InputValidator.getDoublePos(label15);

        controller.createNewFridge(fridgeDeviceName, annualEnergyConsumption, fridgeNominalPower, freezerCapacity, refrigeratorCapacity);
        System.out.println("The Fridge was successfully created and added to the selected room.");
    }

    private void creationOfFreezer() {
        String label11 = "What is the name of the freezer?";
        String freezerDeviceName = InputValidator.getString(label11);
        String label12 = "What is the freezer nominal power (kW)?";
        double freezerNominalPower = InputValidator.getDoublePos(label12);
        String label13 = "What is the freezer capacity (l)?";
        double freezerCapacity = InputValidator.getDoublePos(label13);
        String label15 = "What is the annual energy consumption of the freezer (kWh)?";
        double annualEnergyConsumption = InputValidator.getDoublePos(label15);

        controller.createNewFreezer(freezerDeviceName, annualEnergyConsumption, freezerNominalPower, freezerCapacity);
        System.out.println("The Fridge was successfully created and added to the selected room.");
    }

    private void creationOfLamp() {
        String label21 = "What is the name of the lamp?";
        String lampDeviceName = InputValidator.getString(label21);
        String label22 = "What is the lamp nominal power (kW)?";
        double lampNominalPower = InputValidator.getDoublePos(label22);
        String label23 = "What is the luminous flux (lm)?";
        double luminousFlux = InputValidator.getDoublePos(label23);

        controller.createNewLamp(lampDeviceName, lampNominalPower, luminousFlux);
        System.out.println("The Lamp was successfully created and added to the selected room.");
    }

    private void creationOfDishWasher() {
        String label31 = "What is the name of the dish washer?";
        String dishWasherDeviceName = InputValidator.getString(label31);
        String label32 = "What is the Dish Washer nominal power (kW)?";
        double dishWasherNominalPower = InputValidator.getDoublePos(label32);
        String label33 = "What is the capacity (in dish sets)?";
        int dishWasherCapacity = InputValidator.getIntPos(label33);

        controller.createNewDishWasher(dishWasherDeviceName, dishWasherNominalPower,
                dishWasherCapacity);

        if (controller.isProgrammable()) {
            creationOfTimeConstantPrograms();
        }

        System.out.println("The Dishwasher was successfully created and added to the selected room.");
    }

    private void creationOfWineCooler() {
        String label31 = "What is the name of the wine cooler?";
        String wineCoolerDeviceName = InputValidator.getString(label31);
        String label32 = "What is the wine cooler nominal power (kW)?";
        double wineCoolerNominalPower = InputValidator.getDoublePos(label32);
        String label33 = "What is the capacity (number of bottles)?";
        int wineCoolerCapacity = InputValidator.getIntPos(label33);
        String label34 = "What is the wine cooler annual energy consumption (kWh)";
        double wineCoolerAnnualConsumption = InputValidator.getDoublePos(label34);

        controller.createNewWineCooler(wineCoolerDeviceName, wineCoolerNominalPower,
                wineCoolerCapacity,wineCoolerAnnualConsumption);

        System.out.println("The Wine cooler was successfully created and added to the selected room.");
    }

    private void creationOfTelevision() {
        String label21 = "What is the name of the television?";
        String televisionDeviceName = InputValidator.getString(label21);
        String label22 = "What is the television nominal power (kW)?";
        double televisionNominalPower = InputValidator.getDoublePos(label22);
        String label23 = "What is the television standby power (kW)?";
        double stanbyPower = InputValidator.getDoublePos(label23);

        controller.createNewTelevision(televisionDeviceName, televisionNominalPower, stanbyPower);
        System.out.println("The Television was successfully created and added to the selected room.");
    }

    private void creationOfWallTowelHeater() {
        String label21 = "What is the name of the WallTowelHeater?";
        String WallTowelHeaterDeviceName = InputValidator.getString(label21);
        String label22 = "What is the WallTowelHeater nominal power (kW)?";
        double WallTowelHeaterNominalPower = InputValidator.getDoublePos(label22);
        String label23 = "What is the WallTowelHeater turned on time (s)?";
        double time = InputValidator.getDoublePos(label23);

        controller.createNewWallTowelHeater(WallTowelHeaterDeviceName, WallTowelHeaterNominalPower, time);
        System.out.println("The Wall towel heater was successfully created and added to the selected room.");
    }


    private void creationOfWashingMachine() {
        String label41 = "What is the name of the washing machine?";
        String washingMachineDeviceName = InputValidator.getString(label41);
        String label42 = "What is the Washing Machine nominal power (kW)?";
        double washingMachineNominalPower = InputValidator.getDoublePos(label42);
        String label43 = "What is the capacity (kg)?";
        double washingMachineCapacity = InputValidator.getDoublePos(label43);

        controller.createNewWashingMachine(washingMachineDeviceName, washingMachineNominalPower,
                washingMachineCapacity);

        if (controller.isProgrammable()) {
            creationOfTimeConstantPrograms();
        }

        System.out.println("The Washing Machine was successfully created and added to the selected room.");
    }

    private void creationOfTimeConstantPrograms() {
        String label34 = "How many programs has the selected device?";
        int numberOfPrograms = InputValidator.getIntPos(label34);
        String programName = "";
        double programDuration = 0;
        double programEnergyConsumption = 0;
        for (int i = 0; i < numberOfPrograms; i++) {
            System.out.println("PROGRAM " + (i + 1));
            String label45 = "What is the name of this program?";
            programName = InputValidator.getString(label45);
            String label46 = "What is the duration of this program?";
            programDuration = InputValidator.getDoublePos(label46);
            String label47 = "What is the energy consumption of this program?";
            programEnergyConsumption = InputValidator.getDoublePos(label47);
            controller.createNewProgram(programName);
            controller.setProgramAttributes("duration", programDuration);
            controller.setProgramAttributes("energyConsumption", programEnergyConsumption);
            if (controller.addProgram()) {
                System.out.println("The program " + programName + " was added to the selected device.\n");
            }
        }
    }

    private void creationOfTimeVariablePrograms() {
        String label34 = "How many programs has the selected device?";
        int numberOfPrograms = InputValidator.getIntPos(label34);
        String programName = "";
        double programNominalPower = 0;
        for (int i = 0; i < numberOfPrograms; i++) {
            System.out.println("PROGRAM " + (i + 1));
            String label45 = "What is the name of this program?";
            programName = InputValidator.getString(label45);
            String label47 = "What is the nominal power of this program?";
            programNominalPower = InputValidator.getDoublePos(label47);
            controller.createNewProgram(programName);
            controller.setProgramAttributes("programNominalPower", programNominalPower);
            if (controller.addProgram()) {
                System.out.println("The program " + programName + " was added to the selected device.\n");
            }
        }
    }

    private void creationOfElectricWaterHeater() {
        String label51 = "What is the name of the electric water heater?";
        String electricWaterHeaterDeviceName = InputValidator.getString(label51);
        String label52 = "What is the Electric Water Heater nominal power (kW)?";
        double electricWaterHeaterNominalPower = InputValidator.getDoublePos(label52);
        String label53 = "What is the maximum temperature you want to configure on the electric water heater?";
        double maxHotTemperature = InputValidator.getDoublePos(label53);
        String label54 = "What is the capacity (l)?";
        double volumeOfWater = InputValidator.getDoublePos(label54);
        String label55 = "What is the performance ratio (tipically 0.9)?";
        double performanceRatio = InputValidator.getDoublePos(label55);

        controller.createNewElectricWaterHeater(electricWaterHeaterDeviceName, maxHotTemperature, volumeOfWater, electricWaterHeaterNominalPower, performanceRatio);
        System.out.println("The Electric Water Heater was successfully created and added to the selected room.");
    }

    private void showUpdatedDeviceListOfRoom(RoomDTO selectedRoom) {
        String label = "Do you want to see the list of devices of the selected Room? (Y/N)";
        String answer = InputValidator.confirmValidation(label);
        if ("y".equals(answer) || "Y".equals(answer)) {
            System.out.println(getDeviceListOfRoom(controller.getDeviceListContentOfARoom(selectedRoom.getId())));
        }
    }

    private String getDeviceListOfRoom(List<DeviceDTO> deviceDTOS){
        StringBuilder content = new StringBuilder();
        int listOrderByNumber = 1;
        for (DeviceDTO deviceDTO : deviceDTOS) {
            content.append(listOrderByNumber);
            content.append(" - ");
            content.append(deviceDTO.getName()+ " - "+ deviceDTO.getDeviceType()+"\n");
            listOrderByNumber++;
        }
        return content.toString();
    }
}
