/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lockin;

/**
 *
 * @author Thinkpad
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author MihailJ
 */
public class Gui {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        JFrame frame = new JFrame("Sudoku");
        Proekt obekt = new Proekt();
        Fily chok = new Fily();
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 9));
        int masivI[][] = obekt.Random();
        String masivS[][] = chok.Check();//file
        TextField[][] arr = new TextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                arr[i][k] = new TextField();
                arr[i][k].x = i;
                arr[i][k].y = k;
                
                panel.add(arr[i][k]);
            }
        }
        for (int i = 0; i < 1; i++) {
            for (int k = 0; k < 22; k++) {
                arr[masivI[i][k]][masivI[i + 1][k]].setText(masivS[masivI[i][k]][masivI[i + 1][k]]);
                arr[masivI[i][k]][masivI[i + 1][k]].setEditable(false);
            }
        }
        
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                arr[i][k].setHorizontalAlignment(SwingConstants.CENTER);
                arr[i][k].addKeyListener(new KeyListener() {
                    
                    @Override
                    public void keyTyped(KeyEvent arg0) {
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                    @Override
                    public void keyPressed(KeyEvent button) {
                        if (button.getKeyCode() == KeyEvent.VK_ENTER) {
                            String check = arr[button.getComponent().getX()][button.getComponent().getY()].getText();
                            Pattern p = Pattern.compile("\\d{1}$");
                            Matcher m = p.matcher(check);
                            boolean cifra = m.matches();
                            if (cifra == false) {
                                JOptionPane.showMessageDialog(null, "Въведете само една цифра ", " Грешка", JOptionPane.ERROR_MESSAGE);
                            }  
                            
                            int counter = 0;
                         
                            
                            
                            for (int l = 0; l < 9; l++) {
                                for (int h = 0; h < 9; h++) {
                                     if(!arr[l][h].getText().equals("")&&!arr[l][h].getText().equals(masivS[l][h])){
                                        counter++;
                                    }
                                    if (arr[l][h].getText() != null) {
                                        if (arr[l][h].getText().equals(masivS[l][h])) {
                                            arr[l][h].setEditable(false);
                                        }
                                        
                                    }
                                    if(!arr[l][h].getText().equals(masivS[l][h])){
                                        arr[l][h].setText("");                     
                                    }
                                    
                                }                                
                            }
                            if(counter>0){
                            JOptionPane.showMessageDialog(null, "There is a mistake", " Грешка", JOptionPane.ERROR_MESSAGE);
                            }
                            int secCounter = 0;
                            for(int f = 0;f<9;f++){
                                for(int s = 0;s<9;s++){
                                    if(arr[f][s].getText().equals(masivS[f][s])){
                                        secCounter++;
                                    }
                                }
                            }
                            if(secCounter==81){
                                JOptionPane.showMessageDialog(null, "YOU WIN!!!", " Good job", JOptionPane.INFORMATION_MESSAGE);
                            }
                            
                            //int x2 = button.getSource();
                        } //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                    @Override
                    public void keyReleased(KeyEvent arg0) {
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                });
                
            }
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
