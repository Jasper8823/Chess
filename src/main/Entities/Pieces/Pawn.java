package main.Entities.Pieces;

import main.Entities.Board;
import main.Entities.Piece;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {
    public Pawn(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        this.frameXPos = col * board.blockSize-2;
        this.frameYPos = row * board.blockSize-3;
        this.name = "Pawn";

        this.sprite = sheet.getSubimage(5*sheetScale, isWhite ? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(board.blockSize+4,board.blockSize+4, BufferedImage.SCALE_SMOOTH);


    }
}