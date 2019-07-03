package za.co.allangray.assessment.twitter.utility;

import java.io.File;
import java.net.URL;

public class FileUtils {

    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = new FileUtils().getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File named "+ fileName + " not found!");
        } else {
            return new File(resource.getFile());
        }

    }

}
