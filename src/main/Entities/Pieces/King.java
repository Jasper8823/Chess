package main.Entities.Pieces;

import main.Controllers.BoardController;
import main.Entities.Board;
import main.Entities.Move;
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

    private boolean canCastle(int col, int row) {
        if(this.row == row){
            if(col == 6){
                Piece rook = boardcontroller.getPieceByXY(7, row);

                if(rook != null && this.isFirstMove && rook.isFirstMove){
                    return boardcontroller.getPieceByXY(5, row)==null && boardcontroller.getPieceByXY(6, row)==null &&
                            !boardcontroller.checkScanner.isKingChecked(new Move(boardcontroller, this,row, this.col))&&
                            !boardcontroller.checkScanner.isKingChecked(new Move(boardcontroller, this,row, 5))&&
                            !boardcontroller.checkScanner.isKingChecked(new Move(boardcontroller, this,row, 6));
                }
            }else if(col == 2){
                Piece rook = boardcontroller.getPieceByXY(0, row);

                if(rook != null && this.isFirstMove && rook.isFirstMove){
                    return boardcontroller.getPieceByXY(3, row)==null && boardcontroller.getPieceByXY(2, row)==null && boardcontroller.getPieceByXY(1, row)==null &&
                            !boardcontroller.checkScanner.isKingChecked(new Move(boardcontroller, this,row, 3))&&
                            !boardcontroller.checkScanner.isKingChecked(new Move(boardcontroller, this,row, this.col))&&
                            !boardcontroller.checkScanner.isKingChecked(new Move(boardcontroller, this,row, 2));
                }
            }
        }

        return false;
    }

    public boolean isValidMovement(int row, int col) {
        return Math.abs((col - this.col)*(row-this.row))==1||Math.abs(col - this.col)+Math.abs(row-this.row)==1 || canCastle(col,row);
    }
}