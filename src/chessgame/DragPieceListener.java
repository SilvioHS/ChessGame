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

    public DragPieceListener(ChessGame game) {
        this.game = game;
        gamelog = new GameLog(game);
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
                tmpPiece2.setBoardPosition(64);
                gamelog.setMove(tmpPiece);
                //game.chessboard.remove(ChessBoard.pieces[game.index]);
                ChessBoard.pieces[game.index] = null;
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
                    //game.setChessLocation(tmpPiece2, game.coordinate.getPieceX(game.index), game.coordinate.getPieceY(game.index));
                    ChessBoard.pieces[game.index] = tmpPiece2;
                    tmpPiece2.setBoardPosition(game.index);
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
                //ChessBoard.pieces[game.index] = null;
                if (tmpPiece2 != null) {
                    //game.setChessLocation(tmpPiece2, game.coordinate.getPieceX(game.index), game.coordinate.getPieceY(game.index));
                    ChessBoard.pieces[game.index] = tmpPiece2;
                    tmpPiece2.setBoardPosition(game.index);
                    ChessBoard.pieces[64] = null;
                } else {
                    ChessBoard.pieces[game.index] = null;
                }

                return;

            }

            if (game.colorsTurn.equals("white")) {
                game.colorsTurn = "black";
                gamelog.logCurrentTurn("Black");
                game.blackplayer.isTurn = true;
                game.whiteplayer.isTurn = false;
                //if taking temp2 was legal remove it from the board 
                if (tmpPiece2 != null) {
                    game.chessboard.remove(tmpPiece2);
                    ChessBoard.pieces[game.index] = null;
                    ChessBoard.pieces[64] = null;
                }

                //ChessGame.textarea.append("Black's turn to move \n");
            } else if (game.colorsTurn.equals("black")) {
                game.colorsTurn = "white";
                gamelog.logCurrentTurn("White");
                game.blackplayer.isTurn = true;
                game.whiteplayer.isTurn = false;
                if (tmpPiece2 != null) {
                    game.chessboard.remove(tmpPiece2);
                    ChessBoard.pieces[game.index] = null;
                    ChessBoard.pieces[64] = null;
                }

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
