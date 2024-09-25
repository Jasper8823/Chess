package main.Controllers;

import main.Entities.Board;
import main.Entities.Piece;
import main.Entities.Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIController {
    public GUIController() {
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(1000, 1000));

        frame.setLayout(new GridBagLayout());
        frame.pack();

        BoardController boardController = new BoardController();
        frame.add(boardController);
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


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
