/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;



import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;


/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
public class Menu implements ActionListener{
    
    ChessGame game;
        
    public Menu(ChessGame g){
        game = g;
    }
    
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("New",KeyEvent.VK_N);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load", KeyEvent.VK_L);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        
        menu.addSeparator();

        //a submenu
        submenu = new JMenu("Preferences");
        submenu.setMnemonic(KeyEvent.VK_P);
        menu.add(submenu);

        cbMenuItem = new JCheckBoxMenuItem("Enable TurnTimer");
        cbMenuItem.setMnemonic(KeyEvent.VK_T);
        submenu.add(cbMenuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Quit", KeyEvent.VK_L);
        menu.add(menuItem);

        
        
        //Player menu
        menu = new JMenu("Player");
        menu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(menu);
        
        //white submenu
        submenu = new JMenu("White");
        submenu.setMnemonic(KeyEvent.VK_W);
        menu.add(submenu);
        
        menuItem = new JMenuItem("Undo", KeyEvent.VK_U);
        submenu.add(menuItem);
        
        menuItem = new JMenuItem("Redo", KeyEvent.VK_R);
        submenu.add(menuItem);
        
        //black submenu
        submenu = new JMenu("Black");
        submenu.setMnemonic(KeyEvent.VK_B);
        menu.add(submenu);
        
        menuItem = new JMenuItem("Undo", KeyEvent.VK_U);
        submenu.add(menuItem);
        
        menuItem = new JMenuItem("Redo", KeyEvent.VK_R);
        submenu.add(menuItem);
        
        //Track menu
        menu = new JMenu("Track");
        menu.setMnemonic(KeyEvent.VK_T);
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load", KeyEvent.VK_L);
        menu.add(menuItem);
        
        //View Menu
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_T);
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Player Stats", KeyEvent.VK_S);
        menu.add(menuItem);

        menuItem = new JMenuItem("Full Screen", KeyEvent.VK_F);
        menu.add(menuItem);
        
        //View Menu
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Documentation", KeyEvent.VK_D);
        menu.add(menuItem);

        menuItem = new JMenuItem("About", KeyEvent.VK_A);
        menu.add(menuItem);
        
        

        return menuBar;
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("New")){
            System.out.println("New Game");
            game.newGame();
        } else if(e.getActionCommand().equals("Save")){
            System.out.println("Clear Game");
            //game.clearGame();
        }
        
    }
    
}