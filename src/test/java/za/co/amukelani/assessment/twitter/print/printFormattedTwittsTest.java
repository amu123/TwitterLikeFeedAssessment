package za.co.amukelani.assessment.twitter.print;

import org.junit.Before;
import org.mockito.*;
import za.co.amukelani.assessment.twitter.model.Tweet;
import za.co.amukelani.assessment.twitter.model.User;

import java.util.Arrays;
import java.util.HashSet;

public class printFormattedTwittsTest {

    @Mock
    private PrintFormattedTwits printFormattedTwits;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void ShouldPrintTweetSuccess() {

        Tweet tweet = getTweet();

        printFormattedTwits.write(tweet);

        Mockito.verify(printFormattedTwits, Mockito.atLeastOnce()).write(tweet);

    }

    private Tweet getTweet() {
        return Tweet.builder()
                .user("user")
                .message("message")
                .build();
    }

    @org.junit.Test
    public void ShouldPrintUserSuccess() {

        User user = getUser();

        printFormattedTwits.write(user);

        Mockito.verify(printFormattedTwits, Mockito.atLeastOnce()).write(user);
    }

    private User getUser() {
        return User.builder().name("name").followers(new HashSet<>(Arrays.asList("name")))
                .build();
    }
}