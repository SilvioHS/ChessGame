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
    
    /**
     * <p>
     * Construct ChessPiece object give an image, color, and the starting board position
     * of the ChessPiece. 
     * <p>
     * 
     * @param image The image to be associated with this ChessPiece
     * @param color the color of this ChessPiece, white or black
     * @param boardPosition The starting position of this ChessPiece
     */
    public ChessPiece(Image image, String color, int boardPosition){
        this.image = image;
        this.color = color;
        this.boardPosition = boardPosition;
    }
    
    /**
     * 
     * @return the Image of this ChessPiece
     */
    public Image getImage(){
        return this.image;
    }
    
    /**
     * 
     * @return the color of this ChessPiece
     */
    public String getColor(){
        return this.color;
    }
    
    /**
     * 
     * @return the position of this ChessPiece on the ChessBoard
     */
    public int getBoardPosition(){
        return this.boardPosition;
    }
    
    /**
     * set the board position of this ChessPiece on the ChessBoard
     * <p>
     * @param boardPosition the new board position of this ChessPiece to be set
     */
    public void setBoardPosition(int boardPosition){
        this.boardPosition = boardPosition;
        //ChessBoard.pieces[boardPosition] = this;
    }
    /**
     * 
     * @return A string that contains the class name of the ChessPiece, the color
     * of the ChessPiece, the location of the ChessPiece in the form of ranks and file
     * (e.g. black ChessPiece at a5). 
     * 
     * 
     */
    @Override
    public String toString(){
        String result = "";
        result  += this.getColor() + " " + this.getClass().getSimpleName()  + " at " + 
                ChessBoard.getRank(this.getBoardPosition()) + ChessBoard.getFile(this.getBoardPosition());
        return result;
    }
   /**
    * <p>
     * If this ChessPiece is attempting to move in a valid way given its implementation. false is returned. 
     * <p>
     * If the attempt move is valid but there is n number of ChessPieces between 
     * the current location of the this ChessPiece and the new position a value of false is returned.
     * <p>
     *  If the new position is occupied by another ChessPiece of the same color a value of false is returned.
     * <p>
     *  If the new position is occupied by another ChessPiece of the opposing color or 
     * the new position is not occupied by another ChessPiece true is returned. 
     * 
    * @see Rook#isValidMove(int newPosition)
    * @see Bishop#isValidMove(int newPosition)
    * @see King#isValidMove(int newPosition)
    * @see Knight#isValidMove(int newPosition)
    * @see Queen#isValidMove(int newPosition)
    * @see Pawn#isValidMove(int newPosition)
    * @param boardPosition
    * @return 
    */
    public abstract boolean isValidMove(int boardPosition);
    
}