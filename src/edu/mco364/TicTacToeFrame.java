package edu.mco364;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private TicTacToeModel gameModel;
    private JLabel statusBar;

    public TicTacToeFrame() {
        gameModel = new TicTacToeModel();
        statusBar = new JLabel(" ");

        setSize(500, 520);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(new TicTacToePanel(), BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }

    class TicTacToePanel extends JPanel { // non-static iner class has access to enclosing class fields/methods
        private JButton[][] gridButtons = new JButton[3][3];

        public TicTacToePanel() {
            setLayout(new GridLayout(3, 3));
            ButtonEventHandler buttonEventHandler = new ButtonEventHandler();
            for (int row = 0; row < 3; row++)
                for (int col = 0; col < 3; col++) {
                    gridButtons[row][col] = new JButton();
                    add(gridButtons[row][col]);

                    gridButtons[row][col].addActionListener(buttonEventHandler);
                }
        }

        private class ButtonEventHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                // determine which button and row, col fired event
                int row = 0, col = 0;
                gridLocationFinder:
                for (row = 0; row < 3; row++) {
                    for (col = 0; col < 3; col++) {
                        if (e.getSource() == gridButtons[row][col])
                            break gridLocationFinder; // break out of outer loop using labelled break
                    }
                }
                if (gameModel.setCell(row, col, gameModel.currentPlayer())) // legal
                {
                    JButton buttonPressed = (JButton) e.getSource();
                    buttonPressed.setText(gameModel.currentPlayer().toString());
                    buttonPressed.setEnabled(false); // disable pressing again

                    // check for gameOver...display game over in statusBar or whose turn it now is
                    statusBar.setText(gameModel.currentPlayer().toString());
                }

            }
        }
    }
}
