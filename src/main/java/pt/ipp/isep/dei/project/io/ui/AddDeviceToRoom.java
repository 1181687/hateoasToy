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
        //SELECT quarto
        String exit = "\r0 - Exit";

        // falta considerar o facto de nao haver rooms na casa
        String label2 = "Please choose a room to attach a new device: \n" + mCtrl.getRoomListContent() + exit;
        int indexSelectedRoom = InputValidator.getIntRange(label2, 0, mCtrl.roomListLength());
        mCtrl.getRoom(indexSelectedRoom - 1);
        Room selectedRoom = mCtrl.getSelectedRoom();


        //SELECT DEVICE TYPE FROM A LIST
        //eventualmente mudar este metodo para ser validado no input validator -- Ver attachRoomToHouseGrid

        String label0 = "Please select the Device Type: \n" + mCtrl.getDeviceTypeListContent() + exit;
        int selectedType = InputValidator.getIntRange(label0, 1, 5);


        //Escolher atributos do tipo escolhido

        int option = selectedType;

        while (option != 0) {
            switch (option) {
                case 1:
                    String label11 = "What is the name of the fridge?";
                    String fridgeDeviceName = InputValidator.getString(label11);
                    String label12 = "What is the nominal power (kW)?";
                    double fridgeNominalPower = InputValidator.getDoublePos(label12);
                    String label13 = "What is the freezer capacity (l)?";
                    double freezerCapacity = InputValidator.getDoublePos(label13);
                    String label14 = "What is the refrigerator capacity (l)?";
                    double refrigeratorCapacity = InputValidator.getDoublePos(label14);


                    break;
                case 2:
                    String label21 = "What is the name of the lamp?";
                    String LampDeviceName = InputValidator.getString(label21);
                    String label22 = "What is the nominal power (kW)?";
                    double LampNominalPower = InputValidator.getDoublePos(label22);
                    String label23 = "What is the luminous flux (lm)?";

                    break;
                case 3:
                    String label31 = "What is the name of the dish washer?";
                    String dishWasherDeviceName = InputValidator.getString(label31);
                    String label32 = "What is the nominal power (kW)?";
                    double dishWasherNominalPower = InputValidator.getDoublePos(label32);
                    String label33 = "What is the capacity (in dish sets)?";
                    int dishWasherCapacity = InputValidator.getIntPos(label33);
                    break;
                case 4:
                    String label41 = "What is the name of the washing machine?";
                    String washingMachineDeviceName = InputValidator.getString(label41);
                    String label42 = "What is the nominal power (kW)?";
                    double washingMachineNominalPower = InputValidator.getDoublePos(label42);
                    String label43 = "What is the capacity (kg)?";
                    double washingMachineCapacity = InputValidator.getDoublePos(label43);


                    break;
                case 5:
                    String label51 = "What is the name of the electric water heater?";
                    String electricWaterHeaterDeviceName = InputValidator.getString(label51);
                    String label52 = "What is the nominal power (kW)?";
                    double electricWaterHeaterNominalPower = InputValidator.getDoublePos(label52);
                    String label53 = "What is the maximum temperature you want to configure on the electric water heater?";
                    double maxHotTemperature = InputValidator.getDoublePos(label53);
                    String label54 = "What is the capacity (l)?";
                    double volumeOfWater = InputValidator.getDoublePos(label54);

                    mCtrl.createNewElectricWaterHeater(electricWaterHeaterDeviceName, selectedRoom, maxHotTemperature, volumeOfWater, electricWaterHeaterNominalPower);
                    if (mCtrl.addDeviceToRoom()) {

                        StringBuilder content = new StringBuilder();
                        content.append("The device " + electricWaterHeaterDeviceName + " was succesfully added to " + selectedRoom.getmName() +
                                " and created with the following specifications:\n");
                        content.append("- Nominal Power: " + electricWaterHeaterNominalPower + " kW \n");
                        content.append("- Volume of water: " + volumeOfWater + " l\n");
                        content.append("- Maximum temperature configured: " + maxHotTemperature + " ºC\n");
                        System.out.println(content.toString());

                    } else {
                        System.out.println("Something went wrong. The device wasn't added to the room. Please try again.");
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 5.");
            }
        }
    }
}