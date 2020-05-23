package moe.jae.osrlauncher.Utils;

import javax.swing.*;
import java.io.File;

public class ClientLauncher {

    private static ClassLoader loader;
    private static Class<?> mainClass;
    private String configDirectory;
    private String gameType;
    private static JFrame frame;

    public ClientLauncher() {
        this.configDirectory = Defaults._DEFAULT_CONFIG_DIR;
        this.gameType = "base";
    }

    public ClientLauncher(String configDir) {
        this.configDirectory = configDir;
        this.gameType = "base";
    }

    public ClientLauncher(String configDir, String gameType) {
        this.configDirectory = configDir;
        this.gameType = gameType;
    }

    public void launch() {

        File gameFile;

        System.out.println("Config dir: " + this.configDirectory);
        System.out.println("Game type: " + this.gameType);

        switch (this.gameType) {
            case "openpk":
                gameFile = new File(this.configDirectory + File.separator + Defaults._CLIENT_OPENPK_FILENAME);
                break;

            case "default":
                gameFile = new File(this.configDirectory + File.separator + Defaults._CLIENT_FILENAME);
                break;

            default:
                return;
        }

        try {
            System.out.println("Launching!");
            ProcessBuilder gameProcess = new ProcessBuilder(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java", "-jar", gameFile.getAbsolutePath());
            gameProcess.start();
        } catch (Exception error) {
            System.out.println("Something bad happened, please report this error to admins:\n");
            error.printStackTrace();
        }

    }

}
