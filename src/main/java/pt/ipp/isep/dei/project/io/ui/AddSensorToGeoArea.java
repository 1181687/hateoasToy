package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddSensorToGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeDTO;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import java.util.List;

/**
 * US006 As an Administrator, I want to add a new sensor and associate it to a geographical
 * area, so that one can get measurements of that type in that area.
 */

public class AddSensorToGeoArea {
    private AddSensorToGeoAreaController controller;
    private List<GeographicalAreaDTO> geographicalAreaDTOList;
    private List<SensorTypeDTO> sensorTypeDTOList;

    public AddSensorToGeoArea(GeoAreaService geoAreaService) {
        this.controller = new AddSensorToGeoAreaController(geoAreaService);
    }

    public void run() {

        GeoAreaSensorDTO geoAreaSensorDTO = new GeoAreaSensorDTO();

        // ID
        String label1 = "Introduce the ID of the new sensor.";
        String id = InputValidator.getString(label1);
        geoAreaSensorDTO.setId(id);

        // Name
        String label2 = "Introduce the name of the new sensor.";
        String name = InputValidator.getString(label2);
        geoAreaSensorDTO.setName(name);

        // Type
        String label3 = "Please select the sensor type: \n" + this.getSensorTypeDTOListToString();
        int positionOfSensor = InputValidator.getIntRange(label3, 1, this.getSensorTypeListSize()) - 1;

        String sensorTypeId = this.getSensorTypeByPosition(positionOfSensor).getSensorType();








/*
            if (controller.newPowerSource(powerSourceId, powerSourceTypeId, gridId)) {
                isPowerSourceAdded = true;
            } else {
                System.out.println("Name already exists. Please write a new one.");
                isPowerSourceAdded = false;
            }


        System.out.println("Success! The power source " + powerSourceId + " was added to the house grid " + gridId + ".");

    }
*/








        /*// Units
        String label5 = "Introduce the units of the new sensor.";


        Scanner read = new Scanner(System.in);

        String units = InputValidator.getString(label5);


        String label4 = "Introduce the latitude of the new sensor (valid numbers between -90 and 90).";
        double latitude = InputValidator.getDoubleRange(label4, -90, 90);

        String label5 = "Introduce the longitude of the new sensor (valid numbers between -180 and 180).";
        double longitude = InputValidator.getDoubleRange(label5, -180, 180);

        String label6 = "Introduce the altitude of the new sensor.";
        double altitude = InputValidator.getInt(label6);

        System.out.println("Introduce the type of sensor");


        if (controller.adicionarSensorAAreaGeografica(controller.criarNovoSensor(id, nome, units))) {
            System.out.println("Success! A sensor was created.");
        } else {
            System.out.println("This sensor already exists in this geographical area.");
        }*/



        /*
        int posicao1 = -1;
        do {
            for (int i = 0; i < controller.numeroElementosDaListaTipoDeSensor(); i++) {
                System.out.println((i + 1) + " - " + (controller.getNomeTipoSensorPorIndice(i)));
            }
            posicao1 = ler.nextInt();
        }
        while (posicao1 < 0 || posicao1 > controller.numeroElementosDaListaTipoDeSensor());

        ler.nextLine();
        System.out.println("In which geographical area is this sensor included?");
        int posicao2 = -1;
        do {
            for (int i = 0; i < controller.numeroElementosDaListaAreaGeografica(); i++) {
                System.out.println((i + 1) + " - " + (controller.getNomeAreaGeograficaPorIndice(i)));
            }
            posicao2 = ler.nextInt();
        }
        while (posicao2 < 0 || posicao2 > controller.numeroElementosDaListaAreaGeografica());

        controller.getAreaGeograficaNaListaPorPosicao(posicao2 - 1);
        controller.criarNovaLocalizacao(altitude, latitude, longitude);
        controller.getTipoSensorPorPosicao(posicao1 - 1);



         */
    }


    private String getGridListToString() {
        this.geographicalAreaDTOList = this.controller.getGeographicalAreaDTOList();
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (GeographicalAreaDTO geographicalAreaDTO : this.geographicalAreaDTOList) {
            content.append(number);
            content.append(" - ");
            content.append(geographicalAreaDTO.getId());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private String getSensorTypeDTOListToString() {
        this.sensorTypeDTOList = this.controller.getSensorTypeDTOList();
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (SensorTypeDTO typeDTO : this.sensorTypeDTOList) {
            content.append(number);
            content.append(" - ");
            content.append(typeDTO.getSensorType());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private int getGridListSize() {
        return this.geographicalAreaDTOList.size();
    }

    private int getSensorTypeListSize() {
        return this.sensorTypeDTOList.size();
    }

    private GeographicalAreaDTO getGridDTOByPosition(int position) {
        return this.geographicalAreaDTOList.get(position);
    }

    private SensorTypeDTO getSensorTypeByPosition(int position) {
        return this.sensorTypeDTOList.get(position);
    }
}
