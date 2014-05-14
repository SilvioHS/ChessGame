/*
 * class that handles dragging of pieces in chess game.
 */


package chessgame;

import static chessgame.ChessGame.SQUAREDIM;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Kevin Velasco & Qilin Fu
 */
public class DragPieceListener implements MouseInputListener {

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
    GameLog gamelog;
    
    Image queen_w, queen_b;

    public DragPieceListener(ChessGame game) {
        this.game = game;
        gamelog = new GameLog(game);
        try {
            queen_w = ImageIO.read(new File("src/image/queen_white.png"));
            queen_b = ImageIO.read(new File("src/image/queen_black.png"));
        } catch (IOException ex) {
            Logger.getLogger(DragPieceListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    boolean contains(int x, int y) {
        // Calculate center of draggable chess piece.
        int cox = ox + SQUAREDIM / 2;
        int coy = oy + SQUAREDIM / 2;
        return (cox - x) * (cox - x) + (coy - y) * (coy - y) < SQUAREDIM * SQUAREDIM;
    }

    public void mousePressed(MouseEvent e) {
        ChessPiece tmpPiece = (ChessPiece) e.getSource();
        point = SwingUtilities.convertPoint(tmpPiece, e.getPoint(), tmpPiece.getParent());

        int x = point.x;
        int y = point.y;
        ox = x / SQUAREDIM * SQUAREDIM;
        oy = y / SQUAREDIM * SQUAREDIM;
        game.index = ox / SQUAREDIM + oy / SQUAREDIM * 8;
        lastIndex = game.index;

        if (contains(x, y) && tmpPiece.getColor().equals(game.colorsTurn)) {
            relx = x - ox;
            rely = y - oy;
            inDrag = true;
            game.chessboard.moveToFront(tmpPiece);
        }

    }

    public void mouseDragged(MouseEvent e) {
        ChessPiece tmpPiece = (ChessPiece) e.getSource();
        Point newPoint = SwingUtilities.convertPoint(tmpPiece, e.getPoint(), tmpPiece.getParent());
        point = newPoint;
        if (inDrag) {

            int tmpox = newPoint.x - relx;
            int tmpoy = newPoint.y - rely;
            int cox = tmpox + SQUAREDIM / 2;
            int coy = tmpoy + SQUAREDIM / 2;
            game.index = cox / SQUAREDIM + coy / SQUAREDIM * 8;

            if (tmpox > boardx && tmpoy > boardy && tmpox + SQUAREDIM < boardx + game.BOARDDIM + SQUAREDIM && tmpoy + SQUAREDIM < boardy + game.BOARDDIM + SQUAREDIM) {
                ox = tmpox;
                oy = tmpoy;
                game.setChessLocation(tmpPiece, ox, oy);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
                    
        ChessPiece tmpPiece = (ChessPiece) e.getSource();
        ChessPiece tmpPiece2 = null;
        //keep track of the king's position 
        int wKingPos = game.whiteplayer.getPositionOfKing();
        int bKingPos = game.blackplayer.getPositionOfKing();
        if (inDrag) {
            inDrag = false;
        }
        int cox = ox + SQUAREDIM / 2;
        int coy = oy + SQUAREDIM / 2;
        cox = cox / SQUAREDIM * SQUAREDIM + SQUAREDIM / 2 - 65 / 2;
        coy = coy / SQUAREDIM * SQUAREDIM + SQUAREDIM / 2 - 65 / 2;

        if (tmpPiece.isValidMove(game.index)) {

            //update the king's position 
            if (tmpPiece instanceof King && tmpPiece.color.equals("black")) {
                game.blackplayer.setPositionOfKing(game.index);
            } else if (tmpPiece instanceof King && tmpPiece.color.equals("white")) {
                game.whiteplayer.setPositionOfKing(game.index);
            }

            
            
            //before removing a piece from the board with store it in temp2
            //and place it in index 64. taking temp2 cannot put the attacking 
            //player in check. Illegal move 
            if (ChessBoard.pieces[game.index] != null) {
                tmpPiece2 = ChessBoard.pieces[game.index];
                gamelog.setMove(tmpPiece);
                //game.chessboard.remove(ChessBoard.pieces[game.index]);
                ChessBoard.pieces[game.index] = null;
                tmpPiece2.setBoardPosition(64);
                ChessBoard.pieces[64] = tmpPiece2;
                

            }

            //write piece movement 
            gamelog.logPieceAction(tmpPiece);
            
            //update moving chess piece location 
            game.setChessLocation(tmpPiece, game.coordinate.getPieceX(game.index), game.coordinate.getPieceY(game.index));
            tmpPiece.setBoardPosition(game.index);
            ChessBoard.pieces[game.index] = tmpPiece;
            ChessBoard.pieces[lastIndex] = null;

            //check to see if player performed an illegal move 
            if (game.blackplayer.isChecked() && game.colorsTurn.equals("black")) {
                gamelog.logText("Illegal Move, You're in check. Pick another move");
                //reset last move that the player made and reset king's position 
                game.setChessLocation(tmpPiece, game.coordinate.getPieceX(lastIndex), game.coordinate.getPieceY(lastIndex));
                tmpPiece.setBoardPosition(lastIndex);
                game.blackplayer.setPositionOfKing(bKingPos);
                ChessBoard.pieces[lastIndex] = tmpPiece;

                //if player was trying to take a piece put it back 
                if (tmpPiece2 != null) {
                    tmpPiece2.setBoardPosition(game.index);
                    ChessBoard.pieces[game.index] = tmpPiece2;
                    ChessBoard.pieces[64] = null;
                } else {
                    ChessBoard.pieces[game.index] = null;
                }
                //exit function 
                return;

             //repeat for white 
            } else if (game.whiteplayer.isChecked() && game.colorsTurn.equals("white")) {
                
                gamelog.logText("Illegal Move, You're in check. Pick another move");
                
                game.setChessLocation(tmpPiece, game.coordinate.getPieceX(lastIndex), game.coordinate.getPieceY(lastIndex));
                tmpPiece.setBoardPosition(lastIndex);
                game.whiteplayer.setPositionOfKing(wKingPos);
                ChessBoard.pieces[lastIndex] = tmpPiece;
                
                if (tmpPiece2 != null) {                 
                    tmpPiece2.setBoardPosition(game.index);
                    ChessBoard.pieces[game.index] = tmpPiece2;
                    ChessBoard.pieces[64] = null;
                } else {
                    ChessBoard.pieces[game.index] = null;
                }
                /*
                if(game.whiteplayer.isCheckMated()){
                    System.out.println("game over");
                }
                */
                return;

            }
            
            
            //pawn promotion
            
            if (tmpPiece instanceof Pawn && tmpPiece.color.equals("white")&&
                    ChessBoard.getFile(game.index) == 1) {
                    //tmpPiece = null;
                    game.chessboard.remove(ChessBoard.pieces[game.index]);
                    System.out.println("promoted white pawn");
                    Queen queen = new Queen(queen_w, "white", game.index);
                    ChessBoard.pieces[game.index] = queen;
                    game.chessboard.add(queen,JLayeredPane.DEFAULT_LAYER);
                    game.setChessLocation(queen, game.coordinate.getPieceX(game.index), game.coordinate.getPieceY(game.index));
            }else if (tmpPiece instanceof Pawn && tmpPiece.color.equals("black")&&
                    ChessBoard.getFile(game.index) == 8) {
                    //tmpPiece = null;
                    game.chessboard.remove(ChessBoard.pieces[game.index]);
                    System.out.println("promoted black pawn");
                    Queen queen = new Queen(queen_b, "black", game.index);
                    ChessBoard.pieces[game.index] = queen;
                    game.chessboard.add(queen,JLayeredPane.DEFAULT_LAYER);
                    game.setChessLocation(queen, game.coordinate.getPieceX(game.index), game.coordinate.getPieceY(game.index));
            }
            
            
            //if taking temp2 was legal remove it from the board 
            if (tmpPiece2 != null) {
                    game.chessboard.remove(ChessBoard.pieces[64]);
                    ChessBoard.pieces[64] = null;
                    //String s = "Removed piece at index: " + game.index;
                    //gamelog.logText(s);
                }

            if (game.colorsTurn.equals("white")) {
                if(game.blackplayer.isCheckMated()){
                    //System.out.println("black has been checkmated");
                }
                
                game.colorsTurn = "black";
                gamelog.logCurrentTurn("black");
                game.blackplayer.isTurn = true;
                game.whiteplayer.isTurn = false;
                            
                //ChessGame.textarea.append("Black's turn to move \n");
            } else if (game.colorsTurn.equals("black")) {
                        
                if(game.whiteplayer.isCheckMated()){
                    //System.out.println("white has been checkmated");
                }
                game.colorsTurn = "white";
                gamelog.logCurrentTurn("white");
                game.blackplayer.isTurn = true;
                game.whiteplayer.isTurn = false;
                //ChessGame.textarea.append("White's turn to move \n");
            }

        } else {
            game.setChessLocation(tmpPiece, game.coordinate.getPieceX(lastIndex), game.coordinate.getPieceY(lastIndex));
        }

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
