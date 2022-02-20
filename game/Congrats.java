package game;

import javax.swing.*;

import function.SetImg;

import java.awt.*;
import java.awt.event.*;

class Congrats extends JPanel implements ActionListener {
    ImageIcon congrats,home;
    Image background;
    static JButton home1;
    static String status = "";
    private int frame = 0;
    private Timer time;
    Display display;

    Congrats () {}

    Congrats(String status,Display display) {
        setBackground(Color.white);
        home = new SetImg("Button","Home1").get(); 
        background = new SetImg("Board","CongratPage").get().getImage();
        home1 = new JButton(home);
        home1.setBounds(470, 630, 282, 89);
        home1.setBorderPainted(false);
        home1.setFocusPainted( false );
        home1.setContentAreaFilled( false );
        setLayout(null);
        add(home1);
        home1.addActionListener(this);    

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

        switch(status){  
            case("none"):
                break;
            case ("next") :
                frame = 20;
                status = "event";
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
                    status = "newmenu";
                }
                break;  
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home1) {
            status = "next";
            System.out.println("home");
        }
    }
}