package function;

import javax.swing.ImageIcon;

public class SetImg {
    private String baseLocate = "/Users/KruJu/Desktop/work/project/image";
    private ImageIcon image;

    public SetImg(String type, String fileName) {
        image = new ImageIcon(baseLocate + "/" + type + "/" + fileName + ".png");
    }


    public SetImg(String type, String fileName, String Animation) {
        image = new ImageIcon(baseLocate + "/" + type + "/" + fileName + "_" + Animation + ".gif");
    }

    public SetImg(String type, String question, String fileName, String i){
        image = new ImageIcon(baseLocate + "/Question/Q" + question + "/" + fileName + ".png");
    }

    public ImageIcon get() {
        return image;
    }
}