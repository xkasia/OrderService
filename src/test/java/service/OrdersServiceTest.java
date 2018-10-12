package service;

import connectors.DataBaseConnector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OrdersServiceTest {

    private  OrdersService ordersService;
    private  DataBaseConnector DB;


    @BeforeAll
     void setUp() {
        String [] columnsNamesForXML = {"clientId","requestId","name", "quantity", "price"};
        String [] columnsNamesFoCSV = {"Client_Id","Request_id","Name", "Quantity", "Price"};
        DB = mock(DataBaseConnector.class);
        ordersService = new OrdersService(DB, columnsNamesForXML, columnsNamesFoCSV);
    }

    @Test
    void checkFiles_check_if_return_0()
    {
        String [] filepath = new String[0];

        assertEquals(ordersService.checkFiles(filepath),0 );
    }

}