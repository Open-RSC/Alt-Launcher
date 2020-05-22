package moe.jae.osrlauncher.Frame.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RadioButton extends JRadioButton implements MouseListener {

    public RadioButton(final Rectangle bounds) {
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setForeground(Color.WHITE);
        this.addMouseListener(this);
        this.setFocusable(false);
        this.setBounds(bounds);
        this.addActionListener();
    }

    @Override
    public void mouseClicked(final MouseEvent e) {}

    @Override
    public void mousePressed(final MouseEvent e) {}

    @Override
    public void mouseReleased(final MouseEvent e) {}

    @Override
    public void mouseEntered(final MouseEvent e) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        this.setForeground(this.getForeground().darker());
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        this.setForeground(this.getForeground().brighter());
    }
}
