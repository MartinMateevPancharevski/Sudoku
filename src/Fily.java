/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author Thinkpad
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author MartinP
 */
public class Fily {

    public String[][] Check() throws FileNotFoundException {
        File file = new File("Sudoku_solved.txt");
        Random rand = new Random();
        int number = rand.nextInt(5);
        Scanner fileReader = new Scanner(file);
        int line = 0;
        String save;
        String masiv[][] = new String[9][9];
        int p = 0;
        while (fileReader.hasNextLine()) {
            save = fileReader.nextLine();

            if (line >= number * 10 && line <= number * 10 + 8) {
                if (p < 9) {
                    masiv[p] = save.split(" ");
                    p++;
                }
            }
            line++;
        }
        return masiv;
    }

    public static void main(String[] args) {
        try {
            Fily fily = new Fily();
            String[][] sudoku = fily.Check();
            for (String[] row : sudoku) {
                for (String cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}