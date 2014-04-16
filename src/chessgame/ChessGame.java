package chessgame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
   
   
   
   //*****************
   //Chess piece
   //*****************
   ChessPiece[] pieces = new ChessPiece[64];
   Image pawn_w, rook_w, knight_w, bishop_w, queen_w, king_w, pawn_b, rook_b,
           knight_b, bishop_b, queen_b, king_b;
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
    public void init(){
        
        try {
            pawn_w = ImageIO.read(new File("src/image/pawn_white.png"));
            rook_w = ImageIO.read(new File("src/image/rook_white.png"));
            knight_w = ImageIO.read(new File("src/image/kngiht_white.png")); //spelling....
            bishop_w = ImageIO.read(new File("src/image/bishop_white.png")); 
            queen_w = ImageIO.read(new File("src/image/queen_white.png"));
            king_w = ImageIO.read(new File("src/image/king_white.png"));
            
            pawn_b = ImageIO.read(new File("src/image/pawn_black.png"));
            rook_b = ImageIO.read(new File("src/image/rook_black.png"));
            knight_b = ImageIO.read(new File("src/image/kngiht_black.png")); //spelling....
            bishop_b = ImageIO.read(new File("src/image/bishop_black.png")); 
            queen_b = ImageIO.read(new File("src/image/queen_black.png"));
            king_b = ImageIO.read(new File("src/image/king_black.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        
        
        String color = new String();
        JFrame frame = new JFrame();//new a JFrame
        JPanel chessboard = new ChessBoard();
        chessboard.setLayout(null);
        frame.getContentPane().add(chessboard);//new a ChessBoard that extends JPanel
        Menu demo = new Menu();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setSize(660,690);
        frame.setVisible(true);
        
        //dropdown menu
        
        
        //white
        Pawn pawn = new Pawn(pawn_w, color,8);//Just for testing
        Pawn pawn2 = new Pawn(pawn_w, color,9);
        Pawn pawn3 = new Pawn(pawn_w, color,10);
        Pawn pawn4 = new Pawn(pawn_w, color,11);
        Pawn pawn5 = new Pawn(pawn_w, color,12);
        Pawn pawn6 = new Pawn(pawn_w, color,13);
        Pawn pawn7 = new Pawn(pawn_w, color,14);
        Pawn pawn8 = new Pawn(pawn_w, color,15);
        
        //just going to create regular pieces as pawns too for now
        
        Pawn rook1 = new Pawn(rook_w, color, 0);
        Pawn knight1 = new Pawn(knight_w, color, 1);
        Pawn bishop1 = new Pawn(bishop_w, color, 2);
        Pawn queen = new Pawn(queen_w, color, 3);
        Pawn king = new Pawn(king_w, color, 4);
        Pawn bishop2 = new Pawn(bishop_w, color, 5);
        Pawn knight2 = new Pawn(knight_w, color, 6);
        Pawn rook2 = new Pawn(rook_w, color, 7);
        
        //black
        
        Pawn b_pawn = new Pawn(pawn_b, color,48);//Just for testing
        Pawn b_pawn2 = new Pawn(pawn_b, color,49);
        Pawn b_pawn3 = new Pawn(pawn_b, color,50);
        Pawn b_pawn4 = new Pawn(pawn_b, color,51);
        Pawn b_pawn5 = new Pawn(pawn_b, color,52);
        Pawn b_pawn6 = new Pawn(pawn_b, color,53);
        Pawn b_pawn7 = new Pawn(pawn_b, color,54);
        Pawn b_pawn8 = new Pawn(pawn_b, color,55);
        
        //just going to create regular pieces as pawns too for now
        
        Pawn b_rook1 = new Pawn(rook_b, color, 56);
        Pawn b_knight1 = new Pawn(knight_b, color,57);
        Pawn b_bishop1 = new Pawn(bishop_b, color, 58);
        Pawn b_queen = new Pawn(queen_b, color, 59);
        Pawn b_king = new Pawn(king_b, color, 60);
        Pawn b_bishop2 = new Pawn(bishop_b, color, 61);
        Pawn b_knight2 = new Pawn(knight_b, color, 62);
        Pawn b_rook2 = new Pawn(rook_b, color, 63);
        
        
        //white
        chessboard.add(pawn);
        chessboard.add(pawn2);
        chessboard.add(pawn3);
        chessboard.add(pawn4);
        chessboard.add(pawn5);
        chessboard.add(pawn6);
        chessboard.add(pawn7);
        chessboard.add(pawn8);
        
        chessboard.add(rook1);
        chessboard.add(knight1);
        chessboard.add(bishop1);
        chessboard.add(queen);
        chessboard.add(king);
        chessboard.add(bishop2);
        chessboard.add(knight2);
        chessboard.add(rook2);
        
        setChessLocation(pawn,0,80);
        setChessLocation(pawn2,80,80);
        setChessLocation(pawn3,160,80);
        setChessLocation(pawn4,240,80);
        setChessLocation(pawn5,320,80);
        setChessLocation(pawn6,400,80);
        setChessLocation(pawn7,480,80);
        setChessLocation(pawn8,560,80);
        
        setChessLocation(rook1, 0, 0);
        setChessLocation(knight1, 80, 0);
        setChessLocation(bishop1, 160, 0);
        setChessLocation(queen, 240, 0);
        setChessLocation(king, 320, 0);
        setChessLocation(bishop2, 400, 0);
        setChessLocation(knight2, 480, 0);
        setChessLocation(rook2, 560, 0);
        
        //black
        
        chessboard.add(b_pawn);
        chessboard.add(b_pawn2);
        chessboard.add(b_pawn3);
        chessboard.add(b_pawn4);
        chessboard.add(b_pawn5);
        chessboard.add(b_pawn6);
        chessboard.add(b_pawn7);
        chessboard.add(b_pawn8);
        
        chessboard.add(b_rook1);
        chessboard.add(b_knight1);
        chessboard.add(b_bishop1);
        chessboard.add(b_queen);
        chessboard.add(b_king);
        chessboard.add(b_bishop2);
        chessboard.add(b_knight2);
        chessboard.add(b_rook2);
        
        setChessLocation(b_pawn,0,480);
        setChessLocation(b_pawn2,80,480);
        setChessLocation(b_pawn3,160,480);
        setChessLocation(b_pawn4,240,480);
        setChessLocation(b_pawn5,320,480);
        setChessLocation(b_pawn6,400,480);
        setChessLocation(b_pawn7,480,480);
        setChessLocation(b_pawn8,560,480);
        
        setChessLocation(b_rook1, 0, 560);
        setChessLocation(b_knight1, 80, 560);
        setChessLocation(b_bishop1, 160, 560);
        setChessLocation(b_queen, 240, 560);
        setChessLocation(b_king, 320, 560);
        setChessLocation(b_bishop2, 400, 560);
        setChessLocation(b_knight2, 480, 560);
        setChessLocation(b_rook2, 560, 560);
        
        DragPieceListener listener = new DragPieceListener();  //MouseListener
        DragPieceListener listener2 = new DragPieceListener();
        pawn.addMouseListener(listener);  //add mouselistener to image
        pawn.addMouseMotionListener(listener);
        pawn2.addMouseListener(listener2);
        pawn2.addMouseMotionListener(listener2);
        //need to add mouse listeners for rest of pawns
        
        //index = (ox)/80 +(oy)/80*8;
//        for(int i = 0; i<64; i++)
//            pieces[i] = null;
//        pieces[index] = pawn;
    }
    public void setChessLocation(Pawn aPawn, int xx, int yy){//Just for test, set the selected chess piece(a JButton)'s location
        aPawn.setLocation(xx, yy);
        index = (xx)/80 +(yy)/80*8;
        System.out.println("index: " + index);
    }
    
    private class DragPieceListener implements MouseInputListener{
        //***************
   //Set coordinate
   //***************
    boolean inDrag = false;
   // Left coordinate of chessboard's upper-left corner.
   int boardx = 0;
   // Top coordinate of chessboard's upper-left corner.
   int boardy = 0;
   // Left coordinate of chess piece origin (upper-left corner).
   int ox = 0;
   // Top coordinate of chess piece origin (upper-left corner).
   int oy = 0;
   // Left displacement between mouse coordinates at time of press and chess
   // piece origin.
   int relx;
   // Top displacement between mouse coordinates at time of press and chess
   // piece origin.
   int rely;
        Point point = new Point(0, 0);
        boolean contains (int x, int y){
            // Calculate center of draggable chess piece.
            int cox = ox + SQUAREDIM / 2;
            int coy = oy + SQUAREDIM / 2;
            // Return true if (x, y) locates with bounds
            // of draggable chess piece. SQUAREDIM / 2 is the
            // radius.
            System.out.println("contains: " + ((cox - x) * (cox - x) + (coy - y) * (coy - y) < SQUAREDIM  * SQUAREDIM));
            return (cox - x) * (cox - x) + (coy - y) * (coy - y) < SQUAREDIM  * SQUAREDIM ;
        }
        public void mousePressed(MouseEvent e){
            Pawn tmpPawn = (Pawn)e.getSource();
            point = SwingUtilities.convertPoint(tmpPawn, e.getPoint(), tmpPawn.getParent());
            System.out.println("point: " + point);
            // Obtain mouse coordinates at time of press.
            int x = point.x;
            int y = point.y;
            
            // If mouse is over draggable chess piece at time
            // of press (i.e., contains (x, y) returns
            // true), save distance between current mouse
            // coordinates and draggable chess piece origin
            // (which will always be positive) and set drag
            // flag to true (to indicate drag in progress).
            if (contains (x, y)){
                relx = x - ox;
                rely = y - oy;
                inDrag = true;
              }
            
        }
        public void mouseDragged(MouseEvent e){
            Pawn tmpPawn = (Pawn)e.getSource();
            Point newPoint = SwingUtilities.convertPoint(tmpPawn, e.getPoint(), tmpPawn.getParent());
            point = newPoint;
            if (inDrag){
                // Calculate draggable chess piece's new
                // origin (the upper-left corner of
                // the chess piece).
                int tmpox = newPoint.x - relx;
                int tmpoy = newPoint.y - rely;

                // If the chess piece is not being moved
                // (at least partly) off board, 
                // assign the previously calculated
                // origin (tmpox, tmpoy) as the
                // permanent origin (ox, oy), and
                // redraw the display area (with the
                // draggable chess piece at the new
                // coordinates).
                if (tmpox > boardx &&tmpoy > boardy && tmpox + SQUAREDIM < boardx + BOARDDIM && tmpoy + SQUAREDIM < boardy + BOARDDIM){
                    ox = tmpox;
                    oy = tmpoy;
                    setChessLocation(tmpPawn,ox,oy);
                    //setChessLocation(pieces[index],ox,oy);//give new location to selected chess piece
                }
            }
        }
        public void mouseReleased(MouseEvent e){
            Pawn tmpPawn = (Pawn)e.getSource();
            // When mouse is released, clear inDrag (to
            // indicate no drag in progress) if inDrag is
            // already set.
            if (inDrag)
                inDrag = false;
            //Pawntest tmpPawn = (Pawntest)e.getSource();
            int cox = ox + SQUAREDIM/2;
            int coy = oy + SQUAREDIM/2;
            cox = cox/80 *80 + 40 - 65/2;
            coy = coy/80 *80 + 40 - 65/2;
            setChessLocation(tmpPawn,cox,coy);
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
    public static void main(String[] args) {  
          ChessGame demo = new ChessGame();
          demo.init();
    }  
}