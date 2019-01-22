package pt.ipp.isep.dei.project.model;

import java.util.HashSet;
import java.util.Set;

public class MeasurableList {
    private Set<Measurable> mMeasurableList = new HashSet<>();

    public MeasurableList() {
        //intentionally empty
    }

    public void addMeasurable(Measurable measurable) {
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

    public String getListToString() {
        StringBuilder measurableId = new StringBuilder();
        for (Measurable measurable : mMeasurableList) {
            measurableId.append(measurable.getNameToString());
        }
        return measurableId.toString();
    }
}

