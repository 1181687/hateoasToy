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
        String label100 = "Choose one option:\n1- Get instants in which the temperature fell below the comfort level.\n 2- Get instants in which the temperature rose above the comfort level.\n 0- Exit.\n\n";
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
        String label99 = "Choose the category of comfort temperature:\n 1- Category I.\n 2- Category II.\n  3- Category III.\n 0- Exit.\n\n";
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
        String label0 = "Choose the room you want to get the instants:";
        int roomOption = InputValidator.getIntRange(label0, 1, roomDTOS.size()) - 1;
        if (roomOption == -1) {
            return;
        }

        //get and set roomId
        String roomId = roomDTOS.get(roomOption).getRoomId();
        controller.setRoomId(roomId);

        //if there are no temperature Sensor
        if (!controller.existTempSensors()) {
            System.out.println("There are no temperature sensors available in the choosen room.\n");
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
                System.out.println("That is not a valid period. Please try again.\n");
                flag = true;
            }
        }
        while (flag);


        //if there are not readings in house area or in the room
        if (!controller.existsReadingsHouseAreaAndRoom(startDate, endDate)) {
            System.out.println("There weren't enough measurements in that period, to calculate the instants.");
            return;
        }

        //get comfort Temp
        controller.getComfortTemperature(startDate, endDate);

        //get instants bellow or above
        controller.getInstantsOutOfComfortTemperature(startDate, endDate);
        List<LocalDateTime> instantList = controller.getInstantListOutOfComfortLevel();

        if (instantList.isEmpty()) {
            System.out.println("There are no instants out of the Comfort level.");
            return;
        } else {
            controller.getInstantListOutOfComfortLevel();
        }

        displayResults(roomId, instantList, startDate, endDate);
    }

    /**
     * outputs the instants, that were above or bellow the comfort temperature in an interval, of a choosen room
     *
     * @param roomId      given String room name
     * @param instantlist given list of instants (LocalDateTime)
     * @param startdate   Localdate inicial
     * @param endDate     Localdate end
     */
    public void displayResults(String roomId, List<LocalDateTime> instantlist, LocalDate startdate, LocalDate endDate) {
        StringBuilder content = new StringBuilder();
        content.append("The list of instants ");
        content.append(belowOrAbove);
        content.append(" for the room ");
        content.append(roomId);
        content.append(" in the interval date between ");
        content.append(startdate);
        content.append(" and ");
        content.append(endDate);
        content.append(" are:\n");
        content.append(this.getListInstantsToString(instantlist));
        content.append("\n");
        if (controller.existsDaysWithoutComfortTemp()) {
            content.append("There weren't enough measurements to calculate the instants during:\n");
            List<LocalDate> daysWithoutComfortTemp = controller.getDaysWithoutComfortTemp();
            content.append(this.getDaysWithoutComfortTempToString(daysWithoutComfortTemp));
        }
        // content.append(localDateTime.toLocalTime().toString());
        //content.append(localDateTime.toString().substring(11));
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
     * @param localDates given List<LocalDate>
     * @return strings
     */
    public String getDaysWithoutComfortTempToString(List<LocalDate> localDates) {
        StringBuilder list = new StringBuilder();

        if (localDates.isEmpty()) {
            return "There are no dates without Comfort temperature level.";
        }

        int listOrderByNumber = 1;
        for (LocalDate localDate : localDates) {
            list.append(listOrderByNumber);
            list.append(" - Date: ");
            list.append(localDate.toString());
            list.append("\n");
            listOrderByNumber++;
        }
        return list.toString();
    }


    /**
     * method that list to string the instants LocalDateTime
     *
     * @param instantlist given List<LocalDateTime>
     * @return strings
     */
    public String getListInstantsToString(List<LocalDateTime> instantlist) {
        StringBuilder list = new StringBuilder();

        if (instantlist.isEmpty()) {
            return "There are no instants out of the Comfort level.";
        }

        int listOrderByNumber = 1;
        for (LocalDateTime localDateTime : instantlist) {
            list.append(listOrderByNumber);
            list.append(" - Date time: ");
            list.append(localDateTime.toString());
            list.append("\n");
            listOrderByNumber++;
        }
        return list.toString();
    }



}