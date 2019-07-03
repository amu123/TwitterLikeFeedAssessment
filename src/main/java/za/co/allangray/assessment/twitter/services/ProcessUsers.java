package za.co.allangray.assessment.twitter.services;

import za.co.allangray.assessment.twitter.model.TwitterAccount;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface ProcessUsers {
    Set<TwitterAccount> process() throws IOException;
}
