package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddDeviceToRoomController;
import pt.ipp.isep.dei.project.model.house.House;


public class AddDeviceToRoom {

    private AddDeviceToRoomController controller;

    public AddDeviceToRoom(House house) {
        this.controller = new AddDeviceToRoomController(house);
    }

    public void run() {
        boolean flag = true;
        int indexSelectedRoom = -1;
        while (flag) {
            //SELECT A ROOM
            String exit = "\r0 - Exit";
            if (controller.roomListSize() != 0) {
                String label2 = "Please choose a room to attach a new device: \n" + controller.getRoomListContent() + exit;
                indexSelectedRoom = InputValidator.getIntRange(label2, 0, controller.roomListSize()) - 1;
                if (indexSelectedRoom == -1) {
                    return;
                }
                controller.getRoom(indexSelectedRoom);
            } else {
                System.out.println("There are no rooms available. Please create one\n");
            }
            //SELECT A DEVICE TYPE
            String label0 = "Please select the Device Type: \n" + controller.getDeviceTypeListToString() + "\r0 - Exit";
            int selectedType = InputValidator.getIntRange(label0, 0, controller.getNumberOfDeviceTypes());
            if (selectedType == 0) {
                continue;
            }
            //CREATE A DEVICE
            createDevice(selectedType);
            flag = false;
        }
        //SHOW DEVICE LIST IN THE ROOM - Y/N?
        showUpdatedDeviceListOfRoom(indexSelectedRoom);
    }

    public void createDevice(int selectedType) {

        int option = selectedType;

        while (option != 0) {
            switch (option) {
                case 1:
                    //CREATION OF A FRIGDE
                    creationOfFridge();
                    option = 0;

                    break;

                case 2:
                    //CREATION OF A LAMP
                    creationOfLamp();
                    option = 0;

                    break;

                case 3:
                    //CREATION OF A DISH WASHER
                    creationOfDishWasher();
                    option = 0;

                    break;
                case 4:
                    //CREATION OF A WASHING MACHINE
                    creationOfWashingMachine();

                    option = 0;
                    break;
                case 5:
                    //CREATION OF A ELECTRIC WATER HEATER
                    creationOfElectricWaterHeater();

                    option = 0;
                    break;
                case 11:
                    //CREATION OF A FAN
                    creationOfFan();

                    option = 0;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 5.");
            }
        }
    }

    public void creationOfFan() {
        String label77 = "What is the name of the fan?";
        String fanDeviceName = InputValidator.getString(label77);
        String label78 = "What is the fan nominal power (kW)?";
        double fanNominalPower = InputValidator.getDoublePos(label78);

        controller.createFan(fanDeviceName, fanNominalPower);

        if (controller.isProgrammable()) {
            creationOfTimeVariablePrograms();
        }

        System.out.println("The Fan was successfully created and added to the selected room.");
    }


    public void creationOfFridge() {
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

    public void creationOfLamp() {
        String label21 = "What is the name of the lamp?";
        String lampDeviceName = InputValidator.getString(label21);
        String label22 = "What is the lamp nominal power (kW)?";
        double lampNominalPower = InputValidator.getDoublePos(label22);
        String label23 = "What is the luminous flux (lm)?";
        double luminousFlux = InputValidator.getDoublePos(label23);

        controller.createNewLamp(lampDeviceName, lampNominalPower, luminousFlux);
        System.out.println("The Lamp was successfully created and added to the selected room.");
    }

    public void creationOfDishWasher() {
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


    public void creationOfWashingMachine() {
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

    public void creationOfTimeConstantPrograms() {
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

    public void creationOfTimeVariablePrograms() {
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

    public void creationOfElectricWaterHeater() {
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

    public void showUpdatedDeviceListOfRoom(int indexSelectedRoom) {
        String label = "Do you want to see the list of devices of the selected Room? (Y/N)";
        String answer = InputValidator.confirmValidation(label);
        if ("y".equals(answer) || "Y".equals(answer)) {
            System.out.println(controller.getDeviceListContentOfARoom(indexSelectedRoom));
        }
    }
}
