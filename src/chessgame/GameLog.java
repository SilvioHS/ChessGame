
package chessgame;

import javax.swing.JTextArea;

/**
 *
 * @author Silvio
 */
public class GameLog {
    private ChessGame game;
    static JTextArea textlog;
    private String move;
    private int whiteScore = 0;
    private int blackScore = 0;
    
    
    public GameLog(ChessGame g){
        game = g;
        textlog = game.textarea;
        move = "";
    }
    
    public void setMove(ChessPiece temp){
        
        move = temp.toString() + " took " + ChessBoard.pieces[game.index].toString() + " \n";
        
    }
    
    public void logPieceAction(ChessPiece temp){
        
        
        if (!move.isEmpty()) { 
            //piece is taking another piece
            textlog.append(move);
        } else {
            //piece is just moving
            move = temp.toString() + " moved to " + ChessBoard.getRank(game.index) + ChessBoard.getFile(game.index) + "  \n";
            textlog.append(move);
        }
    }
    
    public void logCurrentTurn(String turn){
        
        textlog.append(turn + "'s turn to move \n");
    }
    
    public void logText(String s){
        textlog.append(s + " \n");
    }
    
    public void logScore(){
        
    }
}
