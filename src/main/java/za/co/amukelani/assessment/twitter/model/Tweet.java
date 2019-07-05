package za.co.amukelani.assessment.twitter.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Tweet {

    private final String user;
    private final String message;

}
