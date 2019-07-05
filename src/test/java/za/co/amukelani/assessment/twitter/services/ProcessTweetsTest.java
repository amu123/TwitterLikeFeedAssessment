package za.co.amukelani.assessment.twitter.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import za.co.amukelani.assessment.twitter.model.Tweet;

import java.io.IOException;
import java.util.List;

public class ProcessTweetsTest {

    @Mock
    private ProcessTweets processTweets;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldProcessTweetsSuccess() throws IOException {

        List<Tweet> tweets = processTweets.processTweets();

        Assert.assertNotNull(tweets);

    }
}