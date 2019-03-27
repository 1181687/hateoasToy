package pt.ipp.isep.dei.project.controllersTests;

public class ImportGeoAreasFromJSONAndXMLControllerTest {

    /**
     * Test that imports imports geo areas and sensors
     */
    /* @Test
    public void testImportGeographicalAreaAndSensors_True() throws FileNotFoundException {
        // arrange

        // DTO's
        List<Object> geographicalAreaDTOList = new ArrayList<>();

        // LocationDTO
        double latitude1 = 45;
        double longitude1 = 45;
        double altitude1= 45;
        LocationDTO locationDTO = LocationMapper.newLocationDTO();
        locationDTO.setLatitude(latitude1);
        locationDTO.setLongitude(longitude1);
        locationDTO.setElevation(altitude1);

        // SensorDTO
        String idSensor = "S1";
        String nameSensor ="sensor";
        LocalDate startingDate = LocalDate.of(2017, GregorianCalendar.AUGUST, 15);
        String typeSensor = "Temperature";
        String units = "1m/s";

        SensorDTO sensorDTO = SensorMapper.newSensorDTO();
        sensorDTO.setId(idSensor);
        sensorDTO.setName(nameSensor);
        sensorDTO.setSensorType(typeSensor);
        sensorDTO.setStartingDate(startingDate);
        sensorDTO.setLocation(locationDTO);
        sensorDTO.setUnits(units);

        // Geo area DTO
        String id = "A01";
        String description = "Espinho";
        String type = "Urban area";
        double width = 24;
        double length = 34;
        double latitude = -516;
        double longitude = 12;
        double altitude = 156;
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(id, description, type, width, length, latitude, longitude, altitude);

        // Geographical Area List
        GeographicalAreaList geoList = new GeographicalAreaList();

        // add
        geographicalAreaDTOList.add(geographicalAreaDTO);
        geographicalAreaDTO.addSensor(sensorDTO);
        File file = new File("JSONfile.json");

        ImportGeoAreasFromJSONAndXMLController ctrl = new ImportGeoAreasFromJSONAndXMLController(geoList);

        // act
        boolean result = ctrl.importGeographicalAreaAndSensors(file);

        // assert
        assertTrue(result);
    } */

    /**
     * test that doesn't import geo areas and sensors
     */
    /* @Test
    public void testImportGeographicalAreaAndSensors_False() throws FileNotFoundException {
        // arrange

        // DTO's
        List<Object> geographicalAreaDTOList = new ArrayList<>();

        // LocationDTO
        double latitude1 = 45;
        double longitude1 = 45;
        double altitude1= 45;
        LocationDTO locationDTO = LocationMapper.newLocationDTO();
        locationDTO.setLatitude(latitude1);
        locationDTO.setLongitude(longitude1);
        locationDTO.setElevation(altitude1);

        // SensorDTO
        String idSensor = "S1";
        String nameSensor ="sensor";
        LocalDate startingDate = LocalDate.of(2017, GregorianCalendar.AUGUST, 15);
        String typeSensor = "Temperature";
        String units = "1m/s";

        SensorDTO sensorDTO = SensorMapper.newSensorDTO();
        sensorDTO.setId(idSensor);
        sensorDTO.setName(nameSensor);
        sensorDTO.setSensorType(typeSensor);
        sensorDTO.setStartingDate(startingDate);
        sensorDTO.setLocation(locationDTO);
        sensorDTO.setUnits(units);

        // Geo area DTO
        String id = "A01";
        String description = "Espinho";
        String type = "Urban area";
        double width = 24;
        double length = 34;
        double latitude = -516;
        double longitude = 12;
        double altitude = 156;
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(id, description, type, width, length, latitude, longitude, altitude);

        // Geographical Area List
        GeographicalAreaList geoList = new GeographicalAreaList();
        File file = new File("Jsonfile.json");

        // add
        geographicalAreaDTO.addSensor(sensorDTO);

        ImportGeoAreasFromJSONAndXMLController ctrl = new ImportGeoAreasFromJSONAndXMLController(geoList);

        // act
        boolean result = ctrl.importGeographicalAreaAndSensors(file);

        // assert
        assertFalse(result);
    } */
}