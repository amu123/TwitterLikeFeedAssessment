package za.co.amukelani.assessment.twitter.services;

import za.co.amukelani.assessment.twitter.model.Tweet;

import java.io.IOException;
import java.util.List;

public interface ProcessTweets {
    List<Tweet> processTweets() throws IOException;
}
