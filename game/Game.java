package game;


import javax.swing.*;

import function.*;

import java.awt.event.*;
import java.awt.*;


class Game extends JPanel implements ActionListener {

    //set sound
    private USound usFootstep,usClick,usGame,usrollDice;
    //set type img
    ImageIcon Dice_animation, character_stand, OG_dice,img2,Bg_img;
    ImageIcon img1;
    Image board2, b_roll, player, Dice, Wolf, Hunter, Quiz;
    
    //setbutton
    JButton b1;
    JButton btn1;
    JButton btn2;

    //value
    private int framerate = 0;
    private int count_graphics = 0;
    private String state_dice = "none";
    private int frame = 0;
    private int random = 0;
    private boolean usePopup = false;
    private int Anw ;
    private String Anw_click = "";

    //time
    private Timer time;

    //count value
    protected int count = 0;
    protected int c_click = 0;

    //wolf value
    protected int wolf_count = 0;
    protected int wolf_pos = 8;

    //huter value
    protected int hunter_count = 0;
    protected int hunter_pos = 1;
    
    // redhood position
    int px_player = 40;
    int py_player = 700;
    int map_p[][] = player_position();

    // wolf position
    int wolf_p[][] = wolf_position();
    int px_wolf = wolf_p[0][0];
    int py_wolf = wolf_p[0][1];

    //hunter position
    int hunter_p[][] = hunter_position();
    int px_hunter = hunter_p[0][0];
    int py_hunter = hunter_p[0][1];
    
    //graphics
    protected Graphics g;    


    Game() {
        setSounnd();
        //set imgIcon
        setBackground(Color.black);
        img2 = new SetImg("Button","roll_1_187x60").get();
        Dice_animation = new ImageIcon("Dice_roll.gif");
        
        //setimg
        board2 = new SetImg("Board","gameBoard").get().getImage();
        player = new SetImg("Character","Stand").get().getImage();
        Dice = new SetImg("Dice","Dice").get().getImage();
        Wolf = new SetImg("Character", "Wolf").get().getImage();
        Hunter = new SetImg("Character", "Hunter").get().getImage();

        //setbutton
        setLayout(null);
        btn1 = new JButton(img1);
        btn1.setBounds(855, 665, 132, 51);
        btn1.setBorderPainted(false);
        btn1.setFocusPainted(false);
        btn1.setContentAreaFilled(false);
        add(btn1);
        btn1.addActionListener(this);

        btn2 = new JButton(img1);
        btn2.setBounds(1009, 665, 132, 51);
        btn2.setBorderPainted(false);
        btn2.setFocusPainted(false);
        btn2.setContentAreaFilled(false);
        add(btn2);

        btn2.addActionListener(this);
        
        b1 = new JButton(img2);
        b1.setBounds(909, 386, 187, 60);
        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setContentAreaFilled(false);
        add(b1);

        b1.addActionListener(this);

        //| playsound
        usGame.start();    

        // |set loop of game
        time = new Timer(10, this);
        time.start();
    }
        // USound
    public void setSounnd(){
        usClick = new USound("click");
        usFootstep = new USound("Footsteps");
        usGame = new USound("enchantedforest");
        usrollDice = new USound("rolldice");
    }

