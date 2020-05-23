package moe.jae.osrlauncher.Fancy;

import moe.jae.osrlauncher.Utils.Defaults;
import moe.jae.osrlauncher.Utils.Utils;

import javax.swing.*;

public class MainWindow {
    private JFrame launcherWindow;

    public MainWindow() {}

    public void initializeWindow() {
        launcherWindow = new JFrame();
        launcherWindow.setVisible(true);
        launcherWindow.setSize(1280,720);
        launcherWindow.setLocationRelativeTo(null);
        launcherWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launcherWindow.setTitle(Defaults._TITLE);
        launcherWindow.setResizable(false);
        launcherWindow.setIconImage(Utils.getImage("icon.png").getImage());
        launcherWindow.setFont(Utils.getFont("Helvetica.otf", 0, 11.0f));
    }

    public void populateWindow() {

    }

}
