package connectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataBaseConnectorTest {

    private DataBaseConnector connector;

    @BeforeAll
    void setUp()
    {
         connector = new DataBaseConnector();

    }

    @Test
    void checkIfClientIDExists_check_If_return_is_null_when_givenClientID_is_null() {

        String givenClientID = null;

        assertEquals(connector.checkIfClientIDExists(givenClientID), false);
    }


}