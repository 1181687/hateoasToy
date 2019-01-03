package pt.ipp.isep.dei.project.io.ui;

public class Menu {

    public static int usersMenu() {
        StringBuilder content = new StringBuilder();
        content.append("What type of user are you?");
        content.append("\n");
        content.append("1-Administrator");
        content.append("\n");
        content.append("2-Regular User");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 2);
        return op;
    }

    public static int adminMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Please choose an option:");
        content.append("\n");
        content.append("1-Geographical Area");
        content.append("\n");
        content.append("2-House");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 2);
        return op;
    }

    public static int adminGeoAreaMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Choose one of the following options:");
        content.append("\n");
        content.append("1-New type of geographical area");
        content.append("\n");
        content.append("2-Show list of geographical area types");
        content.append("\n");
        content.append("3-Add a new geographical area");
        content.append("\n");
        content.append("4-Show list of geographical areas by type");
        content.append("\n");
        content.append("5-Define sensor types");
        content.append("\n");
        content.append("6-Add a new sensor");
        content.append("\n");
        content.append("7-Add a geographical area to another one");
        content.append("\n");
        content.append("8-Verify if one geographical area is included in another one");
        content.append("\n");
        content.append("0-Exit");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 8);
        return op;
    }

    public static int adminHouseMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Choose one of the following options:");
        content.append("\n");
        content.append("1-Configure the location of the house");
        content.append("\n");
        content.append("2-Add a room to the house");
        content.append("\n");
        content.append("3-Manage House Grid");
        content.append("\n");
        content.append("4-Add sensor to a room");
        content.append("\n");
        content.append("0-Exit");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 4);
        return op;
    }

    public static int regularUserMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Choose one of the options:");
        content.append("\n");
        content.append("1-House Area");
        content.append("\n");
        content.append("2-Rooms");
        content.append("\n");
        content.append("0-Exit");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 2);
        return op;
    }

    public static int regularUserHouseAreaMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Choose one of the options:");
        content.append("\n");
        content.append("1-Current temperature in the house area");
        content.append("\n");
        content.append("2-Total rainfall in the house area");
        content.append("\n");
        content.append("3-Average daily rainfall in the house area");
        content.append("\n");
        content.append("0-Exit");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 3);
        return op;
    }

    public static int regularUserRoomsMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Choose one of the options:");
        content.append("\n");
        content.append("1-Current temperature in a room");
        content.append("\n");
        content.append("2-Maximum temperature of a room in a given day");
        content.append("\n");
        content.append("0-Exit");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 2);
        return op;
    }

    public static int houseGridMenu() {
        StringBuilder content = new StringBuilder();
        content.append("Choose one of the options:");
        content.append("\n");
        content.append("1-Create a House Grid");
        content.append("\n");
        content.append("2-Add a power source to a house grid");
        content.append("\n");
        content.append("3-Attach a room to a house grid");
        content.append("\n");
        content.append("4-Detach a room from a house grid");
        content.append("\n");
        content.append("0-Exit");
        String label = content.toString();
        int op = InputValidator.getIntRange(label, 0, 4);
        return op;
    }
}