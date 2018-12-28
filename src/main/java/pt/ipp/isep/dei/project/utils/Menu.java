package pt.ipp.isep.dei.project.utils;

public class Menu {

    public static int usersMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Please choose your user role:");
        content.append("\n");
        content.append("1-Administrator");
        content.append("\n");
        content.append("2-Regular User");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 2);
        return op;
    }

    public static int administratorMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Choose one of the following options:");
        content.append("\n");
        content.append("1-US1-New type of geographical area");
        content.append("\n");
        content.append("2-US2-Show list of geographical area types");
        content.append("\n");
        content.append("3-US3-Add a new geographical area");
        content.append("\n");
        content.append("4-US4-Show list of geographical areas by type");
        content.append("\n");
        content.append("5-US5-Define sensor types");
        content.append("\n");
        content.append("6-US6-Add a new sensor");
        content.append("\n");
        content.append("7-US7-Add a geographical area to another one");
        content.append("\n");
        content.append("8-US8-Verify if one geographical area is included in another one");
        content.append("\n");
        content.append("9-US101-Configure the location of the house");
        content.append("\n");
        content.append("10-US105-Add a room to the house");
        content.append("\n");
        content.append("11-US108-Edit a room from a list of rooms");
        content.append("\n");
        content.append("12-US130-Create a House Grid");
        content.append("\n");
        content.append("13-US135-Add a power source to a house grid");
        content.append("\n");
        content.append("15-US147-Attach a room to a house grid");
        content.append("\n");
        content.append("16-US149-Detach a room from a house grid");
        content.append("\n");
        content.append("0-Exit");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 16);
        return op;
    }

    public static int regularUserMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Choose one of the options:");
        content.append("\n");
        content.append("1-US600");
        content.append("\n");
        content.append("2-US605-Get the current temperature in a room");
        content.append("\n");
        content.append("3-US610- Get the maximum temperature in a room");
        content.append("\n");
        content.append("4-US620");
        content.append("\n");
        content.append("5-US623");
        content.append("\n");
        content.append("0-Exit");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 5);
        return op;
    }
}