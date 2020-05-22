package moe.jae.osrlauncher.Utils;

import java.io.File;

public class Utils {

    // Simple valid path checker
    public static boolean isValidPath(String path) {
        File checkFile = new File(path);
        try {
            checkFile.getCanonicalPath();
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    // Canonical path getter
    public static String getCanonicalPath(String path) {
        File checkFile = new File(path);
        try {
            return checkFile.getCanonicalPath();
        } catch(Exception error) { // If error, fallback to the default location
            return Defaults._DEFAULT_CONFIG_DIR;
        }
    }


}
