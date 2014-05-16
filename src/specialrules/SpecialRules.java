/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialrules;

import chessgame.ChessBoard;
import chesspieces.ChessPiece;
import chessgame.ChessGame;
import chesspieces.Queen;
import java.awt.Image;
import javax.swing.JLayeredPane;
import menu.GameLog;
import mousemovement.DragPieceListener;

/**
 *
 * @author Kevin Velasco
 */
public class SpecialRules {

    private ChessGame game;
    private GameLog gamelog;

    public SpecialRules(ChessGame game, GameLog gamelog) {
        this.game = game;
        this.gamelog = gamelog;
    }

    public void castle(ChessPiece tmpPiece, int index, int rookOld, int rookNew, int kingPos, String color) {

        game.setChessLocation(ChessBoard.pieces[rookOld], game.getCoordinate().getPieceX(rookNew), game.getCoordinate().getPieceY(rookNew));
        ChessBoard.pieces[rookOld].setBoardPosition(rookNew);
        ChessBoard.pieces[rookNew] = ChessBoard.pieces[rookOld];
        ChessBoard.pieces[rookOld] = null;

        game.setChessLocation(tmpPiece, game.getCoordinate().getPieceX(index), game.getCoordinate().getPieceY(index));
        tmpPiece.setBoardPosition(index);
        ChessBoard.pieces[index] = tmpPiece;
        ChessBoard.pieces[kingPos] = null;

        game.setTurn(color);
        gamelog.logCurrentTurn(color);
        if (color.equals("white")) {
            game.getBlackPlayer().setIsTurn(false);
            game.getWhitePlayer().setIsTurn(true);
            game.getBlackPlayer().setPositionOfKing(index);
        } else {
            game.getBlackPlayer().setIsTurn(true);
            game.getWhitePlayer().setIsTurn(false);
            game.getWhitePlayer().setPositionOfKing(index);
        }
    }

    public void promote(String color, Image queenImage) {

        game.getChessBoard().remove(ChessBoard.pieces[game.getIndex()]);
        System.out.println("promoted " + color + " pawn");
        Queen queen = new Queen(queenImage, color, game.getIndex());
        ChessBoard.pieces[game.getIndex()] = queen;
        game.getChessBoard().add(queen, JLayeredPane.DEFAULT_LAYER);
        game.setChessLocation(queen, game.getCoordinate().getPieceX(game.getIndex()), game.getCoordinate().getPieceY(game.getIndex()));
        DragPieceListener newQueen = new DragPieceListener(game);
        queen.addMouseListener(newQueen);
        queen.addMouseMotionListener(newQueen);

    }

    public void revertCheck(ChessPiece tmpPiece, ChessPiece tmpPiece2, int lastIndex, int kingPos, String color) {
        gamelog.logText("Illegal Move, You're in check. Pick another move");
        //reset last move that the player made and reset king's position 
        game.setChessLocation(tmpPiece, game.getCoordinate().getPieceX(lastIndex), game.getCoordinate().getPieceY(lastIndex));
        tmpPiece.setBoardPosition(lastIndex);
        if(color.equals("black")){
            game.getBlackPlayer().setPositionOfKing(kingPos);
        }else{
            game.getWhitePlayer().setPositionOfKing(kingPos);
        }
        
        ChessBoard.pieces[lastIndex] = tmpPiece;

        //if player was trying to take a piece put it back 
        if (tmpPiece2 != null) {
            tmpPiece2.setBoardPosition(game.getIndex());
            ChessBoard.pieces[game.getIndex()] = tmpPiece2;
            ChessBoard.pieces[64] = null;
        } else {
            ChessBoard.pieces[game.getIndex()] = null;
        }
    }
}
