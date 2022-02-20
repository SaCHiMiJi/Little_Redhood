import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//private void removeCongrats() {
//    this.remove(congrats);
//    this.getContentPane().repaint();
//}

//public void newCongrats(String status) {
//    congrats = new Congrats(status, this);
//    this.add(congrats);
//    congrats.requestFocus();
//    SwingUtilities.updateComponentTreeUI(this); 
//}

//if (Congrats.status == "newmenu") {
//    removeCongrats();
//    newMenu("newgame");
//    Congrats.status = "none";
//}

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
        congrats = new ImageIcon("CongratPage.jpg");
        home = new ImageIcon("home1.png"); 
        background = congrats.getImage();
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
            case ("next") :
                frame = 30;
                status = "event";
                break;
            case ("event") :
                if (frame > 0) {
                    frame--;
                    if (frame > 15) {
                        home1.setIcon(new ImageIcon("home2_282x89.png"));
                        repaint();
                    } else {
                        home1.setIcon(new ImageIcon("home1.png"));
                        repaint();
                    }
                } 
                if (frame == 14){
                    status = "newmenu";
                }
                break;  
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home1) {
            status = "next";
            System.out.print("home");
        }
    }
}