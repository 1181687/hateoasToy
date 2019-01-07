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

    public String chooseRechargeableOption() {

        StringBuilder content = new StringBuilder();
        content.append("1 - Yes");
        content.append("\n");
        content.append("2 - No");
        content.append("\n");

        return content.toString();
    }
}
