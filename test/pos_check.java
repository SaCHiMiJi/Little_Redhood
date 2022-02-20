package test;

import java.util.*;
class Main {
    public static void main(String[] args) {
      int a[][] = map_position() ;
      print2D(a);
    }
    public static int[][] map_position() {// ตำแหน่งแต่ละช่องในแมพ
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
    public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
}