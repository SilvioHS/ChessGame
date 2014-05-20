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

    /**
     * <p>
     * Constructs a SpecialRules Object. SpecialRules contains methods that process
     * special moves in chess, such as promotion and castling. It also contains a 
     * method that reverts an illegal move. 
     * <p>
     * 
     * @param game The ChessGane to which the Special Rules will belong to
     * @param gamelog The GameLog to record the moves made by SpecialRules 
     */
    public SpecialRules(ChessGame game, GameLog gamelog) {
        this.game = game;
        this.gamelog = gamelog;
    }

    /**
     * <p>
     * Castling consists of moving the king two squares towards a rook, then placing the rook on the other side of the king, adjacent to it.
     * Castling is only permissible if all of the following conditions hold
     * <p>
     * 
     * <p>
     * The King and Rook must not have previously moved, (e.g It is their first move)
     * There must be no pieces between the King and the Rook
     * <p>
     * 
     * 
     * @param tmpPiece The King ChessPiece that is castling 
     * @param index the new position of the King after castling
     * @param rookOld the position of the Rook before castling
     * @param rookNew the new position of the Rook after castling
     * @param kingPos the position of the King before castling
     * @param color The color of the Player who's turn it will be after the castling
     */
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

    /**
     * A Pawn that reaches its eighth File is immediately changed into a Queen of the same color.
     * The new piece replaces the pawn on the same square, as part of the same move. 
     * Every pawn that reaches its eighth File must be promoted. 
     * 
     * @param color The color of the Pawn to be promoted
     * @param queenImage The image of the Queen to be used to promote the Pawn
     */
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

    /**
     * <p>
     * If a player attempts them to put themselves into check, which will result in an 
     * illegal move. That player will have their move retracted and must make a legal move that 
     * does not result with them in check. 
     * <p>
     * 
     * @param tmpPiece The ChessPiece that was attempting to make an illegal move
     * @param tmpPiece2 tmpPiece2,ChessPiece that was attempted to be taken resulting in an illegal move 
     * @param lastIndex The ChessPiece starting position at the start of the move, where it gets put back to
     * @param kingPos The position of the Player's king, to be reverted due to illegal move
     * @param color The player of the Player make the illegal move
     */
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
