/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import java.awt.Graphics;  
import java.awt.Image;  
import java.io.File; 

//This is just a test program. It is not related to ChessGame project!!!!!
//Modifed by Qilin Fu 04/03/2014
public class Test
{
   JLabel Pic; //图片，用于拖动
   JFrame frame;
   JPanel panel;
   
   
   public Test() 
   {
   frame=new JFrame("图片的拖动");
   Pic = new JLabel(new ImageIcon("C:\\MINE\\chess_pieces_Queen80.jpg"));
   Pic.setBorder(BorderFactory.createLineBorder(Color.black)); 
      panel=new JPanel() {  
  
            protected void paintComponent(Graphics g) {  
                ImageIcon icon = new ImageIcon("C:\\MINE\\ChessBroad80X80.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),icon.getIconHeight(), icon.getImageObserver());  
                frame.setSize(660,690);  
  
            }  
  
        };
      
      //panel.setBackground(Color.white);
      panel.add(Pic);
      
      //事件
      DragPicListener listener=new DragPicListener();  //鼠标事件处理
      Pic.addMouseListener(listener);  //增加标签的鼠标事件处理
      Pic.addMouseMotionListener(listener);       
      
      frame.add(panel);
      //frame.setSize(660,690);
      frame.pack(); 
      frame.setVisible(true); 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   class DragPicListener implements MouseInputListener
   { 
      Point point=new Point(0,0); //坐标点
      int xx,yy;
      
      public void mousePressed(MouseEvent e)
      {
         point=SwingUtilities.convertPoint(Pic,e.getPoint(),Pic.getParent()); //得到当前坐标点
         //System.out.println("X:" + e.getX() + " Y: " +e.getY());
       }
      
      public void mouseDragged(MouseEvent e)
      {
         Point newPoint=SwingUtilities.convertPoint(Pic,e.getPoint(),Pic.getParent()); //转换坐标系统
         xx = (Pic.getX()+(newPoint.x-point.x))/80;
         yy = (Pic.getY()+(newPoint.y-point.y))/80;
//         Pic.setLocation(xxxx*80,yyyy*80);
         Pic.setLocation(Pic.getX()+(newPoint.x-point.x),Pic.getY()+(newPoint.y-point.y)); //设置标签图片的新位置
         point=newPoint; //更改坐标点
         //System.out.println("X:" + e.getX() + " Y: " +e.getY());
       }
      
      public void mouseReleased(MouseEvent e){
         Pic.setLocation(xx*80,yy*80);
      }
      
      public void mouseEntered(MouseEvent e){}
      
      public void mouseExited(MouseEvent e){}
      
      public void mouseClicked(MouseEvent e){}
      
      public void mouseMoved(MouseEvent e){}
   }
    public static void main(String[] args) 
    {
          //new Test();
        JFrame frame1 = new JFrame("TEST");
        ChessBoard chessboard = new ChessBoard();
        Pawntest pawn = new Pawntest();
        pawn.setSize(80, 80);
        chessboard.add(pawn);
        frame1.add(chessboard);
        frame1.setSize(660, 690);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
   } 
}
