package moe.jae.osrlauncher.Gameupdater.UpdaterGui;

import moe.jae.osrlauncher.Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class MainUpdaterGui extends JFrame {

    private JFrame _UPDATER_WINDOW;
    private JProgressBar _DOWNLOAD_PROGRESS;
    private static MainUpdaterGui _INSTANCE;
    private JLabel _BACKGROUND;

    public MainUpdaterGui() {
        MainUpdaterGui._INSTANCE = this;
    }

    public void setWinVisible() {
        this._UPDATER_WINDOW.setVisible(true);
    }

    public void hideWin() {
        this._UPDATER_WINDOW.setVisible(false);
    }

    public static MainUpdaterGui get() {
        return MainUpdaterGui._INSTANCE;
    }

    public void init() {
        _UPDATER_WINDOW = new JFrame();
        _UPDATER_WINDOW.setVisible(false);
        _UPDATER_WINDOW.setSize(400,200);
        _UPDATER_WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _UPDATER_WINDOW.setUndecorated(true);
        _UPDATER_WINDOW.setTitle("ORSC Updater");
        _UPDATER_WINDOW.setIconImage(Utils.getImage("icon.png").getImage());
        _UPDATER_WINDOW.setLocationRelativeTo(null);

        (this._BACKGROUND = new JLabel()).setBounds(0, 0, 400, 200);
        this.add(this._BACKGROUND);

        (this._DOWNLOAD_PROGRESS = new JProgressBar(0, 100)).setBounds(0, 0, 400, 200);
        this._DOWNLOAD_PROGRESS.setBackground(new Color(45, 46, 42));
        this._DOWNLOAD_PROGRESS.setOpaque(true);
        this._DOWNLOAD_PROGRESS.setStringPainted(true);
        this._DOWNLOAD_PROGRESS.setBorderPainted(false);
        this.add(this._DOWNLOAD_PROGRESS);
    }

    public void setDownloadProgress(String f, float percent) {
        if (percent >= 90) this._DOWNLOAD_PROGRESS.setForeground(new Color(0, 153, 0));
        else if (percent >= 80 && percent < 90) this._DOWNLOAD_PROGRESS.setForeground(new Color(91, 153, 0));
        else if (percent >= 70 && percent < 80) this._DOWNLOAD_PROGRESS.setForeground(new Color(130, 153, 0));
        else if (percent >= 60 && percent < 70) this._DOWNLOAD_PROGRESS.setForeground(new Color(153, 147, 0));
        else if (percent >= 50 && percent < 60) this._DOWNLOAD_PROGRESS.setForeground(new Color(153, 122, 0));
        else if (percent >= 40 && percent < 50) this._DOWNLOAD_PROGRESS.setForeground(new Color(153, 102, 0));
        else if (percent >= 30 && percent < 40) this._DOWNLOAD_PROGRESS.setForeground(new Color(153, 63, 0));
        else if (percent >= 20 && percent < 30) this._DOWNLOAD_PROGRESS.setForeground(new Color(153, 43, 0));
        else this._DOWNLOAD_PROGRESS.setForeground(new Color(153, 0, 0));
        this._DOWNLOAD_PROGRESS.setValue((int) percent);
        this._DOWNLOAD_PROGRESS.setString(f + " - " + (int) percent + "%");
        this._DOWNLOAD_PROGRESS.repaint();
    }


}
