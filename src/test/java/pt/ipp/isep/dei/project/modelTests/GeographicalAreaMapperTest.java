package pt.ipp.isep.dei.project.modelTests;
/*

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.GeoAreaService;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
public class GeographicalAreaMapperTest {
    private GeographicalAreaDTO portoDTO;
    private GeographicalArea porto;
    @Autowired
    private GeoAreaRepository geoAreaRepository;
*/
    /**
     * Method that initializes some attributes of this test class to simplify all tests.
     */
   /* @Before
    void StartUp() {
        GeoAreaService.getInstance().setGeoAreaRepository(geoAreaRepository);

        // Geo Area DTO
        portoDTO = GeographicalAreaMapper.newGeoAreaDTO();
        portoDTO.setId("Porto");
        portoDTO.setDescription("City of Porto");
        portoDTO.setType("City");
        portoDTO.setWidth(200);
        portoDTO.setLength(150);
        portoDTO.setLatitude(15);
        portoDTO.setLongitude(20);
        portoDTO.setElevation(10);

        // Geo Area
        GeographicalAreaType city = new GeographicalAreaType(portoDTO.getType());
        Location location = new Location(portoDTO.getLatitude(), portoDTO.getLongitude(), portoDTO.getElevation());
        AreaShape shape = new AreaShape(portoDTO.getWidth(), portoDTO.getLength(), location);
        porto = new GeographicalArea(portoDTO.getId(), portoDTO.getDescription(), city, location, shape);
    }*/

    /**
     * method that transforms a map to DTO
     */
  /*  @Test
    public void testMapToDTO_mapToDTO_GeoAreaDTO() {
        // Arrange
        String id = "Nation";
        String description = "Porto";
        String geographicalAreaType = "City";
        double width = 200;
        double length = 150;
        double latitude = 15;
        double longitude = 20;
        double altitude = 10;

        GeographicalAreaDTO geographicalAreaDTO = new GeographicalAreaDTO();
        geographicalAreaDTO.setId(id);
        geographicalAreaDTO.setDescription(description);
        geographicalAreaDTO.setType(geographicalAreaType);
        geographicalAreaDTO.setWidth(width);
        geographicalAreaDTO.setLength(length);
        geographicalAreaDTO.setLatitude(latitude);
        geographicalAreaDTO.setLongitude(longitude);
        geographicalAreaDTO.setElevation(altitude);
        GeographicalArea expectedResult = GeographicalAreaMapper.mapToEntity(geographicalAreaDTO);

        //Act
        GeographicalAreaDTO anotherArea = GeographicalAreaMapper.mapToDTO(id, description, geographicalAreaType, width, length, latitude, longitude, altitude);
        GeographicalArea result = GeographicalAreaMapper.mapToEntity(anotherArea);

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(width, result.getAreaShape().getWidth(), 0.0001);
        assertEquals(length, result.getAreaShape().getLength(),0.0001);

    }*/

    /**
     * Test that tries to create a GeographicalArea based on a GeographicalAreaDTO, which results in a
     * new GeographicalArea with the information contained by the GeographicalAreaDTO.
     */
   /* @Test
    void testMapToEntity_tryingToCreateBasedOnAGeoAreaDTO_ShouldReturnTrue() {
        // Act
        boolean result = GeographicalAreaMapper.mapToEntity(portoDTO).equals(porto);

        // Assert
        assertTrue(result);
    }*/

    /**
     * Test that tries to create a GeographicalArea based on a null Object, which results in a non creation.
     */
   /* @Test
    void testMapToEntity_tryingToCreateBasedOnANullObject_ShouldReturnNull() {
        // Act
        GeographicalArea result = GeographicalAreaMapper.mapToEntity(null);

        // Assert
        assertNull(result);
    }*/

    /**
     * Test that tries to create a ReadingDTO based on a Reading, which results in a new ReadingDTO with the information
     * contained by the Reading.
     */
   /* @Test
    void testMapToDTO_tryingToCreateBasedOnAReading_ShouldReturnTrue() {
        // Act
        boolean result = GeographicalAreaMapper.mapToDTO(porto).getId().equalsIgnoreCase(portoDTO.getId());

        // Assert
        assertTrue(result);
    }*/

    /**
     * Test that tries to create a ReadingDTO based on a null Object, which results in a non creation.
     */
   /* @Test
    void testMapToDTO_tryingToCreateBasedOnANullObject_ShouldReturnNull() {
        // Act
        GeographicalAreaDTO result = GeographicalAreaMapper.mapToDTO(null);

        // Assert
        assertNull(result);
    }
}
*/