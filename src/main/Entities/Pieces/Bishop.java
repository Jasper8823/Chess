package main.Entities.Pieces;

import main.Controllers.BoardController;
import main.Entities.Board;
import main.Entities.Piece;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {
    public Bishop(BoardController boardController, int col, int row, boolean isWhite) {
        super(boardController);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        this.frameXPos = col * boardController.getblockSize()-1;
        this.frameYPos = row * boardController.getblockSize()-1;
        this.name = "Bishop";

        this.sprite = sheet.getSubimage(2*sheetScale, isWhite ? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(boardController.getblockSize(),boardController.getblockSize(), BufferedImage.SCALE_SMOOTH);


    }
}