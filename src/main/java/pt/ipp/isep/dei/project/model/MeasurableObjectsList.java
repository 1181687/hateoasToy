package pt.ipp.isep.dei.project.model;

import java.util.HashSet;
import java.util.Set;

public class MeasurableObjectsList {
    private Set<Measurable> mMeasurableList = new HashSet<>();

    public MeasurableObjectsList() {
        //intentionally empty
    }

    public void addMeasurableObjToMeasurableList(Measurable measurable) {
        this.mMeasurableList.add(measurable);
    }
    public double getNominalPower() {
        double totalNominalPower = 0;
        for (Measurable measurable : mMeasurableList) {
            totalNominalPower += measurable.getNominalPower();
        }
        return Math.floor(totalNominalPower);
    }

    public boolean checkIfMeasurableObjIsInList(Measurable measurable) {
        return mMeasurableList.contains(measurable);
    }
}

