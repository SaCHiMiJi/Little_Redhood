package game;

import javax.swing.*;

import function.*;
import java.awt.*;
import java.awt.event.*;

class Tutorial extends JPanel implements ActionListener {
    ImageIcon tuto,back;
    Image background;
    static JButton back1;
    Display display;
    static String status = "";
    private int frame = 0;
    private Timer time;
    private USound usClick;

    Tutorial() {}


    Tutorial(String status2, Display display){
        usClick = new USound("click");
 
        background = new SetImg("Board","Tutorial").get().getImage();
        back = new SetImg("Button","back_1_187x60").get();
        
        setLayout(null);
        back1 = new JButton(back);
        back1.setBounds(500, 650, 187, 60);
        back1.setBorderPainted(false);
        back1.setFocusPainted( false );
        back1.setContentAreaFilled( false );
        
        add(back1);
        back1.addActionListener(this);

        time = new Timer(6, this);
        time.start();
    }

    public void setDefault() {
        this.setBounds(0, 0, 1200, 800);
        this.setFocusable(true);
        this.setLayout(null);  
        
        
    }


    public void paintComponent(Graphics g) {
        setBackground(Color.BLACK);
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 1200, 800, this);
        
        switch(status){
            case("newtutorial"):
                break;
            case("next"):
                frame = 20;
                status = "event";
                usClick.start();
                break;
            case("event") :
                if (frame > 0) {
                    frame--;
                    if (frame > 10) {
                        back1.setIcon(new SetImg("Button","back_2_187x60").get());
                        repaint();
                    } else{ 
                        back1.setIcon(new SetImg("Button","back_1_187x60").get());
                        repaint();
                    }
                }
                if (frame == 0) {
                    status = "newmenu";
                } 
                break;  
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == back1) 
            status = "next";
            
            //System.out.println("back");
    }
}
