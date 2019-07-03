package za.co.allangray.assessment.twitter.print.Impl;

import za.co.allangray.assessment.twitter.model.Tweet;
import za.co.allangray.assessment.twitter.model.TwitterAccount;
import za.co.allangray.assessment.twitter.print.SysOutputter;

public class SysOutputterImp implements SysOutputter {
    public void write(Tweet tweet) {
        System.out.println(String.format("\t@%s: %s", tweet.getOwner(), tweet.getMessage()));
    }

    public void write(TwitterAccount user) {
        System.out.println(user.getName());
    }
}
