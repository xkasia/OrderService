package reports;

import service.SelectedOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportGeneratorTest {

    private SelectedOptions options;
    private  ReportGenerator generator;

    @BeforeAll
    public void setUp()
    {
        options = mock(SelectedOptions.class);
    }


    @Test
    void generateRaport_check_if_correct_report_was_called()
    {
        generator = new ReportGenerator(options);

        when(options.getReportNumber()).thenReturn(3);

        assertTrue(generator.generateReport() instanceof ReportTotalCostOfOrders);
    }

    @Test
    void generateRaport_check_if_correct_report_with_ID_was_called()
    {
        generator = new ReportGenerator(options);

        when(options.getReportNumber()).thenReturn(6);
        when(options.getGivenClientID()).thenReturn("1");

        assertTrue(generator.generateReport() instanceof ReportListOfOrdersForClientWithSpecifiedID);

    }

    @Test
    void generateRaport_check_if_wrong_report_was_called()
    {
        when(options.getReportNumber()).thenReturn(4);

        assertFalse(generator.generateReport() instanceof ReportTotalCostOfOrders);
    }



}