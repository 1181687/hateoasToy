package pt.ipp.isep.dei.project.model;

import java.util.*;

public class MeasurableObjectsList {
    private Set<Measurable> mMeasurableList = new HashSet<>();
    // private List<Measurable> mMeasurableList = new ArrayList<>();

    public MeasurableObjectsList() {
    }

    public Set<Measurable> mMeasurableList() {
        return mMeasurableList;
    }

    public boolean addMeasurableObjToMeasurableList(Measurable measurable) {
        return this.mMeasurableList.add(measurable);
    }

    public double getNominalPower() {
        double totalNominalPower = 0;
        for (Measurable measurable : mMeasurableList) {
            totalNominalPower += measurable.getNominalPower();
        }
        return totalNominalPower;
    }

    public boolean checkIfMeasurableObjIsInList(Measurable measurable){
        if (mMeasurableList.contains(measurable)){
            return true;
        }
        else return false;
    }
}
