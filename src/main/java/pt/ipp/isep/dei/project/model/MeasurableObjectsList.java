package pt.ipp.isep.dei.project.model;

import java.util.HashSet;
import java.util.Set;

public class MeasurableObjectsList implements Measurable {
    private Set<Measurable> mMeasurableList = new HashSet<>();
    //private List<Measurable> mMeasurableList = new ArrayList<>();

    public MeasurableObjectsList() {
    }

    public void addMeasurableObjToMeasurableList(Measurable measurable) {
        this.mMeasurableList.add(measurable);
    }

    public double getNominalPower() {
        double totalNominalPower = 0;
        for (Measurable measurable : mMeasurableList) {
            totalNominalPower += measurable.getNominalPower();
        }
        return totalNominalPower;
    }

    public boolean checkIfMeasurableObjIsInList(Measurable measurable) {
        return mMeasurableList.contains(measurable);
    }

    public boolean checkIsMeasurableListIsEmpty() {
        return mMeasurableList.isEmpty();
    }
}
