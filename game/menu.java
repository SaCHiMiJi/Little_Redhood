package game;

import javax.swing.*;
import function.*;
import java.awt.*;
import java.awt.event.*;

class Menu extends JPanel implements ActionListener {
    ImageIcon img1,img2,img3;
    Image board;
    static JButton b1,b2;
    private Timer time;
    static String status_tutorial = "none";
    static String status_play = "none";
    private int frame = 0;
    Display display;
    static String status;
    private USound usClick;
    

    Menu () {}

    Menu(String status, Display display){
            usClick = new USound("click");
            //|set img
            img1 = new SetImg("button","play_1_407x114").get();
            img2 = new SetImg("button","tutorial_1_407x114").get();
            board = new SetImg("Board", "gameTitle").get().getImage();

            //|set button
            setLayout(null);
            b1 = new JButton(img1);
            b1.setBounds(413, 400,407,114);
            b1.setBorderPainted(false);
            b1.setFocusPainted( false );
            b1.setContentAreaFilled( false );
            b1.addActionListener(this);
            add(b1);

            b2 = new JButton(img2);
            b2.setBounds(413,550,407,114);
            b2.setBorderPainted(false);
            b2.setFocusPainted( false );
            b2.setContentAreaFilled( false );
            b2.addActionListener(this);
            add(b2);

            // |time loop
            time = new Timer(6, this);
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
        g.drawImage(board, 0, 0, 1200, 800, this);

        switch (status_tutorial) {
            case "none" :
                break; 
            case "next" :
                frame = 20;
                status_tutorial = "event";
                break; 
            case "event" :
                if (frame > 0) {
                    frame--;
                    if (frame > 10) {
                        b2.setIcon(new SetImg("Button","tutorial_2_407x114").get());
                        repaint();
                    } else {
                        b2.setIcon(new SetImg("Button","tutorial_1_407x114").get());
                        repaint();
                    }

                } 
                if (frame == 0){
                    status_tutorial = "newtutorial";
                }
                break;  
        }
        switch(status_play){
            case"none":
                break; 
            case"next":
                usClick.play();
                frame = 20;
                status_play = "event";
                break; 
            case "event" :
                if (frame > 0) {
                    frame--;
                    if (frame > 10) {
                        b1.setIcon(new SetImg("Button","play_1_407x114").get());
                        repaint();
                    } else {
                        b1.setIcon(new SetImg("Button","play_2_407x114").get());
                        repaint();
                    }

                } 
                if (frame == 0){
                    status_play = "newgame";
                }
                break; 
        }
    
    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            status_play = "next";
            usClick.start();
            //System.out.println("play");
        }
        if (e.getSource() == b2) {
            status_tutorial = "next";
            usClick.start();
            //System.out.println("back");
        }
    }

    
}

class Menu1 {
    public static void main(String[] args) {
        Display display = new Display();
    }

}



