package launcher.Utils;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ClientLauncher {

    private static ClassLoader loader;
    private static Class<?> mainClass;
    private static JFrame frame;
    private final String configDirectory;
    private final String gameType;
    private final String ip;
    private final String port;

    public ClientLauncher(String configDir, String gameType, String ip, String port) {
        this.configDirectory = configDir;
        this.gameType = gameType;
        this.ip = ip;
        this.port = port;
    }

    public void launch() {

        File gameFile;

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
        File cacheLocation = new File(this.configDirectory).getParentFile();

        try {
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

        File ipFile;
        File portFile;

        try {

            switch (type) {
                case "default":
                    fileOutIp = new FileOutputStream(this.configDirectory + File.separator + "ip.txt");
                    fileOutPort = new FileOutputStream(this.configDirectory + File.separator + "port.txt");
                    ipFile = new File(this.configDirectory + File.separator + "ip.txt");
                    portFile = new File(this.configDirectory + File.separator + "port.txt");
                    break;

                case "openpk":
                    fileOutIp = new FileOutputStream(this.configDirectory + File.separator + "PK" + File.separator + "ip.txt");
                    fileOutPort = new FileOutputStream(this.configDirectory + File.separator + "PK" + File.separator + "port.txt");
                    ipFile = new File(this.configDirectory + File.separator + "PK" + File.separator + "ip.txt");
                    portFile = new File(this.configDirectory + File.separator + "PK" + File.separator + "port.txt");
                    break;

                default:
                    return;
            }

            // Sets the IP and port
            FileOutputStream fileout;
            try {
                fileout = new FileOutputStream("Cache" + File.separator + "ip.txt");
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(ip);
                outputWriter.close();
            } catch (Exception ignored) {
            }
            try {
                fileout = new FileOutputStream("Cache" + File.separator + "port.txt");
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write(port);
                outputWriter.close();
            } catch (Exception ignored) {
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
