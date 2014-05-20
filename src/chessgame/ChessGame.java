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

    /**
     * starts the thread for the ChessGame 
     */
    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    /**
     * Runs the ChessGame 
     */
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

    /**
     * initialize this ChessGame 
     */
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

    /**
     * <p>
     * Restarts the ChessGame
     * <p>
     * 
     * Clears the ChessBoard of all the current ChessPiece and then 
     * and resets the ChessBoard with all ChessPieces with their starting positions 
     * <p>
     */
    public void newGame() {

        //reset game board before initializing newgame
        clearGame();
        
        whiteplayer = new Player("white", chessboard, this);
        blackplayer = new Player("black", chessboard, this);
        colorsTurn = "white";
        gamelog.logCurrentTurn(colorsTurn);

    }
    
    /**
     * Clears the ChessBoard of all ChessPieces and clears GameLog
     */
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

    
    /**
     * <p>
     * load piece array, player turn and GameLog text from file
     * <p>
     * 
     * @param f The file to where the game is to be loaded from 
     */
    public void loadGame(File f) {
        clearGame();
        loadgame = new LoadGame(f, chessboard, this, gamelog);
        System.out.println("Gamed Loaded.");
        
        frame.update(frame.getGraphics());

    }
    
    /**
     * Sets the position of the ChessPiece 
     * 
     * @param aPiece The ChessPiece who's location is to be set
     * @param xx The new x coordinate of the ChessPiece
     * @param yy The new y coordinate of the ChessPiece
     */
    public void setChessLocation(ChessPiece aPiece, int xx, int yy) {//Just for test, set the selected chess piece(a JButton)'s location
        aPiece.setLocation(xx, yy);

    }

    /**
     * 
     * @return the position on the ChessBoard
     */
    public int getIndex() {
        return index;
    }

    /**
     * 
     * @param index the new position on the ChessBoard
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * 
     * @return The GameLog object that belongs to this ChessGame
     */
    public GameLog getGameLog(){
        return this.gamelog;
    }
    
    /**
     * 
     * @return The JFrame that belongs to this ChessGame
     */
    public JFrame getFrame(){
        return this.frame;
    }
    
    /**
     * 
     * @return The the dimension of single square 
     */
    public int getSqaureDim(){
        return this.SQUAREDIM;
    }
    
    /**
     * 
     * @return The overall dimension of the board
     */
    public int getBoardDim(){
        return this.BOARDDIM;
    }
    
    /**
     * 
     * @return The ChessBoard that belongs to this ChessGame
     */
    public ChessBoard getChessBoard(){
        return chessboard;
    }
    
    /**
     * Set which Player turn it is 
     * 
     * @param color The color of the Player who's turn it will be 
     */
    public void setTurn(String color){
        this.colorsTurn = color;
    }
    
    /**
     * 
     * @return The Player's turn who's turn it currently is 
     */
    public String getTurn(){
        return this.colorsTurn;
    }
    
    
    /**
     * 
     * @return The Player object who's color is white that belongs to this ChessGame
     */
    public Player getWhitePlayer(){
        return this.whiteplayer;
    }
    
    /**
     * 
     * @return The Player object who's color is black that belongs to this ChessGame
     */
    public Player getBlackPlayer(){
        return this.blackplayer;
    }
    
    /**
     * 
     * @return The Coordinate object that belongs to this ChessGame
     */
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    
    /**
     * 
     * @return The number of  white pieces that have been taken 
     */
    public int getWhiteCounter(){
        return this.whiteCounter;
    }
    
    /**
     * 
     * @return  The number of black pieces that have been taken 
     */
    public int getBlackCounter(){
        return this.blackCounter;
    }
    
    /**
     * set the number of black pieces taken 
     * @param num the new number of black pieces taken 
     */
    public void setBlackCounter(int num){
        this.blackCounter = num;
    }
    
    /**
    *  set the number of white pieces taken 
     * @param num the new number of white pieces taken 
     */
    public void setWhiteCounter(int num){
        this.whiteCounter = num;
    }
    
    /**
     * 
     * @return The Taken object that belongs to this ChessGame
     */
    public Taken getTakenPiece(){
        return this.takenPiece; 
    }
    
    public static void main(String[] args) {

        ChessGame demo = new ChessGame();
        demo.init();
    }
}
