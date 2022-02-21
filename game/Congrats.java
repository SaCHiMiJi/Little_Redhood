package game;

import javax.swing.*;

import function.SetImg;
import function.USound;

import java.awt.*;
import java.awt.event.*;

class Congrats extends JPanel implements ActionListener {
    ImageIcon congrats,home,play_a;
    Image background;
    static JButton home1,playagain;
    static String status_home = "";
    static String status_playagian = "";
    private int frame = 0;
    private Timer time;
    USound usClick;
    Display display;

    Congrats () {}

    Congrats(String status,Display display) {
        
        setDefault();
        setBackground(Color.white);

        //setsound
        usClick = new USound("click");

        //set img
        play_a = new SetImg("Button", "replay1").get(); 
        home = new SetImg("Button","Home1").get(); 
        background = new SetImg("Board","CongratPage").get().getImage();

        //set button
        setLayout(null);
        home1 = new JButton(home);
        home1.setBounds(298, 610, 282, 89);
        home1.setBorderPainted(false);
        home1.setFocusPainted( false );
        home1.setContentAreaFilled( false );
        
        add(home1);
        home1.addActionListener(this);
        
        playagain = new JButton(play_a);
        playagain.setBounds(607, 610, 282, 89);
        playagain.setBorderPainted(false);
        playagain.setFocusPainted( false );
        playagain.setContentAreaFilled( false );
        
        add(playagain);
        playagain.addActionListener(this);    

        //time loop
        time = new Timer(6, this);
        time.start();
    }

    public void setDefault() {
        this.setBounds(0, 0, 1200, 800);
        this.setFocusable(true);
        this.setLayout(null);  
    }

    public void paintComponent(Graphics g) {
        setBackground(Color.black);
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 1200, 800, this);

        switch(status_home){  
            case("none"):
                break;
            case ("next") :
                frame = 20;
                status_home = "event";
                break;
            case ("event") :
                if (frame > 0) {
                    frame--;
                    if (frame > 10) {
                        home1.setIcon(new SetImg("Button","Home2").get());
                        repaint();
                    } else {
                        home1.setIcon(new SetImg("Button","Home1").get());
                        repaint();
                    }
                } 
                if (frame == 0){
                    status_home = "newmenu";
                }
                break;  
        }
        switch(status_playagian){  
            case("none"):
                break;
            case ("next") :
                frame = 20;
                status_playagian = "event";
                break;
            case ("event") :
                if (frame > 0) {
                    frame--;
                    if (frame > 10) {
                        playagain.setIcon(new SetImg("Button","replay2").get());
                        repaint();
                    } else {
                        playagain.setIcon(new SetImg("Button","replay1").get());
                        repaint();
                    }
                } 
                if (frame == 0){
                    status_playagian = "newgame";
                }
                break;  
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home1) {
            status_home = "next";
            System.out.println("home");
            usClick.start();
        }
        if (e.getSource() == playagain) {
            status_playagian = "next";
            System.out.println("playagian");
            usClick.start();
        }
    }
}