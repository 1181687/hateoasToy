package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class Program {
    private String name;
    private double duration;
    private double energyConsumption;
    private double nominalPower;

    public Program(String name, double duration, double energyConsumption, double nominalPower) {
        this.name = name;
        this.duration = duration;
        this.energyConsumption = energyConsumption;
        this.nominalPower = nominalPower;
    }

    /**
     * method that creates the same hashcode to programs with the same attribute name.
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Equals method to determine if two Programs are equal.
     * They are equals if name are equal.
     * Names are case insensitive.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Program)) {
            return false;
        }
        Program programOne = (Program) obj;
        return this.name.equalsIgnoreCase(programOne.name);
    }
}
