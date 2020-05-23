package moe.jae.osrlauncher.Fancy;

import moe.jae.osrlauncher.Utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Elements extends JPanel {
    private JLabel logo;

    public void paintComponent(Graphics g) {
        (this.logo = new JLabel(Utils.getImage("rslogo.png"))).setBounds(242, 86, 0, 0);
        this.add(logo);
    }

}
