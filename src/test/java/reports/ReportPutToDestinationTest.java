package reports;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class ReportPutToDestinationTest {

    private  ReportPutToDestination reportPutToDestination;

    @BeforeAll
    public void setUp() {

        reportPutToDestination = new ReportPutToDestination();
    }

    @Test
    void callReport_check_if_Destination_is_correct()
    {
        int outputDestination = 1;
        assertEquals(reportPutToDestination.callReport(anyString(), outputDestination),0);

    }

    @Test
    void callReport_check_if_outputDestination_is_not_correct()
    {
        int outputDestination = 3;
        assertEquals(reportPutToDestination.callReport(anyString(), outputDestination),1);

    }
}