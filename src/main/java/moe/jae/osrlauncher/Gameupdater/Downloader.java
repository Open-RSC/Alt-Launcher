package moe.jae.osrlauncher.Gameupdater;

import moe.jae.osrlauncher.Gameupdater.UpdaterGui.MainUpdaterGui;
import moe.jae.osrlauncher.Utils.Defaults;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Downloader {

    private ArrayList<String> _EXCLUDED_FILES = new ArrayList<>();
    private String _GAMEFOLDER;
    private MainUpdaterGui _UPDATERGUI;

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
        this._UPDATERGUI = new MainUpdaterGui();
        _UPDATERGUI.init();
    }

    public void populateMd5Table() {
        _UPDATERGUI.setWinVisible();

        File currentMd5Table = new File(this._GAMEFOLDER + File.separator + Defaults._MD5_TABLE_FILENAME);
        if(currentMd5Table.exists())
            currentMd5Table.delete();

        String description = getDescription(new File(Defaults._MD5_TABLE_FILENAME));

        int fileSize = 0;

        try {
            URLConnection connection = new URL(Defaults._GAME_FILES_SERVER + Defaults._MD5_TABLE_FILENAME).openConnection();
            fileSize = connection.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
            return; // Return because if fails, division by 0
        }

        try(BufferedInputStream inputStream = new BufferedInputStream(new URL(Defaults._GAME_FILES_SERVER + Defaults._MD5_TABLE_FILENAME).openStream());
            FileOutputStream fileOS = new FileOutputStream(this._GAMEFOLDER + File.separator +Defaults._MD5_TABLE_FILENAME)) {
            byte data[] = new byte[1024];
            int byteContent;
            int totalRead = 0;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                totalRead += byteContent;
                fileOS.write(data, 0, byteContent);
                MainUpdaterGui.get().setDownloadProgress(description, (float) (totalRead * 100 / fileSize));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        _UPDATERGUI.hideWin();
    }

    private String getDescription(File ref) {
        int index = ref.getName().lastIndexOf('.');
        if(index == -1)
            return "General";
        else {
            String extension = ref.getName().substring(index + 1);
            if (extension.equalsIgnoreCase("ospr"))
                return "Graphics";
            else if (extension.equalsIgnoreCase("wav"))
                return "Audio";
            else if (extension.equalsIgnoreCase("orsc"))
                return "Graphics";
            else if (extension.equalsIgnoreCase("jar"))
                return "Executable";
            else if(extension.equalsIgnoreCase("xm"))
                return "Module";
            else
                return "General";

        }
    }

}
