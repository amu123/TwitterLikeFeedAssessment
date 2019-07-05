package za.co.amukelani.assessment.twitter.services;

import za.co.amukelani.assessment.twitter.model.User;

import java.io.IOException;
import java.util.Set;

public interface ProcessUsers {
    Set<User> processUsers() throws IOException;
}
