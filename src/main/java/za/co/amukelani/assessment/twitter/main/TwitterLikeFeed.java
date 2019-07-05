package za.co.amukelani.assessment.twitter.main;


import org.apache.log4j.Logger;
import za.co.amukelani.assessment.twitter.model.Tweet;
import za.co.amukelani.assessment.twitter.model.User;
import za.co.amukelani.assessment.twitter.print.Impl.PrintFormattedTwitsImp;
import za.co.amukelani.assessment.twitter.print.PrintFormattedTwits;
import za.co.amukelani.assessment.twitter.services.impl.ProcessTweetsImpl;
import za.co.amukelani.assessment.twitter.services.impl.ProcessUsersImpl;
import za.co.amukelani.assessment.twitter.services.ProcessTweets;
import za.co.amukelani.assessment.twitter.services.ProcessUsers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static za.co.amukelani.assessment.twitter.utility.Constants.PROCESS_FILE_WITH_THE_FOLLOWING_ERROR;

public class TwitterLikeFeed {

    private static final Logger LOG = Logger.getLogger(TwitterLikeFeed.class);

    public static PrintFormattedTwits out = new PrintFormattedTwitsImp();

    public static void main(String[] args) {

        printFormattedTwits();
    }

    private static void printFormattedTwits() {
        Set<User> users = null;

        try {

            ProcessUsers processUsers = new ProcessUsersImpl();
            users = processUsers.processUsers();

            users.forEach(currentUser -> {

                System.out.println();
                out.write(currentUser);

                getTweetsForCurrentUser(currentUser);
            });
        } catch (IOException ex) {
            LOG.error(PROCESS_FILE_WITH_THE_FOLLOWING_ERROR, ex);
        }
    }

    private static void getTweetsForCurrentUser(User currentUser) {

        List<Tweet> tweets = null;

        try {
            ProcessTweets processTweets = new ProcessTweetsImpl();

            tweets = processTweets.processTweets();

            tweets.forEach(currentTweet -> {
                if(currentUser.getName().equals(currentTweet.getUser())
                        || currentUser.getFollowers().contains(currentTweet.getUser())){
                    out.write(currentTweet);
                }
            });
        } catch (IOException ex) {
            LOG.error(PROCESS_FILE_WITH_THE_FOLLOWING_ERROR, ex);
        }

    }
}
