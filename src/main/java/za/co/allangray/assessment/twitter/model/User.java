package za.co.allangray.assessment.twitter.model;

import java.util.Set;

public class User implements Comparable<User> {

    private String name;
    private Set<String> followers;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<String> followers) {
        this.followers = followers;
    }

  public int compareTo(User user) {
        return name.compareToIgnoreCase(user.name);
   }

}
