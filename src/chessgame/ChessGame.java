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
   ChessPiece[] pieces;
   Image image;
   int index;
   Pawntest pawn = new Pawntest();//Just for testing
   Pawntest pawn2 = new Pawntest();
   Container contain;
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
        JFrame frame = new JFrame();//new a JFrame
        JPanel chessboard = new ChessBoard();
        frame.getContentPane().add(chessboard);//new a ChessBoard that extends JPanel
        frame.setSize(660,690);
        frame.setVisible(true);
        chessboard.add(pawn, BorderLayout.LINE_START);
        chessboard.add(pawn2,BorderLayout.LINE_START);
//        chessboard.add(pawn);  
//        pawn.setLocation(0, 0);
        DragPieceListener listener = new DragPieceListener();  //MouseListener
        DragPieceListener listener2 = new DragPieceListener();
        pawn.addMouseListener(listener);  //add mouselistener to image
        pawn.addMouseMotionListener(listener);
        pawn2.addMouseListener(listener2);
        pawn2.addMouseMotionListener(listener2);
        //index = (ox)/80 +(oy)/80*8;
//        for(int i = 0; i<64; i++)
//            pieces[i] = null;
//        pieces[index] = pawn;
    }
    public void setChessLocation(Pawntest aPawn, int xx, int yy){//Just for test, set the selected chess piece(a JButton)'s location
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
   int ox = 280;
   // Top coordinate of chess piece origin (upper-left corner).
   int oy = 5;
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
            Pawntest tmpPawn = (Pawntest)e.getSource();
            point = SwingUtilities.convertPoint(tmpPawn, e.getPoint(), tmpPawn.getParent());
            System.out.println("point: " + point);
            // Obtain mouse coordinates at time of press.
            int x = point.x;
            int y = point.y;
            //System.out.println("x: " + tmpPawn.getX() + " y: " + tmpPawn.getY() + " point:" + e.getPoint() + " what? " + tmpPawn.getParent());
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
            Pawntest tmpPawn = (Pawntest)e.getSource();
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
            Pawntest tmpPawn = (Pawntest)e.getSource();
            // When mouse is released, clear inDrag (to
            // indicate no drag in progress) if inDrag is
            // already set.
            if (inDrag)
                inDrag = false;
            //Pawntest tmpPawn = (Pawntest)e.getSource();
            ox = ox/80 *80;
            oy = oy/80 *80;
            setChessLocation(tmpPawn,ox,oy);
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