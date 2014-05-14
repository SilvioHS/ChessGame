package chessgame;

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
    
    
    public GameLog(ChessGame g){
        game = g;
        
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
        
        g.frame.add(scroll);
        
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
    
    public void updateLog(){
        textlog.update(textlog.getGraphics());
    }
    
    public String getLog(){
        return textlog.getText();
    }
    
    public void appendLog(String s){
        textlog.append(s + " \n");
    }
    
    public void clearLog(){
        textlog.setText("");
    }
}