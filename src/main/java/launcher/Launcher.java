package launcher;

import launcher.Fancy.MainWindow;
import launcher.Gameupdater.Updater;
import launcher.Utils.Defaults;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Launcher extends Component {

    public static ImageIcon icon = null;

    public void initializeLauncher() {
        Settings.loadSettings();

        if (!Settings.autoUpdate) {
            int response =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Open RuneScape Classic has an automatic update feature.\n"
                                    + "\n"
                                    + "When enabled, this will prompt for and install updates when launching the client.\n"
                                    + "The updates are obtained from our 'Latest' release on GitHub.\n"
                                    + "\n"
                                    + "Would you like to enable this feature?\n"
                                    + "\n"
                                    + "NOTE: This option can be toggled in the Settings interface under the General tab.",
                            "Open RuneScape Classic",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            icon);
            if (response == JOptionPane.YES_OPTION || response == JOptionPane.CLOSED_OPTION) {
                Settings.autoUpdate = true;
                JOptionPane.showMessageDialog(
                        this,
                        "Open RuneScape Classic is set to check for updates on GitHub at every launch!",
                        "Open RuneScape Classic",
                        JOptionPane.INFORMATION_MESSAGE,
                        icon);
            } else if (response == JOptionPane.NO_OPTION) {
                Settings.autoUpdate = false;
                JOptionPane.showMessageDialog(
                        this,
                        "Open RuneScape Classic will not check for updates automatically.\n"
                                + "\n"
                                + "You will not get notified when new releases are available. To update your client, you\n"
                                + "will need to do it manually by replacing 'OpenRSC.jar' in your directory.\n"
                                + "\n"
                                + "You can enable GitHub updates again in the localSettings.conf file.",
                        "Open RuneScape Classic",
                        JOptionPane.INFORMATION_MESSAGE,
                        icon);
            }
            Settings.saveSettings();
        }

        if (Settings.autoUpdate) {
            System.out.println("Checking for launcher update...");

            if (!checkVersionNumber()) {
                System.out.println("Launcher update is available");
                int response =
                        JOptionPane.showConfirmDialog(
                                this,
                                "A launcher update is available!\n"
                                        + "\n"
                                        + "Latest: "
                                        //+ String.format("%8.6f", latestVersion)
                                        + "\n"
                                        + "Installed: "
                                        + String.format("%8.6f", Defaults._CURRENT_VERSION)
                                        + "\n"
                                        + "\n"
                                        + "Would you like to update now?",
                                "Open RuneScape Classic",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                icon);
                if (response == JOptionPane.YES_OPTION) {
                    if (updateJar()) {
                        JOptionPane.showMessageDialog(
                                this,
                                "The launcher has been updated successfully!\n"
                                        + "\n"
                                        + "The launcher requires a restart, and will now exit.",
                                "Open RuneScape Classic",
                                JOptionPane.INFORMATION_MESSAGE,
                                icon);
                        System.exit(0);
                    } else {
                        response =
                                JOptionPane.showConfirmDialog(
                                        this,
                                        "The launcher has failed to update, please try again later.\n"
                                                + "\n"
                                                + "Would you like to continue without updating?",
                                        "Open RuneScape Classic",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.ERROR_MESSAGE,
                                        icon);
                        if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {
                            System.exit(0);
                        }
                    }
                }
            }
        }

        // Fetch client file and cache updates
        Updater updater = new Updater(Defaults._DEFAULT_CONFIG_DIR, Defaults._CURRENT_VERSION.toString());
        updater.updateGame();

        // Initialize UI
        final MainWindow frame = new MainWindow();
        frame.build();
    }

    private static boolean checkVersionNumber() {
        try {
            Double currentVersion = 0.0;
            URL updateURL = new URL(Defaults._VERSION_UPDATE_URL);

            // Open connection
            URLConnection connection = updateURL.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains("VERSION_NUMBER")) {
                    currentVersion = Double.parseDouble(line.substring(line.indexOf('=') + 1, line.indexOf(';')));
                    break;
                }
            }

            // Close connection
            in.close();
            return currentVersion.equals(Defaults._CURRENT_VERSION);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateJar() {
        try {
            if (checkVersionNumber()) // Check if version is the same
                return true;

            URL url = new URL(Defaults._GAME_FILES_SERVER+Defaults._LAUNCHER_FILENAME);

            // Open connection
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            int size = connection.getContentLength();
            int offset = 0;
            byte[] data = new byte[size];

            InputStream input = url.openStream();

            int readSize;
            while ((readSize = input.read(data, offset, size - offset)) != -1) {
                offset += readSize;
            }

            if (offset == size) {
                File file = new File("./" + Defaults._LAUNCHER_FILENAME);
                FileOutputStream output = new FileOutputStream(file);
                output.write(data);
                output.close();
            }
        } catch (Exception ignored) {
        }
        return true;
    }
}
