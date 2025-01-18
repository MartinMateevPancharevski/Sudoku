import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGame extends JFrame {
    private SudokuBoard sudokuBoard;
    private JTextField[][] cells;
    private JButton checkButton;

    public SudokuGame(int difficulty) {
        sudokuBoard = new SudokuBoard(difficulty);
        cells = new JTextField[9][9];

        setTitle("Sudoku Game");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                if (sudokuBoard.getBoard()[i][j] != 0) {
                    cells[i][j].setText(String.valueOf(sudokuBoard.getBoard()[i][j]));
                    cells[i][j].setEditable(false);
                }
                boardPanel.add(cells[i][j]);
            }
        }

        checkButton = new JButton("Check");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSudoku();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkButton);

        add(boardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void checkSudoku() {
        int[][] userBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = cells[i][j].getText();
                if (text.isEmpty()) {
                    userBoard[i][j] = 0;
                } else {
                    userBoard[i][j] = Integer.parseInt(text);
                }
            }
        }

        if (SudokuBoard.isSolved(userBoard)) {
            JOptionPane.showMessageDialog(this, "Congratulations! You solved the Sudoku!");
        } else {
            JOptionPane.showMessageDialog(this, "The Sudoku is not solved correctly. Please try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGame(1)); // 0 for Easy, 1 for Normal, 2 for Hard
    }
}