package main.Controllers;

import main.Entities.Board;
import main.Entities.CheckScanner;
import main.Entities.Move;
import main.Entities.Piece;
import main.Entities.Pieces.Bishop;
import main.Entities.Pieces.Knight;
import main.Entities.Pieces.Queen;
import main.Entities.Pieces.Rook;
import main.UserInteraction.Mouse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BoardController extends JPanel {

    public Board board;
    Mouse mouse = new Mouse(this);

    GUIController guiController;

    public CheckScanner checkScanner = new CheckScanner(this);

    public BoardController(GUIController guiController) {
        this.guiController = guiController;
        board = new Board();

        this.setPreferredSize(new Dimension(board.cols * board.blockSize, board.rows * board.blockSize));

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public ArrayList<Piece> getPieceList(){
        return board.pieces;
    }

    public int getTilenum(int col, int row) {
        return row * board.rows + col;
    }

    public void setSelectedPiece(Piece piece) {
        board.selectedPiece = piece;
    }


    public int getblockSize(){
        return board.blockSize;
    }

    public Piece getselectedPiece(){
        return board.selectedPiece;
    }

    public void addPiece(Piece piece){
        board.pieces.add(piece);
    }

    public Piece getPieceByXY(int col, int row){
        for(Piece piece : board.pieces){
            if(piece.getCol() == col && piece.getRow() == row){
                return piece;
            }
        }
        return null;
    }

    public void makeMove(Move move){

        if(move.piece.name.equals("Pawn")){
            makeMovePaw(move);
        }else if(move.piece.name.equals("King")){
            makeMoveKing(move);
        }
        move.piece.col = move.toCol;
        move.piece.row = move.toRow;
        move.piece.frameXPos = move.toCol * board.blockSize;
        move.piece.frameYPos = move.toRow * board.blockSize;

        move.piece.isFirstMove = false;

        capture(move.capture);

        board.isWhiteToMove = !board.isWhiteToMove;

        updateGameState();
    }

    private void updateGameState() {
        Piece king = findKing(board.isWhiteToMove);

        if (checkScanner.isGameOver(king)) {

            if (checkScanner.isKingChecked(new Move(this, king, king.col, king.row))) {
                System.out.println(board.isWhiteToMove ? "Black Wins!" : "White Wins!");
            }
            else {
                System.out.println("Stalemate");
            }
        }else if(notEnoughPieces(true) && notEnoughPieces(false)){
            System.out.println("Not enough pieces to mate");
        }
    }

    private boolean notEnoughPieces(boolean isWhite) {
        ArrayList<String> names = board.pieces.stream()
                .filter(p -> p.isWhite == isWhite)
                .map(p -> p.name)
                .collect(Collectors.toCollection(ArrayList::new));

        if (names.contains("Queen") || names.contains("Rook") || names.contains("Pawn")) {
            return false;
        }

        return names.size() < 3;
    }


    private void makeMoveKing(Move move) {
        if (Math.abs(move.piece.col - move.toCol) == 2) {
            Piece rook;

            if (move.piece.col < move.toCol) {
                rook = getPieceByXY(7, move.piece.row);
                rook.col = 5;
            }
            else {
                rook = getPieceByXY(0, move.piece.row);
                rook.col = 3;
            }

            rook.frameXPos = rook.col * board.blockSize;
        }
    }


    private void makeMovePaw(Move move){

        int colorIndex = move.piece.isWhite ? 1 : -1;

        if(getTilenum(move.toCol, move.toRow) == board.enPassantTile){
            move.capture = getPieceByXY(move.toCol, move.toRow+colorIndex);
        }
        if(Math.abs(move.piece.row - move.toRow)==2){
            board.enPassantTile = getTilenum(move.toCol, move.toRow + colorIndex);
        }else{
            board.enPassantTile = -1;
        }

        colorIndex = move.piece.isWhite ? 0 : 7;

        if(move.toRow == colorIndex){
            promotePawn(move);
        }
    }

    public void promotePawn(Move move) {
        String chosenPiece = guiController.showPromotionDialog(move.piece.isWhite);

        Piece promotedPiece = null;
        switch (chosenPiece) {
            case "Knight":
                promotedPiece = new Knight(this, move.toCol, move.toRow, move.piece.isWhite);
                break;
            case "Bishop":
                promotedPiece = new Bishop(this, move.toCol, move.toRow, move.piece.isWhite);
                break;
            case "Rook":
                promotedPiece = new Rook(this, move.toCol, move.toRow, move.piece.isWhite);
                break;
            case "Queen":
            default:
                promotedPiece = new Queen(this, move.toCol, move.toRow, move.piece.isWhite);
                break;
        }

        // Replace the pawn with the selected piece
        board.pieces.add(promotedPiece);
        capture(move.piece);  // Remove the pawn from the board
    }

    public int getEnPassant(){
        return board.enPassantTile;
    }

    public void capture(Piece piece){
        board.pieces.remove(piece);
    }

    public Piece findKing(boolean isWhite){
        for(Piece piece : board.pieces){
            if(piece.isWhite==isWhite && piece.name.equals("King")){
                return piece;
            }
        }
        return null;
    }

    public boolean isValidMove(Move move){

        if(move.piece.isWhite != board.isWhiteToMove){
            return false;
        }

        if(isSameTeam(move.piece, move.capture)){
            return false;
        }

        if(!move.piece.isValidMovement(move.toRow, move.toCol)){
            return false;
        }

        if(move.piece.moveCollidesWithPiece(move.toCol, move.toRow)){
            return false;
        }
        if(checkScanner.isKingChecked(move)){
            return false;
        }


        return true;
    }

    public boolean isSameTeam(Piece p1, Piece p2){
        if(p1 == null || p2 == null){
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for(int i=0; i<board.rows; i++){
            for(int ii=0; ii<board.cols; ii++){
                g2d.setColor((i+ii)%2 == 0 ? new Color(255, 254, 195) : new Color(114, 143, 76, 255));
                g2d.fillRect(i*board.blockSize, ii*board.blockSize, board.blockSize, board.blockSize);
            }
        }
        if(board.selectedPiece!=null) {
            for (int r = 0; r < board.rows; r++) {
                for (int c = 0; c < board.cols; c++) {
                    if (isValidMove(new Move(this, board.selectedPiece, r, c))) {
                        g2d.setColor(new Color(124, 227, 59, 169));
                        g2d.fillRect(c * board.blockSize, r * board.blockSize, board.blockSize, board.blockSize);
                    }
                }
            }
        }

        for(Piece piece : board.pieces){
            piece.paint(g2d);
        }

    }
}
