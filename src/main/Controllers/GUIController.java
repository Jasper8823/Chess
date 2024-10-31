package main.Controllers;

import main.Entities.Piece;
import main.Entities.Pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GUIController {

    public BufferedImage sheet;

    private void loadSpriteSheet() {
        try {
            sheet = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("pieces.png")));
            sheetScale = sheet.getWidth() / 6; // Assuming the sheet has 6 pieces in width
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BoardController boardController;

    protected int sheetScale;

    JFrame promoterChoice = new JFrame();
    JFrame frame;

    public GUIController() {
        loadSpriteSheet();

        frame = new JFrame();
        frame.setMinimumSize(new Dimension(1000, 1000));

        frame.setLayout(new GridBagLayout());
        frame.pack();

        gameStart();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void gameEnd(){
        frame.dispose();
    }

    public void gameStart(){
        boardController = new BoardController(this);
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new Knight(boardController, 1,7,true));
        pieces.add(new Knight(boardController, 6,7,true));

        pieces.add(new Knight(boardController, 1,0,false));
        pieces.add(new Knight(boardController, 6,0,false));

        pieces.add(new Pawn(boardController, 0,1,false));
        pieces.add(new Pawn(boardController, 1,1,false));
        pieces.add(new Pawn(boardController, 2,1,false));
        pieces.add(new Pawn(boardController, 3,1,false));
        pieces.add(new Pawn(boardController, 4,1,false));
        pieces.add(new Pawn(boardController, 5,1,false));
        pieces.add(new Pawn(boardController, 6,1,false));
        pieces.add(new Pawn(boardController, 7,1,false));

        pieces.add(new Pawn(boardController, 0,6,true));
        pieces.add(new Pawn(boardController, 1,6,true));
        pieces.add(new Pawn(boardController, 2,6,true));
        pieces.add(new Pawn(boardController, 3,6,true));
        pieces.add(new Pawn(boardController, 4,6,true));
        pieces.add(new Pawn(boardController, 5,6,true));
        pieces.add(new Pawn(boardController, 6,6,true));
        pieces.add(new Pawn(boardController, 7,6,true));

        pieces.add(new Rook(boardController, 0,7,true));
        pieces.add(new Rook(boardController, 7,7,true));

        pieces.add(new Rook(boardController, 0,0,false));
        pieces.add(new Rook(boardController, 7,0,false));

        pieces.add(new Bishop(boardController, 2,7,true));
        pieces.add(new Bishop(boardController, 5,7,true));

        pieces.add(new Bishop(boardController, 2,0,false));
        pieces.add(new Bishop(boardController, 5,0,false));

        pieces.add(new King(boardController, 4,7,true));
        pieces.add(new Queen(boardController, 3,7,true));

        pieces.add(new King(boardController, 4,0,false));
        pieces.add(new Queen(boardController, 3,0,false));

        for(Piece piece : pieces){
            boardController.addPiece(piece);
        }


        frame.add(boardController);
    }

    public String showPromotionDialog(boolean isWhite) {
        final String[] chosenPiece = new String[1];

        JDialog dialog = new JDialog(promoterChoice, "Promote Pawn", true);
        dialog.setLayout(new FlowLayout());
        dialog.setMinimumSize(new Dimension(300, 130));

        // Create and add buttons with their respective images
        JButton knightButton = new JButton(new ImageIcon(sheet.getSubimage(3*sheetScale, isWhite? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(65,65, BufferedImage.SCALE_SMOOTH)));
        knightButton.addActionListener(e -> {
            chosenPiece[0] = "Knight";
            dialog.dispose();
        });

        JButton bishopButton = new JButton(new ImageIcon(sheet.getSubimage(2*sheetScale, isWhite? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(65,65, BufferedImage.SCALE_SMOOTH)));
        bishopButton.addActionListener(e -> {
            chosenPiece[0] = "Bishop";
            dialog.dispose();
        });

        JButton rookButton = new JButton(new ImageIcon(sheet.getSubimage(4*sheetScale, isWhite? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(65,65, BufferedImage.SCALE_SMOOTH)));
        rookButton.addActionListener(e -> {
            chosenPiece[0] = "Rook";
            dialog.dispose();
        });

        JButton queenButton = new JButton(new ImageIcon(sheet.getSubimage(sheetScale, isWhite? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(65,65, BufferedImage.SCALE_SMOOTH)));
        queenButton.addActionListener(e -> {
            chosenPiece[0] = "Queen";
            dialog.dispose();
        });

        // Add buttons to the dialog
        dialog.add(knightButton);
        dialog.add(bishopButton);
        dialog.add(rookButton);
        dialog.add(queenButton);

        // Show the dialog and block until a choice is made
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        // Return the chosen piece
        return chosenPiece[0];
    }

}
