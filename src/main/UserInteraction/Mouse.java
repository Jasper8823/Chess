package main.UserInteraction;

import main.Controllers.BoardController;
import main.Entities.Board;
import main.Entities.Move;
import main.Entities.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse extends MouseAdapter {

    private final BoardController boardController;

    public Mouse(BoardController boardController) {
        this.boardController = boardController;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int col = e.getX()/boardController.getblockSize();
        int row = e.getY()/boardController.getblockSize();

        Piece piece = boardController.getPieceByXY(col,row);

        if (piece != null) {
            boardController.setSelectedPiece(piece);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / boardController.getblockSize();
        int row = e.getY() / boardController.getblockSize();

        if(col<8 && col>=0 && row <8 && row>=0) {
            if (boardController.getselectedPiece() != null) {
                Move move = new Move(boardController, boardController.getselectedPiece(), row, col);

                if (boardController.isValidMove(move)) {
                    boardController.makeMove(move);
                } else {
                    resetPiecePosition();
                }

                boardController.setSelectedPiece(null);
                boardController.repaint();
            }
        }else{
            resetPiecePosition();
            boardController.setSelectedPiece(null);
            boardController.repaint();
        }
    }

    private void resetPiecePosition() {
        boardController.getselectedPiece().frameXPos = boardController.getselectedPiece().col * boardController.getblockSize();
        boardController.getselectedPiece().frameYPos = boardController.getselectedPiece().row * boardController.getblockSize();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if(boardController.getselectedPiece() != null) {
            boardController.getselectedPiece().frameXPos = e.getX()-boardController.getblockSize()/2;
            boardController.getselectedPiece().frameYPos = e.getY()-boardController.getblockSize()/2;

            boardController.repaint();
        }

    }
}
