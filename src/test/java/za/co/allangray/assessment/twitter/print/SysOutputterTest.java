package za.co.allangray.assessment.twitter.print;

import org.junit.Before;
import org.mockito.*;
import za.co.allangray.assessment.twitter.model.Tweet;
import za.co.allangray.assessment.twitter.model.User;

import java.util.Arrays;
import java.util.HashSet;

public class SysOutputterTest {

    @Mock
    private SysOutputter sysOutputter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void ShouldPrintTweetSuccess() {

        Tweet tweet = getTweet();

        sysOutputter.write(tweet);

        Mockito.verify(sysOutputter, Mockito.atLeastOnce()).write(tweet);

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

        sysOutputter.write(user);

        Mockito.verify(sysOutputter, Mockito.atLeastOnce()).write(user);
    }

    private User getUser() {
        return User.builder().name("name").followers(new HashSet<>(Arrays.asList("name")))
                .build();
    }
}