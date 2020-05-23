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

    // Buttons
    private JLabel _BUG_REPORT;
    private JLabel _RSC_WIKI;
    private JLabel _ORSC_WIKI;
    private JLabel _LAUNCHER_BUGS;

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

        // Add elements
        this.addLogos();
        this.addButtons();

        // Add listeners
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

        // Launch dev
        this._DEV_LOGO.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClientLauncher devLauncher = new ClientLauncher(currentGameFolder, "default", "orsc.dev", "43599");
                devLauncher.launch();
            }
        });

        // Launch OpenPK
        this._OPENPK_LOGO.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClientLauncher pkLauncher = new ClientLauncher(currentGameFolder, "openpk", "openpk.openrsc.com", "43597");
                pkLauncher.launch();
            }
        });

        // Launch OpenRSC
        this._OPENRSC_LOGO.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClientLauncher rscLauncher = new ClientLauncher(currentGameFolder, "default", "game.openrsc.com", "43596");
                rscLauncher.launch();
            }
        });

        this._BUG_REPORT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utils.openWebPage("https://orsc.dev/open-rsc/Game/issues");
            }
        });

        this._RSC_WIKI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utils.openWebPage("https://classic.runescape.wiki");
            }
        });

        this._ORSC_WIKI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utils.openWebPage("https://openrsc.com/wiki");
            }
        });

        this._LAUNCHER_BUGS.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utils.openWebPage("https://orsc.dev/jae/alternative-openrsc-launcher/-/issues");
            }
        });
    }

    private void addButtons() {
        int baseXLocation = 125;
        int addOffset = 150;

        (this._BUG_REPORT = new JLabel(Utils.getImage("bug_report.png"))).setBounds(baseXLocation,250,100,150);
        this._BACKGROUND.add(this._BUG_REPORT);

        baseXLocation += addOffset;

        (this._RSC_WIKI = new JLabel(Utils.getImage("official_wiki.png"))).setBounds(baseXLocation, 250, 100, 150);
        this._BACKGROUND.add(this._RSC_WIKI);

        baseXLocation += addOffset;

        (this._ORSC_WIKI = new JLabel(Utils.getImage("orsc_wiki.png"))).setBounds(baseXLocation, 250, 100, 150);
        this._BACKGROUND.add(this._ORSC_WIKI);

        baseXLocation += addOffset;

        (this._LAUNCHER_BUGS = new JLabel(Utils.getImage("launcher_bugs.png"))).setBounds(baseXLocation, 250, 100, 150);
        this._BACKGROUND.add(this._LAUNCHER_BUGS);

    }

}
