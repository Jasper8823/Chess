package main.Entities;

import main.Entities.Pieces.Knight;
import main.UserInteraction.Mouse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int blockSize = 85;

    final int cols  = 8;
    final int rows  = 8;

    Mouse mouse = new Mouse(this);

    public Piece selectedPiece;

    ArrayList<Piece> pieces = new ArrayList<>();

    public Board(){
        this.setPreferredSize(new Dimension(cols * blockSize, rows * blockSize));

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public void addPiece(Piece piece){
        pieces.add(piece);

    }

    public Piece getPieceByXY(int col, int row){
        System.out.println(col+ " "+row);
        for(Piece piece : pieces){
            if(piece.getCol() == col && piece.getRow() == row){
                return piece;
            }
        }

        return null;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for(int i=0; i<rows; i++){
            for(int ii=0; ii<cols; ii++){
                g2d.setColor((i+ii)%2 == 0 ? new Color(255, 254, 195) : new Color(114, 143, 76, 255));
                g2d.fillRect(i*blockSize, ii*blockSize, blockSize, blockSize);
            }
        }

        for(Piece piece : pieces){
            piece.paint(g2d);
        }

    }

    public void makeMove(Move move){

        move.piece.col = move.toCol;
        move.piece.row = move.toRow;
        move.piece.frameXPos = move.toCol * blockSize;
        move.piece.frameYPos = move.toRow * blockSize;

        capture(move);
    }

    public void capture(Move move){
        pieces.remove(move.capture);
    }

    public boolean isValidMove(Move move){

        if(isSameTeam(move.piece, move.capture)){
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

}
