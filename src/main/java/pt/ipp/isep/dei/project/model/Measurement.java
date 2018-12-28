package pt.ipp.isep.dei.project.model;

import java.util.Date;

public class Measurement {
    private double mValor;
    private Date mDataHora;

    public Measurement(double mValor, Date mDataHora) {
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
