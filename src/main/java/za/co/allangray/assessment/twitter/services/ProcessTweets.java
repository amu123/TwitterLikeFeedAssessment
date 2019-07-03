package za.co.allangray.assessment.twitter.services;

import za.co.allangray.assessment.twitter.model.Tweet;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ProcessTweets {
    List<Tweet> processTweets() throws IOException;
}
