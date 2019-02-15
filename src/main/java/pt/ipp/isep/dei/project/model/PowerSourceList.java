package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class PowerSourceList {
    private List<PowerSource> mPowerSourceList;

    /**
     * Power Source List constructor
     */
    public PowerSourceList() {
        this.mPowerSourceList = new ArrayList<>();
    }

    /**
     * Method that creates a new Power Source
     *
     * @param powerSourceName name of Power Source (String)
     * @param type            type of Power Source
     * @return new Power Source
     */
    public PowerSource createNewPowerSource(String powerSourceName, PowerSourceType type){
        if(!this.powerSourceNameAlreadyExists(powerSourceName)){
            return new PowerSource(powerSourceName, type);
        }
        throw new RuntimeException("Name already exists. Please, write a new one.");
    }

    public boolean powerSourceNameAlreadyExists(String name){
        int listSize = this.mPowerSourceList.size();

        for (int i = 0; i < listSize; i++) {
            if (this.mPowerSourceList.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that adds a new Power Source to the Power Source List.
     *
     * @param newPowerSource new Power Source
     * @return true if the adds a power source or false if it doesn't
     */
    public boolean addPowerSource(PowerSource newPowerSource){

        if(!(this.mPowerSourceList.contains(newPowerSource))){
            this.mPowerSourceList.add(newPowerSource);
            return true;
        }
        return false;
    }

    public String getPowerSourcesListToString() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (int i = 0; i < mPowerSourceList.size(); i++) {
            String powerSourceName = mPowerSourceList.get(i).getName();
            content.append(numberInTheList + "- ");
            content.append(powerSourceName);
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }
}
