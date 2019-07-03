package za.co.allangray.assessment.twitter.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import za.co.allangray.assessment.twitter.model.User;
import za.co.allangray.assessment.twitter.utility.Constants;
import za.co.allangray.assessment.twitter.utility.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;

public class ProcessUsersImplTest {

    @Spy
    @InjectMocks
    private ProcessUsersImpl processUsersImpl;

    @Mock
    private FileUtils utils;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldProcessUsers() throws IOException {

        Mockito.when(utils.getFileFromResources(Mockito.anyString())).thenReturn(new File(Constants.USER_FILE));

        Set<User> users = processUsersImpl.processUsers();

        assertNotNull(users);
        assertEquals(3, users.size());
    }
}