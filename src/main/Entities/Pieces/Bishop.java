package main.Entities.Pieces;

import main.Entities.Board;
import main.Entities.Piece;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {
    public Bishop(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        this.frameXPos = col * board.blockSize-1;
        this.frameYPos = row * board.blockSize-1;
        this.name = "Bishop";

        this.sprite = sheet.getSubimage(2*sheetScale, isWhite ? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(board.blockSize,board.blockSize, BufferedImage.SCALE_SMOOTH);


    }
}