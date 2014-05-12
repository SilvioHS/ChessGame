/*
 * Knight class that extends Chesspiece
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
 * @author Nick Ottrando
 */
public class Knight extends ChessPiece {

    public Knight(Image image, String color, int boardPosition) {
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
