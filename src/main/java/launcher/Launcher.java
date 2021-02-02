package launcher;

import launcher.Fancy.MainWindow;
import launcher.Gameupdater.Updater;
import launcher.Utils.Defaults;

public class Launcher {

    private MainWindow launcherWindow;

    public void initializeLauncher() {
        Updater updater = new Updater(Defaults._DEFAULT_CONFIG_DIR, Defaults._CURRENT_VERSION.toString());
        updater.updateGame();

        // Initialize UI
        final MainWindow frame = new MainWindow();
        frame.build();
    }

}
