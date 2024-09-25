package main.Entities.Pieces;

import main.Controllers.BoardController;
import main.Entities.Board;
import main.Entities.Piece;

import java.awt.image.BufferedImage;

public class King extends Piece {
    public King(BoardController boardController, int col, int row, boolean isWhite) {
        super(boardController);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        this.frameXPos = col * boardController.getblockSize()-2;
        this.frameYPos = row * boardController.getblockSize()-3;
        this.name = "King";

        this.sprite = sheet.getSubimage(0, isWhite ? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(boardController.getblockSize()+4,boardController.getblockSize()+4, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int row, int col) {
        return Math.abs((col - this.col)*(row-this.row))==1||Math.abs(col - this.col)+Math.abs(row-this.row)==1;
    }
}