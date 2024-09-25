package main.Entities.Pieces;

import main.Controllers.BoardController;
import main.Entities.Board;
import main.Entities.Piece;

import java.awt.image.BufferedImage;

public class Rook extends Piece {
    public Rook(BoardController boardController, int col, int row, boolean isWhite) {
        super(boardController);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        this.frameXPos = col * boardController.getblockSize()-2;
        this.frameYPos = row * boardController.getblockSize()-3;
        this.name = "Rook";

        this.sprite = sheet.getSubimage(4*sheetScale, isWhite ? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(boardController.getblockSize()+4,boardController.getblockSize()+4, BufferedImage.SCALE_SMOOTH);


    }
}
