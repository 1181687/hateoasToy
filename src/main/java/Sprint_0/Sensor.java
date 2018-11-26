package Sprint_0;

import java.util.Date;
import java.util.List;

public class Sensor {
    private String mNomeSensor;
    private Date mDataFuncionamento;
    private List<Medicao> mRegistos;
    private TipoSensor mTipoSensor;
    private Localizacao mLocalizacao;

    public String getmNomeSensor() {
        return mNomeSensor;
    }

    public void setmNomeSensor(String mNomeSensor) {
        this.mNomeSensor = mNomeSensor;
    }

    public Date getmDataFuncionamento() {
        return mDataFuncionamento;
    }

    public void setmDataFuncionamento(Date mDataFuncionamento) {
        this.mDataFuncionamento = mDataFuncionamento;
    }

    public List<Medicao> getmRegistos() {
        return mRegistos;
    }

    public void setmRegistos(List<Medicao> mRegistos) {
        this.mRegistos = mRegistos;
    }

    public TipoSensor getmTipoSensor() {
        return mTipoSensor;
    }

    public void setmTipoSensor(TipoSensor mTipoSensor) {
        this.mTipoSensor = mTipoSensor;
    }

    public Localizacao getmLocalizacao() {
        return mLocalizacao;
    }

    public void setmLocalizacao(Localizacao mLocalizacao) {
        this.mLocalizacao = mLocalizacao;
    }
}
