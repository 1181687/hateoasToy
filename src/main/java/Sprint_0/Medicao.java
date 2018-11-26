package Sprint_0;

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

    public void setmValor(double mValor) {
        this.mValor = mValor;
    }

    public Date getmDataHora() {
        return mDataHora;
    }

    public void setmDataHora(Date mDataHora) {
        this.mDataHora = mDataHora;
    }
}
