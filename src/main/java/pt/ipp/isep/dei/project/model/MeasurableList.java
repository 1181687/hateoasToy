package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.HashSet;
import java.util.Set;

public class MeasurableList {
    private Set<Measurable> measurableObjectList = new HashSet<>();

    public MeasurableList() {
        //intentionally empty
    }

    /**
     * method that adds a measurable object to list
     *
     * @param measurable Object: Room or Device1
     */
    public void addMeasurable(Measurable measurable) {
        this.measurableObjectList.add(measurable);
    }

    /**
     * method to get total nominal power of objects in measurable list (rooms and/or devices)
     *
     * @return double nominal power of objects in list
     */
    public double getNominalPower() {
        double totalNominalPower = 0;
        for (Measurable measurable : measurableObjectList) {
            totalNominalPower += measurable.getNominalPower();
        }
        return Utils.round(totalNominalPower, 2);
    }

    /**
     * method that checks if measurable object is in list
     * @param measurable object: room and/or device
     * @return true if measurable is in list or false if it is not
     */
    public boolean checkIfMeasurableObjIsInList(Measurable measurable) {
        return measurableObjectList.contains(measurable);
    }

    /**
     * method that returns the list in string
     * @return String (list)
     */
    public String getListToString() {
        StringBuilder measurableId = new StringBuilder();
        for (Measurable measurable : measurableObjectList) {
            measurableId.append(measurable.getNameToString());
        }
        return measurableId.toString();
    }
}

