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

        Download(new File(Defaults._MD5_TABLE_FILENAME));

        _UPDATERGUI.hideWin();
    }

    private void Download(File file) {

        try {

            String filename = file.toString();

            String completeFileUrl = Defaults._GAME_FILES_SERVER + filename;

            URLConnection connection = new URL(completeFileUrl).openConnection();

            // File metadata
            String description = getDescription(file);
            int fileSize = connection.getContentLength();

            try (BufferedInputStream inputStream = new BufferedInputStream(new URL(completeFileUrl).openStream());
            FileOutputStream fileOS = new FileOutputStream(this._GAMEFOLDER + File.separator + filename)){
                byte data[] = new byte[1024];
                int byteContent;
                int totalRead = 0;

                while((byteContent = inputStream.read(data, 0, 1024)) != -1) {

                    totalRead += byteContent;
                    fileOS.write(data, 0, byteContent);
                    MainUpdaterGui.get().setDownloadProgress(description, (float) (totalRead * 100 / fileSize));

                }

            } catch (Exception error) {
                error.printStackTrace();
            }


        } catch (Exception error) {
            error.printStackTrace();
        }

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
