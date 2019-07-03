package za.co.allangray.assessment.twitter.services.Impl;

import org.apache.log4j.Logger;
import za.co.allangray.assessment.twitter.main.Constants;
import za.co.allangray.assessment.twitter.model.TwitterAccount;
import za.co.allangray.assessment.twitter.services.ProcessUsers;
import za.co.allangray.assessment.twitter.utility.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ProcessUsersImpl implements ProcessUsers {

    private static final Logger LOG = Logger.getLogger(ProcessUsers.class);

    private static final String KEYWORD_FOLLOWS = "follows";

    public Set<TwitterAccount> process() throws IOException {
        Map<String, TwitterAccount> tmpAccs = new HashMap<String, TwitterAccount>();

        FileUtils utils = new FileUtils();
        File usersFile = utils.getFileFromResources(Constants.USER_FILE);

        FileReader reader = new FileReader(usersFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

            LOG.info("Starting to process twitter user file: {}" + usersFile.getPath());

            int lineNumber = 0;
            while((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                int keywordIdx = line.indexOf(KEYWORD_FOLLOWS);
                if (keywordIdx<0) {
                    throw new RuntimeException(String.format("Failed processing user file at line: %d (syntax error)", lineNumber));
                }
                String user = line.substring(0, keywordIdx).trim();

                if(line.contains(KEYWORD_FOLLOWS)) {
                    String followerCheck = line.substring(keywordIdx + KEYWORD_FOLLOWS.length()).trim();

                    if(followerCheck == null) {
                        throw new RuntimeException(String.format("Failed processing user file at line: %d (syntax error)", lineNumber));
                    }
                }
                String[] rawFollowers = line.substring(keywordIdx + KEYWORD_FOLLOWS.length()).split(",");

                TwitterAccount acc;

                if(!tmpAccs.containsKey(user)) {
                    acc = new TwitterAccount();
                    acc.setName(user);
                    acc.setFollowers(new HashSet<String>());

                    tmpAccs.put(user, acc);
                } else {
                    acc = tmpAccs.get(user);
                }

                Set<String> followers = acc.getFollowers();
                for(String currentFollower : rawFollowers) {
                    String follower = currentFollower.trim();
                    followers.add(follower);

                    TwitterAccount followerAcc;
                    if(!tmpAccs.containsKey(follower)) {
                        followerAcc = new TwitterAccount();
                        followerAcc.setName(follower);
                        followerAcc.setFollowers(new HashSet<String>());

                        tmpAccs.put(follower, followerAcc);
                    }
                }
            }


        LOG.info(String.format("%d users created.", tmpAccs.size()));

        Set<TwitterAccount> accounts = new TreeSet<TwitterAccount>();
        accounts.addAll(tmpAccs.values());

        return accounts;
    }
}
