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

    public boolean moveCollidesWithPiece(int col, int row){
        // top-left
        if (this.col > col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (boardcontroller.getPieceByXY( this.col - i, this.row - i) != null)
                return true;
            }
        }
        // top-right
        if (this.col < col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (boardcontroller.getPieceByXY(this.col + i, this.row - i) != null)
                return true;
            }
        }
        // down-left
        if (this.col > col && this.row < row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (boardcontroller.getPieceByXY(this.col - i, this.row + i) != null)
                return true;
            }
        }
        // down-right
        if (this.col < col && this.row < row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (boardcontroller.getPieceByXY( this.col + i, this.row + i) != null)
                return true;
            }
        }
        return false;
    }

    public boolean isValidMovement(int row, int col) {
        return Math.abs(col - this.col)==Math.abs(row - this.row);
    }
}