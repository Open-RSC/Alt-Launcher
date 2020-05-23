package moe.jae.osrlauncher;

import moe.jae.osrlauncher.Fancy.MainWindow;
import moe.jae.osrlauncher.Gameupdater.Updater;
import moe.jae.osrlauncher.Utils.Defaults;

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
        launcherWindow = new MainWindow();
        launcherWindow.initializeWindow();
        launcherWindow.build();
    }

}
