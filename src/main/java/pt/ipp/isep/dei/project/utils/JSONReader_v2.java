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
        List<ReadingDTO> readingList = new ArrayList<>();

        // Get reading within list
        if (sensorReading.isJsonArray()) {
            JsonArray readingArray = sensorReading.getAsJsonArray();
            JsonArray readingAttributes = readingArray.get("readings").getAsJsonArray();
            List<JsonObject> list = new ArrayList<>();
            for (int i = 0; i < readingAttributes.size(); i++) {
                list.add(readingAttributes.get(i).getAsJsonObject());
            }

            //Reads reading attributes
            for (JsonObject object : list) {

                // Get objects from reading
                LocalDateTime dateTime = LocalDateTime.parse(reading.get("timestamp/date").getAsString());
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
                    String sensorUnits = sensor.get("units").getAsString();

                    SensorDTO areaSensor1 = new SensorDTO();
                    areaSensor1.setId(sensorId);
                    areaSensor1.setUnits(sensorUnits);

                    sensorList.add(areaSensor1);

                }
                readingList.add(readingDTO);
            }
        return readingList;
    }*/
}