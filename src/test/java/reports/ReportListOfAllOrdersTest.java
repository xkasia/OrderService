package reports;


import connectors.DataBaseConnector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportListOfAllOrdersTest {

    private  Report report;
    private DataBaseConnector DB;

    @BeforeAll
    void setUp()
    {
        report = new ReportListOfAllOrders();
        DB = mock(DataBaseConnector.class);
    }

    @Test
    void generateRaport_check_if_return_is_null_when_executeSQL_return_is_null()
    {
        when(DB.executeSQL(anyString())).thenReturn(null);
        assertEquals(report.generateRaport(DB), null);
    }
    @Test
    void generateRaport_check_if_return_value_is_correct() throws SQLException {
        ResultSet result = mock(ResultSet.class);

        when(result.next()).thenReturn(true).thenReturn(false);
        when(DB.executeSQL(anyString())).thenReturn(result);
        when(result.getString(anyString())).thenReturn("a").thenReturn("b").thenReturn("c").thenReturn("d").thenReturn("E");

        assertEquals(report.generateRaport(DB), "The list of all orders:\nClient_Id,Request_id,Name,Quantity,Price\n"
                                                        + "a, b, c, d, E \n");
    }

    @Test
    void generateRaport_check_if_SQLException_was_thrown() throws SQLException {
        ResultSet result = mock(ResultSet.class);

        when(DB.executeSQL(anyString())).thenReturn(result);
        when(result.next()).thenThrow(SQLException.class);

        assertEquals(report.generateRaport(DB), null);
    }

}