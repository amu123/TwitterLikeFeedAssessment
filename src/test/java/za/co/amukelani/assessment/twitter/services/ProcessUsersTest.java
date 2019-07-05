package za.co.amukelani.assessment.twitter.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import za.co.amukelani.assessment.twitter.model.User;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;

public class ProcessUsersTest {

    @Mock
    private ProcessUsers processUsers;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldProcessUsers() throws IOException {


        Set<User> users = processUsers.processUsers();

        assertNotNull(users);
        assertEquals(0, users.size());
    }

}