package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class PowerSourceTypeList {
    private List<PowerSourceType> mPowerSourceTypeList = new ArrayList<>();

    public PowerSourceTypeList() {
    }

    public boolean addPowerSourceTypeToPowerSourceTypeList (PowerSourceType newPowerSourceType){
        if(!(this.mPowerSourceTypeList.contains(newPowerSourceType))){
            mPowerSourceTypeList.add(newPowerSourceType);
            return true;
        }
        return false;
    }

    public List<PowerSourceType> getPowerSourceTypeList() {
        return mPowerSourceTypeList;
    }

    public PowerSourceType newPowerSourceType(String newPowerSourceType) {
        return new PowerSourceType(newPowerSourceType);
    }

    public String getContentOfPowerSourceTypeList() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= this.mPowerSourceTypeList.size(); i++) {
            content.append(i + " - Power Source Type: " + this.mPowerSourceTypeList.get(i - 1).getPowerSourceType());
            content.append("\n");
        }
        return content.toString();
    }
}
