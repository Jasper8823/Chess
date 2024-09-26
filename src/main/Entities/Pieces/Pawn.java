package main.Entities.Pieces;

import main.Controllers.BoardController;
import main.Entities.Board;
import main.Entities.Piece;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {
    public Pawn(BoardController boardController, int col, int row, boolean isWhite) {
        super(boardController);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        this.frameXPos = col * boardController.getblockSize()-2;
        this.frameYPos = row * boardController.getblockSize()-3;
        this.name = "Pawn";

        this.sprite = sheet.getSubimage(5*sheetScale, isWhite ? 0: sheetScale, sheetScale,sheetScale).getScaledInstance(boardController.getblockSize()+4,boardController.getblockSize()+4, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int row, int col) {
        int colorIndex = isWhite ? 1 : -1;

        //Push pawn 1
        if (this.col == col && row == this.row - colorIndex && boardcontroller.getPieceByXY(col, row) == null) {
            return true;
        }

        //Push pawn 2 (on first move)
        if (isFirstMove && this.col == col && row == this.row - colorIndex * 2 &&
                boardcontroller.getPieceByXY(col, row) == null && boardcontroller.getPieceByXY(col, row + colorIndex) == null) {
            return true;
        }

        //Capture left
        if (col == this.col - 1 && row == this.row - colorIndex && boardcontroller.getPieceByXY(col, row) != null) {
            return true;
        }

        //Capture right
        if (col == this.col + 1 && row == this.row - colorIndex && boardcontroller.getPieceByXY(col, row) != null) {
            return true;
        }

        //enPassant left
        if(boardcontroller.getTilenum(col,row) == boardcontroller.getEnPassant() && col == this.col-1 && row == this.row - colorIndex && boardcontroller.getPieceByXY(col, row+colorIndex) != null) {
            return true;
        }

        //enPassant right
        if(boardcontroller.getTilenum(col,row) == boardcontroller.getEnPassant() && col == this.col+1 && row == this.row - colorIndex&& boardcontroller.getPieceByXY(col, row+colorIndex) != null) {
            return true;
        }

        return false;
    }

}