/*
 * Pawn class that extends Chesspiece
 */



package chesspieces;

import chessgame.ChessBoard;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author Nick Ottrando
 */
public class Pawn extends ChessPiece{
    private boolean firstMove = true;

    public Pawn(Image image, String color, int boardPosition) {
        super(image, color, boardPosition);
        
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setContentAreaFilled(false);
	this.setBorderPainted(false);
	this.setOpaque(false);
        this.setSize(65, 65);
        this.setIcon(new ImageIcon(image));
    }
    
    public boolean getFirstMove(){
        return firstMove;
    }
    
    public void setFirstMove(boolean firstMove){
        this.firstMove = firstMove;
    }

    @Override
    public boolean isValidMove(int newPosition) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean validMove = false;
        
        if(this.getColor().equals("white")){
            
            if(this.getBoardPosition() == newPosition-8 && ChessBoard.pieces[newPosition] == null){
                validMove = true;
            }else if(this.getBoardPosition() == newPosition-16 && firstMove && ChessBoard.getIndex(newPosition-8)==null 
                    && ChessBoard.getIndex(newPosition) == null){
                validMove = true;
            }else if((Math.abs((int) ChessBoard.getRank(this.getBoardPosition()) - (int) ChessBoard.getRank(newPosition)) == 1)
                    && (ChessBoard.getFile(this.getBoardPosition()) - ChessBoard.getFile(newPosition) == 1)
                    && ChessBoard.getIndex(newPosition) != null && ChessBoard.getIndex(newPosition).getColor().equals("black")){
                validMove = true;
            }
        
        //black
        }else{
        
            if(this.getBoardPosition() == newPosition +8 && ChessBoard.pieces[newPosition] == null){
                validMove = true;
            }else if(this.getBoardPosition() == newPosition+16 && firstMove && ChessBoard.getIndex(newPosition+8)==null 
                    && ChessBoard.getIndex(newPosition) == null){
                validMove = true;
            }
            else if((Math.abs((int) ChessBoard.getRank(this.getBoardPosition()) - (int) ChessBoard.getRank(newPosition)) == 1)
                    && (ChessBoard.getFile(this.getBoardPosition()) - ChessBoard.getFile(newPosition) == -1)
                    && ChessBoard.getIndex(newPosition) != null && ChessBoard.getIndex(newPosition).getColor().equals("white")){
                validMove = true;
            }
        }
        return validMove;
    }
    
}