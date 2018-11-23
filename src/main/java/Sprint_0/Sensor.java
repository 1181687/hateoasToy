package Sprint_0;

import java.util.Date;
import java.util.List;

public class Sensor {
    private String mNomeSensor;
    private Date mDataFuncionamento;
    private List<Medicao> mRegistos;
    private TipoSensor mTipoSensor;
    private Localizacao mLocalizacao;

    public Sensor(String mNomeSensor, Date mDataFuncionamento, List<Medicao> mRegistos, TipoSensor mTipoSensor, Localizacao mLocalizacao) {
        this.mNomeSensor = mNomeSensor;
        this.mDataFuncionamento = mDataFuncionamento;
        this.mRegistos = mRegistos;
        this.mTipoSensor = mTipoSensor;
        this.mLocalizacao = mLocalizacao;
    }
}
