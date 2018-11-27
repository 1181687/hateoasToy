package Sprint_0;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Sensor {
    private String mNomeSensor;
    private Date mDataFuncionamento;
    private List<Medicao> mRegistos;
    private TipoSensor mTipoSensor;
    private Localizacao mLocalizacao;

    public Sensor(String mNomeSensor, Date mDataFuncionamento, TipoSensor mTipoSensor, Localizacao mLocalizacao) {
        this.mNomeSensor = mNomeSensor;
        this.mDataFuncionamento = mDataFuncionamento;
        this.mTipoSensor = mTipoSensor;
        this.mLocalizacao = mLocalizacao;
    }

    public String getmNomeSensor() {
        return mNomeSensor;
    }

    public Date getmDataFuncionamento() {
        return mDataFuncionamento;
    }

    public TipoSensor getmTipoSensor() {
        return mTipoSensor;
    }

    public Localizacao getmLocalizacao() {
        return mLocalizacao;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sensor)) {
            return false;
        }
        Sensor c = (Sensor) o;
        if (this.getmNomeSensor().equals(c.getmNomeSensor())) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 1;
    }


}
