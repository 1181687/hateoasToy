package pt.ipp.isep.dei.project.utilsTests;

class CSVReaderTest {

    /**
     * Test that tries to parse a valid String, which results in a list with the required information.
     */
    /*@Test
    void testParseLine_tryingToParseAValidString_ShouldReturnSuccessfulResults() {
        // Arrange
        String line = "TT12346,2018-12-30T02:00:00+00:00,14.0";

        // Act
        List<String> result = parseLine(line);

        // Assert
        assert
        assertEquals(3, result.size());
        assertEquals("TT12346", result.get(0));
        assertEquals("2018-12-30T02:00:00+00:00", result.get(1));
        assertEquals("14.0", result.get(2));
    }*/

    /**
     * Test that tries to parse an empty String, which results in a null Object.
     */
    /*@Test
    void testParseLine_tryingToParseAnEmptyString_ShouldReturnWithASizeOfZero() {
        // Arrange
        String line = "";

        // Act
        List<String> result = CSVReader.parseLine(line);

        // Assert
        assertEquals(0, result.size());
    }*/

    /**
     * Test that tries to parse a null String, which results in a null Object.
     */
    /*@Test
    void testParseLine_tryingToParseANullString_ShouldReturnWithASizeOfZero() {
        // Arrange
        String line = null;

        // Act
        List<String> result = CSVReader.parseLine(line);

        // Assert
        assertEquals(0, result.size());
    }*/
}