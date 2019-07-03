package za.co.allangray.assessment.twitter.services.Impl;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.apache.log4j.Logger;
import za.co.allangray.assessment.twitter.main.Constants;
import za.co.allangray.assessment.twitter.model.Tweet;
import za.co.allangray.assessment.twitter.services.ProcessTweets;
import za.co.allangray.assessment.twitter.utility.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ProcessTweetsImpl implements ProcessTweets {

    private static final Logger LOG = Logger.getLogger(ProcessTweets.class);

    private static final String SEPARATOR = "> ";


    public List<Tweet> process() throws IOException {
        List<Tweet> tweets = new ArrayList<Tweet>(400);

        FileUtils utils = new FileUtils();
        File tweetsFile = utils.getFileFromResources(Constants.TWEET_FILE);

        FileReader reader = new FileReader(tweetsFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        LOG.info("Starting to process tweet file: {}" + tweetsFile.getPath());


        String line;
        while((line = bufferedReader.readLine()) != null) {
            final String[] values = line.split(SEPARATOR);
            final String user = values[0];
            final String message = values[1];

            Tweet tweet = new Tweet(user, message);
            tweets.add(tweet);
        }

        LOG.info("Completed processing tweet file.");

        bufferedReader.close();

        return tweets;
    }
}
