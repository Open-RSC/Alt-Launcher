package moe.jae.osrlauncher.Frame.Listeners;

import moe.jae.osrlauncher.Frame.AppFrame;
import moe.jae.osrlauncher.Frame.Elements.CheckCombo;
import moe.jae.osrlauncher.Utils.Utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void actionPerformed(ActionEvent event, final String configDirectory) {

        final String action = event.getActionCommand().toLowerCase();

        switch(action) {

            case "rsc wiki": {
                Utils.openWebPage("https://classic.runescape.wiki");
                return;
            }

            case "our wiki": {
                Utils.openWebPage("https://openrsc.com/wiki");
                return;
            }
            case "bug reports": {
                Utils.openWebPage("https://orsc.dev/open-rsc/Game/issues");
                return;
            }
            case "discord": {
                Utils.openWebPage("https://discord.gg/94vVKND");
                return;
            }

            case "openrsc": {
                String ip = "game.openrsc.com";
                String port = "43596";
                set(ip, port, configDirectory);
                launch(configDirectory);
                return;
            }
            case "cabbage": {
                String ip = "game.openrsc.com";
                String port = "43595";
                set(ip, port, configDirectory);
                launch(configDirectory);
                return;
            }

            case "preservation": {
                String ip = "game.openrsc.com";
                String port = "43594";
                set(ip, port, configDirectory);
                launch(configDirectory);
                return;
            }

            case "openpk": {
                String ip = "openpk.openrsc.com";
                String port = "43597";
                setOpenPK(ip, port, configDirectory);
                launch_openpk(configDirectory);
                return;
            }

            case "wk": {
                String ip = "game.openrsc.com";
                String port = "43598";
                set(ip, port, configDirectory);
                launch(configDirectory);
                return;
            }

            case "dev": {
                String ip = "orsc.dev";
                String port = "43599";
                set(ip, port, configDirectory);
                launch(configDirectory);
                return;
            }

            case "minimize": {
                AppFrame.get().setState(1);
                return;
            }

            case "close": {
                System.exit(0);
                return;
            }

            case "delete": {

                File folder = new File(configDirectory);
                File[] fList = folder.listFiles();
                assert fList != null;
                for(File file : fList) {
                    String extension = String.valueOf(file);
                    if(!extension.endsWith(".txt")) {
                        new File(String.valueOf(file)).delete();
                    }
                }

                File video = new File(configDirectory + "/video");
                File[] vList = video.listFiles();
                assert vList != null;
                for(File file : vList) {
                    String extension = String.valueOf(file);
                    if(extension.endsWith(".orsc")) {
                        new File(String.valueOf(file)).delete();
                    }
                    if(extension.endsWith(".osar")) {
                        new File(String.valueOf(file)).delete();
                    }
                }

                File spritePacks = new File(configDirectory + "/video/spritepacks");
                File[] sList = spritePacks.listFiles();
                assert sList != null;
                for(File file : sList) {
                    String extension = String.valueOf(file);
                    if(extension.endsWith(".osar")) {
                        new File(String.valueOf(file)).delete();
                    }
                }

                File pk = new File(configDirectory + File.separator + "PK");
                File[] pList = pk.listFiles();
                assert pList != null;
                for(File file : pList) {
                    String extension = String.valueOf(file);
                    if(extension.endsWith(".osrc")) {
                        new File(String.valueOf(file)).delete();
                    }
                    if(extension.endsWith(".osar")) {
                        new File(String.valueOf(file)).delete();
                    }
                    if(extension.endsWith(".wav")) {
                        new File(String.valueOf(file)).delete();
                    }
                    if(extension.endsWith(".txt")) {
                        new File(String.valueOf(file)).delete();
                    }
                    if(extension.endsWith(".pack")) {
                        new File(String.valueOf(file)).delete();
                    }
                    if(extension.endsWith(".mem")) {
                        new File(String.valueOf(file)).delete();
                    }
                }

                System.exit(0);

            }

            default:
                break;

        }

        System.out.println(action);

    }

    private void set(String ip, String port, String configDirectory) {
        FileOutputStream fileout;
        try {
            fileout = new FileOutputStream(configDirectory + File.separator + "ip.txt");
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(ip);
            outputWriter.close();
        } catch (Exception error) {}

        try {
            fileout = new FileOutputStream(configDirectory + File.separator + "port.txt");
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(port);
            outputWriter.close();
        } catch (Exception error) {}
    }

    private void setOpenPK(String ip, String port, String configDirectory) {
        FileOutputStream fileout;
        try {
            fileout = new FileOutputStream(configDirectory + File.separator +"PK" + File.separator + "ip.txt");
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(ip);
            outputWriter.close();
        } catch (Exception error) {}

        try {
            fileout = new FileOutputStream(configDirectory + File.separator + "PK" + File.separator + "port.txt");
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(port);
            outputWriter.close();
        } catch (Exception ignored) {
        }
    }

    private void launch(String configDirectory) {
        File f = new File(configDirectory + File.separator + "client.properties");
        f.delete();

        File configFile = new File(configDirectory + File.separator + "config.txt");
        configFile.delete();

        CheckCombo.store[] entries = AppFrame.get().getComboBoxState();

        if(!(entries.length == 1 && entries[0].text.equalsIgnoreCase("none"))) {
            try {
                FileWriter write = new FileWriter(configFile, true);
                PrintWriter writer = new PrintWriter(write);
                for(CheckCombo.store entry : entries)
                    writer.println(entry.text + ":" + (entry.state ? 1 : 0));
                writer.close();
                write.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }

        ClientLauncher.launchClient();
    }

    private void launch_openpk(String configDirectory) {
        File f = new File(configDirectory + File.separator + "PK" + File.separator + "client.properties");
        f.delete();

        File config = new File(configDirectory + File.separator + "PK" + File.separator + "config.txt");
        config.delete();

        CheckCombo.store[] entries = AppFrame.get().getComboBoxState();

        if (!(entries.length == 1 && entries[0].text.equalsIgnoreCase("none"))) {
            try {
                FileWriter write = new FileWriter(configFile, true);
                PrintWriter writer = new PrintWriter(write);
                for (CheckCombo.store entry : entries)
                    writer.println(entry.text + ":" + (entry.state ? 1 : 0));
                writer.close();
                write.close();
            } catch (IOException a) {
                a.printStackTrace();
            }
        }

        ClientLauncher.launchClient_openpk();
    }

}
