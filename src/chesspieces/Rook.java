/*
 * Rook class that extends Chesspiece
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
public class Rook extends ChessPiece {

    private boolean firstMove = true;
    
    /**
     * <p>
     * Construct Rook object give an image, color, and the starting board position
     * of the Rook. 
     * <p>
     * 
     * @param image The image to be associated with this Rook
     * @param color the color of this Rook, white or black
     * @param boardPosition The starting position of this Rook
     */
    public Rook(Image image, String color, int boardPosition) {
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
     * @return True or false if this Rook has already made it's first move
     */
    public boolean getFirstMove() {
        return firstMove;
    }
    
    /**
     * <p>
     * Set the first move flag of this Rook to either true or false
     * <p>
     * 
     * @param firstMove The new value of the first move flag 
     */
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    /**
     * <p>
     * Checks to see if the this Rook is attempting a valid move from its current 
     * board position to the new board position.
     * <p>
     * 
     * Rook is a allowed to move vertically or horizontally through any number of 
     * of unoccupied squares of the chess board. (e.g. a rook can move through a file or rank)
     * Rook, like all other pieces except the knight, cannot jump over other pieces. 
     * A Rook captures by occupying the square on which an enemy piece sits.
     * <p>
     * 
     * 
     * @param newPosition The position that this Rook is attempting to move from its current position
     * <P>
     * @return true if the move made by this Rook was valid otherwise return false.
     * 
     */
    @Override
    public boolean isValidMove(int newPosition) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean validMove = false;

        //8 to 1 file
        if (ChessBoard.getRank(this.getBoardPosition()) == ChessBoard.getRank(newPosition)) {
            validMove = true;
            //are there any pieces in between
            if (this.getBoardPosition() < newPosition) {
                for (int i = this.getBoardPosition() + 8; i < newPosition; i += 8) {
                    if (ChessBoard.getIndex(i) != null) {
                        validMove = false;
                        break;
                    }
                }
            } else {
                for (int i = this.getBoardPosition() - 8; i > newPosition; i -= 8) {
                    if (ChessBoard.getIndex(i) != null) {
                        validMove = false;
                        break;
                    }
                }
            }
            if ((ChessBoard.getIndex(newPosition) != null) && (ChessBoard.getIndex(this.getBoardPosition()).getColor().equals(ChessBoard.getIndex(newPosition).getColor()))) {
                validMove = false;
            }
        }

        //if same file
        if (ChessBoard.getFile(this.getBoardPosition()) == ChessBoard.getFile(newPosition)) {
            //are there any pieces in between
            validMove = true;

            if (this.getBoardPosition() < newPosition) {
                for (int i = this.getBoardPosition() + 1; i < newPosition; i++) {
                    if (ChessBoard.getIndex(i) != null) {
                        validMove = false;
                        break;
                    }
                }
            } else {
                for (int i = this.getBoardPosition() - 1; i > newPosition; i--) {
                    if (ChessBoard.getIndex(i) != null) {
                        validMove = false;
                        break;
                    }
                }
            }

            if ((ChessBoard.getIndex(newPosition) != null) && (ChessBoard.getIndex(this.getBoardPosition()).getColor().equals(ChessBoard.getIndex(newPosition).getColor()))) {
                validMove = false;
            }

        }

        return validMove;
    }

}
