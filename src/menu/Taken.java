/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package menu;
import chessgame.ChessGame;
import chessgame.ChessGame;
import chesspieces.ChessPiece;
import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author xiaoweii
 */
public class Taken {
    private ChessGame game;
    private JLabel whiteLabel[];
    private JLabel blackLabel[];
    
    private JLabel whiteLabel1;
    private JLabel blackLabel1;

    public Image pawn_w, rook_w, knight_w, bishop_w, queen_w, king_w, pawn_b, rook_b,
         knight_b, bishop_b, queen_b, king_b;
    public Taken(ChessGame g){
        game = g;
        try{
            pawn_w = ImageIO.read(new File("src/image/pawn_white50.png"));
            rook_w = ImageIO.read(new File("src/image/rook_white50.png"));
            knight_w = ImageIO.read(new File("src/image/knight_white50.png"));
            bishop_w = ImageIO.read(new File("src/image/bishop_white50.png"));
            queen_w = ImageIO.read(new File("src/image/queen_white50.png"));
            king_w = ImageIO.read(new File("src/image/king_white50.png"));
            
            pawn_b = ImageIO.read(new File("src/image/pawn_black50.png"));
            rook_b = ImageIO.read(new File("src/image/rook_black50.png"));
            knight_b = ImageIO.read(new File("src/image/knight_black50.png"));
            bishop_b = ImageIO.read(new File("src/image/bishop_black50.png"));
            queen_b = ImageIO.read(new File("src/image/queen_black50.png"));
            king_b = ImageIO.read(new File("src/image/king_black50.png"));
            whiteLabel = new JLabel[16];
            blackLabel = new JLabel[16];
            for(int i = 0; i<16; i++){
                whiteLabel[i] = new JLabel();
                blackLabel[i] = new JLabel();
            }
            whiteLabel1 = new JLabel("White:");
            whiteLabel1.setSize(50, 50);
            whiteLabel1.setLocation(660, 0);
            whiteLabel1.setVisible(true);
        
            blackLabel1 = new JLabel("Black:");
            blackLabel1.setSize(50, 50);
            blackLabel1.setLocation(660, 150);
            blackLabel1.setVisible(true);
            game.getFrame().add(whiteLabel1);
            game.getFrame().add(blackLabel1);
        }
        catch(IOException e){
            
        }
    }
    
    public void taken(ChessPiece temp){
        String a[] = temp.toString().split(" ");
        
        if(a[0].equals("white")){
            whiteLabel[game.getWhiteCounter()].setIcon(new ImageIcon(getWhiteImage(a[1])));
            whiteLabel[game.getWhiteCounter()].setLocation(game.getCoordinate().getWhiteX(game.getWhiteCounter()), game.getCoordinate().getWhiteY(game.getWhiteCounter()));
            whiteLabel[game.getWhiteCounter()].setSize(50, 50);
            whiteLabel[game.getWhiteCounter()].setVisible(true);
            game.getChessBoard().add(whiteLabel[game.getWhiteCounter()]);
            game.setWhiteCounter(game.getWhiteCounter()+1);
        }
        else if (a[0].equals("black")){
            blackLabel[game.getBlackCounter()].setIcon(new ImageIcon(getBlackImage(a[1])));
            blackLabel[game.getBlackCounter()].setLocation(game.getCoordinate().getBlackX(game.getBlackCounter()), game.getCoordinate().getBlackY(game.getBlackCounter()));
            blackLabel[game.getBlackCounter()].setSize(50, 50);
            blackLabel[game.getBlackCounter()].setVisible(true);
            game.getChessBoard().add(blackLabel[game.getBlackCounter()]);
            game.setBlackCounter(game.getBlackCounter()+1);
        }
    }
    
    public Image getWhiteImage(String s){
        if(s.equals("Pawn"))
            return pawn_w;
        else if(s.equals("Bishop"))
            return bishop_w;
        else if(s.equals("King"))
            return king_w;
        else if(s.equals("Knight"))
            return knight_w;
        else if(s.equals("Queen"))
            return queen_w;
        else if(s.equals("Rook"))
            return rook_w;
        return null;
    }
    public Image getBlackImage(String s){
        if(s.equals("Pawn"))
            return pawn_b;
        else if(s.equals("Bishop"))
            return bishop_b;
        else if(s.equals("King"))
            return king_b;
        else if(s.equals("Knight"))
            return knight_b;
        else if(s.equals("Queen"))
            return queen_b;
        else if(s.equals("Rook"))
            return rook_b;
        return null;
    }
}
