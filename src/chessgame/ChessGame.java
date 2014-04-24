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
   //ChessPiece[] pieces = new ChessPiece[64];
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
            knight_w = ImageIO.read(new File("src/image/knight_white.png")); //spelling....
            bishop_w = ImageIO.read(new File("src/image/bishop_white.png")); 
            queen_w = ImageIO.read(new File("src/image/queen_white.png"));
            king_w = ImageIO.read(new File("src/image/king_white.png"));
            
            pawn_b = ImageIO.read(new File("src/image/pawn_black.png"));
            rook_b = ImageIO.read(new File("src/image/rook_black.png"));
            knight_b = ImageIO.read(new File("src/image/knight_black.png")); //spelling....
            bishop_b = ImageIO.read(new File("src/image/bishop_black.png")); 
            queen_b = ImageIO.read(new File("src/image/queen_black.png"));
            king_b = ImageIO.read(new File("src/image/king_black.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        
        
        String white = new String();
        white = "white";
        String black = new String();
        black = "black";
        JFrame frame = new JFrame();//new a JFrame
        JPanel chessboard = new ChessBoard();
        chessboard.setLayout(null);
        frame.getContentPane().add(chessboard);//new a ChessBoard that extends JPanel
        Menu demo = new Menu();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setSize(660,705);
        frame.setVisible(true);
        
        //dropdown menu
        
        
        //white
        Pawn pawn = new Pawn(pawn_w, white,8);//Just for testing
        Pawn pawn2 = new Pawn(pawn_w, white,9);
        Pawn pawn3 = new Pawn(pawn_w, white,10);
        Pawn pawn4 = new Pawn(pawn_w, white,11);
        Pawn pawn5 = new Pawn(pawn_w, white,12);
        Pawn pawn6 = new Pawn(pawn_w, white,13);
        Pawn pawn7 = new Pawn(pawn_w, white,14);
        Pawn pawn8 = new Pawn(pawn_w, white,15);
        
        //just going to create regular pieces as pawns too for now
        
        Rook rook1 = new Rook(rook_w, white, 0);
        Knight knight1 = new Knight(knight_w, white, 1);
        Bishop bishop1 = new Bishop(bishop_w, white, 2);
        Pawn queen = new Pawn(queen_w, white, 3);
        King king = new King(king_w, white, 4);
        Bishop bishop2 = new Bishop(bishop_w, white, 5);
        Knight knight2 = new Knight(knight_w, white, 6);
        Rook rook2 = new Rook(rook_w, white, 7);
        
        //black
        
        Pawn b_pawn = new Pawn(pawn_b, black,48);//Just for testing
        Pawn b_pawn2 = new Pawn(pawn_b, black,49);
        Pawn b_pawn3 = new Pawn(pawn_b, black,50);
        Pawn b_pawn4 = new Pawn(pawn_b, black,51);
        Pawn b_pawn5 = new Pawn(pawn_b, black,52);
        Pawn b_pawn6 = new Pawn(pawn_b, black,53);
        Pawn b_pawn7 = new Pawn(pawn_b, black,54);
        Pawn b_pawn8 = new Pawn(pawn_b, black,55);
        
        //just going to create regular pieces as pawns too for now
        
        Rook b_rook1 = new Rook(rook_b, black, 56);
        Knight b_knight1 = new Knight(knight_b, black,57);
        Bishop b_bishop1 = new Bishop(bishop_b, black, 58);
        Pawn b_queen = new Pawn(queen_b, black, 59);
        King b_king = new King(king_b, black, 60);
        Bishop b_bishop2 = new Bishop(bishop_b, black, 61);
        Knight b_knight2 = new Knight(knight_b, black, 62);
        Rook b_rook2 = new Rook(rook_b, black, 63);
        
        
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
        
        setChessLocation(pawn,8,88);
        setChessLocation(pawn2,88,88);
        setChessLocation(pawn3,168,88);
        setChessLocation(pawn4,248,88);
        setChessLocation(pawn5,328,88);
        setChessLocation(pawn6,408,88);
        setChessLocation(pawn7,488,88);
        setChessLocation(pawn8,568,88);
        
        setChessLocation(rook1, 8, 8);
        setChessLocation(knight1, 88, 8);
        setChessLocation(bishop1, 168, 8);
        setChessLocation(queen, 248, 8);
        setChessLocation(king, 328, 8);
        setChessLocation(bishop2, 408, 8);
        setChessLocation(knight2, 488, 8);
        setChessLocation(rook2, 568, 8);
        
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
        
        setChessLocation(b_pawn,8,488);
        setChessLocation(b_pawn2,88,488);
        setChessLocation(b_pawn3,168,488);
        setChessLocation(b_pawn4,248,488);
        setChessLocation(b_pawn5,328,488);
        setChessLocation(b_pawn6,408,488);
        setChessLocation(b_pawn7,488,488);
        setChessLocation(b_pawn8,568,488);
        
        setChessLocation(b_rook1, 8, 568);
        setChessLocation(b_knight1, 88, 568);
        setChessLocation(b_bishop1, 168, 568);
        setChessLocation(b_queen, 248, 568);
        setChessLocation(b_king, 328, 568);
        setChessLocation(b_bishop2, 408, 568);
        setChessLocation(b_knight2, 488, 568);
        setChessLocation(b_rook2, 568, 568);
        
        DragPieceListener WhitePawnlistener = new DragPieceListener();  //white MouseListener
        DragPieceListener WhitePawnlistener2 = new DragPieceListener();
        DragPieceListener WhitePawnlistener3 = new DragPieceListener();
        DragPieceListener WhitePawnlistener4 = new DragPieceListener();
        DragPieceListener WhitePawnlistener5 = new DragPieceListener();
        DragPieceListener WhitePawnlistener6 = new DragPieceListener();
        DragPieceListener WhitePawnlistener7 = new DragPieceListener();
        DragPieceListener WhitePawnlistener8 = new DragPieceListener();
        DragPieceListener WhiteRooklistener = new DragPieceListener();
        DragPieceListener WhiteKnightlistener = new DragPieceListener();
        DragPieceListener WhiteBishoplistener = new DragPieceListener();
        DragPieceListener WhiteQueenlistener = new DragPieceListener();
        DragPieceListener WhiteKinglistener = new DragPieceListener();
        DragPieceListener WhiteBishoplistener2 = new DragPieceListener();
        DragPieceListener WhiteKnightlistener2 = new DragPieceListener();
        DragPieceListener WhiteRooklistener2 = new DragPieceListener();
        
        DragPieceListener BlackPawnlistener = new DragPieceListener();  //blalk MouseListener
        DragPieceListener BlackPawnlistener2 = new DragPieceListener();
        DragPieceListener BlackPawnlistener3 = new DragPieceListener();
        DragPieceListener BlackPawnlistener4 = new DragPieceListener();
        DragPieceListener BlackPawnlistener5 = new DragPieceListener();
        DragPieceListener BlackPawnlistener6 = new DragPieceListener();
        DragPieceListener BlackPawnlistener7 = new DragPieceListener();
        DragPieceListener BlackPawnlistener8 = new DragPieceListener();
        DragPieceListener BlackRooklistener = new DragPieceListener();
        DragPieceListener BlackKnightlistener = new DragPieceListener();
        DragPieceListener BlackBishoplistener = new DragPieceListener();
        DragPieceListener BlackQueenlistener = new DragPieceListener();
        DragPieceListener BlackKinglistener = new DragPieceListener();
        DragPieceListener BlackBishoplistener2 = new DragPieceListener();
        DragPieceListener BlackKnightlistener2 = new DragPieceListener();
        DragPieceListener BlackRooklistener2 = new DragPieceListener();
        pawn.addMouseListener(WhitePawnlistener);  // white add mouselistener to image
        pawn.addMouseMotionListener(WhitePawnlistener);
        pawn2.addMouseListener(WhitePawnlistener2);
        pawn2.addMouseMotionListener(WhitePawnlistener2);
        pawn3.addMouseListener(WhitePawnlistener3);
        pawn3.addMouseMotionListener(WhitePawnlistener3);
        pawn4.addMouseListener(WhitePawnlistener4);
        pawn4.addMouseMotionListener(WhitePawnlistener4);
        pawn5.addMouseListener(WhitePawnlistener5);
        pawn5.addMouseMotionListener(WhitePawnlistener5);
        pawn6.addMouseListener(WhitePawnlistener6);
        pawn6.addMouseMotionListener(WhitePawnlistener6);
        pawn7.addMouseListener(WhitePawnlistener7);
        pawn7.addMouseMotionListener(WhitePawnlistener7);
        pawn8.addMouseListener(WhitePawnlistener8);
        pawn8.addMouseMotionListener(WhitePawnlistener8);
        rook1.addMouseListener(WhiteRooklistener);
        rook1.addMouseMotionListener(WhiteRooklistener);
        rook2.addMouseListener(WhiteRooklistener2);
        rook2.addMouseMotionListener(WhiteRooklistener2);
        knight1.addMouseListener(WhiteKnightlistener);
        knight1.addMouseMotionListener(WhiteKnightlistener);
        knight2.addMouseListener(WhiteKnightlistener2);
        knight2.addMouseMotionListener(WhiteKnightlistener2);
        bishop1.addMouseListener(WhiteBishoplistener);
        bishop1.addMouseMotionListener(WhiteBishoplistener);
        bishop2.addMouseListener(WhiteBishoplistener2);
        bishop2.addMouseMotionListener(WhiteBishoplistener2);
        king.addMouseListener(WhiteKinglistener);
        king.addMouseMotionListener(WhiteKinglistener);
        queen.addMouseListener(WhiteQueenlistener);
        queen.addMouseMotionListener(WhiteQueenlistener);
        
        b_pawn.addMouseListener(BlackPawnlistener);  // Black add mouselistener to image
        b_pawn.addMouseMotionListener(BlackPawnlistener);
        b_pawn2.addMouseListener(BlackPawnlistener2);
        b_pawn2.addMouseMotionListener(BlackPawnlistener2);
        b_pawn3.addMouseListener(BlackPawnlistener3);
        b_pawn3.addMouseMotionListener(BlackPawnlistener3);
        b_pawn4.addMouseListener(BlackPawnlistener4);
        b_pawn4.addMouseMotionListener(BlackPawnlistener4);
        b_pawn5.addMouseListener(BlackPawnlistener5);
        b_pawn5.addMouseMotionListener(BlackPawnlistener5);
        b_pawn6.addMouseListener(BlackPawnlistener6);
        b_pawn6.addMouseMotionListener(BlackPawnlistener6);
        b_pawn7.addMouseListener(BlackPawnlistener7);
        b_pawn7.addMouseMotionListener(BlackPawnlistener7);
        b_pawn8.addMouseListener(BlackPawnlistener8);
        b_pawn8.addMouseMotionListener(BlackPawnlistener8);
        b_rook1.addMouseListener(BlackRooklistener);
        b_rook1.addMouseMotionListener(BlackRooklistener);
        b_rook2.addMouseListener(BlackRooklistener2);
        b_rook2.addMouseMotionListener(BlackRooklistener2);
        b_knight1.addMouseListener(BlackKnightlistener);
        b_knight1.addMouseMotionListener(BlackKnightlistener);
        b_knight2.addMouseListener(BlackKnightlistener2);
        b_knight2.addMouseMotionListener(BlackKnightlistener2);
        b_bishop1.addMouseListener(BlackBishoplistener);
        b_bishop1.addMouseMotionListener(BlackBishoplistener);
        b_bishop2.addMouseListener(BlackBishoplistener2);
        b_bishop2.addMouseMotionListener(BlackBishoplistener2);
        b_king.addMouseListener(BlackKinglistener);
        b_king.addMouseMotionListener(BlackKinglistener);
        b_queen.addMouseListener(BlackQueenlistener);
        b_queen.addMouseMotionListener(BlackQueenlistener);
        //need to add mouse listeners for rest of pawns
        
        //index = (ox)/80 +(oy)/80*8;
//        for(int i = 0; i<64; i++)
//            pieces[i] = null;
//        pieces[index] = pawn;
    }
    public void setChessLocation(ChessPiece aPiece, int xx, int yy){//Just for test, set the selected chess piece(a JButton)'s location
        aPiece.setLocation(xx, yy);       
        
    }
    
    private class DragPieceListener implements MouseInputListener{
        //***************
        //Set coordinate
        //***************
        boolean inDrag = false;
        int boardx = 0;
        int boardy = 0;
        int ox = 0;
        int oy = 0;
        int relx;
        int rely;
        Point point = new Point(0, 0);
        
        boolean contains (int x, int y){
            // Calculate center of draggable chess piece.
            int cox = ox + SQUAREDIM / 2;
            int coy = oy + SQUAREDIM / 2;
            return (cox - x) * (cox - x) + (coy - y) * (coy - y) < SQUAREDIM  * SQUAREDIM ;
        }
        public void mousePressed(MouseEvent e){
            ChessPiece tmpPiece = (ChessPiece)e.getSource();
            point = SwingUtilities.convertPoint(tmpPiece, e.getPoint(), tmpPiece.getParent());
            
            int x = point.x;
            int y = point.y;
            ox = x/SQUAREDIM*SQUAREDIM;
            oy = y/SQUAREDIM*SQUAREDIM;
            index = ox/SQUAREDIM +oy/SQUAREDIM*8;

            if (contains (x, y)){
                relx = x - ox;
                rely = y - oy;
                inDrag = true;
              }
            
        }
        public void mouseDragged(MouseEvent e){
             ChessPiece tmpPiece = (ChessPiece)e.getSource();
            Point newPoint = SwingUtilities.convertPoint(tmpPiece, e.getPoint(), tmpPiece.getParent());
            point = newPoint;
            if (inDrag){

                int tmpox = newPoint.x - relx;
                int tmpoy = newPoint.y - rely;

                if (tmpox > boardx &&tmpoy > boardy && tmpox + SQUAREDIM < boardx + BOARDDIM + SQUAREDIM && tmpoy + SQUAREDIM < boardy + BOARDDIM + SQUAREDIM){
                    ox = tmpox;
                    oy = tmpoy;
                    index = ox/SQUAREDIM +oy/SQUAREDIM*8;                
                    setChessLocation(tmpPiece,ox,oy);
                }
            }
        }
        public void mouseReleased(MouseEvent e){
            ChessPiece tmpPiece = (ChessPiece)e.getSource();
            if (inDrag)
                inDrag = false;
            int cox = ox + SQUAREDIM/2;
            int coy = oy + SQUAREDIM/2;
            cox = cox/SQUAREDIM *SQUAREDIM + SQUAREDIM/2 - 65/2;
            coy = coy/SQUAREDIM *SQUAREDIM + SQUAREDIM/2 - 65/2;
            
            ChessBoard.pieces[index] = tmpPiece;
            if(tmpPiece.isValidMove(index)){
                setChessLocation(tmpPiece,cox,coy);
                tmpPiece.setBoardPosition(index);
            }    
            else
                setChessLocation(tmpPiece,tmpPiece.getBoardPosition()%8*80+8,tmpPiece.getBoardPosition()/8*80+8);
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