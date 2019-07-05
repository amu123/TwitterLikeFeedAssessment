package za.co.amukelani.assessment.twitter.print;

import za.co.amukelani.assessment.twitter.model.Tweet;
import za.co.amukelani.assessment.twitter.model.User;

public interface PrintFormattedTwits {

    void write(User user);

    void write(Tweet tweet);
}
