package za.co.amukelani.assessment.twitter.print.Impl;

import za.co.amukelani.assessment.twitter.model.Tweet;
import za.co.amukelani.assessment.twitter.model.User;
import za.co.amukelani.assessment.twitter.print.PrintFormattedTwits;

public class PrintFormattedTwitsImp implements PrintFormattedTwits {
    public void write(Tweet tweet) {
        System.out.println(String.format("\t@%s: %s", tweet.getUser(), tweet.getMessage()));
    }

    public void write(User user) {
        System.out.println(user.getName());
    }
}
