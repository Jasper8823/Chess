package main.ChessController;

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

        Board board = new Board();
        frame.add(board);
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new Knight(board, 1,7,true));
        pieces.add(new Knight(board, 6,7,true));

        pieces.add(new Knight(board, 1,0,false));
        pieces.add(new Knight(board, 6,0,false));

        pieces.add(new Pawn(board, 0,1,false));
        pieces.add(new Pawn(board, 1,1,false));
        pieces.add(new Pawn(board, 2,1,false));
        pieces.add(new Pawn(board, 3,1,false));
        pieces.add(new Pawn(board, 4,1,false));
        pieces.add(new Pawn(board, 5,1,false));
        pieces.add(new Pawn(board, 6,1,false));
        pieces.add(new Pawn(board, 7,1,false));

        pieces.add(new Pawn(board, 0,6,true));
        pieces.add(new Pawn(board, 1,6,true));
        pieces.add(new Pawn(board, 2,6,true));
        pieces.add(new Pawn(board, 3,6,true));
        pieces.add(new Pawn(board, 4,6,true));
        pieces.add(new Pawn(board, 5,6,true));
        pieces.add(new Pawn(board, 6,6,true));
        pieces.add(new Pawn(board, 7,6,true));

        pieces.add(new Rook(board, 0,7,true));
        pieces.add(new Rook(board, 7,7,true));

        pieces.add(new Rook(board, 0,0,false));
        pieces.add(new Rook(board, 7,0,false));

        pieces.add(new Bishop(board, 2,7,true));
        pieces.add(new Bishop(board, 5,7,true));

        pieces.add(new Bishop(board, 2,0,false));
        pieces.add(new Bishop(board, 5,0,false));

        pieces.add(new King(board, 4,7,true));
        pieces.add(new Queen(board, 3,7,true));

        pieces.add(new King(board, 4,0,false));
        pieces.add(new Queen(board, 3,0,false));




        for(Piece piece : pieces){
            board.addPiece(piece);
        }


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
