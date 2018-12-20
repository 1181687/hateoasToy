package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class PowerSourceList {
    private List<PowerSource> mPowerSourceList = new ArrayList<>();

    public PowerSourceList() {
    }

    public PowerSource createNewPowerSource(String powerSourceName, PowerSourceType type, boolean isRechargeable){
        return new PowerSource(powerSourceName,type,isRechargeable);
    }

    public boolean addPowerSourceToList(PowerSource newPowerSource){

        if(!(this.mPowerSourceList.contains(newPowerSource))){
            this.mPowerSourceList.add(newPowerSource);
            return true;
        }
        return false;
    }


}
