package za.co.amukelani.assessment.twitter.services.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import za.co.amukelani.assessment.twitter.model.Tweet;
import za.co.amukelani.assessment.twitter.utility.Constants;
import za.co.amukelani.assessment.twitter.utility.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProcessTweetsImplTest {

    @Spy
    @InjectMocks
    private ProcessTweetsImpl processTweetsImpl;

    @Mock
    private FileUtils utils;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldProcessTweetsSuccess() throws IOException {

        Mockito.when(utils.getFileFromResources(Mockito.anyString())).thenReturn(new File(Constants.TWEET_FILE));

        List<Tweet> tweets = processTweetsImpl.processTweets();

        Assert.assertNotNull(tweets);
        Assert.assertEquals(tweets.size(), 3);


    }
}