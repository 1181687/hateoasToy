package pt.ipp.isep.dei.project.utils;

public final class JSONReader_v2 {

    private JSONReader_v2() {
        // empty
    }
    /*

    @SuppressWarnings("unchecked")
    public static List<ReadingDTO> readJSONReadingFileToList(FileReader reader) {
        List<ReadingDTO> readingList;
        //JSON parser object to parse read file
        JsonParser jsonParser = new JsonParser();
        //Read JSON file
        JsonElement elem = jsonParser.parse(reader);
        try {
            readingList = parseJsonObjects(elem);
        } catch (NumberFormatException | DateTimeParseException | NullPointerException e) {
            readingList = null;
        }
        return readingList;
    }

    private static List<ReadingDTO> parseJsonObjects(JsonElement sensorReading) throws NumberFormatException, DateTimeParseException, NullPointerException {
        //List<GeographicalAreaDTO> readingList = new ArrayList<>();

        // Get area geo object within list
        /*if (sensorReading.isJsonObject()) {
            JsonObject jObject = sensorReading.getAsJsonObject();
            JsonObject reading = jObject.get("readings").getAsJsonObject();
            JsonArray readingAttributes = reading.get("readings").getAsJsonArray();
            List<JsonObject> list = new ArrayList<>();
            for (int i = 0; i < readingAttributes.size(); i++) {
                list.add(readingAttributes.get(i).getAsJsonObject());
            }

            //Reads reading attributes
            /*for (JsonObject object : list) {

                // Get objects from reading
                String sensorId = object.get("id").getAsString();
                String dateTime = object.get("timestamp/date").getAsString();
                double value = object.get("value").getAsDouble();

                ReadingDTO readingDTO = ReadingMapper.mapToDTO(dateTime,value);

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

                    readingDTO.addSensor(areaSensor1);

                }
                readingList.add(readingDTO);
            }
        //return readingList;
    }*/
}