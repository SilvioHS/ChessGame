package chessgame;

import menu.Taken;
import mousemovement.Coordinate;
import menu.GameLog;
import menu.LoadGame;
import menu.SaveGame;
import menu.Menu;
import chesspieces.ChessPiece;
import javax.swing.*;
import java.io.File;

/**
 *
 * @author Qilin Fu
 */
public class ChessGame extends JApplet implements Runnable {

    //CONSTANTS:
    // Dimension of chessboard square.
    private final int SQUAREDIM = 80;
    // Dimension of chessboard
    private final int BOARDDIM = 8 * SQUAREDIM;
    // Dragging flag -- set to true when user presses mouse button over chess
    // and cleared to false when user releases mouse button.

    //new window
    private JFrame frame;

    //chessboard panel
    private ChessBoard chessboard;

    //main menu bar
    private Menu dropmenu;

    //GameLog gamelog;
    private GameLog gamelog;

    
    //text area
    private JTextArea textarea;
    private  JScrollPane scroll;

    //game objects
    private Player whiteplayer;
    private Player blackplayer;
    private Coordinate coordinate;
    private SaveGame savegame;
    private LoadGame loadgame;
    private Taken takenPiece;

    //fields
    private String colorsTurn = "white";
    private int index;
    private int whiteCounter = 0;
    private int blackCounter = 0;

    //game thread
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

    public void init() {

        //new a JFrame window
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //init gamelog
        gamelog = new GameLog(this);
        takenPiece = new Taken(this);
        //chessboard gui
        chessboard = new ChessBoard();
        chessboard.setLayout(null);
        frame.getContentPane().add(chessboard);//new a ChessBoard that extends 

        //menu gui
        dropmenu = new Menu(this);
        frame.setJMenuBar(dropmenu.createMenuBar());
        frame.setSize(1060, 705);
        frame.setVisible(true);
        frame.setResizable(false);

        coordinate = new Coordinate();
        newGame();
    }

    public void newGame() {

        //reset game board before initializing newgame
        clearGame();
        
        whiteplayer = new Player("white", chessboard, this);
        blackplayer = new Player("black", chessboard, this);
        colorsTurn = "white";
        gamelog.logCurrentTurn(colorsTurn);

    }

    public void clearGame() {
        //loop through piece array erase pieces

        //clear piece array
        for (int i = 0; i < 64; i++) {
            ChessBoard.pieces[i] = null;
        }

        //remove all piece components from chessboard
        chessboard.removeAll();
        
        //clear game log
        gamelog.clearLog();
        
        //refresh graphics
        frame.update(frame.getGraphics());
    }

    //save piece array, player turn and score to file
    public void saveGame(File f) {
        savegame = new SaveGame(f, chessboard, gamelog, colorsTurn);
        System.out.println("Gamed Saved.");

    }

    //load piece array, player turn and score from file
    public void loadGame(File f) {
        clearGame();
        loadgame = new LoadGame(f, chessboard, this, gamelog);
        System.out.println("Gamed Loaded.");
        
        frame.update(frame.getGraphics());

    }

    public void setChessLocation(ChessPiece aPiece, int xx, int yy) {//Just for test, set the selected chess piece(a JButton)'s location
        aPiece.setLocation(xx, yy);

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public GameLog getGameLog(){
        return this.gamelog;
    }
    
    public JFrame getFrame(){
        return this.frame;
    }
    
    public int getSqaureDim(){
        return this.SQUAREDIM;
    }
    
    public int getBoardDim(){
        return this.BOARDDIM;
    }
    
    public ChessBoard getChessBoard(){
        return chessboard;
    }
    
    public void setTurn(String color){
        this.colorsTurn = color;
    }
    
    public String getTurn(){
        return this.colorsTurn;
    }
    
    public Player getWhitePlayer(){
        return this.whiteplayer;
    }
    
    public Player getBlackPlayer(){
        return this.blackplayer;
    }
    
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    
    public int getWhiteCounter(){
        return this.whiteCounter;
    }
    
    public int getBlackCounter(){
        return this.blackCounter;
    }
    
    public void setBlackCounter(int num){
        this.blackCounter = num;
    }
    
    public void setWhiteCounter(int num){
        this.whiteCounter = num;
    }
    
    public Taken getTakenPiece(){
        return this.takenPiece; 
    }
    
    public static void main(String[] args) {

        ChessGame demo = new ChessGame();
        demo.init();
    }
}
