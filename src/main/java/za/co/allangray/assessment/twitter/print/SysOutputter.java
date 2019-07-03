package za.co.allangray.assessment.twitter.print;

import za.co.allangray.assessment.twitter.model.Tweet;
import za.co.allangray.assessment.twitter.model.User;

public interface SysOutputter {

    void write(User user);

    void write(Tweet tweet);
}
