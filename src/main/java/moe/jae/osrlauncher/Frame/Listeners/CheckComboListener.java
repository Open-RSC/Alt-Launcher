package moe.jae.osrlauncher.Frame.Listeners;

import moe.jae.osrlauncher.Frame.Elements.CheckCombo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckComboListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getModifiers() > 0) {
            JComboBox cb = (JComboBox) e.getSource();
            CheckCombo.store store = (CheckCombo.store) cb.getSelectedItem();
            if(store != null) {
                CheckCombo ccr = (CheckCombo) cb.getRenderer();
                ccr.checkBox.setSelected(store.state = !store.state);
            }
        }
    }
}
