package pt.ipp.isep.dei.project.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class JSONReaderGeoAreasSensors implements ProjectFileReader {
    private String readerName = "json";

    public JSONReaderGeoAreasSensors() {
        // empty
    }

    private static List<Object> parseJsonObjects(JsonElement areaGeo) throws NumberFormatException, DateTimeParseException, NullPointerException {
        List<Object> areaGeolist = new ArrayList<>();

        // Get area geo object within list
        if (areaGeo.isJsonObject()) {
            JsonObject jObject = areaGeo.getAsJsonObject();
            JsonObject geoArea = jObject.get("geographical_area_list").getAsJsonObject();
            JsonArray cenas = geoArea.get("geographical_area").getAsJsonArray();
            List<JsonObject> lista = new ArrayList<>();
            for (int i = 0; i < cenas.size(); i++) {
                lista.add(cenas.get(i).getAsJsonObject());
            }

            //Reads Geo Area attributes
            for (JsonObject object : lista) {

                // Get objects from geo area
                String id = object.get("id").getAsString();
                String description = object.get("description").getAsString();
                String type = object.get("type").getAsString();
                double width = object.get("width").getAsDouble();
                double length = object.get("length").getAsDouble();

                JsonObject location = object.get("location").getAsJsonObject();

                LocationDTO areaLocation = locationParser(location);

                GeographicalAreaDTO geoAreaDTO = GeographicalAreaMapper.mapToDTO(id, description, type, width, length, areaLocation.getLatitude(), areaLocation.getLongitude(), areaLocation.getElevation());

                //Reads Sensor attributes
                JsonArray areaSensor = object.get("area_sensor").getAsJsonArray();

                List<JsonObject> sensorList = new ArrayList<>();

                for (JsonElement sensor : areaSensor) {
                    sensorList.add(sensor.getAsJsonObject());
                }

                for (JsonObject sensor1 : sensorList) {
                    JsonObject sensor = sensor1.get("sensor").getAsJsonObject();

                    String sensorId = sensor.get("id").getAsString();
                    String sensorName = sensor.get("name").getAsString();
                    String sensorType = sensor.get("type").getAsString();
                    LocalDate startingDate = LocalDate.parse(sensor.get("start_date").getAsString());
                    String sensorUnits = sensor.get("units").getAsString();

                    //Reads sensor Location
                    JsonObject locationSensor = object.get("location").getAsJsonObject();

                    LocationDTO sensorLocation = locationParser(locationSensor);

                    SensorDTO areaSensor1 = new SensorDTO();
                    areaSensor1.setId(sensorId);
                    areaSensor1.setName(sensorName);
                    areaSensor1.setSensorType(sensorType);
                    areaSensor1.setLocation(sensorLocation);
                    areaSensor1.setStartingDate(startingDate);
                    areaSensor1.setUnits(sensorUnits);

                    geoAreaDTO.addSensor(areaSensor1);

                }
                areaGeolist.add(geoAreaDTO);
            }

        }
        return areaGeolist;
    }

    @Override
    public String getTypeName() {
        return this.readerName;
    }

    private static LocationDTO locationParser(JsonObject object) throws NumberFormatException {

        double latitude = object.get("latitude").getAsDouble();
        double longitude = object.get("longitude").getAsDouble();
        double altitude = object.get("altitude").getAsDouble();

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(latitude);
        locationDTO.setLongitude(longitude);
        locationDTO.setElevation(altitude);

        return locationDTO;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> readFile(File file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        List<Object> finallist;
        //JSON parser object to parse read file
        JsonParser jsonParser = new JsonParser();
        //Read JSON file
        JsonElement elem = jsonParser.parse(fileReader);
        try {
            finallist = parseJsonObjects(elem);
        } catch (NumberFormatException | DateTimeParseException | NullPointerException e) {
            finallist = null;
        }
        return finallist;
    }
}
