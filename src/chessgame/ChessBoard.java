/*
 * Class to represent the chess board.
 */

/**
 *
 * @author Nick Ottrando & Qilin Fu
 */

package chessgame;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.BorderLayout; 

public  class ChessBoard extends JLayeredPane {
    private Image image;
    public static ChessPiece[] pieces = new ChessPiece[66];
    
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
    
    public static ChessPiece getIndex(int index){
        return pieces[index];
    }
    
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
