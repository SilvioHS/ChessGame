/*
 * queen class that extends Chesspiece
 */


package chesspieces;

import chesspieces.Rook;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @authors Kevin Velasco & Nick Ottrando
 */
public class Queen extends ChessPiece{
    
    private ChessPiece bishopQ;
    private ChessPiece rookQ;

      /**
     * <p>
     * Construct Queen object give an image, color, and the starting board position
     * of the Queen. 
     * <p>
     * 
     * @param image The image to be associated with this Queen
     * @param color the color of this Pawn, white or Queen
     * @param boardPosition The starting position of this Queen
     */
    public Queen(Image image, String color, int boardPosition) {
        super(image, color, boardPosition);

        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setSize(65, 65);
        this.setIcon(new ImageIcon(image));
        
        bishopQ = new Bishop(image, color, this.boardPosition);
        rookQ = new Rook(image, color, this.boardPosition);
    }

    /**
     * <p>
     * The queen can be moved any number of unoccupied squares in a straight line vertically, horizontally, 
     * or diagonally, thus combining the moves of the rook and bishop.
     * <p>
     * 
     * <p>
     * The queen captures by occupying the square on which an enemy piece sits.
     * <p>
     * 
     * @param newPosition The position that this Queen is attempting to move from its current position
     * @return  true if the move made by this Queen was valid otherwise return false.
     */
    @Override
    public boolean isValidMove(int newPosition) {
        
        bishopQ.setBoardPosition(this.getBoardPosition());
        rookQ.setBoardPosition(this.getBoardPosition()); 
               
        return ((bishopQ.isValidMove(newPosition)) || (rookQ.isValidMove(newPosition)));
    }
    
}
