package main.Entities;

import main.Entities.Pieces.Knight;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int blockSize = 85;

    final int cols  = 8;
    final int rows  = 8;

    ArrayList<Piece> pieces = new ArrayList<>();

    public Board(){
        this.setPreferredSize(new Dimension(cols * blockSize, rows * blockSize));
    }

    public void addPiece(Piece piece){
        pieces.add(piece);

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

}
