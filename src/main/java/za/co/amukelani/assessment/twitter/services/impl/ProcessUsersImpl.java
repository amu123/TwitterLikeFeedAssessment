package za.co.amukelani.assessment.twitter.services.impl;

import org.apache.log4j.Logger;
import za.co.amukelani.assessment.twitter.model.User;
import za.co.amukelani.assessment.twitter.services.ProcessUsers;
import za.co.amukelani.assessment.twitter.utility.Constants;
import za.co.amukelani.assessment.twitter.utility.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static za.co.amukelani.assessment.twitter.utility.Constants.*;

public class ProcessUsersImpl implements ProcessUsers {

    private static final Logger LOG = Logger.getLogger(ProcessUsers.class);

    public Set<User> processUsers() throws IOException {
        LOG.info(STARTING_TO_PROCESS_TWITTER_USER_FILE);

        Map<String, User> userMap = new HashMap<>();

        FileUtils utils = new FileUtils();
        File usersFile = utils.getFileFromResources(Constants.USER_FILE);

        FileReader reader = new FileReader(usersFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {

            String[] userAndFollowers = line.split(FOLLOWS);
            String userName = userAndFollowers[0].trim();

            String[] followersArray = userAndFollowers[1].split(",");

            User user = createUser(userMap, userName);

            Set<String> followers = user.getFollowers();

            List<String> followersList = Arrays.asList(followersArray);
            constructUserAndFollowers(userMap, followers, followersList);

        }

        Set<User> users = new TreeSet<>();
        users.addAll(userMap.values());

        LOG.info(COMPLETED_PROCESSING_TWITTER_USER_FILE);

        return users;
    }

    private Map<String, User> constructUserAndFollowers(Map<String, User> userMap, Set<String> followers, List<String> followersList) {
        followersList.forEach(currentFollower -> {
            String follower = currentFollower.trim();
            followers.add(follower);

            User followerAcc;
            if (!userMap.containsKey(follower)) {
                followerAcc = User.builder()
                        .name(follower)
                        .followers(new HashSet<>())
                        .build();

                userMap.put(follower, followerAcc);
            }
        });

        return userMap;
    }

    private User createUser(Map<String, User> userMap, String name) {
        User user;
        if (!userMap.containsKey(name)) {
            user = User.builder()
                    .name(name)
                    .followers(new HashSet<>())
                    .build();

            userMap.put(name, user);
        } else {
            user = userMap.get(name);
        }
        return user;
    }
}
