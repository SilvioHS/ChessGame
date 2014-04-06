/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import java.awt.Image;

/**
 *
 * @author Nick
 */
public class Pawn extends ChessPiece{

    public Pawn(Image image, String color, int boardPosition) {
        super(image, color, boardPosition);
    }

    @Override
    public boolean isValidMove(int boardPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
