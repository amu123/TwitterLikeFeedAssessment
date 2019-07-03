package za.co.allangray.assessment.twitter.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class User implements Comparable<User> {

    private String name;
    private Set<String> followers;

  public int compareTo(User user) {
        return name.compareToIgnoreCase(user.name);
   }

}
