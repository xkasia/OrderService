package reports;

import connectors.DataBaseConnector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportNumOfOrdersForClientWithSpecifiedIDTest {

    private Report report;
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
        report = new ReportNumOfOrdersForClientWithSpecifiedID(clientID);

        when(DB.executeSQL(anyString())).thenReturn(null);

        assertEquals(report.generateRaport(DB), null);
    }

    @Test
    void generateRaport_check_if_return_is_null_when_getClientID_is_null()
    {
        report = new ReportNumOfOrdersForClientWithSpecifiedID(null);
        assertEquals(report.generateRaport(DB), null);
    }
    @Test
    void generateRaport_check_if_return_value_is_correct() throws SQLException {

        int numberOfOrders = 15;
        String clientID= "2";
        report = new ReportNumOfOrdersForClientWithSpecifiedID(clientID);
        ResultSet result = mock(ResultSet.class);

        when(DB.executeSQL(anyString())).thenReturn(result);
        when(result.next()).thenReturn(true);
        when(DB.getResultSet()).thenReturn(result);
        when(result.getInt(anyInt())).thenReturn(numberOfOrders);

        assertEquals(report.generateRaport(DB),
                "The total number of orders  for client with ID " + clientID + " =  " + numberOfOrders);

    }

    @Test
    void generateRaport_check_if_SQLException_was_thrown() throws SQLException {

        String clientID= "3";
        report = new ReportNumOfOrdersForClientWithSpecifiedID(clientID);
        ResultSet result = mock(ResultSet.class);

        when(DB.executeSQL(anyString())).thenReturn(result);
        when(result.next()).thenThrow(SQLException.class);

        assertEquals(report.generateRaport(DB), null);
    }

}