    public void paintComponent(Graphics g) {
        count_graphics += 1;

        super.paintComponent(g);
        g.drawImage(board2, 0, 0, this);

        g.drawImage(Dice, 931, 204, this);

        // |Charater drawing
        g.drawImage(Wolf, px_wolf, py_wolf,this);

        g.drawImage(Hunter, px_hunter, py_hunter, this);

        g.drawImage(player, px_player, py_player, this);

        g.drawString(count_graphics + "", 1000, 30);

        
        // |map character position
        int map_p[][] = player_position();
        int wolf_p[][] = wolf_position();
        int hunter_p[][] = hunter_position();

        switch (state_dice) {
            case "none":
                break;
            case "next":
                random = 1;//(int) Math.round(Math.random() * 5 + 1);
                g.drawString("random: " + random, 1000, 50);
                frame = (random * 25) + 75;
                state_dice = "beingNext";
                System.out.println("next");
                Dice = new SetImg("Dice","Dice","roll").get().getImage();
                
                //|sound play               
                usClick.start();
                usrollDice.start();      
                repaint();
                break;
            case "beingNext":
                count = 27;
                //usrollDice.stop(true); 
                if (frame > 0) {
                    frame--;
                    
                    if (frame-50 > (random * 25)) {
                        b1.setIcon(new SetImg("Button","roll_2_187x60").get());
                        repaint();

                    } else if (frame > (random * 25)) {
                        b1.setIcon(new SetImg("Button","roll_1_187x60").get());
                        repaint();

                    } else if (frame == (random * 25)) {
                        if (random == 1) {
                            Dice = new SetImg("Dice","Dice1").get().getImage(); 
                        } else if (random == 2){
                            Dice = new SetImg("Dice","Dice2").get().getImage();
                        } else if (random == 3){
                            Dice = new SetImg("Dice","Dice3").get().getImage();
                        } else if (random == 4){
                            Dice = new SetImg("Dice","Dice4").get().getImage();
                        } else if (random == 5){
                            Dice = new SetImg("Dice","Dice5").get().getImage();
                        } else if (random == 6){
                            Dice = new SetImg("Dice","Dice6").get().getImage();
                        }
                        repaint();

                    } else { //animation walk
                        if (frame % 25 == 0) {
                            usFootstep.start();
                            if (count < 5||(count < 18 && count > 12)||(count < 30 && count > 24)) {
                                player = new SetImg("Character","Walk","Right").get().getImage();
                            } else if ((count < 11 && count > 6)||(count < 23 && count > 18)) {
                                player = new SetImg("Character","Walk","Left").get().getImage();
                            } else {
                                player = new SetImg("Character","Walk","Up").get().getImage();
                            }
                            px_player = map_p[count][0];
                            py_player = map_p[count][1];
                            System.out.println(px_player + "\n" + py_player);
                            
                            count++;
                            repaint();
                            
                        }

                    }

                    if(frame == 0){// move end
                        player = new SetImg("Character","Stand_front").get().getImage();
                        if (count == 8 ||count == 15 ||count == 23 ||count == 28 || count == 3||count == 17) {
                            state_dice = "setquiz";
                        }
                    }

                    
                } else { 
                    update_pos();
                    repaint();
                    state_dice = "none";
                }
                System.out.print(count);
                System.out.println("beingNext");
                break;     
            case("setquiz"):
                random_pos();
                if (count == 3){
                    //correct answer
                    btn1.setIcon(new SetImg("Question", "5", "q5_114a_132x51","0" ).get());
                    //wrong answer
                    btn2.setIcon(new SetImg("Question", "5", "q5_110a_132x51","0" ).get());
                } else if (count == 8) {
                    //correct answer
                    btn1.setIcon(new SetImg("Question", "1", "q1_120a_132x51","0" ).get());
                    //wrong answer
                    btn2.setIcon(new SetImg("Question", "1", "q1_140a_132x51","0" ).get());
                } else if (count == 15){
                    //correct answer
                    btn1.setIcon(new SetImg("Question", "2", "q2_35a_132x51","0" ).get());
                    //wrong answer
                    btn2.setIcon(new SetImg("Question", "2", "q2_100a_132x51","0" ).get());
                } else if (count == 17){
                    //correct answer
                    btn1.setIcon(new SetImg("Question", "6", "q6_72a","0" ).get());
                    //wrong answer
                    btn2.setIcon(new SetImg("Question", "6", "q6_63a","0" ).get());
                } else if (count == 23){
                    //correct answer
                    btn1.setIcon(new SetImg("Question", "3", "q3_10a_132x51","0" ).get());
                    //wrong answer
                    btn2.setIcon(new SetImg("Question", "3", "q3_0a_132x51","0" ).get());
                } else if (count == 28){
                    //correct answer
                    btn1.setIcon(new SetImg("Question", "4", "q4_50a_132x51","0" ).get());
                    //wrong answer
                    btn2.setIcon(new SetImg("Question", "4", "q4_43a_132x51","0" ).get());
                }
                state_dice = "ans";
                System.out.println("quiz");
                repaint();
                break;
            case("ans"):
                // System.out.println(frame);
                usePopup = true;
                if(count == 3){
                    if (Anw == 1) {
                        count = 9;
                        px_player = map_p[count][0];
                        py_player = map_p[count][1];
                        count++;
                    }
                } else if (count == 8) {
                    if (Anw == 2) {
                        count = 3;
                        px_player = map_p[count][0];
                        py_player = map_p[count][1];
                        count++;
                    }
                } else if (count == 15){
                    if (Anw == 2) {
                        count = 6;
                        px_player = map_p[count][0];
                        py_player = map_p[count][1];
                        count++;
                    }
                } else if(count == 17){
                    if (Anw == 1) {
                        count = 23;
                        px_player = map_p[count][0];
                        py_player = map_p[count][1];
                        count++;  
                    }
                } else if (count == 23){
                    if (Anw == 2) {
                        count = 15;
                        px_player = map_p[count][0];
                        py_player = map_p[count][1];
                        count++;
                    }
                } else if (count == 28){
                    if (Anw == 2) {
                        count = 19;
                        px_player = map_p[count][0];
                        py_player = map_p[count][1];
                        count++; 
                    } 
                    // hunter action
                }
                if(Anw_click == "done"){
                    btn1.setIcon(img1);
                    btn2.setIcon(img1);
                    state_dice = "beingNext";
                    usePopup = false;
                    Anw = 3;
                    Anw_click = "";
                }
                break;
        }
        if (usePopup){
            // System.out.println(count);
            if(count == 3){
                Quiz = new SetImg("Question", "5", "q5_295x68","0").get().getImage();
            } else if (count == 8) {
                Quiz = new SetImg("Question", "1", "q1_295x68","0").get().getImage();
            } else if(count == 15){
                Quiz = new SetImg("Question", "2", "q2_295x68","0").get().getImage();
            } else if(count == 17){
                Quiz = new SetImg("Question", "6", "q6_295x68","0").get().getImage();
            } else if(count == 23){
                Quiz = new SetImg("Question", "3", "q3_295x68","0").get().getImage();
            } else if(count == 28){
                Quiz = new SetImg("Question", "4", "q4_295_68","0").get().getImage();
            } 
            
            popup(g);
            repaint();
            
        }
    }

