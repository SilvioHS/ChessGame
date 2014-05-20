/*
 * Knight class that extends Chesspiece
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
public class Knight extends ChessPiece {

   /**
     * <p>
     * Construct Knight object give an image, color, and the starting board position
     * of the Knight. 
     * <p>
     * 
     * @param image The image to be associated with this Knight
     * @param color the color of this Knight, white or black
     * @param boardPosition The starting position of this Knight
     */
    public Knight(Image image, String color, int boardPosition) {
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
     * The knight move is unusual among chess pieces. When it moves, it can move 
     * to a square that is two squares horizontally and one square vertically, or 
     * two squares vertically and one square horizontally. 
     * The complete move therefore looks like the letter L. 
     * <p>
     * 
     * <p>
     * Unlike all other chess pieces the knight can jump over all other pieces (of either color) 
     * to its destination square.It captures an enemy piece by replacing it on its square
     * <p>
     * 
     * 
     * @param newPosition The position that this Knight is attempting to move from its current position
     * @return true if the move made by this Knight was valid otherwise return false.
     */
    @Override
    public boolean isValidMove(int newPosition) {
        boolean validMove = false;

        if ((Math.abs((int) ChessBoard.getRank(this.getBoardPosition()) - (int) ChessBoard.getRank(newPosition)) == 1)
                && (Math.abs(ChessBoard.getFile(this.getBoardPosition()) - ChessBoard.getFile(newPosition)) == 2)) {
            validMove = true;
        } else if ((Math.abs((int) ChessBoard.getRank(this.getBoardPosition()) - (int) ChessBoard.getRank(newPosition)) == 2)
                && (Math.abs(ChessBoard.getFile(this.getBoardPosition()) - ChessBoard.getFile(newPosition)) == 1)) {
            validMove = true;
        }

        if (ChessBoard.getIndex(newPosition) != null && ChessBoard.getIndex(newPosition).getColor().equals(this.getColor())) {
            validMove = false;
        }

        return validMove;
    }

}
