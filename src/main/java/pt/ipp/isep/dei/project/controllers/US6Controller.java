package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class US6Controller {
    private ListaTiposSensores mListaTiposSensores;
    private ListaAG mListaAreaGeografica;

    public US6Controller(ListaTiposSensores listaTiposSensores, ListaAG listaAG) {
        this.mListaTiposSensores = listaTiposSensores;
        this.mListaAreaGeografica = listaAG;
    }


    public TipoSensor getTipoSensorPorPosicao (int posicao) {
        return mListaTiposSensores.getTipoSensorPorPosicao(posicao);
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return mListaAreaGeografica.getNomeAreaGeograficaPorIndice(posicao);
    }

    public AreaGeografica getAreaGeograficaNaListaPorPosicao (int posicao) {
        return mListaAreaGeografica.getmListaAG().get(posicao);
    }

    public int numeroElementosDaListaAreaGeografica () {
        return mListaAreaGeografica.getmListaAG().size();
    }

    public int numeroElementosDaListaTipoDeSensor () {
        return mListaTiposSensores.getmListaTiposSensores().size();
    }

    public String getNomeTipoSensorPorIndice (int posicao) {
        return mListaTiposSensores.getTipoSensorPorPosicao(posicao).getmTipo();
    }

    public boolean adicionarSensorAAreaGeografica(Sensor sensor, AreaGeografica areaGeografica) {
        if ((!(this.mListaAreaGeografica.getAreaGeografica(areaGeografica).getmListaSensor().contains(sensor)))) {
            mListaAreaGeografica.getAreaGeografica(areaGeografica).adicionarSensorAListaDeSensores(sensor);
            return true;
        }
        return false;
    }

    public Localizacao criarNovaLocalizacao (double mLatitude, double mLongitude, double mAltitude, AreaGeografica areaGeografica) {
        return this.mListaAreaGeografica.getAreaGeografica(areaGeografica).novaLocalizacao(mLatitude, mLongitude, mAltitude);
    }

    public Sensor criarNovoSensor (String nome, TipoSensor novoTipoSensor, Localizacao novaLocalizacao, AreaGeografica areaGeografica) {
        return this.mListaAreaGeografica.getAreaGeografica(areaGeografica).novoSensor(nome, novoTipoSensor, novaLocalizacao);
    }
}
