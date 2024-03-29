package pt.ipp.isep.dei.project.model.house.powersource;

import java.util.ArrayList;
import java.util.List;

public class PowerSourceTypeList {
    private List<PowerSourceType> listOfPowerSourceTypes;

    public PowerSourceTypeList() {
        this.listOfPowerSourceTypes = new ArrayList<>();
    }

    /**
     * Method that adds a new Type of Power Source to the Power Source Type List
     *
     * @param newPowerSourceType new Power Source Type
     * @return true if the adds a power source type or false if it doesn't
     */
    public boolean addPowerSourceType(PowerSourceType newPowerSourceType) {
        if (!(this.listOfPowerSourceTypes.contains(newPowerSourceType))) {
            listOfPowerSourceTypes.add(newPowerSourceType);
            return true;
        }
        return false;
    }

    /**
     * method that displays the List of Power Source Types
     *
     * @return Content of Power Source Type List
     */
    public String getPowerSourceTypeListToString() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= this.listOfPowerSourceTypes.size(); i++) {
            content.append(i + " - Power Source Type: " + this.listOfPowerSourceTypes.get(i - 1).getTypeOfPowerSource());
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * Method that gets the Type of Power Source from a specific position
     *
     * @param position position of the Type of Power Source in a List
     * @return Type of Power Source
     */
    public PowerSourceType getPowerSourceTypeFromASpecificPosition(int position) {
        return listOfPowerSourceTypes.get(position);
    }

    /**
     * Method that gives us the size of the Power Source List
     *
     * @return Power Source List size
     */
    public int getSize() {
        return this.listOfPowerSourceTypes.size();
    }
}
