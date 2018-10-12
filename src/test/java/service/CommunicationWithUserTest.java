package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CommunicationWithUserTest {

    private CommunicationWithUser communicationWithUser;
    private  SelectedOptions selectedOptions;


    @BeforeAll
   void setUp()
    {
        int [] reportNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int [] numersWithID = {2,4,6,8};
        int [] reportDestinationNumbers = {1, 2};
        selectedOptions = mock(SelectedOptions.class);
        communicationWithUser = new CommunicationWithUser(reportNumbers, numersWithID, reportDestinationNumbers, selectedOptions);
    }

    @Test
    void checkOptionsAreCorrect_check_if_return_true() {

        assertEquals(communicationWithUser.checkOptionsAreCorrect(), true);
    }


}