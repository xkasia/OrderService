package reports;

import connectors.DataBaseConnector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportTotalCostOfOrdersTest {


    private  Report report;
    private  DataBaseConnector DB;

    @BeforeAll
    void setUp()
    {
        report = new ReportTotalCostOfOrders();
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
        double totalCostOfOrders = 65.00;
        ResultSet result = mock(ResultSet.class);

        when(DB.executeSQL(anyString())).thenReturn(result);
        when(result.next()).thenReturn(true);
        when(result.getDouble(1)).thenReturn(totalCostOfOrders);

        assertEquals(report.generateRaport(DB),
                String.format("The total cost of orders = %.2f", totalCostOfOrders));
    }

    @Test
    void generateRaport_check_if_SQLException_was_thrown() throws SQLException {
        ResultSet result = mock(ResultSet.class);

        when(DB.executeSQL(anyString())).thenReturn(result);
        when(result.next()).thenThrow(SQLException.class);

        assertEquals(report.generateRaport(DB), null);
    }

}



