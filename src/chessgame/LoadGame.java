
package chessgame;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class LoadGame {
    private final File loadFile;
    private InputStream inStream;
    private BufferedReader loadReader;
    private final ChessBoard chessboard;
    private ChessGame game;
    private final GameLog gamelog;
    private String square, logLine, turn;
    
    private int xlocation, ylocation;
    
    private Image pawn_w, rook_w, knight_w, bishop_w, queen_w, king_w, pawn_b, rook_b,
         knight_b, bishop_b, queen_b, king_b;

    
    public LoadGame(File f, ChessBoard cb, ChessGame g, GameLog gl){
        loadFile = f;
        chessboard = cb;
        game = g;
        gamelog = gl;
        
        loadFromFile();
    }
   
    public void loadFromFile() {
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

            inStream = new FileInputStream(loadFile);
            loadReader = new BufferedReader(new InputStreamReader(inStream));
            
            
            for (int i = 0; i < 64; i++) {
                
                xlocation = 8+(i%8)*80;
                ylocation = 8+(i/8)*80;
                
                square = loadReader.readLine();
                if (square.equals("0")) {
                    //do nothing for empty square
                } else if (square.equals("wp")) {
                    Pawn wp = new Pawn(pawn_w, "white", i);
                    ChessBoard.pieces[i] = wp;
                    chessboard.add(wp);
                    ChessGame.setChessLocation(wp, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    wp.addMouseListener(WhitePawnlistener);
                    wp.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("wr")) {
                    Rook wr = new Rook(rook_w, "white", i);
                    ChessBoard.pieces[i] = wr;
                    chessboard.add(wr);
                    ChessGame.setChessLocation(wr, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    wr.addMouseListener(WhitePawnlistener);
                    wr.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("wn")) {
                    Knight wn = new Knight(knight_w, "white", i);
                    ChessBoard.pieces[i] = wn;
                    chessboard.add(wn);
                    ChessGame.setChessLocation(wn, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    wn.addMouseListener(WhitePawnlistener);
                    wn.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("wb")) {
                    Bishop wb = new Bishop(bishop_w, "white", i);
                    ChessBoard.pieces[i] = wb;
                    chessboard.add(wb);
                    ChessGame.setChessLocation(wb, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    wb.addMouseListener(WhitePawnlistener);
                    wb.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("wq")) {
                    Queen wq = new Queen(queen_w, "white", i);
                    ChessBoard.pieces[i] = wq;
                    chessboard.add(wq);
                    ChessGame.setChessLocation(wq, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    wq.addMouseListener(WhitePawnlistener);
                    wq.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("wk")) {
                    King wk = new King(king_w, "white", i);
                    ChessBoard.pieces[i] = wk;
                    chessboard.add(wk);
                    ChessGame.setChessLocation(wk, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    wk.addMouseListener(WhitePawnlistener);
                    wk.addMouseMotionListener(WhitePawnlistener);
                } 
                
                
                else if (square.equals("bp")) {
                    Pawn bp = new Pawn(pawn_b, "black", i);
                    ChessBoard.pieces[i] = bp;
                    chessboard.add(bp);
                    ChessGame.setChessLocation(bp, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    bp.addMouseListener(WhitePawnlistener);
                    bp.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("br")) {
                    Rook br = new Rook(rook_b, "black", i);
                    ChessBoard.pieces[i] = br;
                    chessboard.add(br);
                    ChessGame.setChessLocation(br, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    br.addMouseListener(WhitePawnlistener);
                    br.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("bn")) {
                    Knight bn = new Knight(knight_b, "black", i);
                    ChessBoard.pieces[i] = bn;
                    chessboard.add(bn);
                    ChessGame.setChessLocation(bn, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    bn.addMouseListener(WhitePawnlistener);
                    bn.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("bb")) {
                    Bishop bb = new Bishop(bishop_b, "black", i);
                    ChessBoard.pieces[i] = bb;
                    chessboard.add(bb);
                    ChessGame.setChessLocation(bb, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    bb.addMouseListener(WhitePawnlistener);
                    bb.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("bq")) {
                    Queen bq = new Queen(queen_b, "black", i);
                    ChessBoard.pieces[i] = bq;
                    chessboard.add(bq);
                    ChessGame.setChessLocation(bq, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    bq.addMouseListener(WhitePawnlistener);
                    bq.addMouseMotionListener(WhitePawnlistener);
                } else if (square.equals("bk")) {
                    King bk = new King(king_b, "black", i);
                    ChessBoard.pieces[i] = bk;
                    chessboard.add(bk);
                    ChessGame.setChessLocation(bk, xlocation, ylocation);
                    DragPieceListener WhitePawnlistener = new DragPieceListener(game);
                    bk.addMouseListener(WhitePawnlistener);
                    bk.addMouseMotionListener(WhitePawnlistener);
                }
            }
            
            //load correct players turn
            turn = loadReader.readLine();
            game.colorsTurn = turn;
            
            //load gamelog
            while((logLine = loadReader.readLine()) != null){
                gamelog.appendLog(logLine);
            }

            
        } catch (IOException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        finally {
            try {
                // Close the writer regardless of what happens...
                loadReader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
