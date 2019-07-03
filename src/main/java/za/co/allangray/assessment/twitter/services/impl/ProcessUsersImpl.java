package za.co.allangray.assessment.twitter.services.impl;

import org.apache.log4j.Logger;
import za.co.allangray.assessment.twitter.utility.Constants;
import za.co.allangray.assessment.twitter.model.User;
import za.co.allangray.assessment.twitter.services.ProcessUsers;
import za.co.allangray.assessment.twitter.utility.FileUtils;

import java.io.*;
import java.util.*;

import static za.co.allangray.assessment.twitter.utility.Constants.*;

public class ProcessUsersImpl implements ProcessUsers {

    private static final Logger LOG = Logger.getLogger(ProcessUsers.class);

    public Set<User> process() throws IOException {
        Map<String, User> tmpAccs = new HashMap<String, User>();

        FileUtils utils = new FileUtils();
        File usersFile = utils.getFileFromResources(Constants.USER_FILE);

        FileReader reader = new FileReader(usersFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

            LOG.info(STARTING_TO_PROCESS_TWITTER_USER_FILE + usersFile.getPath());

            int lineNumber = 0;
            while((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                int keywordIdx = line.indexOf(KEYWORD_FOLLOWS);
                if (keywordIdx<0) {
                    throw new RuntimeException(String.format(USER_FILE_AT_LINE_D_SYNTAX_ERROR, lineNumber));
                }
                String user = line.substring(0, keywordIdx).trim();

                if(line.contains(KEYWORD_FOLLOWS)) {
                    String followerCheck = line.substring(keywordIdx + KEYWORD_FOLLOWS.length()).trim();

                    if(followerCheck == null) {
                        throw new RuntimeException(String.format(USER_FILE_AT_LINE_D_SYNTAX_ERROR, lineNumber));
                    }
                }
                String[] rawFollowers = line.substring(keywordIdx + KEYWORD_FOLLOWS.length()).split(",");

                User acc;

                if(!tmpAccs.containsKey(user)) {
                    acc = User.builder()
                            .name(user)
                            .followers(new HashSet<String>())
                            .build();

                    tmpAccs.put(user, acc);
                } else {
                    acc = tmpAccs.get(user);
                }

                Set<String> followers = acc.getFollowers();

                List<String> rawFollowersList = Arrays.asList(rawFollowers);
                rawFollowersList.forEach(currentFollower -> {
                    String follower = currentFollower.trim();
                    followers.add(follower);

                    User followerAcc;
                    if(!tmpAccs.containsKey(follower)) {
                        followerAcc = User.builder()
                                .name(follower)
                                .followers(new HashSet<String>())
                                .build();

                        tmpAccs.put(follower, followerAcc);
                    }
                });

            }


        LOG.info(String.format(USERS_CREATED, tmpAccs.size()));

        Set<User> accounts = new TreeSet<>();
        accounts.addAll(tmpAccs.values());

        return accounts;
    }
}
