package game;

import javax.swing.*;

import function.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import utils.*;

public class Display extends JFrame implements ActionListener {
    private Dimension size = new Dimension(1200, 800);
    private Game game;
    private Menu menu;
    private Tutorial tutorial;
    private Congrats congrats;
    private Timer time;
    static String status;
    private USound usWelcome;
   
    

    public Display() {
        newMenu("newgame");
        setting();
        
    }

    void setting() {
        this.setTitle("Little Redhood");
        this.setIconImage(new SetImg("Icon","Icon").get().getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(size);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void removeGame() {
        this.remove(game);
        game = null;
        this.getContentPane().repaint();
    }

    public void removeMenu() {
        this.remove(menu);
        this.getContentPane().repaint();
    }
    public void removeTutorial() {
        this.remove(tutorial);
        this.getContentPane().repaint();
    }
    public void removeCongrats() {
        this.remove(congrats);
        this.getContentPane().repaint();
    }

    public void newGame() {
        game = new Game();
        this.getContentPane().add(game);
        game.requestFocus();
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void newMenu(String status) {
        menu = new Menu(status, this);
        this.add(menu);
        menu.requestFocus();
        SwingUtilities.updateComponentTreeUI(this);

        time = new Timer(6, this);
        time.start();

        // |set sound welcome
        usWelcome = new USound("menuBMG");
        usWelcome.loop(true);
        usWelcome.start();
    }

    public void newTutorial(String status) {
        tutorial = new Tutorial(status, this);
        this.add(tutorial);
        tutorial.requestFocus();
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void newCongrats(String status) {
        congrats = new Congrats(status, this);
        this.add(congrats);
        congrats.requestFocus();
        SwingUtilities.updateComponentTreeUI(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (Menu.status_tutorial == "newtutorial") {
            removeMenu();
            newTutorial("newtutorial");
            Menu.status_tutorial = "none";
        }
        if (Menu.status_play == "newgame") {
            usWelcome.stop(true);
            removeMenu();
            newGame();
            Menu.status_play = "none";
        }
        if (Tutorial.status == "newmenu") {
            removeTutorial();
            newMenu("newgame");
            Tutorial.status = "none";
        }
        if (Game.status == "congrats") {
            removeGame();
            newCongrats("none");
            Game.status = "none";
        }
        if (Congrats.status_home == "newmenu") {
            removeCongrats();
            newMenu("newgame");
            Congrats.status_home = "none";
        }
        if (Congrats.status_playagian == "newgame") {
            removeCongrats();
            newGame();
            Congrats.status_playagian = "none";
        }
    }
}
