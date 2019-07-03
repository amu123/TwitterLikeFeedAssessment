package za.co.allangray.assessment.twitter.main;


import com.beust.jcommander.Parameter;
import org.apache.log4j.Logger;
import za.co.allangray.assessment.twitter.print.Impl.SysOutputterImp;
import za.co.allangray.assessment.twitter.print.SysOutputter;
import za.co.allangray.assessment.twitter.services.ProcessTweets;
import za.co.allangray.assessment.twitter.services.ProcessUsers;
import za.co.allangray.assessment.twitter.model.Tweet;
import za.co.allangray.assessment.twitter.model.TwitterAccount;
import za.co.allangray.assessment.twitter.services.Impl.ProcessTweetsImpl;
import za.co.allangray.assessment.twitter.services.Impl.ProcessUsersImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);

    private static final String ASCII = "ASCII";

    @Parameter(names = "--users", description = "File containing twitter users.", required = true)
    private String twitterUsersFile;
    @Parameter(names = "--tweets", description = "File containing tweets.", required = true)
    private String tweetsFile;

    @Parameter(names = "--help", help = true)
    private static boolean help;

    public static SysOutputter out = new SysOutputterImp();

    public void execute() {
        List<Tweet> tweets = null;
        Set<TwitterAccount> users = null;

        try {
            ProcessTweets tweetProc = new ProcessTweetsImpl();
            tweets = tweetProc.process();

            ProcessUsers usersProc = new ProcessUsersImpl();
            users = usersProc.process();

            for(TwitterAccount currentUser : users) {
                out.write(currentUser);

                for(Tweet currentTweet : tweets) {
                    final String owner = currentTweet.getOwner();

                    if(currentUser.getName().equals(owner) || currentUser.getFollowers().contains(owner)) {
                        out.write(currentTweet);
                    }
                }
            }
        } catch(IOException io) {
            LOG.error("Unable to process file correctly.");
        }
    }

    public static final void main(String[] args) {
    Main main = new Main();

        try {
            main.execute();
        } catch(RuntimeException exception) {
            LOG.error("", exception);

            throw exception;
        }

    }
}
