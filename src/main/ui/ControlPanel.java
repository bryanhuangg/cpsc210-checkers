package ui;

import model.Board;
import model.Piece;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ControlPanel extends JPanel implements ActionListener {
    private static final String JSON_STORE = "./data/checkers.json";

    private CheckersApp app;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTextField selectX;
    private JTextField selectY;
    private JTextField targetX;
    private JTextField targetY;

    private JCheckBox colour;

    private JButton add;
    private JButton remove;
    private JButton move;



    // EFFECTS: set up control side panel
    public ControlPanel(CheckersApp app) {
        setPreferredSize(new Dimension(300,600));
        setLayout(new GridLayout(15,10,0,0));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(Color.LIGHT_GRAY);
        this.app = app;

        innit();

        add(new JLabel("Save and Load your checkers board"));
        loadButton();
        saveButton();

        newGameButton();

        selectCord();
        addButton();
        removeButton();
        moveButton();
    }

    // EFFECTS: initialize JSON
    public void innit() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    // MODIFIES: app
    // EFFECTS: load board from file
    public void loadButton() {
        JButton load = new JButton("load");
        load.setBounds(50,150,150,40);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Board load = jsonReader.read();
                    app.setBoard(load);
                    System.out.println("Board loaded from " + JSON_STORE);
                    JOptionPane.showMessageDialog(null, "Board loaded from" + JSON_STORE);
                } catch (IOException f) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                    JOptionPane.showMessageDialog(null, "Unable to read from file");
                }
            }
        });
        add(load);
    }

    // EFFECTS: save board onto file
    public void saveButton() {
        JButton save = new JButton("save");
        save.setBounds(100,150,150,40);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(app.getBoard());
                    jsonWriter.close();
                    System.out.println("Board saved to " + JSON_STORE);
                    JOptionPane.showMessageDialog(null, "Board saved to" + JSON_STORE);
                } catch (FileNotFoundException f) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                    JOptionPane.showMessageDialog(null, "Unable to write from file");
                }
            }
        });
        add(save);
    }

    // EFFECTS: sets pieces on standard checkers position
    public void newGameButton() {
        JButton newGame = new JButton("new game");
        newGame.setBounds(100,150,150,40);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.getBoard().newGame();
            }
        });
        add(newGame);
    }

    // EFFECTS: renders input text field for targeted x coordinate
    public void selectCord() {
        selectX = new JTextField("1");
        selectX.setBounds(50,100,200,30);

        selectY = new JTextField("0");
        selectY.setBounds(50,100,200,30);

        targetX = new JTextField("3");
        targetX.setBounds(50,100,200,30);

        targetY = new JTextField("2");
        targetY.setBounds(50,100,200,30);

        colour = new JCheckBox("Black piece?");
        colour.setBounds(100,150,50,50);



        add(new JLabel("Select Tile"));
        add(selectX);
        add(selectY);

        add(colour);

        add(new JLabel("Target Tile"));
        add(targetX);
        add(targetY);
    }

    // EFFECT: button for adding pieces at selected location
    public void addButton() {
        add = new JButton("add piece");
        add.setBounds(100,150,150,40);
        add.addActionListener(this);
        add(add);
    }


    // EFFECT: button for removing pieces at selected location
    public void removeButton() {
        remove = new JButton("remove piece");
        remove.setBounds(100,150,150,40);
        remove.addActionListener(this);
        add(remove);
    }

    // EFFECT: button for move piece from selected location to target location
    public void moveButton() {
        move = new JButton("move piece");
        move.setBounds(100,150,150,40);
        move.addActionListener(this);
        add(move);
    }



    public void actionPerformed(ActionEvent e) {
        try {
            int sx = Integer.parseInt(selectX.getText());
            int sy = Integer.parseInt(selectY.getText());
            int tx = Integer.parseInt(targetX.getText());
            int ty = Integer.parseInt(targetY.getText());
            boolean c = colour.isSelected();

            if (e.getSource() == add) {
                addPiece(sx,sy,c);
            }

            if (e.getSource() == remove) {
                removePiece(sx,sy);
            }

            if (e.getSource() == move) {
                movePiece(sx,sy,tx,ty);
            }
        } catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Error: Non integer in field");
        }
    }

    // MODIFIES: app
    // EFFECTS: add a piece onto the board of given input
    public void addPiece(int sx, int sy, boolean c) {
        if (app.getBoard().addPiece(sx, sy, c)) {
            app.getBoard().addPiece(sx, sy, c);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Can not add piece there");
        }
    }

    // MODIFIES: app
    // EFFECTS: remove a piece off the board on given location
    public void removePiece(int sx, int sy) {
        Piece remove = null;
        for (Piece p : app.getBoard().getPieces()) {
            if (sx == p.getXPos() && sy == p.getYPos()) {
                remove = p;
            }
        }
        app.getBoard().deletePiece(remove);
    }

    // MODIFIES: app
    // EFFECTS: moves a piece from selected location to target location
    public void movePiece(int sx, int sy, int tx, int ty) {
        Piece move = null;
        for (Piece p : app.getBoard().getPieces()) {
            if (sx == p.getXPos() && sy == p.getYPos()) {
                move = p;
            }
        }
        app.getBoard().movePiece(move,tx,ty);
    }


}
