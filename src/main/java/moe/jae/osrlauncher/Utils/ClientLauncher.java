package moe.jae.osrlauncher.Utils;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ClientLauncher {

    private static ClassLoader loader;
    private static Class<?> mainClass;
    private String configDirectory;
    private String gameType;
    private static JFrame frame;
    private String ip;
    private String port;

    public ClientLauncher(String configDir, String gameType, String ip, String port) {
        this.configDirectory = configDir;
        this.gameType = gameType;
        this.ip = ip;
        this.port = port;
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

        set(this.ip, this.port, this.gameType);
        File cacheLocation = new File(this.configDirectory);

        try {
            System.out.println("Launching!");
            ProcessBuilder gameProcess = new ProcessBuilder(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java", "-jar", gameFile.getAbsolutePath());
            gameProcess.directory(cacheLocation);
            gameProcess.start();
        } catch (Exception error) {
            System.out.println("Something bad happened, please report this error to admins:\n");
            error.printStackTrace();
        }

    }

    private void set(String ip, String port, String type) {

        FileOutputStream fileOutIp;
        FileOutputStream fileOutPort;
        try {

            switch (type) {
                case "default":
                    fileOutIp = new FileOutputStream(this.configDirectory + File.separator + "ip.txt");
                    fileOutPort = new FileOutputStream(this.configDirectory + File.separator + "port.txt");
                    break;

                case "openpk":
                    fileOutIp = new FileOutputStream(this.configDirectory + File.separator + "PK" + File.separator + "ip.txt");
                    fileOutPort = new FileOutputStream(this.configDirectory + File.separator + "PK" + File.separator + "port.txt");
                    break;

                default:
                    return;
            }

            OutputStreamWriter outputWriterip = new OutputStreamWriter(fileOutIp);
            outputWriterip.write(ip);
            outputWriterip.close();

            OutputStreamWriter outputWriterPort = new OutputStreamWriter(fileOutPort);
            outputWriterPort.write(port);
            outputWriterPort.close();

        } catch (Exception ignoredError) {}

    }

}
