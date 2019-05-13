package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.instantstempoutofcomfortlevelcontroller.InstantsTempOutOfComfortLevelController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * US440 - As a Power User or as a Room Owner, I want to have a list of the instants in which the temperature fell below the comfort level in a given time interval and category (annex A.2 of EN 15251).
 * US445 - As a Power User or as a Room Owner, I want to have a list of the instants in which the temperature rose above the comfort level in a given time interval and category (annex A.2 of EN 15251).
 **/
public class InstantsTempOutOfComfortLevel {

    private InstantsTempOutOfComfortLevelController controller;
    private List<RoomDTO> roomDTOS;
    private String belowOrAbove;

    public InstantsTempOutOfComfortLevel(HouseService houseService, SensorsService sensorsService, RoomService roomService) {
        controller = new InstantsTempOutOfComfortLevelController(houseService, sensorsService, roomService);
        this.roomDTOS = controller.getAllRoomsDTO();
    }

    public void run() {

        //option us440 or us445
        String label100 = "Choose one option:\n1- Get instants in which the temperature fell below the comfort level.\n2- Get instants in which the temperature rose above the comfort level.\n0- Exit.\n\n";
        int usOption = InputValidator.getIntRange(label100, 0, 2);

        if (usOption == 0) {
            return;
        }

        if (usOption == 1) {
            belowOrAbove = "below";
        }
        if (usOption == 2) {
            belowOrAbove = "above";
        }

        controller.setOption(usOption - 1);

        //category
        String label99 = "Choose the building category:\n1- Category I.\n2- Category II.\n3- Category III.\n0- Exit.\n\n";
        int categoryOption = InputValidator.getIntRange(label99, 0, 3);

        if (categoryOption == 0) {
            return;
        }

        controller.setCategory(categoryOption);

        //No Rooms
        if (controller.isRoomListEmpty()) {
            System.out.println("There are no rooms available in the house.\n");
            return;
        }

        //List of rooms
        System.out.println(this.getRoomListToString());

        //choose room
        String label0 = "Choose the room for which you want to get the instants:";
        int roomOption = InputValidator.getIntRange(label0, 1, roomDTOS.size()) - 1;
        if (roomOption == -1) {
            return;
        }

        //get and set roomId
        String roomId = roomDTOS.get(roomOption).getRoomId();
        controller.setRoomId(roomId);

        //if there are no temperature Sensor
        if (!controller.existTempSensors()) {
            System.out.println("There are no temperature sensors available in the chosen room.\n");
            return;
        }

        controller.setSensorID(roomId);

        // inicial and final dates
        LocalDate startDate;
        LocalDate endDate;
        boolean flag;

        do {
            flag = false;

            String label1 = "Please insert the first date of the period (yyyy-MM-dd):";
            startDate = InputValidator.getStringDate(label1);

            String label2 = "Please insert the second date of the period (yyyy-MM-dd):";
            endDate = InputValidator.getStringDate(label2);

            if (startDate.isAfter(endDate)) {
                System.out.println("This is not a valid period: the second date is before the first date. Please try again.\n");
                flag = true;
            }
        }
        while (flag);


        //if there are not readings in house area or in the room
        if (!controller.existsReadingsHouseAreaAndRoom(startDate, endDate)) {
            System.out.println("There weren't enough measurements in that period to calculate the instants.");
            return;
        }

        //get comfort Temp
        controller.getComfortTemperature(startDate, endDate);

        //get instants below or above
        controller.getInstantsOutOfComfortTemperature(startDate, endDate);
        List<LocalDateTime> instantList = controller.getInstantListOutOfComfortLevel();

        //get days that were not considered in calculation for the given interval
        controller.getDaysNotConsideredInCalculation(startDate, endDate);

        displayResults(roomId, instantList, startDate, endDate);
    }

    /**
     * outputs the instants, that were above or bellow the comfort temperature in an interval, of a choosen room
     *
     * @param roomId      given String room name
     * @param instantList given list of instants (LocalDateTime)
     * @param startdate   Localdate inicial
     * @param endDate     Localdate end
     */
    public void displayResults(String roomId, List<LocalDateTime> instantList, LocalDate startdate, LocalDate endDate) {
        StringBuilder content = new StringBuilder();

        if (instantList.isEmpty()) {
            System.out.println("There are no instants out of the Comfort level.");
            return;
        }
        if (!controller.instantListIsEmpty()) {
            content.append("The list of instants ");
            content.append(belowOrAbove);
            content.append(" the comfort Temperature, for the room ");
            content.append(roomId);
            content.append(" in the interval between ");
            content.append(startdate);
            content.append(" and ");
            content.append(endDate);
            content.append(" is:\n");
            content.append(this.getListInstantsToString(instantList));
            content.append("\n");
        }
        if (!controller.daysNotConsideredListIsEmpty()) {
            content.append("There weren't enough measurements to calculate the instants for the following day(s):\n");
            content.append(this.getDaysNotConsideredToString(controller.getDaysNotConsidered()));
        }
        content.append("\n");
        System.out.println(content.toString());
    }

    /**
     * method that list to string the rooms available, so he can choose one
     *
     * @return strings
     */
    public String getRoomListToString() {
        StringBuilder list = new StringBuilder();

        if (roomDTOS.isEmpty()) {
            return "\nThere are no rooms in the house. Please create a new room.";
        }
        int listOrderByNumber = 1;
        for (RoomDTO roomDTO : roomDTOS) {
            list.append(listOrderByNumber);
            list.append(" - Id: ");
            list.append(roomDTO.getRoomId());
            list.append(", Description: ");
            list.append(roomDTO.getDescription());
            list.append("\n");
            listOrderByNumber++;
        }
        return list.toString();
    }

    /**
     * method that list to string the list LocalDate
     *
     * @param daysNotConsidered given List<LocalDate>
     * @return strings
     */
    public String getDaysNotConsideredToString(List<LocalDate> daysNotConsidered) {
        StringBuilder list = new StringBuilder();

        if (!daysNotConsidered.isEmpty()) {

            int listOrderByNumber = 1;
            for (LocalDate localDate : daysNotConsidered) {
                list.append(listOrderByNumber);
                list.append(" - ");
                list.append(localDate.toString());
                list.append("\n");
                listOrderByNumber++;
            }
        }
        return list.toString();
    }


    /**
     * method that list to string the instants LocalDateTime
     *
     * @param instantList given List<LocalDateTime>
     * @return strings
     */
    public String getListInstantsToString(List<LocalDateTime> instantList) {
        StringBuilder list = new StringBuilder();

        if (!instantList.isEmpty()) {

            int listOrderByNumber = 1;
            for (LocalDateTime localDateTime : instantList) {
                list.append(listOrderByNumber);
                list.append(" - Date time: ");
                list.append(localDateTime.toString());
                list.append("\n");
                listOrderByNumber++;
            }
        }
        return list.toString();
    }


}