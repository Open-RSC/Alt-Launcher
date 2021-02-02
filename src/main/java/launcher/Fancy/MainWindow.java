package launcher.Fancy;

import launcher.Settings;
import launcher.Utils.ClientLauncher;
import launcher.Utils.Defaults;
import launcher.Utils.Utils;
import launcher.elements.*;
import launcher.listeners.PositionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private static MainWindow instance;

    private JLabel bg;
    private JLabel logo;
    private CheckCombo comboBox;
    private LaunchButton launch1;
    private LaunchButton launch2;
    private LaunchButton launch3;
    private LaunchButton launch4;
    private LaunchButton launch5;

    private final int launch_button_y = 218;
    private final int link_button_y = 359;

    public MainWindow() {
        this.setPreferredSize(new Dimension(795, 555));
        this.setUndecorated(true);
        this.setTitle(Defaults._TITLE);
        this.setIconImage(Utils.getImage("icon.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainWindow.instance = this;
    }

    public static MainWindow get() {
        return MainWindow.instance;
    }

    public void build() {
        (this.bg = new JLabel(Utils.getImage("background.png"))).setBounds(0, 0, 800, 560);
        this.add(this.bg);
        (this.logo = new JLabel(Utils.getImage("openrsc_sword_logo.png"))).setBounds(265, 86, 277, 100);
        this.bg.add(this.logo);
        this.addButtons();
        this.addMouseListener(new PositionListener(this));
        this.addMouseMotionListener(new PositionListener(this));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addButtons() {
        // Link button size
        int link_button_width = 130;
        int link_button_height = 74;

        // Link buttons
        final String BUTTON1 = "Discord";
        this.bg.add(new LinkButton(BUTTON1, new Rectangle(101, link_button_y, link_button_width, link_button_height)));

        final String BUTTON2 = "Bug Reports";
        this.bg.add(new LinkButton(BUTTON2, new Rectangle(256, link_button_y, link_button_width, link_button_height)));

        final String BUTTON3 = "Our Wiki";
        this.bg.add(new LinkButton(BUTTON3, new Rectangle(414, link_button_y, link_button_width, link_button_height)));

        final String BUTTON4 = "RSC Wiki";
        this.bg.add(new LinkButton(BUTTON4, new Rectangle(567, link_button_y, link_button_width, link_button_height)));

        // Launch Server's Client buttons
        addServerButtons();

        // Control button size
        int control_button_width = 10;
        int control_button_height = 11;

        // Control buttons
        this.bg.add(new ControlButton(1, 695, 60, control_button_width, control_button_height)); // Minimize button
        this.bg.add(new ControlButton(2, 715, 60, control_button_width, control_button_height)); // Exit button
        this.bg.add(new ControlButton(3, 670, 488, 15, 15)); // Delete cache button

        int robotCheckboxX = 690;
        int robotCheckboxY = 438;
        CheckboxButton robotCheckbox = new CheckboxButton("", new Rectangle(robotCheckboxX,robotCheckboxY, 50,25));
        robotCheckbox.setSelected(Settings.showBotButtons);
        this.bg.add(robotCheckbox);
        (this.logo = new JLabel(Utils.getImage("robot.png"))).setBounds(robotCheckboxX + 20, robotCheckboxY, 25, 25);
        this.bg.add(this.logo);
    }

    public void toggleBotServers() {
        if (Settings.showBotButtons) {
            this.bg.remove(this.launch1);
            this.bg.remove(this.launch2);
        } else {
            this.bg.remove(this.launch1);
            this.bg.remove(this.launch2);
            this.bg.remove(this.launch4);
            this.bg.remove(this.launch5);
        }
        addServerButtons();
        Settings.saveSettings();
    }

    private void addServerButtons() {
        int openrsc_x = 0;
        int cabbage_x = 0;
        int uranium_x = 0;
        int coleslaw_x = 0;

        // Launch button size
        int launch_button_width = 100;
        int launch_button_height = 100;

        // Launch buttons
        String preservation = "preservation";

        if (Settings.showBotButtons) {
            openrsc_x = 165;
            cabbage_x = 295;
            uranium_x = 420;
            coleslaw_x = 535;
        } else {
            openrsc_x = 230;
            cabbage_x = 477;
        }

        String openrsc = "openrsc";
        (this.launch1 = new LaunchButton(openrsc)).setBounds(openrsc_x, launch_button_y, launch_button_width, launch_button_height);
        this.bg.add(this.launch1);

        String cabbage = "cabbage";
        (this.launch2 = new LaunchButton(cabbage)).setBounds(cabbage_x, launch_button_y - 3, launch_button_width, launch_button_height);
        this.bg.add(this.launch2);

        if (Settings.showBotButtons) {
            String uranium = "uranium";
            (this.launch4 = new LaunchButton(uranium)).setBounds(uranium_x, launch_button_y, launch_button_width, launch_button_height);
            this.bg.add(this.launch4);

            String coleslaw = "coleslaw";
            (this.launch5 = new LaunchButton(coleslaw)).setBounds(coleslaw_x, launch_button_y, launch_button_width, launch_button_height);
            this.bg.add(this.launch5);
        }
        this.bg.repaint();
    }
}
