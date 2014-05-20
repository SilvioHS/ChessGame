/*
 * Bishop class that extends Chesspiece
 */


package chesspieces;

import chessgame.ChessBoard;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author Kevin Velasco
 */
public class Bishop extends ChessPiece {

     /**
     * <p>
     * Construct Bishop object give an image, color, and the starting board position
     * of the Bishop. 
     * <p>
     * 
     * @param image The image to be associated with this Bishop
     * @param color the color of this Bishop, white or black
     * @param boardPosition The starting position of this Bishop
     */
    public Bishop(Image image, String color, int boardPosition) {
        super(image, color, boardPosition);
        
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setSize(65, 65);
        this.setIcon(new ImageIcon(image));
    }

    /**
     * <p>
     * Checks to see if the this Bishop is attempting a valid move from its current 
     * board position to the new board position.
     * <p>
     * 
     * <p>
     * The bishop has no restrictions in distance for each move, but is limited to diagonal movement. 
     * Bishops, like all other pieces except the knight, cannot jump over other pieces. 
     * A bishop captures by occupying the square on which an enemy piece sits.
     * <p>
     * 
     * 
     * @param newPosition The position that this Bishop is attempting to move from its current position
     * @return true if the move made by this Bishop was valid otherwise return false.
     */
    @Override
    public boolean isValidMove(int newPosition) 
    {
        boolean isValid = false;
        try
        {

        if (ChessBoard.getFile(this.getBoardPosition()) != ChessBoard.getFile(newPosition)) 
        {

            if (this.getBoardPosition() < newPosition) {
                if ((double)(ChessBoard.getFile(this.getBoardPosition()) - ChessBoard.getFile(newPosition))/(double)(ChessBoard.getRank(this.getBoardPosition()) - ChessBoard.getRank(newPosition)) == 1.0 ) 
                {
                    isValid = true;
                    for (int i = this.getBoardPosition() + 7; i < newPosition; i += 7) {
                        if (ChessBoard.getIndex(i) != null) {
                            isValid = false;
                            break;
                        }
                    }
                } 
                else if ((double)(ChessBoard.getFile(this.getBoardPosition()) - ChessBoard.getFile(newPosition))/(double) (ChessBoard.getRank(this.getBoardPosition()) - ChessBoard.getRank(newPosition)) == -1.0 ) 
                {
                    isValid = true;
                    for (int i = this.getBoardPosition() + 9; i < newPosition; i += 9) 
                    {
                        if (ChessBoard.getIndex(i) != null) {
                            isValid = false;
                            break;
                        }
                    }
                }

                if ((ChessBoard.getIndex(newPosition) != null)
                        && (ChessBoard.getIndex(this.getBoardPosition()).getColor().equals(ChessBoard.getIndex(newPosition).getColor()))) {
                    isValid = false;
                }
            } else if (this.getBoardPosition() > newPosition) {
                if ((double)(ChessBoard.getFile(this.getBoardPosition()) - ChessBoard.getFile(newPosition))/(double)(ChessBoard.getRank(this.getBoardPosition()) - ChessBoard.getRank(newPosition)) == 1.0 ) 
                {
                    isValid = true;
                    for (int i = this.getBoardPosition() - 7; i > newPosition; i -= 7) 
                    {
                        if (ChessBoard.getIndex(i) != null) 
                        {
                            isValid = false;
                            break;
                        }
                    }
                } 
                else if ((double)(ChessBoard.getFile(this.getBoardPosition()) - ChessBoard.getFile(newPosition))/(double)(ChessBoard.getRank(this.getBoardPosition()) - ChessBoard.getRank(newPosition)) == -1.0 )
                {
                    isValid = true;
                    for (int i = this.getBoardPosition() - 9; i > newPosition; i -= 9) {
                        if (ChessBoard.getIndex(i) != null) {
                            isValid = false;
                            break;
                        }
                    }
                }

                if (ChessBoard.getIndex(newPosition) != null){
                    if(ChessBoard.getIndex(this.getBoardPosition()).getColor().equals(ChessBoard.getIndex(newPosition).getColor())) {
                        isValid = false;
                    }
                }
            }
        }   
    }
    catch(ArithmeticException ex)
    {
    }
        return isValid;
    }
}