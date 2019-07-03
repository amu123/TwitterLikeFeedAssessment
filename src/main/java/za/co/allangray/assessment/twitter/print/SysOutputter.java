package za.co.allangray.assessment.twitter.print;

import za.co.allangray.assessment.twitter.model.Tweet;
import za.co.allangray.assessment.twitter.model.TwitterAccount;

public interface SysOutputter {

    void write(TwitterAccount user);

    void write(Tweet tweet);
}
