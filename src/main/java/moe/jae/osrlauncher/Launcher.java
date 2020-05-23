package moe.jae.osrlauncher;

import moe.jae.osrlauncher.Fancy.MainWindow;

public class Launcher {

    private String _CONFIG_DIRECTORY;
    private String _CACHE_DIRECTORY;
    private MainWindow launcherWindow;

    public Launcher(String configDirectory, String cacheDirectory) {
        this._CONFIG_DIRECTORY = configDirectory;
        this._CACHE_DIRECTORY = cacheDirectory;
    }

    public void initializeLauncher() {
        /* TODO
        *   DOWNLOAD ALL GAME FILES TO THE CACHE FOLDER */

        // Initialize UI
        launcherWindow = new MainWindow();
        launcherWindow.initializeWindow();
        launcherWindow.populateWindow();
    }

}
