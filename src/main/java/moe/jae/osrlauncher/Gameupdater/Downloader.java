package moe.jae.osrlauncher.Gameupdater;

import moe.jae.osrlauncher.Utils.Defaults;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;

public class Downloader {

    private ArrayList<String> _EXCLUDED_FILES = new ArrayList<>();
    private String _GAMEFOLDER;

    public Downloader(String gameFolder) {
        this._EXCLUDED_FILES.add(Defaults._MD5_TABLE_FILENAME);
        this._EXCLUDED_FILES.add("android_version.txt");
        this._EXCLUDED_FILES.add("android_version_pk.txt");
        this._EXCLUDED_FILES.add("openrsc.apk");
        this._EXCLUDED_FILES.add("openpk.apk");
        this._EXCLUDED_FILES.add("credentials.txt");
        this._EXCLUDED_FILES.add("config.txt");
        this._EXCLUDED_FILES.add("OpenRSC.jar");
        this._GAMEFOLDER = gameFolder;
    }

    public void populateMd5Table() {
        try(BufferedInputStream inputStream = new BufferedInputStream(new URL(Defaults._GAME_FILES_SERVER + Defaults._MD5_TABLE_FILENAME).openStream());
            FileOutputStream fileOS = new FileOutputStream(this._GAMEFOLDER + File.separator +Defaults._MD5_TABLE_FILENAME)) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }
    }

}
