package sprint0.Model;

import java.util.Date;

public class Medicao {
    private double mValor;
    private Date mDataHora;

    public Medicao(double mValor, Date mDataHora) {
        this.mValor = mValor;
        this.mDataHora = mDataHora;
    }

    public double getmValor() {
        return mValor;
    }

    public Date getmDataHora() {
        return mDataHora;
    }

}
