package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddDeviceToRoomController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

public class AddDeviceToRoom {

    private AddDeviceToRoomController mCtrl;

    public AddDeviceToRoom(House house) {
        this.mCtrl = new AddDeviceToRoomController(house);
    }

    public void run() {
        boolean flag = true;
        int indexSelectedRoom = -1;

        while (flag) {
            //SELECT A ROOM
            String exit = "\r0 - Exit";
            if (mCtrl.roomListLength() != 0) {
                String label2 = "Please choose a room to attach a new device: \n" + mCtrl.getRoomListContent() + exit;
                indexSelectedRoom = InputValidator.getIntRange(label2, 0, mCtrl.roomListLength()) - 1;
                if (indexSelectedRoom == -1) {
                    return;
                }
                mCtrl.getRoom(indexSelectedRoom);
            } else {
                System.out.println("There are no rooms available. Please create one\n");
            }

            Room selectedRoom = mCtrl.getSelectedRoom();

            //SELECT A DEVICE TYPE
            String label0 = "Please select the Device Type: \n" + mCtrl.getDeviceTypeListContent() + exit;
            int selectedType = InputValidator.getIntRange(label0, 0, 5);
            if (selectedType == 0) {
                continue;
            }


            int option = selectedType;

            while (option != 0) {
                switch (option) {
                    case 1:
                        //CREATION OF A FRIGDE
                        String label11 = "What is the name of the fridge?";
                        String fridgeDeviceName = InputValidator.getString(label11);
                        String label12 = "What is the nominal power (kW)?";
                        double fridgeNominalPower = InputValidator.getDoublePos(label12);
                        String label13 = "What is the freezer capacity (l)?";
                        double freezerCapacity = InputValidator.getDoublePos(label13);
                        String label14 = "What is the refrigerator capacity (l)?";
                        double refrigeratorCapacity = InputValidator.getDoublePos(label14);
                        String label15 = "What is the annual energy consumption (kWh)?";
                        double annualEnergyConsumption = InputValidator.getDoublePos(label15);

                        mCtrl.createNewFridge(fridgeDeviceName, selectedRoom, annualEnergyConsumption, fridgeNominalPower, freezerCapacity, refrigeratorCapacity);

                        if (mCtrl.addDeviceToRoom()) {

                            StringBuilder content = new StringBuilder();
                            content.append("The device " + fridgeDeviceName + " was succesfully added to " + selectedRoom.getmName() +
                                    " and created with the following specifications:\n");
                            content.append("- Annual Energy Consumption: " + annualEnergyConsumption + " kWh \n");
                            content.append("- Nominal Power: " + fridgeNominalPower + " kW \n");
                            content.append("- Freezer Capacity: " + freezerCapacity + " l \n");
                            content.append("- Refrigerator Capacity: " + refrigeratorCapacity + " l \n");
                            System.out.println(content.toString());
                            option = 0;
                        } else {
                            System.out.println("Something went wrong. The device wasn't added to the room. Please try again.");
                        }

                        break;

                    case 2:
                        //CREATION OF A LAMP
                        String label21 = "What is the name of the lamp?";
                        String lampDeviceName = InputValidator.getString(label21);
                        String label22 = "What is the nominal power (kW)?";
                        double lampNominalPower = InputValidator.getDoublePos(label22);
                        String label23 = "What is the luminous flux (lm)?";
                        double luminousFlux = InputValidator.getDoublePos(label23);

                        mCtrl.createNewLamp(lampDeviceName, selectedRoom, lampNominalPower, luminousFlux);

                        if (mCtrl.addDeviceToRoom()) {

                            StringBuilder content = new StringBuilder();
                            content.append("The device " + lampDeviceName + " was succesfully added to " + selectedRoom.getmName() +
                                    " and created with the following specifications:\n");
                            content.append("- Nominal Power: " + lampNominalPower + " kW \n");
                            content.append("- Luminous Flux: " + luminousFlux + " lm \n");
                            System.out.println(content.toString());
                            option = 0;
                        } else {
                            System.out.println("Something went wrong. The device wasn't added to the room. Please try again.");
                        }

                        break;
                        
                    case 3:
                        //CREATION OF A DISH WASHER
                        String label31 = "What is the name of the dish washer?";
                        String dishWasherDeviceName = InputValidator.getString(label31);
                        String label32 = "What is the nominal power (kW)?";
                        double dishWasherNominalPower = InputValidator.getDoublePos(label32);
                        String label33 = "What is the capacity (in dish sets)?";
                        int capacity = InputValidator.getIntPos(label33);

                        //mCtrl.createNewDishWasher(dishWasherDeviceName, selectedRoom, dishWasherNominalPower, capacity);

                        if (mCtrl.addDeviceToRoom()) {

                            StringBuilder content = new StringBuilder();
                            content.append("The device " + dishWasherDeviceName + " was succesfully added to " + selectedRoom.getmName() +
                                    " and created with the following specifications:\n");
                            content.append("- Nominal Power: " + dishWasherNominalPower + " kW \n");
                            content.append("- Capacity: " + capacity + " dish sets \n");
                            System.out.println(content.toString());
                            option = 0;
                        } else {
                            System.out.println("Something went wrong. The device wasn't added to the room. Please try again.");
                        }

                        break;
                    case 4:
                        //CREATION OF A WASHING MACHINE
                        String label41 = "What is the name of the washing machine?";
                        String washingMachineDeviceName = InputValidator.getString(label41);
                        String label42 = "What is the nominal power (kW)?";
                        double washingMachineNominalPower = InputValidator.getDoublePos(label42);
                        String label43 = "What is the capacity (kg)?";
                        double washingMachineCapacity = InputValidator.getDoublePos(label43);
                        String label44 = "How many programs has the washing machine?";
                        int numberOfPrograms = InputValidator.getIntPos(label44);
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
                            mCtrl.addToProgramList(programName, programDuration, programEnergyConsumption);
                        }

                        mCtrl.createNewWashingMachine(washingMachineDeviceName, selectedRoom, washingMachineNominalPower,
                                washingMachineCapacity);

                        if (mCtrl.addDeviceToRoom()) {

                            StringBuilder content = new StringBuilder();
                            content.append("The device " + washingMachineDeviceName + " was succesfully added to " + selectedRoom.getmName() +
                                    " and created with the following specifications:\n");
                            content.append("- Capacity: " + washingMachineCapacity + " kg \n");
                            content.append("- Nominal Power: " + washingMachineNominalPower + " kW \n");
                            content.append("- " + numberOfPrograms + " programs: \n");
                            System.out.println(content.toString());
                            if (numberOfPrograms > 0) {
                                for (int i = 0; i < numberOfPrograms; i++) {
                                    content.append("     - " + programName + "\n");
                                    content.append("      Duration: " + programDuration + " min \n");
                                    content.append("      Energy Consumption:" + programEnergyConsumption + " kWh \n");
                                    System.out.println(content.toString());

                                }
                            }
                            option = 0;
                        } else {
                            System.out.println("Something went wrong. The device wasn't added to the room. Please try again.");
                        }


                        break;
                    case 5:
                        //CREATION OF A ELECTRIC WATER HEATER
                        String label51 = "What is the name of the electric water heater?";
                        String electricWaterHeaterDeviceName = InputValidator.getString(label51);
                        String label52 = "What is the nominal power (kW)?";
                        double electricWaterHeaterNominalPower = InputValidator.getDoublePos(label52);
                        String label53 = "What is the maximum temperature you want to configure on the electric water heater?";
                        double maxHotTemperature = InputValidator.getDoublePos(label53);
                        String label54 = "What is the capacity (l)?";
                        double volumeOfWater = InputValidator.getDoublePos(label54);
                        String label55 = "What is the performance ratio (tipically 0.9)?";
                        double performanceRatio = InputValidator.getDoublePos(label55);

                        mCtrl.createNewElectricWaterHeater(electricWaterHeaterDeviceName, selectedRoom, maxHotTemperature, volumeOfWater, electricWaterHeaterNominalPower, performanceRatio);
                        if (mCtrl.addDeviceToRoom()) {

                            StringBuilder content = new StringBuilder();
                            content.append("The device " + electricWaterHeaterDeviceName + " was succesfully added to " + selectedRoom.getmName() +
                                    " and created with the following specifications:\n");
                            content.append("- Nominal Power: " + electricWaterHeaterNominalPower + " kW \n");
                            content.append("- Volume of water: " + volumeOfWater + " l\n");
                            content.append("- Maximum temperature configured: " + maxHotTemperature + " ºC\n");
                            System.out.println(content.toString());
                            option = 0;
                        } else {
                            System.out.println("Something went wrong. The device wasn't added to the room. Please try again.");
                        }
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number between 1 and 5.");
                }

            }
            flag = false;
        }
        String label = "Do you want to see the list of ... devices of the selected Room? (Y/N)";
        String answer = InputValidator.confirmValidation(label);
        if ("y".equals(answer) || "Y".equals(answer)) {
            System.out.println(mCtrl.getDeviceListContentOfARoom(indexSelectedRoom));
        } else {
            return;
        }

    }
}