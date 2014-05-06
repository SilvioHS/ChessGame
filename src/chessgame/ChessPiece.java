/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import java.awt.Image;
import javax.swing.*;
/**
 *
 * @author Nick
 */
public abstract class ChessPiece extends JButton {//Changed to extends JLabel, each piece is contained in a JLabel, by Qilin Fu
    
    Image image;
    String color;
    int boardPosition;
    
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