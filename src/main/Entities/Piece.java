package main.Entities;

import main.Controllers.BoardController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Piece {

    public int col, row;
    public int frameXPos, frameYPos;
    public boolean isWhite;
    public String name;
    public int value;

    public BufferedImage sheet;

    public boolean isFirstMove = true;

    private void loadSpriteSheet() {
        try {
            sheet = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("pieces.png")));
            sheetScale = sheet.getWidth() / 6; // Assuming the sheet has 6 pieces in width
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected int sheetScale;

    protected Image sprite;

    protected BoardController boardcontroller;

    public Piece(BoardController boardController){
        this.boardcontroller = boardController;
        loadSpriteSheet();
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite, frameXPos, frameYPos, null);
    }

    public boolean isValidMovement(int col, int row) { return true; }

    public boolean moveCollidesWithPiece(int col, int row) { return false; }

    public int getCol() {return col;}

    public int getRow() {return row;}

}
