package za.co.amukelani.assessment.twitter.print.Impl;

import org.junit.Before;
import org.mockito.*;
import za.co.amukelani.assessment.twitter.model.Tweet;
import za.co.amukelani.assessment.twitter.model.User;

import java.util.Arrays;
import java.util.HashSet;

public class printFormattedTwittsImpTest {

    @Spy
    @InjectMocks
    private PrintFormattedTwitsImp sysOutputterImp;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void ShouldPrintTweetSuccess() {

        Tweet tweet = getTweet();

        sysOutputterImp.write(tweet);

        Mockito.verify(sysOutputterImp, Mockito.atLeastOnce()).write(tweet);

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

        sysOutputterImp.write(user);

        Mockito.verify(sysOutputterImp, Mockito.atLeastOnce()).write(user);
    }

    private User getUser() {
        return User.builder().name("name").followers(new HashSet<>(Arrays.asList("name")))
                .build();
    }
}