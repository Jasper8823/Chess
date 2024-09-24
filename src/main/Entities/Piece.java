package main.Entities;

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

    Board board;

    public Piece(Board board){
        this.board = board;
        loadSpriteSheet();
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite, frameXPos, frameYPos, null);
    }

    public int getCol() {return col;}

    public int getRow() {return row;}

}
