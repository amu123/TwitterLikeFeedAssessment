package za.co.allangray.assessment.twitter.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import za.co.allangray.assessment.twitter.model.Tweet;
import za.co.allangray.assessment.twitter.services.impl.ProcessTweetsImpl;
import za.co.allangray.assessment.twitter.utility.Constants;
import za.co.allangray.assessment.twitter.utility.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

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