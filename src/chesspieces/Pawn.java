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

    /**
     * <p>
     * Construct Pawn object give an image, color, and the starting board position
     * of the Pawn. 
     * <p>
     * 
     * @param image The image to be associated with this Pawn
     * @param color the color of this Pawn, white or black
     * @param boardPosition The starting position of this Pawn
     */
    public Pawn(Image image, String color, int boardPosition) {
        super(image, color, boardPosition);
        
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setContentAreaFilled(false);
	this.setBorderPainted(false);
	this.setOpaque(false);
        this.setSize(65, 65);
        this.setIcon(new ImageIcon(image));
    }
    
     /**
     * 
     * @return True or false if this Pawn has already made it's first move
     */
    public boolean getFirstMove(){
        return firstMove;
    }
    
    /**
     * <p>
     * Set the first move flag of this Pawn to either true or false
     * <p>
     * 
     * @param firstMove The new value of the first move flag 
     */
    public void setFirstMove(boolean firstMove){
        this.firstMove = firstMove;
    }

    /**
     * <p>
     * Unlike the other pieces, pawns may not move backwards. Normally a pawn moves by advancing 
     * a single square, but the first time a pawn is moved, it has the option of advancing two squares
     * <p>
     * 
     * <p>
     * Unlike other pieces, the pawn does not capture in the same direction as it otherwise moves. 
     * A pawn captures diagonally, one square forward and to the left or right.
     * <p>
     * 
     * 
     * @param newPosition The position that this Pawn is attempting to move from its current position
     * @return  true if the move made by this Pawn was valid otherwise return false.
     */
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