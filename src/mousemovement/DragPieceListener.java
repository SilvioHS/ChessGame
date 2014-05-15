/*
 * class that handles dragging of pieces in chess game.
 */


package mousemovement;

import chessgame.ChessBoard;
import chessgame.ChessGame;
import menu.GameLog;
import chesspieces.ChessPiece;
import chesspieces.King;
import chesspieces.Pawn;
import chesspieces.Queen;
import chesspieces.Rook;
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
import specialrules.SpecialRules;

/**
 *
 * @author Kevin Velasco & Qilin Fu
 */
public class DragPieceListener implements MouseInputListener {

    //***************
    //Set coordinate
    //***************
    private boolean inDrag = false;
    private int boardx = 0;
    private int boardy = 0;
    private int ox = 0;
    private int oy = 0;
    private int relx;
    private int rely;
    private int lastIndex;
    private Point point = new Point(0, 0);
    private ChessGame game;
    private GameLog gamelog;
    private SpecialRules rules;
    
    private Image queen_w, queen_b;

    public DragPieceListener(ChessGame game) {
        this.game = game;
        gamelog = new GameLog(game);
        rules = new SpecialRules(game, gamelog);
        
        try {
            queen_w = ImageIO.read(new File("src/image/queen_white.png"));
            queen_b = ImageIO.read(new File("src/image/queen_black.png"));
        } catch (IOException ex) {
            Logger.getLogger(DragPieceListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    boolean contains(int x, int y) {
        // Calculate center of draggable chess piece.
        int cox = ox + game.getSqaureDim() / 2;
        int coy = oy + game.getSqaureDim() / 2;
        return (cox - x) * (cox - x) + (coy - y) * (coy - y) < game.getSqaureDim()* game.getSqaureDim();
    }

    public void mousePressed(MouseEvent e) {
        ChessPiece tmpPiece = (ChessPiece) e.getSource();
        point = SwingUtilities.convertPoint(tmpPiece, e.getPoint(), tmpPiece.getParent());

        int x = point.x;
        int y = point.y;
        ox = x / game.getSqaureDim() * game.getSqaureDim();
        oy = y / game.getSqaureDim() * game.getSqaureDim();
        game.setIndex(ox / game.getSqaureDim() + oy / game.getSqaureDim() * 8);
        lastIndex = game.getIndex();

        if (contains(x, y) && tmpPiece.getColor().equals(game.getTurn())) {
            relx = x - ox;
            rely = y - oy;
            inDrag = true;
            game.getChessBoard().moveToFront(tmpPiece);
        }

    }

    public void mouseDragged(MouseEvent e) {
        ChessPiece tmpPiece = (ChessPiece) e.getSource();
        Point newPoint = SwingUtilities.convertPoint(tmpPiece, e.getPoint(), tmpPiece.getParent());
        point = newPoint;
        if (inDrag) {

            int tmpox = newPoint.x - relx;
            int tmpoy = newPoint.y - rely;
            int cox = tmpox + game.getSqaureDim() / 2;
            int coy = tmpoy + game.getSqaureDim() / 2;
            game.setIndex(cox / game.getSqaureDim() + coy / game.getSqaureDim() * 8);

            if (tmpox > boardx && tmpoy > boardy && tmpox + game.getSqaureDim() < boardx + game.getBoardDim() + game.getSqaureDim() && tmpoy + game.getSqaureDim() < boardy + game.getBoardDim()  + game.getSqaureDim()) {
                ox = tmpox;
                oy = tmpoy;
                game.setChessLocation(tmpPiece, ox, oy);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
                    
        ChessPiece tmpPiece = (ChessPiece) e.getSource();
        ChessPiece tmpPiece2 = null;
        //keep track of the king's position 
        int wKingPos = game.getWhitePlayer().getPositionOfKing();
        int bKingPos = game.getBlackPlayer().getPositionOfKing();
        if (inDrag) {
            inDrag = false;
        }
        int cox = ox + game.getSqaureDim() / 2;
        int coy = oy + game.getSqaureDim() / 2;
        cox = cox / game.getSqaureDim() * game.getSqaureDim() + game.getSqaureDim() / 2 - 65 / 2;
        coy = coy / game.getSqaureDim() * game.getSqaureDim() + game.getSqaureDim() / 2 - 65 / 2;

        if (tmpPiece.isValidMove(game.getIndex())) {

            //update the king's position 
            if (tmpPiece instanceof King && tmpPiece.getColor().equals("black")) {
                game.getBlackPlayer().setPositionOfKing(game.getIndex());
            } else if (tmpPiece instanceof King && tmpPiece.getColor().equals("white")) {
                game.getWhitePlayer().setPositionOfKing(game.getIndex());
            }

            
            
            //before removing a piece from the board with store it in temp2
            //and place it in index 64. taking temp2 cannot put the attacking 
            //player in check. Illegal move 
            if (ChessBoard.pieces[game.getIndex()] != null) {
                tmpPiece2 = ChessBoard.pieces[game.getIndex()];
                gamelog.setMove(tmpPiece);
                //game.chessboard.remove(ChessBoard.pieces[game.index]);
                ChessBoard.pieces[game.getIndex()] = null;
                tmpPiece2.setBoardPosition(64);
                ChessBoard.pieces[64] = tmpPiece2;
                

            }

            //write piece movement 
            gamelog.logPieceAction(tmpPiece);
            
            //update moving chess piece location 
            game.setChessLocation(tmpPiece, game.getCoordinate().getPieceX(game.getIndex()), game.getCoordinate().getPieceY(game.getIndex()));
            tmpPiece.setBoardPosition(game.getIndex());
            ChessBoard.pieces[game.getIndex()] = tmpPiece;
            ChessBoard.pieces[lastIndex] = null;

            //check to see if player performed an illegal move 
            if (game.getBlackPlayer().isChecked() && game.getTurn().equals("black")) {
                rules.revertCheck(tmpPiece, tmpPiece2, lastIndex, bKingPos);
                //exit function 
                return;
             //repeat for white 
            } else if (game.getWhitePlayer().isChecked() && game.getTurn().equals("white")) {
                rules.revertCheck(tmpPiece, tmpPiece2, lastIndex, wKingPos);
                return;

            }
            

            if(tmpPiece instanceof Pawn && ((Pawn)tmpPiece).getFirstMove()){
                ((Pawn)tmpPiece).setFirstMove(false);
            }else if(tmpPiece instanceof Rook && ((Rook)tmpPiece).getFirstMove()){
                 ((Rook)tmpPiece).setFirstMove(false);
            }else if (tmpPiece instanceof King && ((King)tmpPiece).getFirstMove()){
                ((King)tmpPiece).setFirstMove(false);
            }
            

   
            //pawn promotion
            
            if (tmpPiece instanceof Pawn && tmpPiece.getColor().equals("white")&&
                    ChessBoard.getFile(game.getIndex()) == 1) {
                    tmpPiece = null;
                    rules.promote("white", queen_w);
            }else if (tmpPiece instanceof Pawn && tmpPiece.getColor().equals("black")&&
                    ChessBoard.getFile(game.getIndex()) == 8) {
                    tmpPiece = null;
                    rules.promote("black", queen_b);
            }
            
         
            //if taking temp2 was legal remove it from the board 
            if (tmpPiece2 != null) {
                    game.getChessBoard().remove(ChessBoard.pieces[64]);
                    ChessBoard.pieces[64] = null;
                    //String s = "Removed piece at index: " + game.index;
                    //gamelog.logText(s);
                }

            if (game.getTurn().equals("white")) {
                if(game.getBlackPlayer().isCheckMated()){
                    //System.out.println("black has been checkmated");
                }
                
                game.setTurn("black");
                gamelog.logCurrentTurn("black");
                game.getBlackPlayer().setIsTurn(true);
                game.getWhitePlayer().setIsTurn(false);
                return;
                            
                //ChessGame.textarea.append("Black's turn to move \n");
            } else if (game.getTurn().equals("black")) {
                        
                if(game.getWhitePlayer().isCheckMated()){
                    //System.out.println("white has been checkmated");
                }
                game.setTurn("white");
                gamelog.logCurrentTurn("white");
                game.getBlackPlayer().setIsTurn(false);
                game.getWhitePlayer().setIsTurn(true);
                return;
                //ChessGame.textarea.append("White's turn to move \n");
            }

        } else {
            game.setChessLocation(tmpPiece, game.getCoordinate().getPieceX(lastIndex), game.getCoordinate().getPieceY(lastIndex));
        }
        
        if (tmpPiece instanceof King && ((King) tmpPiece).getFirstMove() == true) {
            if (tmpPiece.getColor().equals("black") && (game.getIndex() == 58 || game.getIndex() == 62)) {
                if (((ChessBoard.pieces[56] instanceof Rook && ((Rook) ChessBoard.pieces[56]).getFirstMove() == true)) && (ChessBoard.pieces[56].isValidMove(game.getIndex()+1))) {
                    rules.castle(tmpPiece, game.getIndex(), 56, 59, bKingPos, "white");
                }else if (((ChessBoard.pieces[63] instanceof Rook && ((Rook) ChessBoard.pieces[63]).getFirstMove() == true)) && (ChessBoard.pieces[63].isValidMove(game.getIndex()-1))){
                    rules.castle(tmpPiece, game.getIndex(), 63, 61, bKingPos, "white");
                }else{
                    game.setChessLocation(tmpPiece, game.getCoordinate().getPieceX(lastIndex), game.getCoordinate().getPieceY(lastIndex));
                }
            } else if (tmpPiece.getColor().equals("white") && (game.getIndex() == 2 || game.getIndex() == 6)) {
                if (((ChessBoard.pieces[0] instanceof Rook && ((Rook) ChessBoard.pieces[0]).getFirstMove() == true)) && (ChessBoard.pieces[0].isValidMove(game.getIndex()+1))) {
                    rules.castle(tmpPiece, game.getIndex(), 0, 3, wKingPos, "black");
                }else if (((ChessBoard.pieces[7] instanceof Rook && ((Rook) ChessBoard.pieces[7]).getFirstMove() == true)) && (ChessBoard.pieces[7].isValidMove(game.getIndex()-1))){
                    rules.castle(tmpPiece, game.getIndex(), 7,5, wKingPos, "black");
                }else{
                    game.setChessLocation(tmpPiece, game.getCoordinate().getPieceX(lastIndex), game.getCoordinate().getPieceY(lastIndex));
                }
            }
        } else {
            game.setChessLocation(tmpPiece, game.getCoordinate().getPieceX(lastIndex), game.getCoordinate().getPieceY(lastIndex));
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
