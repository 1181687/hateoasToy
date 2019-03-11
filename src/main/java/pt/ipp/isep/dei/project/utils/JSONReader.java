package pt.ipp.isep.dei.project.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pt.ipp.isep.dei.project.model.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.GeographicalAreaMapping;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.SensorDTO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {

    @SuppressWarnings("unchecked")
    public static List<GeographicalAreaDTO> readJSONFileToList(String jsonPath) {

        List<GeographicalAreaDTO> finallist = new ArrayList<>();
        //JSON parser object to parse read file
        JsonParser jsonParser = new JsonParser();

        try (FileReader reader = new FileReader(jsonPath)) {
            //Read JSON file

            JsonElement elem = jsonParser.parse(reader);
            //System.out.println(elem);

            finallist = parseJsonObjects(elem);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finallist;
    }

    private static LocationDTO locationParser(JsonObject object) {
        //JsonObject location = object.get("location").getAsJsonObject();

        double latitude = object.get("latitude").getAsDouble();

        double longitude = object.get("longitude").getAsDouble();

        double altitude = object.get("altitude").getAsDouble();

        return new LocationDTO(latitude, longitude, altitude);
    }

    private static List<GeographicalAreaDTO> parseJsonObjects(JsonElement areaGeo) {
        List<GeographicalAreaDTO> areaGeolist = new ArrayList<>();

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

                LocationDTO areaLocation = locationParser(location);

                GeographicalAreaDTO geoAreaDTO = GeographicalAreaMapping.mapToDTO(id, type, width, length, areaLocation.getLatitude(), areaLocation.getLongitude(), areaLocation.getElevation());

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
                    JsonObject locationSensor = object.get("location").getAsJsonObject();

                    double latitude = locationSensor.get("latitude").getAsDouble();
                    System.out.println("Latitude: " + latitude);

                    double longitude = locationSensor.get("longitude").getAsDouble();
                    System.out.println("Longitude: " + longitude);

                    double altitude = locationSensor.get("altitude").getAsDouble();
                    System.out.println("Altitude: " + altitude);

                    LocationDTO sensorLocation = locationParser(locationSensor);

                    SensorDTO areaSensor1 = new SensorDTO();
                    areaSensor1.setName(sensorName);
                    areaSensor1.setSensorType(sensorType);
                    areaSensor1.setLocation(sensorLocation);
                    areaSensor1.setStartingDate(startingDate);

                    geoAreaDTO.addSensor(areaSensor1);

                }
                areaGeolist.add(geoAreaDTO);
            }

        }
        return areaGeolist;
    }
}
