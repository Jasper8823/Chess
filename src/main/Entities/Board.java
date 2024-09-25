package main.Entities;

import main.Entities.Pieces.Knight;
import main.UserInteraction.Mouse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {

    public int blockSize = 85;

    public final int cols  = 8;
    public final int rows  = 8;

    public Piece selectedPiece;

    public ArrayList<Piece> pieces = new ArrayList<>();

    public Board(){
    }
}
