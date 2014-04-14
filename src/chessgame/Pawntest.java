/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author xiaoweii
 * 
 */

//Just for testing dragging piece, delete or ignore it as you want, by Qilin Fu.
public class Pawntest extends JButton {
    private Image pawnImage;
    
    public Pawntest() {
        try {
            pawnImage = ImageIO.read(new File("src/image/chess_pieces_Queen80.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setBorder(BorderFactory.createRaisedBevelBorder());
	this.setBorderPainted(false);
	this.setOpaque(false);
        this.setSize(80, 80);
        this.setIcon(new ImageIcon(pawnImage));
    }
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(pawnImage, 0, 0, this);
//    }
}
