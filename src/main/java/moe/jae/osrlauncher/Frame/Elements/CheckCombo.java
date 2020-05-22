package moe.jae.osrlauncher.Frame.Elements;

import moe.jae.osrlauncher.Frame.Listeners.CheckComboListener;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CheckCombo extends JComboBox implements ListCellRenderer {

    public RadioButton checkBox;
    public JComboBox combo;
    boolean keepMenuOpen;

    @Override
    public void setPopupVisible(boolean v) {
        super.setPopupVisible(v);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value != null ){
            store store = (store) value;
            checkBox.setText(store.text);
            checkBox.setSelected(store.state);
        } else
            checkBox.setSelected(false);

        this.combo.setBackground(Color.BLACK);
        this.combo.setForeground(Color.WHITE);
        return checkBox;
    }

    public class store {
        public String text;
        public Boolean state;

        public store(String id, Boolean state) {
            this.text = id;
            this.state = state;
        }

    }

    public CheckCombo() {
        init();
    }

    public void setContents(store[] stores) {
        this.combo.removeAllItems();
        for(store store : stores) {
            this.combo.addItem(store);
        }
        this.combo.repaint();
    }

    private void init() {
        checkBox = new RadioButton(new Rectangle(0,0,20,15));
        checkBox.setContentAreaFilled(true);
        store[] stores = new CheckCombo.store[]{new store("none", true)};

        this.combo = new JComboBox();
        this.combo.setRenderer(this);
        this.combo.setBackground(Color.BLACK);
        this.combo.setForeground(Color.WHITE);
        this.combo.addActionListener(new CheckComboListener());

        this.combo.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                keepMenuOpen = true;
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        combo.setPopupVisible(keepMenuOpen);
                    }
                });
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                keepMenuOpen = false;
            }
        });
        this.combo.setVisible(true);
        setContents(stores);
    }

    public void loadSpritesPacks(String config_dir) {
        CheckCombo.store[] stores = null;

        try {

            File configFile = new File(config_dir, "config.txt");
            configFile.createNewFile();

            String spritePackDir = config_dir + File.separator + "video" + File.separator + "spritepacks";

            File spDir = new File(spritePackDir);
            File[] spritePacks = spDir.listFiles(File::isFile);

            if(spritePacks.length > 0) {
                ArrayList<String> packsAvailable = new ArrayList<>();
                Map<String, Boolean> packsSettings = new HashMap<>();

                for(File spritePack : spritePacks) {
                    int index = spritePack.getName().lastIndexOf('.');
                    String name = spritePack.getName().substring(0, index);
                    packsAvailable.add(name);
                }

                BufferedReader br = new BufferedReader(new FileReader(configFile));
                String line;
                while((line = br.readLine()) != null) {
                    String[] packageName = line.split(":");
                    if(packsAvailable.contains(packageName[0])) {
                        packsSettings.put(packageName[0], Integer.parseInt(packageName[1]) == 1);
                    }
                }
                br.close();

                Iterator look = packsAvailable.iterator();
                FileWriter write = new FileWriter(configFile, true);
                PrintWriter writer = new PrintWriter(write);
                while(look.hasNext()) {
                    String nextPack = (String) look.next();
                    if(packsSettings.get(nextPack) == null) {
                        writer.println(nextPack + ":0");
                        packsSettings.put(nextPack, false);
                    }
                }
                writer.close();
                write.close();

                if(packsSettings.size() > 0) {
                    stores = new CheckCombo.store[packsSettings.size()];
                    Iterator it = packsSettings.entrySet().iterator();
                    int j = 0;
                    while(it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        stores[j++] = new store((String) pair.getKey(), (Boolean) pair.getValue());
                    }
                }
            }

        } catch (IOException error) {
            error.printStackTrace();
        }

        if(stores != null)
            setContents(stores);

        this.combo.repaint();

    }

}
