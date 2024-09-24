package main.UserInteraction;

import main.Entities.Board;
import main.Entities.Move;
import main.Entities.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse extends MouseAdapter {

    private Board board;

    public Mouse(Board board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int col = e.getX()/board.blockSize;
        int row = e.getY()/board.blockSize;

        Piece piece = board.getPieceByXY(col,row);

        if (piece != null) {
            board.selectedPiece = piece;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.blockSize;
        int row = e.getY() / board.blockSize;

        if (board.selectedPiece != null) {
            Move move = new Move(board, board.selectedPiece, row, col);

            if (board.isValidMove(move)) {
                board.makeMove(move);
            } else {
                resetPiecePosition();
            }

            board.selectedPiece = null;
            board.repaint();
        }
    }

    private void resetPiecePosition() {
        board.selectedPiece.frameXPos = board.selectedPiece.col * board.blockSize;
        board.selectedPiece.frameYPos = board.selectedPiece.row * board.blockSize;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if(board.selectedPiece != null) {
            board.selectedPiece.frameXPos = e.getX()-board.blockSize/2;
            board.selectedPiece.frameYPos = e.getY()-board.blockSize/2;

            board.repaint();
        }

    }
}
