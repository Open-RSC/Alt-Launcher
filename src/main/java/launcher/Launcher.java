package launcher;

import launcher.Fancy.MainWindow;
import launcher.Gameupdater.Updater;
import launcher.Utils.Defaults;

import javax.swing.*;
import java.awt.*;

public class Launcher extends Component {

    public static ImageIcon icon = null;

    private MainWindow launcherWindow;

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

        // Fetch client file and cache updates
        Updater updater = new Updater(Defaults._DEFAULT_CONFIG_DIR, Defaults._CURRENT_VERSION.toString());
        updater.updateGame();

        // Initialize UI
        final MainWindow frame = new MainWindow();
        frame.build();
    }
}
