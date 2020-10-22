package launcher;

import launcher.Fancy.MainWindow;
import launcher.Gameupdater.Updater;
import launcher.Utils.Defaults;

public class Launcher {

    private String _CONFIG_DIRECTORY;
    private MainWindow launcherWindow;

    public Launcher(String configDirectory) {
        this._CONFIG_DIRECTORY = configDirectory;
    }

    public void initializeLauncher() {
        Updater updater = new Updater(this._CONFIG_DIRECTORY, Defaults._CURRENT_VERSION.toString());
        updater.updateGame();

        // Initialize UI
        launcherWindow = new MainWindow(this._CONFIG_DIRECTORY);
        launcherWindow.initializeWindow();
        launcherWindow.build();
    }

}
