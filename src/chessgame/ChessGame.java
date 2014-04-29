package chessgame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Qilin Fu
 */
public class ChessGame extends JApplet implements Runnable {
   // Dimension of chessboard square.
   final static int SQUAREDIM = 80;
   // Dimension of chessboard
   final static int BOARDDIM = 8 * SQUAREDIM;
   // Dragging flag -- set to true when user presses mouse button over chess
   // and cleared to false when user releases mouse button.
   
   //game window
   JFrame frame;
   
   //chessboard panel
   JPanel chessboard;
   

   Player whiteplayer;
   Player blackplayer;
   String colorsTurn = "white";
   int index;
   
   
   
    private Thread thread;
    
    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public void run() {

        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
        }
        thread = null;
    }
    public void init()
    {
        
     
        
        
        frame = new JFrame();//new a JFrame
        chessboard = new ChessBoard();
        chessboard.setLayout(null);
        frame.getContentPane().add(chessboard);//new a ChessBoard that extends JPanel
        Menu demo = new Menu(this);
        frame.setJMenuBar(demo.createMenuBar());
        frame.setSize(660,705);
        frame.setVisible(true);
        newGame();
    }
    
    public  void newGame(){
        
        //reset game board before initializing newgame
        clearGame();
        whiteplayer = new Player("white",chessboard,this);
        blackplayer = new Player("black",chessboard,this);
       

        


        

        
        //index = (ox)/80 +(oy)/80*8;
//        for(int i = 0; i<64; i++)
//            pieces[i] = null;
//        pieces[index] = pawn;
        
    }
    
    public void clearGame(){
        //loop through piece array erase pieces
        
        //clear piece array
        for(int i = 0; i < 64; i++){
            ChessBoard.pieces[i] = null;
        }
        
        //remove all piece components from chessboard
        chessboard.removeAll();
    }
    
    
    public void saveGame(){
        //save piece array, player turn and score to file
    }
    
    public void loadGame(){
        //load piece array, player turn and score from file
    }
    
    public static void setChessLocation(ChessPiece aPiece, int xx, int yy){//Just for test, set the selected chess piece(a JButton)'s location
        aPiece.setLocation(xx, yy);       
        
    }
    public  int getIndex()
    {
       return index; 
    }
    public void setIndex(int index)
    {
        this.index = index;
    }
    public static void main(String[] args) 
    {  
          ChessGame demo = new ChessGame();
          demo.init();
    }  
}