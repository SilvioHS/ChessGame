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
import java.awt.BorderLayout; 
/**
 *
 * @author xiaoweii
 */
public class ChessBoard extends JPanel {
    private Image image;
    
    public ChessBoard() {
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
}
