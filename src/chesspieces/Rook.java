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

    public Rook(Image image, String color, int boardPosition) {
        super(image, color, boardPosition);

        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setSize(65, 65);
        this.setIcon(new ImageIcon(image));
    }

    public boolean getFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    //rook valid move
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
