package chessgame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Qilin Fu
 */
public class ChessGame extends JApplet implements Runnable {

    //CONSTANTS:
    // Dimension of chessboard square.
    final static int SQUAREDIM = 80;
    // Dimension of chessboard
    final static int BOARDDIM = 8 * SQUAREDIM;
    // Dragging flag -- set to true when user presses mouse button over chess
    // and cleared to false when user releases mouse button.

    //new window
    JFrame frame;

    //chessboard panel
    ChessBoard chessboard;

    //main menu bar
    Menu dropmenu;

    //text area
    static JTextArea textarea;
    JScrollPane scroll;

    //game objects
    Player whiteplayer;
    Player blackplayer;
    Coordinate coordinate;
    SaveGame savegame;
    LoadGame loadgame;

    //fields
    String colorsTurn = "white";
    int index;

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

        //game log text area
        textarea = new JTextArea("Whites' turn to move \n");
        textarea.setLocation(660, 300);
        textarea.setSize(300, 300);
        textarea.setLineWrap(true);
        textarea.setEditable(false);
        textarea.setVisible(true);
        //scrolling pane for game log text area
        scroll = new JScrollPane(textarea);
        scroll.setLocation(660, 490);
        scroll.setSize(375, 150);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);
        
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

        //index = (ox)/80 +(oy)/80*8;
//        for(int i = 0; i<64; i++)
//            pieces[i] = null;
//        pieces[index] = pawn;
    }

    public void clearGame() {
        //loop through piece array erase pieces

        //clear piece array
        for (int i = 0; i < 64; i++) {
            ChessBoard.pieces[i] = null;
        }

        //remove all piece components from chessboard
        chessboard.removeAll();

        chessboard.update(chessboard.getGraphics());
    }

    //save piece array, player turn and score to file
    public void saveGame(File f) {
        savegame = new SaveGame(f, chessboard);
        System.out.println("Gamed Saved.");

    }

    //load piece array, player turn and score from file
    public void loadGame(File f) {
        clearGame();
        loadgame = new LoadGame(f, chessboard, this);
        System.out.println("Gamed Loaded.");

    }

    public static void setChessLocation(ChessPiece aPiece, int xx, int yy) {//Just for test, set the selected chess piece(a JButton)'s location
        aPiece.setLocation(xx, yy);

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static void main(String[] args) {

        ChessGame demo = new ChessGame();
        demo.init();
    }
}
