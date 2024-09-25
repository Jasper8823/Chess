package main.Controllers;

import main.Entities.Board;
import main.Entities.CheckScanner;
import main.Entities.Move;
import main.Entities.Piece;
import main.UserInteraction.Mouse;

import javax.swing.*;
import java.awt.*;

public class BoardController extends JPanel {

    Board board;
    Mouse mouse = new Mouse(this);

    CheckScanner checkScanner = new CheckScanner(this);

    public BoardController() {
        board = new Board();

        this.setPreferredSize(new Dimension(board.cols * board.blockSize, board.rows * board.blockSize));

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public void setSelectedPiece(Piece piece) {
        board.selectedPiece = piece;
    }


    public int getblockSize(){
        return board.blockSize;
    }

    public Piece getselectedPiece(){
        return board.selectedPiece;
    }

    public void addPiece(Piece piece){
        board.pieces.add(piece);
    }

    public Piece getPieceByXY(int col, int row){
        for(Piece piece : board.pieces){
            if(piece.getCol() == col && piece.getRow() == row){
                return piece;
            }
        }
        return null;
    }

    public void makeMove(Move move){

        move.piece.col = move.toCol;
        move.piece.row = move.toRow;
        move.piece.frameXPos = move.toCol * board.blockSize;
        move.piece.frameYPos = move.toRow * board.blockSize;

        capture(move);
    }

    public void capture(Move move){
        board.pieces.remove(move.capture);
    }

    public Piece findKing(boolean isWhite){
        for(Piece piece : board.pieces){
            if(piece.isWhite==isWhite && piece.name.equals("King")){
                return piece;
            }
        }
        return null;
    }

    public boolean isValidMove(Move move){
        if(isSameTeam(move.piece, move.capture)){
            return false;
        }
        if(checkScanner.isKingChecked(move)){
            return false;
        }

        if(!move.piece.isValidMovement(move.toRow, move.toCol)){
            return false;
        }

        if(move.piece.moveCollidesWithPiece(move.toCol, move.toRow)){
            return false;
        }

        return true;
    }

    public boolean isSameTeam(Piece p1, Piece p2){
        if(p1 == null || p2 == null){
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for(int i=0; i<board.rows; i++){
            for(int ii=0; ii<board.cols; ii++){
                g2d.setColor((i+ii)%2 == 0 ? new Color(255, 254, 195) : new Color(114, 143, 76, 255));
                g2d.fillRect(i*board.blockSize, ii*board.blockSize, board.blockSize, board.blockSize);
            }
        }
        if(board.selectedPiece!=null) {
            for (int r = 0; r < board.rows; r++) {
                for (int c = 0; c < board.cols; c++) {
                    if (isValidMove(new Move(this, board.selectedPiece, r, c))) {
                        g2d.setColor(new Color(124, 227, 59, 169));
                        g2d.fillRect(c * board.blockSize, r * board.blockSize, board.blockSize, board.blockSize);
                    }
                }
            }
        }

        for(Piece piece : board.pieces){
            piece.paint(g2d);
        }

    }
}
