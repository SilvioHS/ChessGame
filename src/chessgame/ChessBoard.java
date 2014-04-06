/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import java.io.*;
import java.net.URL;

/**
 *
 * @author Qilin Fu
 */
public class ChessBoard {

    JLabel Pic; //image,for drag and drop
    JFrame frame;
    JPanel panel;
    //Image img;
    int xx, yy; //coordinate fits for each chessquare
    //ChessPiece[] pieces = new ChessPiece[64];
    //ChessPiece p = new Pawn(img, "black", 31);

    public ChessBoard() {
        //pieces[p.boardPosition] = p;
        //System.out.println(pieces[0].getColor());
        frame = new JFrame("Chess");
        Pic = new JLabel(new ImageIcon("src/image/chess_pieces_Queen80.jpg"));
        Pic.setBorder(BorderFactory.createLineBorder(Color.black));
        panel = new JPanel() {

            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon("src/image/ChessBroad80X80.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());
                frame.setSize(660, 690);

            }

        };

        //panel.setBackground(Color.white);
        panel.add(Pic);

        //
        DragPicListener listener = new DragPicListener();  //MouseListener
        Pic.addMouseListener(listener);  //add mouselistener to image
        Pic.addMouseMotionListener(listener);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class DragPicListener implements MouseInputListener {

        Point point = new Point(0, 0); //get coordinate point

        public void mousePressed(MouseEvent e) {
            point = SwingUtilities.convertPoint(Pic, e.getPoint(), Pic.getParent()); //get current coordinate point

        }

        public void mouseDragged(MouseEvent e) {
            Point newPoint = SwingUtilities.convertPoint(Pic, e.getPoint(), Pic.getParent()); //change coordinate system
            xx = (Pic.getX() + (newPoint.x - point.x)) / 80;
            yy = (Pic.getY() + (newPoint.y - point.y)) / 80;
//         Pic.setLocation(xxxx*80,yyyy*80);
            Pic.setLocation(Pic.getX() + (newPoint.x - point.x), Pic.getY() + (newPoint.y - point.y)); //set image new location
            point = newPoint; //更改坐标点
            //System.out.println("X:" + e.getX() + " Y: " +e.getY());
        }

        public void mouseReleased(MouseEvent e) {
            Pic.setLocation(xx * 80, yy * 80);//set image new location
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
//   public static void main(String[] args) 
//    {
//          new ChessBoard();
//   } 
}
