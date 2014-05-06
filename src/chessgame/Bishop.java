/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author Kevin Velasco
 */
public class Bishop extends ChessPiece {

    public Bishop(Image image, String color, int boardPosition) {
        super(image, color, boardPosition);
        
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setContentAreaFilled(false);
<<<<<<< HEAD
        this.setBorderPainted(false);
        this.setOpaque(false);
=======
	this.setBorderPainted(false);
	this.setOpaque(false);
>>>>>>> f4e841a1bbe0cc9d194c63e2f80910dadc665b8b
        this.setSize(65, 65);
        this.setIcon(new ImageIcon(image));
    }

    @Override
    public boolean isValidMove(int newPosition) {
        boolean isValid = false;

<<<<<<< HEAD
        if (ChessBoard.getFile(this.getBoardPosition()) != ChessBoard.getFile(newPosition)) {

=======
        if (ChessBoard.getRank(this.getBoardPosition()) != ChessBoard.getRank(newPosition)) {
>>>>>>> f4e841a1bbe0cc9d194c63e2f80910dadc665b8b
            if (this.getBoardPosition() < newPosition) {
                if (Math.abs(this.getBoardPosition() - newPosition) % 7 == 0) {
                    isValid = true;
                    for (int i = this.getBoardPosition() + 7; i < newPosition; i += 7) {
                        if (ChessBoard.getIndex(i) != null) {
                            isValid = false;
                            break;
                        }
                    }
                } else if (Math.abs(this.getBoardPosition() - newPosition) % 9 == 0) {
                    isValid = true;
                    for (int i = this.getBoardPosition() + 9; i < newPosition; i += 9) {
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
                if (Math.abs(this.getBoardPosition() - newPosition) % 7 == 0) {
                    isValid = true;
                    for (int i = this.getBoardPosition() - 7; i > newPosition; i -= 7) {
                        if (ChessBoard.getIndex(i) != null) {
                            isValid = false;
                            break;
                        }
                    }
                } else if (Math.abs(this.getBoardPosition() - newPosition) % 9 == 0) {
                    isValid = true;
                    for (int i = this.getBoardPosition() - 9; i > newPosition; i -= 9) {
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
            }
<<<<<<< HEAD

=======
>>>>>>> f4e841a1bbe0cc9d194c63e2f80910dadc665b8b
        }

        return isValid;
    }
<<<<<<< HEAD
    
=======

>>>>>>> f4e841a1bbe0cc9d194c63e2f80910dadc665b8b
}
