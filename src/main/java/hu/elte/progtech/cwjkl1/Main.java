package hu.elte.progtech.cwjkl1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neilus on 5/23/16.
 */
public class Main extends JFrame implements ActionListener{
    private WindowAdapter exitApp = new WindowAdapter() {
        /**
         * Invoked when a window is in the process of being closed.
         * The close operation can be overridden at this point.
         *
         * @param e
         */
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    };

    public List<GameView> getGameWindows() {
        return gameWindows;
    }

    private List<GameView> gameWindows = new ArrayList<>();
    private GameConfigView configView;
    private GameView gameView;

    public Main(){
        addWindowListener(exitApp);
        setTitle("Progtech. 1, 2. beadandó - Áttörés");
        getContentPane().setLayout(new FlowLayout());

        configView = new GameConfigView();
        add(configView);

        JToggleButton startBtn = new JToggleButton("Start");
        startBtn.setName("startBtn");
        startBtn.addActionListener(this);
        add(startBtn);
        setVisible(true);

    }
    public static void main(String[] args){
        Main mainWindow = new Main();
        mainWindow.pack();
        mainWindow.show();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Start the game motherfucker!");
        GameView gameWindow = new GameView(configView.getConfig(), Main.this);
        gameWindow.setVisible(true);
        gameWindows.add(gameWindow);

    }
}
