package pt.ipp.isep.dei.project.io.ui;

public class Menu {

    private static final String CHOOSE_OPTIONS = "Choose one of the options:";

    public static int usersMenu() {
        StringBuilder content = new StringBuilder();
        content.append("What type of user are you?");
        content.append("\n");
        content.append("1-Administrator");
        content.append("\n");
        content.append("2-Regular User");
        content.append("\n");
        content.append("3-Power User");
        content.append("\n");
        content.append("4-Room Owner");
        content.append("\n");
        content.append("0-Exit");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 4);
    }

    public static int adminMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-Geographical Area");
        content.append("\n");
        content.append("2-House");
        content.append("\n");
        content.append("0-Return to the previous menu");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 2);
    }

    public static int adminGeoAreaMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-Create new type of geographical area");
        content.append("\n");
        content.append("2-Show list of geographical area types");
        content.append("\n");
        content.append("3-Add a new geographical area");
        content.append("\n");
        content.append("4-Show list of geographical areas by type");
        content.append("\n");
        content.append("5-Create sensor types");
        content.append("\n");
        content.append("6-Add a new sensor");
        content.append("\n");
        content.append("7-Add a geographical area to another one");
        content.append("\n");
        content.append("8-Verify if one geographical area is included in another one");
        content.append("\n");
        content.append("0-Return to the previous menu");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 9);
    }

    public static int adminHouseMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-Configure the location of the house");
        content.append("\n");
        content.append("2-Add a room to the house");
        content.append("\n");
        content.append("3-Manage House Grid");
        content.append("\n");
        content.append("4-Add sensor to a room");
        content.append("\n");
        content.append("5-Check the list of devices in a room");
        content.append("\n");
        content.append("6-Check the list of sensors in a room");
        content.append("\n");
        content.append("7-Edit a device in a room");
        content.append("\n");
        content.append("8-Check the nominal power of all devices in a room");
        content.append("\n");
        content.append("9-Check the nominal power of House Grid");
        content.append("\n");
        content.append("10-Add a new device to a room");
        content.append("\n");
        content.append("0-Return to the previous menu");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 10);
    }

    public static int regularUserMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-House Area");
        content.append("\n");
        content.append("2-Rooms");
        content.append("\n");
        content.append("0-Return to the previous menu");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 2);
    }

    public static int regularUserHouseAreaMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-Get current temperature in the house area");
        content.append("\n");
        content.append("2-Get total rainfall in the house area");
        content.append("\n");
        content.append("3-Get average daily rainfall in the house area");
        content.append("\n");
        content.append("0-Return to the previous menu");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 3);
    }

    public static int regularUserRoomsMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-Current temperature in a room");
        content.append("\n");
        content.append("2-Maximum temperature of a room in a given day");
        content.append("\n");
        // JUST TESTING
        content.append("3-Estimate total energy used to heat water in a given day ");
        content.append("\n");
        content.append("0-Return to the previous menu");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 3);
    }

    public static int houseGridMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-Create a House Grid");
        content.append("\n");
        content.append("2-Add a power source to a house grid");
        content.append("\n");
        content.append("3-Attach a room to a house grid");
        content.append("\n");
        content.append("4-Detach a room from a house grid");
        content.append("\n");
        content.append("5-Get list of devices in a house grid");
        content.append("\n");
        content.append("6-Get nominal power of a subset of rooms and/or devices in a house grid");
        content.append("\n");
        content.append("0-Return to the previous menu");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 6);
    }

    public static int regularUserPowerConsuptionManagement() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-Check Nominal Power of a room.");
        content.append("\n");
        content.append("0-Return to the previous menu");
        content.append("\n");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 1);
    }

    public static int powerUserMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-House configuration.");
        content.append("\n");
        content.append("2-Energy consumption management.");
        content.append("\n");
        content.append("0-Return to the previous menu");
        content.append("\n");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 2);
    }

    public static int powerUserHouseMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-List all devices connected to a grid.");
        content.append("\n");
        content.append("2-Check nominal power of a house grid.");
        content.append("\n");
        content.append("3-Check nominal power of a room");
        content.append("\n");
        content.append("0-Return to the previous menu");
        content.append("\n");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 3);
    }

    public static int powerUserEnergyConsumtionMenu() {
        StringBuilder content = new StringBuilder();
        content.append(CHOOSE_OPTIONS);
        content.append("\n");
        content.append("1-Total nominal power of a subset of rooms and/or devices.");
        content.append("\n");
        content.append("2-Estimate the total energy used in heating water in a day.");
        content.append("\n");
        content.append("0-Return to the previous menu");
        content.append("\n");
        String label = content.toString();
        return InputValidator.getIntRange(label, 0, 2);
    }
}