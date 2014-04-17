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
    
    public int getFile(int pos){
        int file = 0;
        
        if (pos < 8){
            file = 8;
        }else if(pos < 16){
            file = 7;
        }else if(pos < 24){
            file = 6;
        }else if(pos < 32){
            file = 5;
        }else if(pos < 40){
            file = 4;
        }else if(pos < 48){
            file = 3;
        }else if(pos < 56){
            file = 2;
        }else if(pos < 64){
            file = 1;
        }
        
        return file;
    }
    
    public char getRank(int pos){
        char rank = 'x';
        
        if (pos % 8 == 0){
            rank = 'a';
        }else if(pos % 8 == 1){
            rank = 'b';
        }else if(pos % 8 == 2){
            rank = 'c';
        }else if(pos % 8 == 3){
            rank = 'd';
        }else if(pos % 8 == 4){
            rank = 'e';
        }else if(pos % 8 == 5){
            rank = 'f';
        }else if(pos % 8 == 6){
            rank = 'g';
        }else if(pos % 8 == 7){
            rank = 'h';
        }
        
        return rank;
    }
    
    public void setBoardPosition(int boardPosition){
        this.boardPosition = boardPosition;
    }
    
    public abstract boolean isValidMove(int boardPosition);
    
}