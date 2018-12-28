package pt.ipp.isep.dei.project.model;

public class SensorType {
    private String mTipo;

    public SensorType(String mTipoSensor) {
        this.mTipo = mTipoSensor;
    }

    public String getmTipo() {
        return mTipo;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SensorType)) {
            return false;
        }
        SensorType ts = (SensorType) obj;
        return this.mTipo.equals(ts.mTipo);

    }
}
