package launcher;

import launcher.Fancy.MainWindow;
import launcher.Gameupdater.Updater;
import launcher.Utils.Defaults;

import javax.swing.*;
import java.awt.*;

public class Launcher extends Component {

    public static ImageIcon icon = null;
    public static ImageIcon icon_warn = null;

    private MainWindow launcherWindow;

    public void initializeLauncher() {
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

        // Fetch client file and cache updates
        Updater updater = new Updater(Defaults._DEFAULT_CONFIG_DIR, Defaults._CURRENT_VERSION.toString());
        updater.updateGame();

        // Initialize UI
        final MainWindow frame = new MainWindow();
        frame.build();
    }
}
