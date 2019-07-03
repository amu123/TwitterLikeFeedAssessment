package za.co.allangray.assessment.twitter.model;

public class Tweet {

    private final String user;
    private final String message;

    public Tweet(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
