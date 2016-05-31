package hu.elte.progtech.cwjkl1.attores;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;



/**
 * A játék konfigurációs nézet
 */
public class GameConfigView extends JPanel implements ActionListener{
    private Map<String, Integer> validSizes;
    private GameConfig config;
    private JComboBox tableSizes;
    private JLabel tableSizeLabel;

    public GameConfigView(){
        this(new GameConfig());
    }

    public GameConfigView(GameConfig config){
        setLayout(new FlowLayout());

        tableSizeLabel = new JLabel("Specify the table size: ");
        tableSizeLabel.setName("tableSizeLabel");
        add(tableSizeLabel);

        // create the map for the tableSizes ComboBox
        validSizes = new HashMap<>();
        for(Integer i:config.getValidDimensions()){
            validSizes.put("" + i + "x" + i, i);
        }
        // Fills up the ComboBox with the "generated" text keys
        tableSizes = new JComboBox(validSizes.keySet().toArray());
        tableSizes.setName("tableSizes");
        // Selects the currently set/default dimension based on the config
        config.setTableSize(getConfiguredSize());
//        tableSizes.setSelectedIndex(config.getTableSizeIndex());
        tableSizes.addActionListener(this);
        add(tableSizes);

        this.config = config;
        setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        config.setTableSize(getConfiguredSize());
    }

    private Integer getConfiguredSize(){
        String key = (String)tableSizes.getSelectedItem();
        return validSizes.get(key);
    }
    public GameConfig getConfig() {
        return config;
    }


}
