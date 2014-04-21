/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author Nick
 */
public class Pawn extends ChessPiece{
    boolean firstMove = true;

    public Pawn(Image image, String color, int boardPosition) {
        super(image, color, boardPosition);
        
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setContentAreaFilled(false);
	this.setBorderPainted(false);
	this.setOpaque(false);
        this.setSize(65, 65);
        this.setIcon(new ImageIcon(image));
    }

    @Override
    public boolean isValidMove(int newPosition) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean validMove = false;
        
        if(this.getColor().equals("white")){
            
            if(this.getBoardPosition() == newPosition-8){
                validMove = true;
            }else if(this.getBoardPosition() == newPosition-16 && firstMove){
                validMove = true;
            }
        
        //black
        }else{
        
            if(this.getBoardPosition() == newPosition+8){
                validMove = true;
            }else if(this.getBoardPosition() == newPosition+16 && firstMove){
                validMove = true;
            }
        }
        this.firstMove = false;
        return validMove;
    }
    
}