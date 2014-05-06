/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import static chessgame.ChessGame.SQUAREDIM;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Saitx
 */
public class DragPieceListener implements MouseInputListener
    {
        //***************
        //Set coordinate
        //***************
        boolean inDrag = false;
        int boardx = 0;
        int boardy = 0;
        int ox = 0;
        int oy = 0;
        int relx;
        int rely;
        int lastIndex;
        Point point = new Point(0, 0);
        ChessGame game;
        public DragPieceListener(ChessGame game)
        {
            this.game = game;
        }
       
        boolean contains (int x, int y)
        {
            // Calculate center of draggable chess piece.
            int cox = ox + SQUAREDIM / 2;
            int coy = oy + SQUAREDIM / 2;
            return (cox - x) * (cox - x) + (coy - y) * (coy - y) < SQUAREDIM  * SQUAREDIM ;
        }
        public void mousePressed(MouseEvent e){
            ChessPiece tmpPiece = (ChessPiece)e.getSource();
            point = SwingUtilities.convertPoint(tmpPiece, e.getPoint(), tmpPiece.getParent());
            
            int x = point.x;
            int y = point.y;
            ox = x/SQUAREDIM*SQUAREDIM;
            oy = y/SQUAREDIM*SQUAREDIM;
            game.index = ox/SQUAREDIM +oy/SQUAREDIM*8;
            lastIndex = game.index;

            if (contains (x, y) && tmpPiece.getColor().equals(game.colorsTurn)){
                relx = x - ox;
                rely = y - oy;
                inDrag = true;
              }
            
        }
        public void mouseDragged(MouseEvent e)
        {
             ChessPiece tmpPiece = (ChessPiece)e.getSource();
            Point newPoint = SwingUtilities.convertPoint(tmpPiece, e.getPoint(), tmpPiece.getParent());
            point = newPoint;
            if (inDrag){

                int tmpox = newPoint.x - relx;
                int tmpoy = newPoint.y - rely;
                int cox = tmpox + SQUAREDIM/2;
                int coy = tmpoy + SQUAREDIM/2;
                game.index = cox/SQUAREDIM +coy/SQUAREDIM*8; 

                if (tmpox > boardx &&tmpoy > boardy && tmpox + SQUAREDIM < boardx + game.BOARDDIM + SQUAREDIM && tmpoy + SQUAREDIM < boardy + game.BOARDDIM + SQUAREDIM){
                    ox = tmpox;
                    oy = tmpoy;              
                    game.setChessLocation(tmpPiece,ox,oy);
                }
            }
        }
        public void mouseReleased(MouseEvent e)
        {
            ChessPiece tmpPiece = (ChessPiece)e.getSource();
            if (inDrag)
                inDrag = false;
            int cox = ox + SQUAREDIM/2;
            int coy = oy + SQUAREDIM/2;
            cox = cox/SQUAREDIM *SQUAREDIM + SQUAREDIM/2 - 65/2;
            coy = coy/SQUAREDIM *SQUAREDIM + SQUAREDIM/2 - 65/2;
            
            
            
            if(tmpPiece.isValidMove(game.index))
            {
                String move = "";
                //take piece need to check for pawn
                if(ChessBoard.pieces[game.index] != null){
                    
                    move = tmpPiece.toString() + " took " + ChessBoard.pieces[game.index].toString() + " \n";
                    
                    //ChessGame.textarea.
                    game.chessboard.remove(ChessBoard.pieces[game.index]);
                    ChessBoard.pieces[game.index] = null;              
                }
                if(!move.isEmpty()){
                    ChessGame.textarea.append(move);
                }else{
                    move = tmpPiece.toString() + " moved to " + ChessBoard.getRank(game.index) + ChessBoard.getFile(game.index) + " \n";
                    ChessGame.textarea.append(move);
                }
                
                game.setChessLocation(tmpPiece,game.coordinate.getPieceX(game.index),game.coordinate.getPieceY(game.index));
                tmpPiece.setBoardPosition(game.index);
                ChessBoard.pieces[game.index] = tmpPiece;
                ChessBoard.pieces[lastIndex] = null;
                
                if(game.colorsTurn.equals("white"))
                {
                    game.colorsTurn = "black";
                    ChessGame.textarea.append("Black's turn to move \n");
                }
                else
                {
                    game.colorsTurn = "white";
                    ChessGame.textarea.append("White's turn to move \n");
                }
                
            }    
            else
                game.setChessLocation(tmpPiece,game.coordinate.getPieceX(lastIndex),game.coordinate.getPieceY(lastIndex));

           //System.out.println("lastIndex: "+ lastIndex +ChessBoard.pieces[lastIndex]);
           //System.out.println("index: "+ index +ChessBoard.pieces[index]);
        }
        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
        }
    }
