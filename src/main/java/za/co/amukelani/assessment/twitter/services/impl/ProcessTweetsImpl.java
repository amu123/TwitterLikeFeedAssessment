package za.co.amukelani.assessment.twitter.services.impl;

import org.apache.log4j.Logger;
import za.co.amukelani.assessment.twitter.utility.Constants;
import za.co.amukelani.assessment.twitter.model.Tweet;
import za.co.amukelani.assessment.twitter.services.ProcessTweets;
import za.co.amukelani.assessment.twitter.utility.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static za.co.amukelani.assessment.twitter.utility.Constants.*;

public class ProcessTweetsImpl implements ProcessTweets {

    private static final Logger LOG = Logger.getLogger(ProcessTweets.class);


    public List<Tweet> processTweets() throws IOException {

        LOG.info(STARTING_TO_PROCESS_TWEET_FILE);

        List<Tweet> tweets = new ArrayList<>();

        FileUtils utils = new FileUtils();
        File tweetsFile = utils.getFileFromResources(Constants.TWEET_FILE);

        FileReader reader = new FileReader(tweetsFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        while((line = bufferedReader.readLine()) != null) {
            final String[] values = line.split(SEPARATOR);
            final String user = values[0];
            final String message = values[1];

            Tweet tweet = Tweet.builder()
                    .user(user)
                    .message(message)
                    .build();

            tweets.add(tweet);
        }

        LOG.info(COMPLETED_PROCESSING_TWEET_FILE);

        bufferedReader.close();

        return tweets;
    }
}
