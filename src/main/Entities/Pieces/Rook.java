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

    public boolean moveCollidesWithPiece(int col, int row){
        // left

        if (this.col > col) {
            for (int c = this.col - 1; c > col; c--) {
                if (boardcontroller.getPieceByXY(c, this.row) != null)
                    return true;
            }
        }

        // right
        if (this.col < col) {
            for (int c = this.col + 1; c < col; c++) {
                if (boardcontroller.getPieceByXY(c, this.row) != null)
                    return true;
            }
        }

        // up
        if (this.row > row) {
            for (int r = this.row - 1; r > row; r--) {
                if (boardcontroller.getPieceByXY(this.col, r) != null)
                    return true;
            }
        }

        // down
        if (this.row < row) {
            for (int r = this.row + 1; r < row; r++) {
                if (boardcontroller.getPieceByXY(this.col, r) != null)
                    return true;
            }
        }

        return false;
    }

    public boolean isValidMovement(int row, int col) {
        return this.col == col || this.row == row;
    }
}
