/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lockin;

/**
 *
 * @author Thinkpad
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author MartinP
 */
public class Fily {

    public String[][] Check() throws FileNotFoundException {
        // TODO code application logic here
        File file = new File("C:\\Users\\martinmp\\Downloads\\SudokuMM\\src\\Sudoku_solved.txt");
        Random rand = new Random();
        int number = rand.nextInt(5);
        Scanner fileReader = new Scanner(file);
        int line = 0;
        String save;
        String masiv[][] = new String[9][9];
        int p = 0;
        while (fileReader.hasNextLine()) {
            save = fileReader.nextLine();

            //for (int i = 0; i < 9; i++) {
            if (line >= number * 10 && line <= number * 10 + 8) {
                if (p < 9) {
                    masiv[p] = save.split(" ");
                    p++;
                }
            }

            //  save = fileReader.nextLine();
            // }
            line++;
        }
        return masiv;
    }

}
