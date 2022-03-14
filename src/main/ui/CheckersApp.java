package ui;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class CheckersApp extends JFrame {
    private static final int INTERVAL = 10;

    private Board board;
    private BoardPanel bp;
    private ControlPanel cp;

    public CheckersApp() throws FileNotFoundException {
        super("Checkers Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        board = new Board();
        board.newGame();

        bp = new BoardPanel(this);
        cp = new ControlPanel(this);
        add(bp);
        add(cp, BorderLayout.EAST);
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //System.out.println(board.getPieces().size());
                bp.repaint();
            }
        });

        t.start();
    }

    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centered on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
