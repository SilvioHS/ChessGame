/*
 * Abstract class for Chesspiece
 */



package chesspieces;

import chessgame.ChessBoard;
import java.awt.Image;
import javax.swing.*;
/**
 *
 * @author Nick Ottrando
 */
public abstract class ChessPiece extends JButton {//Changed to extends JLabel, each piece is contained in a JLabel, by Qilin Fu
    
    private Image image;
    private String color;
    protected int boardPosition;
    
    public ChessPiece(Image image, String color, int boardPosition){
        this.image = image;
        this.color = color;
        this.boardPosition = boardPosition;
    }
    
    public Image getImage(){
        return this.image;
    }
    
    public String getColor(){
        return this.color;
    }
    
    public int getBoardPosition(){
        return this.boardPosition;
    }
    
    public void setBoardPosition(int boardPosition){
        this.boardPosition = boardPosition;
        //ChessBoard.pieces[boardPosition] = this;
    }
    
    @Override
    public String toString(){
        String result = "";
        result  += this.getColor() + " " + this.getClass().getSimpleName()  + " at " + 
                ChessBoard.getRank(this.getBoardPosition()) + ChessBoard.getFile(this.getBoardPosition());
        return result;
    }
    
    public abstract boolean isValidMove(int boardPosition);
    
}