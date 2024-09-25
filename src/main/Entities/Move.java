package main.Entities;

import main.Controllers.BoardController;

public class Move {

    public int toRow;
    public int toCol;

    public int fromRow;
    public int fromCol;

    public Piece piece;
    public Piece capture;

    public Move(BoardController boardController , Piece piece, int toRow, int toCol) {
        this.toRow = toRow;
        this.toCol = toCol;

        this.fromRow = piece.getRow();
        this.fromCol = piece.getCol();

        this.piece = piece;
        this.capture = boardController.getPieceByXY(toCol, toRow);
    }
}
