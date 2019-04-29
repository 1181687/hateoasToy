package pt.ipp.isep.dei.project.controllersTests;

/*

@DataJpaTest
@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
@SpringJUnitConfig(AddSensorToGeoAreaControllerTest.Config.class)
public class ImportGeoAreasFromJSONOrXMLControllerTest {
    @Autowired
    private GeographicalAreaService geographicalAreaService;

    @Autowired
    private GeoAreaSensorService geoAreaSensorService;

    */
/**
     * Test that imports imports geo areas and sensors
     *//*

    @Test
    public void testImportGeographicalAreaAndSensors_True() throws FileNotFoundException {
        MockitoAnnotations.initMocks(this);
        // arrange
        // DTO's
        List<Object> geographicalAreaDTOList = new ArrayList<>();

        // LocationDTO
        double latitude1 = 45;
        double longitude1 = 45;
        double altitude1 = 45;
        LocationDTO locationDTO = LocationMapper.newLocationDTO();
        locationDTO.setLatitude(latitude1);
        locationDTO.setLongitude(longitude1);
        locationDTO.setElevation(altitude1);

        // GeoAreaSensorDTO
        String idSensor = "S1";
        String nameSensor = "sensor";
        LocalDate startingDate = LocalDate.of(2017, GregorianCalendar.AUGUST, 15);
        String typeSensor = "Temperature";
        String units = "1m/s";

        GeoAreaSensorDTO sensorDTO = GeoAreaSensorMapper.newSensorDTO();
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
        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.newDTO();
        geographicalAreaDTO.setId(id);
        geographicalAreaDTO.setDescription(description);
        geographicalAreaDTO.setType(type);
        geographicalAreaDTO.setWidth(width);
        geographicalAreaDTO.setLength(length);
        geographicalAreaDTO.setLatitude(latitude);
        geographicalAreaDTO.setLongitude(longitude);
        geographicalAreaDTO.setElevation(altitude);


        // add
        geographicalAreaDTOList.add(geographicalAreaDTO);
        geographicalAreaDTO.addSensor(sensorDTO);

        String path = "datasets/geoAreas/json/JSONfile.json";
        File file = new File(path);

        ImportGeoAreasFromJSONOrXMLController ctrl = new ImportGeoAreasFromJSONOrXMLController(geographicalAreaService, geoAreaSensorService);
        ctrl.createReader(path);
        ctrl.readFile(file, path);

        // act
        boolean result = ctrl.importGeographicalAreaAndSensors();

        // assert
        assertTrue(result);
    }

    @Configuration
    static class Config {
    }

}*/
