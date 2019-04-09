package pt.ipp.isep.dei.project.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

                GeographicalAreaDTO geoAreaDTO = GeographicalAreaMapper.newDTO();

                geoAreaDTO.setId(id);
                geoAreaDTO.setDescription(description);
                geoAreaDTO.setType(type);
                geoAreaDTO.setWidth(width);
                geoAreaDTO.setLength(length);
                geoAreaDTO.setLongitude(areaLocation.getLongitude());
                geoAreaDTO.setLatitude(areaLocation.getLatitude());
                geoAreaDTO.setElevation(areaLocation.getElevation());

                //Reads GeoAreaSensor attributes
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

                    GeoAreaSensorDTO areaSensor1 = new GeoAreaSensorDTO();
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
    public String getTypeName() {
        return this.readerName;
    }

    private static List<Object> parseJsonObjectsReadings(JsonElement sensorReading) throws NumberFormatException, NullPointerException {
        List<Object> readingList = new ArrayList<>();

        // Get reading within list
        if (sensorReading.isJsonObject()) {
            JsonObject jsonObject = sensorReading.getAsJsonObject();

            JsonArray readingArray = jsonObject.get("readings").getAsJsonArray();

            List<JsonObject> list = new ArrayList<>();

            for (int i = 0; i < readingArray.size(); i++) {
                list.add(readingArray.get(i).getAsJsonObject());
            }
            //Reads reading attributes
            for (JsonObject object : list) {
                // Get objects from reading

                String sensorID = object.get("id").getAsString();

                String readingUnit = object.get("unit").getAsString();

                LocalDateTime dateTime;

                if (sensorID.contains("RF")) {
                    LocalDate date = LocalDate.parse(object.get("timestamp/date").getAsString());
                    dateTime = date.atStartOfDay();
                } else {
                    ZonedDateTime zonedDateTimeateTime = ZonedDateTime.parse(object.get("timestamp/date").getAsString());
                    dateTime = zonedDateTimeateTime.toLocalDateTime();
                }
                double value = object.get("value").getAsDouble();
                ReadingDTO readingDTO = ReadingMapper.mapToDTOwithIDandUnits(sensorID, dateTime, value, readingUnit);
                readingList.add(readingDTO);
            }
        }
        return readingList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> readFile(File file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        //JSON parser object to parse read file
        JsonParser jsonParser = new JsonParser();
        //Read JSON file
        JsonElement elem = jsonParser.parse(fileReader);
        List<Object> finalList = new ArrayList<>();
        try {
            String firstElement = elem.toString();
            if (firstElement.startsWith("{\"geographical_area_list")) {
                finalList = parseJsonObjects(elem);
            }
            if (firstElement.startsWith("{\"readings")) {
                finalList = parseJsonObjectsReadings(elem);
            }
            if (firstElement.startsWith("{\"sensor")) {
                finalList = parseJsonRoomSensorsDTO(elem);
            }
        } catch (NumberFormatException | DateTimeParseException | NullPointerException e) {
            finalList = null;
        }
        return finalList;
    }

    private static List<Object> parseJsonRoomSensorsDTO(JsonElement sensorsDataSet) throws NumberFormatException, DateTimeParseException, NullPointerException {
        List<Object> roomSensorsDTO = new ArrayList<>();

        if (sensorsDataSet.isJsonObject()) {
            JsonObject jObject = sensorsDataSet.getAsJsonObject();
            JsonArray sensorArray = jObject.get("sensor").getAsJsonArray();
            List<JsonObject> jsonObjects = new ArrayList<>();

            for (int i = 0; i < sensorArray.size(); i++) {
                jsonObjects.add(sensorArray.get(i).getAsJsonObject());
            }


            for (JsonObject sensor : jsonObjects) {

                String sensorId = sensor.get("id").getAsString();
                String room = sensor.get("room").getAsString();
                String sensorName = sensor.get("name").getAsString();
                LocalDateTime startingDate = LocalDateTime.parse(sensor.get("start_date").getAsString());
                String sensorType = sensor.get("type").getAsString();
                String sensorUnits = sensor.get("units").getAsString();


                RoomSensorDTO roomSensorDTO = new RoomSensorDTO();
                roomSensorDTO.setId(sensorId);
                roomSensorDTO.setName(sensorName);
                roomSensorDTO.setSensorType(sensorType);
                roomSensorDTO.setRoomId(room);
                roomSensorDTO.setStartingDate(startingDate);
                roomSensorDTO.setUnits(sensorUnits);

                roomSensorsDTO.add(sensor);
            }
        }

        return roomSensorsDTO;
    }
}
