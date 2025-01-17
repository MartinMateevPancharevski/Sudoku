package lockin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.Random;

/**
 *
 * @author Mihail
 */
public class Proekt {

    /**
     * @param args the command line arguments
     */
    Random generator = new Random();

    public int[][] Random() {

        int save[][] = new int[2][22];
        save[0][0] = generator.nextInt(9);
        save[1][0] = generator.nextInt(9);
        for (int i = 0; i < 22; i++) {
            int p = generator.nextInt(9);
            int m = generator.nextInt(9);
            for (int j = 0; j < 22; j++) {
                boolean flag = false;
                if (p == save[0][j] && m == save[1][j]) {
                    flag = true;
                }
                if (flag == true) {
                    p = generator.nextInt(9);
                    m = generator.nextInt(9);
                    j = 0;
                }

            }
            save[0][i] = p;
            save[1][i] = m;
        }
        return save;
    }

}