    private void popup(Graphics g){
        // inside here
        //System.out.println("paint");
        g.drawImage(Quiz, 853, 544, this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            usClick.start();
            //usePopup = true;
            System.out.println("roll");
            state_dice = "next";
        }
        if (e.getSource() == btn1) {
            usClick.play();
            System.out.println("======- btn1 -======");
            Anw = 1;
            Anw_click = "done";
        }
        if (e.getSource() == btn2) {
            usClick.start();
            System.out.println("======- btn2 -======");
            Anw = 2;
            Anw_click = "done";
        }

        

    }
    
    public int[][] player_position() {// ตำแหน่งแต่ละช่องในแมพ
        int position[][] = new int[30][2];
        int x = 130;
        int y = 700;
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (i == 2 || i == 4) {
                x -= 240;
            }
            if (i == 1) {
                y -= 20;
            } else if (i == 3) {
                y -= 10;
            } else if (i == 4) {
                y -= 20;
            }
            for (int j = 0; j < 6; j++) {
                if (i == 0 && j == 0) {
                    position[0][0] = 130;
                    position[0][1] = 700;
                } else if (i % 2 == 0) {
                    x += 120;
                    position[count][0] = x;
                    position[count][1] = y;
                } else {
                    x -= 120;
                    position[count][0] = x;
                    position[count][1] = y;
                }
                count++;
            }
            y -= 120;
            x += 120;
        }
        return position;
    }

    public int[][] wolf_position() {
        int position[][] = new int[5][2];
        int map[][] = player_position();
        position[0] = map[7];
        position[1] = map[14];
        position[2] = map[22];
        position[3] = map[27]; 
        for (int i = 0; i < 4; i++) {
            position[i][1] -=60;
        }
        return position;
    }

    public int[][] hunter_position() {
        int position[][] = new int[5][2];
        int map[][] = player_position();
        position[0] = map[2];
        position[1] = map[16]; 
        for (int i = 0; i < 4; i++) {
            position[i][1] -=60;
        }
        return position;
    }
    public void random_pos(){
        int ran = (int) Math.round(Math.random()*1 + 1);
        if (ran == 1) {
            btn1.setLocation(1009, 665);
            btn2.setLocation(855, 665);
        } 
    }

    public void update_pos(){
        //wolf part
        if (count > wolf_pos) {
            wolf_count++;
            px_wolf = wolf_p[wolf_count][0];
            py_wolf = wolf_p[wolf_count][1];
            if (wolf_count == 1) {
                wolf_pos = 15;
            } else if (wolf_count == 2){
                wolf_pos = 23;
            } else if (wolf_count == 3){
                wolf_pos = 28;
            } else {
                Wolf = new ImageIcon().getImage();
            }
        }
        //huter part
        if (count > hunter_pos) {
            hunter_count++;
            px_hunter = hunter_p[hunter_count][0];
            py_hunter = hunter_p[hunter_count][1];
            if (hunter_count == 1) {
                hunter_pos = 17;
            } else {
                Hunter = new ImageIcon().getImage();
            }
        }
    }

}

