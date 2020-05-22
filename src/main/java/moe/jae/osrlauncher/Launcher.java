package moe.jae.osrlauncher;

public class Launcher {

    private String _CONFIG_DIRECTORY;
    private String _CACHE_DIRECTORY;

    public Launcher(String configDirectory, String cacheDirectory) {
        this._CONFIG_DIRECTORY = configDirectory;
        this._CACHE_DIRECTORY = cacheDirectory;
    }

    public String getCacheDir() {
        return this._CACHE_DIRECTORY;
    }

    public String getConfigDir() {
        return this._CONFIG_DIRECTORY;
    }

}
