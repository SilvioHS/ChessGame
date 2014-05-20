/*
 * King class that extends Chesspiece
 */


package chesspieces;

import chessgame.ChessBoard;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author Nick Ottrando
 */
public class King extends ChessPiece {

    private boolean firstMove = true;

    
    /**
     * <p>
     * Construct King object give an image, color, and the starting board position
     * of the King. 
     * <p>
     * 
     * @param image The image to be associated with this King
     * @param color the color of this King, white or black
     * @param boardPosition The starting position of this King
     */
    public King(Image image, String color, int boardPosition) {
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
     * @return True or false if this Knight has already made it's first move
     */
    public boolean getFirstMove() {
        return firstMove;
    }

    /**
     * <p>
     * Set the first move flag of this King to either true or false
     * <p>
     * 
     * @param firstMove The new value of the first move flag 
     */
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    /**
     * <p>
     * Checks to see if the this King is attempting a valid move from its current 
     * board position to the new board position.
     * <p>
     * 
     * <p>
     * A King can move one square in any direction (horizontally, vertically, or diagonally) 
     * unless the square is already occupied by a friendly piece. 
     * A King captures by occupying the square on which an enemy piece sits.
     * <p>
     * 
     * 
     * @param newPosition The position that this King is attempting to move from its current position
     * @return true if the move made by this King was valid otherwise return false.
     */
    @Override
    public boolean isValidMove(int newPosition) {
        boolean validMove = false;

        if ((Math.abs((int) ChessBoard.getRank(this.getBoardPosition()) - (int) ChessBoard.getRank(newPosition)) <= 1)
                && (Math.abs(ChessBoard.getFile(this.getBoardPosition()) - ChessBoard.getFile(newPosition)) <= 1)) {
            validMove = true;

            //except if it's a rook and they can castle but we'll worry about that later
            if (ChessBoard.getIndex(newPosition) != null && ChessBoard.getIndex(newPosition).getColor().equals(this.getColor())) {
                validMove = false;
            }
        }

        return validMove;
    }

}
