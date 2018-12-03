package Sprint_0;

public class TipoSensor {
    private String mTipo;

    public TipoSensor(String mTipoSensor) {
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
        if (!(obj instanceof TipoSensor)) {
            return false;
        }
        TipoSensor ts = (TipoSensor) obj;
        if (this.mTipo.equals(ts.mTipo)) {
            return true;
        } else {
            return false;
        }
    }
}
