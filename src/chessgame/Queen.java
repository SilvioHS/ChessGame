/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Nick
 */
public class Queen extends ChessPiece{
    
    private ChessPiece bishopQ;
    private ChessPiece rookQ;

    public Queen(Image image, String color, int boardPosition) {
        super(image, color, boardPosition);

        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setSize(65, 65);
        this.setIcon(new ImageIcon(image));
        
        bishopQ = new Bishop(image, "x", this.boardPosition);
        rookQ = new Rook(image, "x", this.boardPosition);
    }

    @Override
    public boolean isValidMove(int newPosition) {
               
        return ((bishopQ.isValidMove(newPosition)) || (rookQ.isValidMove(newPosition)));
    }
    
}
