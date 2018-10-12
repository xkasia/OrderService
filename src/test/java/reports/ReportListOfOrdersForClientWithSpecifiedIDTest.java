package reports;

import connectors.DataBaseConnector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportListOfOrdersForClientWithSpecifiedIDTest {

    private  Report report;
    private  DataBaseConnector DB;

    @BeforeAll
    void setUp()
    {
        DB = mock(DataBaseConnector.class);
    }

    @Test
    void generateRaport_check_if_return_is_null_when_executeSQL_return_is_null()
    {
        String clientID= "1";
        report = new ReportListOfOrdersForClientWithSpecifiedID(clientID);

        when(DB.executeSQL(anyString())).thenReturn(null);

        assertEquals(report.generateRaport(DB), null);
    }

    @Test
    void generateRaport_check_if_return_is_null_when_getClientID_is_null()
    {
        report = new ReportListOfOrdersForClientWithSpecifiedID(null);
        assertEquals(report.generateRaport(DB), null);
    }
    @Test
    void generateRaport_check_if_return_value_is_correct() throws SQLException {


        String clientID= "2";
        report = new ReportListOfOrdersForClientWithSpecifiedID(clientID);
        ResultSet result = mock(ResultSet.class);

        when(result.next()).thenReturn(true).thenReturn(false);
        when(DB.executeSQL(anyString())).thenReturn(result);

        when(DB.getResultSet()).thenReturn(result);
        when(result.getString(anyString())).thenReturn("a").thenReturn("b").thenReturn("c").thenReturn("d").thenReturn("E");


        assertEquals(report.generateRaport(DB),
                String.format("The list of orders for the cilent with ID" + clientID + ": \n" +
                        "Client_Id,Request_id,Name,Quantity,Price\n" + "a, b, c, d, E \n"));

    }

    @Test
    void generateRaport_check_if_SQLException_was_thrown() throws SQLException {

        String clientID= "7";
        report = new ReportListOfOrdersForClientWithSpecifiedID(clientID);
        ResultSet result = mock(ResultSet.class);

        when(DB.executeSQL(anyString())).thenReturn(result);
        when(result.next()).thenThrow(SQLException.class);

        assertEquals(report.generateRaport(DB), null);
    }

}