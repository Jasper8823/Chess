package main.Entities;

import main.Controllers.BoardController;

import java.sql.SQLOutput;

public class CheckScanner {
    BoardController boardController;

    public CheckScanner(BoardController boardController) {
        this.boardController = boardController;
    }

    public boolean isKingChecked(Move move) {

        Piece king = boardController.findKing(move.piece.isWhite);
        assert king != null;

        int kingCol = king.col, kingRow = king.row;

        if(boardController.getselectedPiece()!=null && boardController.getselectedPiece().name.equals("King")){
            kingCol = move.toCol;
            kingRow = move.toRow;
        }

        return  hitByRook(move.toCol, move.toRow, king, kingCol, kingRow, 1,  0) || // right
                hitByRook(move.toCol, move.toRow, king, kingCol, kingRow, -1, 0) || // left
                hitByRook(move.toCol, move.toRow, king, kingCol, kingRow, 0, 1) || // up
                hitByRook(move.toCol, move.toRow, king, kingCol, kingRow, 0, -1) || // down

                hitByBishop(move.toCol, move.toRow, king, kingCol, kingRow, 1, 1) || // up-right
                hitByBishop(move.toCol, move.toRow, king, kingCol, kingRow, 1, -1) || // down-right
                hitByBishop(move.toCol, move.toRow, king, kingCol, kingRow,-1, 1) || // up-left
                hitByBishop(move.toCol, move.toRow, king, kingCol, kingRow, -1, -1) || // down-left

                hitByKnight(move.toCol, move.toRow, king, kingCol, kingRow) ||
                hitByPawn(move.toCol, move.toRow, king, kingCol, kingRow) ||
                hitByKing(king, kingCol, kingRow);

    }

    private boolean hitByRook(int col, int row,Piece king, int kingCol, int kingRow, int colVal, int rowVal) {

        for (int i=1; i<8;i++){
            if(kingCol+(i*colVal) == col && kingRow+(i*rowVal) == row){
                break;
            }

            Piece piece = boardController.getPieceByXY(kingCol+(i*colVal), kingRow+(i*rowVal));
            if(piece!=null && piece != boardController.getselectedPiece()){
                if(!boardController.isSameTeam(piece, king) && ((piece.name.equals("Rook")||piece.name.equals("Queen")))){
                    return true;
                }
                break;
            }
        }

        return false;
    }

    private boolean hitByBishop(int col, int row,Piece king, int kingCol, int kingRow, int colVal, int rowVal) {

        for (int i=1; i<8;i++){
            if(kingCol-(i*colVal) == col && kingRow-(i*rowVal) == row){
                break;
            }

            Piece piece = boardController.getPieceByXY(kingCol-(i*colVal), kingRow-(i*rowVal));
            if(piece!=null && piece != boardController.getselectedPiece()){
                if(!boardController.isSameTeam(piece, king) && ((piece.name.equals("Bishop")||piece.name.equals("Queen")))){
                    return true;
                }
                break;
            }

        }

        return false;
    }

    public boolean checkedByKnight(Piece p, Piece k, int col, int row) {

        return p!=null && !boardController.isSameTeam(p, k) && p.name.equals("Knight")&& !(p.col == col && p.row == row);

    }

    public boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow) {
        return checkedByKnight(boardController.getPieceByXY(kingCol -1, kingRow -2), king, col, row) ||
                checkedByKnight(boardController.getPieceByXY(kingCol -1, kingRow +2), king, col, row) ||
                checkedByKnight(boardController.getPieceByXY(kingCol +1, kingRow -2), king, col, row) ||
                checkedByKnight(boardController.getPieceByXY(kingCol +1, kingRow +2), king, col, row) ||
                checkedByKnight(boardController.getPieceByXY(kingCol +2, kingRow -1), king, col, row) ||
                checkedByKnight(boardController.getPieceByXY(kingCol -2, kingRow +1), king, col, row) ||
                checkedByKnight(boardController.getPieceByXY(kingCol -2, kingRow -1), king, col, row) ||
                checkedByKnight(boardController.getPieceByXY(kingCol +2, kingRow +1), king, col, row);
    }

    public boolean checkedByKing(Piece p, Piece k) {
        return p!=null && !boardController.isSameTeam(p, k) && p.name.equals("King");
    }

    public boolean hitByKing(Piece king, int kingCol, int kingRow) {
        return checkedByKing(boardController.getPieceByXY(kingCol-1, kingRow-1), king)||
                checkedByKing(boardController.getPieceByXY(kingCol+1, kingRow+1), king)||
                checkedByKing(boardController.getPieceByXY(kingCol+1, kingRow-1), king)||
                checkedByKing(boardController.getPieceByXY(kingCol-1, kingRow+1), king)||
                checkedByKing(boardController.getPieceByXY(kingCol, kingRow-1), king)||
                checkedByKing(boardController.getPieceByXY(kingCol-1, kingRow), king)||
                checkedByKing(boardController.getPieceByXY(kingCol+1, kingRow), king)||
                checkedByKing(boardController.getPieceByXY(kingCol, kingRow+1), king);
    }
    public boolean checkByPawn(Piece p, Piece k, int col, int row) {
        return p!=null && !boardController.isSameTeam(p, k) && p.name.equals("Pawn");
    }

    public boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow) {
        int colorVal = king.isWhite ? -1 : 1;
        return checkByPawn(boardController.getPieceByXY(kingCol-1,kingRow+colorVal), king, col, row)||
                checkByPawn(boardController.getPieceByXY(kingCol+1,kingRow+colorVal), king, col, row);
    }

    public boolean isGameOver(Piece king) {
        for (Piece piece : boardController.getPieceList()) {

            if (boardController.isSameTeam(piece, king)) {

                boardController.board.selectedPiece = piece == king ? king : null;
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        Move move = new Move(boardController, piece, col, row);

                        if (boardController.isValidMove(move)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


}
