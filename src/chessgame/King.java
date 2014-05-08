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
 * @author Nick
 */
public class King extends ChessPiece {

    private boolean firstMove = true;

    public King(Image image, String color, int boardPosition) {
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
