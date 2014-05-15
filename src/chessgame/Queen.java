/*
 * queen class that extends Chesspiece
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
 * @authors Kevin Velasco & Nick Ottrando
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
        
        bishopQ = new Bishop(image, color, this.boardPosition);
        rookQ = new Rook(image, color, this.boardPosition);
    }

    @Override
    public boolean isValidMove(int newPosition) {
        
        bishopQ.setBoardPosition(this.getBoardPosition());
        rookQ.setBoardPosition(this.getBoardPosition()); 
               
        return ((bishopQ.isValidMove(newPosition)) || (rookQ.isValidMove(newPosition)));
    }
    
}
