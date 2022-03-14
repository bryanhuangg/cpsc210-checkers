package ui;

import model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private CheckersApp app;
    private int size = 600;

    // EFFECT:  sets size and background colour of panel,
    //          updates this with the game to be displayed
    public BoardPanel(CheckersApp app) {
        setPreferredSize(new Dimension(size,size));
        setBackground(Color.WHITE);
        this.app = app;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCheckers(g);
    }

    // MODIFIES: g
    // EFFECT: draws checkers game onto g
    private void drawCheckers(Graphics g) {
        int[][] temp = app.getBoard().getCurrentBoard();
        for (int y = 0; y <= 7; y++) {
            for (int x = 0; x <= 7; x++) {
                switch (temp[y][x]) {
                    case 1:
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(x * size / 8, y * size / 8, size / 8, size / 8);
                        break;
                    case 2:
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(x * size / 8, y * size / 8, size / 8, size / 8);
                        g.setColor(Color.GRAY);
                        g.fillOval(x * size / 8 + 5, y * size / 8 + 5, size / 9, size / 9);
                        break;
                    case 3:
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(x * size / 8, y * size / 8, size / 8, size / 8);
                        g.setColor(Color.PINK);
                        g.fillOval(x * size / 8 + 5, y * size / 8 + 5, size / 9, size / 9);
                }
            }
        }
    }



}
