package main.Entities.Pieces;

import main.Entities.Board;
import main.Entities.Piece;

import java.awt.image.BufferedImage;

public class King extends Piece {
    public King(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        this.frameXPos = col * board.blockSize-2;
        this.frameYPos = row * board.blockSize-3;
        this.name = "King";

        this.sprite = sheet.getSubimage(0, isWhite ? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(board.blockSize+4,board.blockSize+4, BufferedImage.SCALE_SMOOTH);


    }
}