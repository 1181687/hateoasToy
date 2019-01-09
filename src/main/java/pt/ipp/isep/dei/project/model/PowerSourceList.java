package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class PowerSourceList {
    private List<PowerSource> mPowerSourceList = new ArrayList<>();

    public PowerSourceList() {
    }

    public PowerSource createNewPowerSource(String powerSourceName, PowerSourceType type){
        return new PowerSource(powerSourceName,type);
    }

    public boolean addPowerSourceToList(PowerSource newPowerSource){

        if(!(this.mPowerSourceList.contains(newPowerSource))){
            this.mPowerSourceList.add(newPowerSource);
            return true;
        }
        return false;
    }

    public String listPowerSources(){
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
