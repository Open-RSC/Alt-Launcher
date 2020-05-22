package moe.jae.osrlauncher.Frame;

import moe.jae.osrlauncher.Frame.Elements.CheckCombo;
import moe.jae.osrlauncher.Utils.Defaults;
import moe.jae.osrlauncher.Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private static AppFrame instance;
    private JLabel bg;
    private JLabel logo;
    private JProgressBar progress;

    private JLabel orsc_online;
    private JLabel rscc_online;
    private JLabel openpk_online;
    private JLabel rscp_online;
    private JLabel dev_online;

    private CheckCombo comboBox;

    public AppFrame() {
        this.setPreferredSize(new Dimension(795, 555));
        this.setUndecorated(true);
        this.setTitle(Defaults._TITLE);
        this.setIconImage(Utils.getImage("icon.png").getImage());
        AppFrame.instance = this;
    }

    public static AppFrame get() {
        return AppFrame.instance;
    }

    public void build() {
        (this.bg = new JLabel(Utils.getImage("background.png"))).setBounds(0,0,800,560);
        this.add(this.bg);
        (this.logo = new JLabel(Utils.getImage("rslogo.png"))).setBounds(242,86,312,100);
        this.bg.add(this.logo);
    }

    public CheckCombo.store[] getComboBoxState() {
        int entryCount = comboBox.combo.getItemCount();
        CheckCombo.store[] items = new CheckCombo.store[entryCount];
        JComboBox entry = comboBox.combo;
        for(int p = 0; p < entryCount; p++) {
            items[p] = (CheckCombo.store) entry.getItemAt(p);
        }

        return items;
    }

}
