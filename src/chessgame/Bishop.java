/*
 * Bishop class that extends Chesspiece
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
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setSize(65, 65);
        this.setIcon(new ImageIcon(image));
    }

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