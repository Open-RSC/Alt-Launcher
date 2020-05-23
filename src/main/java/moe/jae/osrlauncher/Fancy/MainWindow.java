package moe.jae.osrlauncher.Fancy;

import moe.jae.osrlauncher.Utils.ClientLauncher;
import moe.jae.osrlauncher.Utils.Defaults;
import moe.jae.osrlauncher.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    // BASIC
    private JFrame _LAUNCHER_WINDOW;
    private JLabel _BACKGROUND;

    // Images
    private JLabel _CABBAGE_LOGO;
    private JLabel _DEV_LOGO;
    private JLabel _OPENPK_LOGO;
    private JLabel _OPENRSC_LOGO;

    // Other
    private String _CONFIG_DIR;

    public MainWindow(String configDir) {
        this._CONFIG_DIR = configDir;
    }

    public void initializeWindow() {
        _LAUNCHER_WINDOW = new JFrame();

        this.setPreferredSize(new Dimension(854,480));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(Defaults._TITLE);
        this.setResizable(false);
        this.setIconImage(Utils.getImage("icon.png").getImage());
        this.setFont(Utils.getFont("Helvetica.otf", 0, 11.0f));
        this.setResizable(false);
    }

    public void build() {
        (this._BACKGROUND = new JLabel()).setBounds(0, 0, 854, 480);
        this.add(this._BACKGROUND);

        this.addLogos();
        this.addListeners();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addLogos() {
        int baseXLocation = 125;
        int addOffset = 150;
        // Cabbage
        (this._CABBAGE_LOGO = new JLabel(Utils.getImage("cabbage_logo.png"))).setBounds(baseXLocation,160,100,100);
        this._BACKGROUND.add(this._CABBAGE_LOGO);
        baseXLocation += addOffset;
        // Local dev
        (this._DEV_LOGO = new JLabel(Utils.getImage("dev_logo.png"))).setBounds(baseXLocation, 160, 100, 100);
        this._BACKGROUND.add(this._DEV_LOGO);
        baseXLocation += addOffset;
        // OpenPK
        (this._OPENPK_LOGO = new JLabel(Utils.getImage("openpk_logo.png"))).setBounds(baseXLocation, 160, 100, 100);
        this._BACKGROUND.add(this._OPENPK_LOGO);
        baseXLocation += addOffset;
        // OpenRSC
        (this._OPENRSC_LOGO = new JLabel(Utils.getImage("openrsc_logo.png"))).setBounds(baseXLocation, 160, 100, 100);
        this._BACKGROUND.add(this._OPENRSC_LOGO);
    }

    private void addListeners() {
        final String currentGameFolder = this._CONFIG_DIR;
        // Launch cabbage
        this._CABBAGE_LOGO.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClientLauncher cabbageLauncher = new ClientLauncher(currentGameFolder, "default", "game.openrsc.com", "43595");
                cabbageLauncher.launch();
            }
        });
    }

}
