package za.co.allangray.assessment.twitter.print.Impl;

import org.junit.Before;
import org.mockito.*;
import za.co.allangray.assessment.twitter.model.Tweet;
import za.co.allangray.assessment.twitter.model.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class SysOutputterImpTest {

    @Spy
    @InjectMocks
    private SysOutputterImp sysOutputterImp;

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