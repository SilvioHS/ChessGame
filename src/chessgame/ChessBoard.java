/*
 * Class to represent the chess board.
 */

/**
 *
 * @author Nick Ottrando & Qilin Fu
 */

package chessgame;
import chesspieces.ChessPiece;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.BorderLayout; 

/**
 *
 * @author Kevin Velasco
 */
public  class ChessBoard extends JLayeredPane {
    private Image image;

    /**
    * <p>
     * the ChessBoard is represent with a 1D array of ChessPieces. Where the 
     * ChessPiece at index i is the position of the ChessPiece in the ChessBoard Array
     * <p>
     */
    public static ChessPiece[] pieces = new ChessPiece[66];
    
    /**
     * <p>
     * Constructs ChessBoard object. The ChessBoard object is represent with a 1D
     * array of ChessPieces. Where the ChessPiece at index i is the position of the 
     * ChessPiece in the ChessBoard Array
     * <p>
     * 
     * 
     */
    public  ChessBoard() {
        try {
            image = ImageIO.read(new File("src/image/ChessBoard80X80.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        
    }
    
    /**
     * <p>
     * retrieves a reference to a chess piece at the specified index in the chess board
     * <p>
     * 
     * @param index the position of the chess piece in the chess board that is 
     *                to be retrieved
     * <p>
     * 
     * @return A reference to the chess piece that is at the specified index
     * <p>
     * 
     */
    public static ChessPiece getIndex(int index){
        return pieces[index];
    }
    
    /**
     * <p>
     * A row of the chessboard. 
     * Files are numbered 1–8 starting at 1 from the left bottom most side of the board
     * and moving upwards to 8
     * <p>
     * 
     * @param pos The position of the chess piece who's file is to be retrieved 
     * <p>
     * 
     * @return The current file of the chess piece given the position. 
     *           The file is returned as an integer 
     */
    public static int getFile(int pos){
        int file = 0;
        
        if (pos < 8){
            file = 8;
        }else if(pos < 16){
            file = 7;
        }else if(pos < 24){
            file = 6;
        }else if(pos < 32){
            file = 5;
        }else if(pos < 40){
            file = 4;
        }else if(pos < 48){
            file = 3;
        }else if(pos < 56){
            file = 2;
        }else if(pos < 64){
            file = 1;
        }
        
        return file;
    }
    
    /**
     * <p>
     * A column of the chessboard. 
     * Ranks are lettered A–H starting from the left most side of the board
     * <p>
     * 
     * @param pos The position of the chess piece who's rank is to be retrieved 
     * <p>
     * @return The current rank of the chess piece given the position. 
     *           The rank is returned as a char 
     *              
     */
    public static char getRank(int pos){
        char rank = 'x';
        
        if (pos % 8 == 0){
            rank = 'a';
        }else if(pos % 8 == 1){
            rank = 'b';
        }else if(pos % 8 == 2){
            rank = 'c';
        }else if(pos % 8 == 3){
            rank = 'd';
        }else if(pos % 8 == 4){
            rank = 'e';
        }else if(pos % 8 == 5){
            rank = 'f';
        }else if(pos % 8 == 6){
            rank = 'g';
        }else if(pos % 8 == 7){
            rank = 'h';
        }
        
        return rank;
    }
}
