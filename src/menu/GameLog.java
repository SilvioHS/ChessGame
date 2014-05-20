package menu;

import chessgame.ChessBoard;
import chessgame.ChessGame;
import chesspieces.ChessPiece;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Silvio
 */
public class GameLog {
    private final ChessGame game;
    private static final JTextArea textlog = new JTextArea();
    private static final JScrollPane scroll = new JScrollPane(textlog);
    private String move;
    private int whiteScore = 0;
    private int blackScore = 0;
    
    
    /**
     * <p>
     * Constructs a GameLog object. GameLog keeps track of all the  moves that 
     * happen in the ChessGame. GameLog will keep track if ChessPiece moves to a 
     * new square, if a ChessPiece takes an enemy CheisssPiece, and if the a player
     * is attempting to make an illegal move.
     * <p>
     * 
     * @param game The ChessGame to which the GameLog will be added to
     */
    public GameLog(ChessGame game){
        this.game = game;
        
        //game log text area
        textlog.setLocation(660, 300);
        textlog.setSize(300, 300);
        textlog.setLineWrap(true);
        textlog.setEditable(false);
        textlog.setVisible(true);
        
        //scrolling pane for game log text area
        scroll.setLocation(660, 490);
        scroll.setSize(375, 150);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        DefaultCaret caret = (DefaultCaret)textlog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        game.getFrame().add(scroll);
        
        move = "";
    }
    
    /**
     * <p>
     * Set move that a ChessPiece was just taken and records the move 
     * <p>
     * 
     * @param temp The ChessPiece who's was attacking 
     */
    public void setMove(ChessPiece temp){
        
        move = temp.toString() + " took " + ChessBoard.pieces[game.getIndex()].toString() + " \n";
        
    }
    
    /**
     * <p>
     * Appends to the GameLog. If move was set display that a piece was taken.
     * Otherwise display that ChessPiece move to an unoccupied square
     * <p>
     * 
     * @param temp  The ChessPiece who's is to be recorded 
     */
    public void logPieceAction(ChessPiece temp){
        
        
        if (!move.isEmpty()) { 
            //piece is taking another piece
            textlog.append(move);
        } else {
            //piece is just moving
            move = temp.toString() + " moved to " + ChessBoard.getRank(game.getIndex()) + ChessBoard.getFile(game.getIndex()) + "  \n";
            textlog.append(move);
        }
    }
    
    /**
     * <p>
     * Appends the text to the GameLog and displays which player turn it is 
     * (e.g. white's turn to move)
     * <p>
     * 
     * @param turn the player who's turn it will currently be 
     */
    public void logCurrentTurn(String turn){
        
        textlog.append(turn + "'s turn to move \n");
    }
    
     /**
    * <p>
    * Appends a string to the GameLog and ends the appending string with a new line character
    * Displays the string to the GameLog
    * <p>
    * 
    * @param s The string to be appended to the GameLog
    */
    public void logText(String s){
        textlog.append(s + " \n");
    }
    
    public void logScore(){
        
    }
    
    /**
     * 
     */
    public void updateLog(){
        textlog.update(textlog.getGraphics());
    }
    
    /**
     * 
     * @return The text that is in This GameLog
     */
    public String getLog(){
        return textlog.getText();
    }
    
   /**
    * <p>
    * Appends a string to the GameLog and ends the appending string with a new line character
    * Displays the string to the GameLog
    * <p>
    * 
    * @param s The string to be appended to the GameLog
    */
    public void appendLog(String s){
        textlog.append(s + " \n");
    }
    
    /**
     * clear the text in the GameLog 
     */
    public void clearLog(){
        textlog.setText("");
    }
}