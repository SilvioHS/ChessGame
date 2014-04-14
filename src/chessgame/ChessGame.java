package chessgame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
/**
 *
 * @author Qilin Fu
 */
public class ChessGame extends JApplet implements Runnable { 
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
    public static void main(String[] args) {  
          ChessGame demo = new ChessGame();
          JFrame frame = new JFrame();
        frame.getContentPane().add(new ChessBoard());
        frame.setSize(660, 690);
        frame.setVisible(true);
    }  
}