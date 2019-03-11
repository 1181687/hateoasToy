package pt.ipp.isep.dei.project.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {

    @SuppressWarnings("unchecked")
    public static void readJSONFileToList() {

        //JSON parser object to parse read file
        JsonParser jsonParser = new JsonParser();

        try (FileReader reader = new FileReader("JSONfile.json")) {
            //Read JSON file

            JsonElement elem = jsonParser.parse(reader);
            //System.out.println(elem);

            parseAreaGeoObject(elem);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseAreaGeoObject(JsonElement areaGeo) {

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
                System.out.println("Id: " + id);

                String description = object.get("description").getAsString();
                System.out.println("Description: " + description);

                String type = object.get("type").getAsString();
                System.out.println("Type: " + type);

                double width = object.get("width").getAsDouble();
                System.out.println("Width: " + width);

                double length = object.get("length").getAsDouble();
                System.out.println("Length: " + length);

                JsonObject location = object.get("location").getAsJsonObject();

                double latitude = location.get("latitude").getAsDouble();
                System.out.println("Latitude: " + latitude);

                double longitude = location.get("longitude").getAsDouble();
                System.out.println("Longitude: " + longitude);

                double altitude = location.get("altitude").getAsDouble();
                System.out.println("Altitude: " + altitude);

                GeographicalAreaDTO newGeoArea = new GeographicalAreaDTO();
                newGeoArea.setId(id);
                newGeoArea.setDescription(description);
                newGeoArea.setGeographicalAreaType(type);
                newGeoArea.setWidth(width);
                newGeoArea.setLenght(length);
                newGeoArea.setLatitude(latitude);
                newGeoArea.setLongitude(longitude);
                newGeoArea.setAltitude(altitude);

            }
        }

    }

    private static void parseSensorObject(JsonElement areaGeo) {

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

                //Reads Sensor attributes
                JsonArray areaSensor = object.get("area_sensor").getAsJsonArray();

                List<JsonObject> sensorList = new ArrayList<>();
                for (JsonElement sensor : areaSensor) {
                    sensorList.add(sensor.getAsJsonObject());
                }

                for (JsonObject sensor1 : sensorList) {
                    JsonObject sensor = sensor1.get("sensor").getAsJsonObject();

                    String sensorId = sensor.get("id").getAsString();
                    System.out.println("Id: " + sensorId);

                    String sensorName = sensor.get("name").getAsString();
                    System.out.println("Name: " + sensorName);

                    LocalDate startingDate = LocalDate.parse(sensor.get("start_date").getAsString());
                    System.out.println("Start date: " + startingDate);

                    String sensorType = sensor.get("type").getAsString();
                    System.out.println("Type: " + sensorType);

                    String sensorUnits = sensor.get("units").getAsString();
                    System.out.println("Units: " + sensorUnits);

                    //Reads sensor Location
                    JsonObject sensorLocation = sensor1.get("location").getAsJsonObject();

                    double sensorLatitude = sensorLocation.get("latitude").getAsDouble();
                    System.out.println("Latitude: " + sensorLatitude);

                    double sensorLongitude = sensorLocation.get("longitude").getAsDouble();
                    System.out.println("Longitude: " + sensorLongitude);

                    double sensorAltitude = sensorLocation.get("altitude").getAsDouble();
                    System.out.println("Altitude: " + sensorAltitude);

                }
            }
        }
    }
}
