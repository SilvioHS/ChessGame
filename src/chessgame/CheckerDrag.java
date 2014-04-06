/*
This is another test code. It is not related to ChessGame project!!!!!!
//Modifed by Qilin Fu 04/03/2014
 */

package chessgame;
import java.awt.*;
import java.awt.event.*;
//This is another test code. It is not related to ChessGame project!!!!!!
//Modifed by Qilin Fu 04/03/2014
public class CheckerDrag extends java.applet.Applet
{
   // Dimension of checkerboard square.
   // 棋盘上每个小方格的尺寸

   final static int SQUAREDIM = 30;

   // Dimension of checkerboard -- includes black outline.
   // 棋盘的尺寸 – 包括黑色的轮廓线

   final static int BOARDDIM = 8 * SQUAREDIM + 2;

   // Dimension of checker -- 3/4 the dimension of a square.
   // 棋子的尺寸 – 方格尺寸的3/4

   final static int CHECKERDIM = 3 * SQUAREDIM / 4;

   // Square colors are dark green or white.
   // 方格的颜色为深绿色或者白色

   final static Color darkGreen = new Color (0, 128, 0);

   // Dragging flag -- set to true when user presses mouse button over checker
   // and cleared to false when user releases mouse button.
   // 拖动标记 --当用户在棋子上按下鼠标按键时设为true，
   // 释放鼠标按键时设为false

   boolean inDrag = false;

   // Left coordinate of checkerboard's upper-left corner.
   // 棋盘左上角的左方向坐标

   int boardx;

   // Top coordinate of checkerboard's upper-left corner.
   //棋盘左上角的上方向坐标

   int boardy;

   // Left coordinate of checker rectangle origin (upper-left corner).
   // 棋子矩形原点(左上角)的左方向坐标

   int ox;

   // Top coordinate of checker rectangle origin (upper-left corner).
   // 棋子矩形原点(左上角)的上方向坐标

   int oy;

   // Left displacement between mouse coordinates at time of press and checker
   // rectangle origin.
   // 在按键时的鼠标坐标与棋子矩形原点之间的左方向位移

   int relx;

   // Top displacement between mouse coordinates at time of press and checker
   // rectangle origin.
   // 在按键时的鼠标坐标与棋子矩形原点之间的上方向位移

   int rely;

   // Width of applet drawing area.
   // applet绘图区域的宽度

   int width;

   // Height of applet drawing area.
   // applet绘图区域的高度

   int height;

   // Image buffer.
   // 图像缓冲

   Image imBuffer;

   // Graphics context associated with image buffer.
   // 图像缓冲相关联的图形背景

   Graphics imG;

   public void init ()
   {
      // OBTain the size of the applet's drawing area.
      // 获取applet绘图区域的尺寸

      width = getSize ().width;
      height = getSize ().height;

      // Create image buffer.
      // 创建图像缓冲

      imBuffer = createImage (width, height);

      // Retrieve graphics context associated with image buffer.
      // 取出图像缓冲相关联的图形背景

      imG = imBuffer.getGraphics ();

      // Initialize checkerboard's origin, so that board is centered.
      // 初始化棋盘的原点，使棋盘在屏幕上居中

      boardx = (width - BOARDDIM) / 2 + 1;
      boardy = (height - BOARDDIM) / 2 + 1;

      // Initialize checker's rectangle's starting origin so that checker is
      // centered in the square located in the top row and second column from
      // the left.
      // 初始化棋子矩形的起始原点，使得棋子在第一行左数第二列的方格里居中

      ox = boardx + SQUAREDIM + (SQUAREDIM - CHECKERDIM) / 2 + 1;
      oy = boardy + (SQUAREDIM - CHECKERDIM) / 2 + 1;

      // Attach a mouse listener to the applet. That listener listens for
      // mouse-button press and mouse-button release events.
      // 向applet添加一个用来监听鼠标按键的按下和释放事件的鼠标监听器

      addMouseListener (new MouseAdapter ()
                        {
                            public void mousePressed (MouseEvent e)
                            {
                               // Obtain mouse coordinates at time of press.
                               // 获取按键时的鼠标坐标

                               int x = e.getX ();
                               int y = e.getY ();

                               // If mouse is over draggable checker at time
                               // of press (i.e., contains (x, y) returns
                               // true), save distance between current mouse
                               // coordinates and draggable checker origin
                               // (which will always be positive) and set drag
                               // flag to true (to indicate drag in progress).
                               // 在按键时如果鼠标位于可拖动的棋子上方
                                   // （也就是contains (x, y)返回true），则保存当前
                                   // 鼠标坐标与棋子的原点之间的距离（始终为正值）并且
                                   // 将拖动标志设为true（用来表明正处在拖动过程中）

                               if (contains (x, y))
                               {
                                   relx = x - ox;
                                   rely = y - oy;
                                   inDrag = true;
                               }
                            }

                            boolean contains (int x, int y)
                            {
                               // Calculate center of draggable checker.
                               // 计算棋子的中心位置

                               int cox = ox + CHECKERDIM / 2;
                               int coy = oy + CHECKERDIM / 2;

                               // Return true if (x, y) locates with bounds
                               // of draggable checker. CHECKERDIM / 2 is the
                               // radius.
                                   // 如果(x, y)仍处于棋子范围内则返回true
                                   // CHECKERDIM / 2为半径

                               return (cox - x) * (cox - x) +
                                      (coy - y) * (coy - y) <
                                      CHECKERDIM / 2 * CHECKERDIM / 2;
                            }

                            public void mouseReleased (MouseEvent e)
                            {
                               // When mouse is released, clear inDrag (to
                               // indicate no drag in progress) if inDrag is
                               // already set.
                                   // 当鼠标按键被释放时，如果inDrag已经为true，
                                   // 则将其置为false（用来表明不在拖动过程中）

                               if (inDrag)
                                   inDrag = false;
                            }
                        });

      // Attach a mouse motion listener to the applet. That listener listens
      // for mouse drag events.
       //向applet添加一个用来监听鼠标拖动事件的鼠标运动监听器

      addMouseMotionListener (new MouseMotionAdapter ()
                              {
                                  public void mouseDragged (MouseEvent e)
                                  {
                                     if (inDrag)
                                     {
                                         // Calculate draggable checker's new
                                         // origin (the upper-left corner of
                                         // the checker rectangle).
                                               // 计算棋子新的原点（棋子矩形的左上角）

                                         int tmpox = e.getX () - relx;
                                         int tmpoy = e.getY () - rely;

                                         // If the checker is not being moved
                                         // (at least partly) off board, 
                                         // assign the previously calculated
                                         // origin (tmpox, tmpoy) as the
                                         // permanent origin (ox, oy), and
                                         // redraw the display area (with the
                                         // draggable checker at the new
                                         // coordinates).
                                               // 如果棋子（至少是棋子的一部分）没有被
                                               // 移出棋盘，则将之前计算的原点
                                               // (tmpox, tmpoy)赋值给永久性的原点(ox, oy)，
                                               // 并且刷新显示区域（此时的棋子已经位于新坐标上）

                                         if (tmpox > boardx &&
                                             tmpoy > boardy &&
                                             tmpox + CHECKERDIM
                                             < boardx + BOARDDIM &&
                                             tmpoy + CHECKERDIM
                                             < boardy + BOARDDIM)
                                         {
                                             ox = tmpox;
                                             oy = tmpoy;
                                             repaint ();
                                         }
                                     }
                                  }
                              });
   }

   public void paint (Graphics g)
   {
      // Paint the checkerboard over which the checker will be dragged.
       // 在棋子将要被拖动的位置上绘制棋盘

      paintCheckerBoard (imG, boardx, boardy);

      // Paint the checker that will be dragged.
       // 绘制即将被拖动的棋子

      paintChecker (imG, ox, oy);

      // Draw contents of image buffer.
       // 绘制图像缓冲的内容

      g.drawImage (imBuffer, 0, 0, this);
   }

   void paintChecker (Graphics g, int x, int y)
   {
      // Set checker shadow color.
       // 设置棋子阴影的颜色

      g.setColor (Color.black);

      // Paint checker shadow.
       // 绘制棋子的阴影

      g.fillOval (x, y, CHECKERDIM, CHECKERDIM);

      // Set checker color.
       // 设置棋子颜色

      g.setColor (Color.red);

      // Paint checker.
       // 绘制棋子

      g.fillOval (x, y, CHECKERDIM - CHECKERDIM / 13, CHECKERDIM - CHECKERDIM / 13);
   }

   void paintCheckerBoard (Graphics g, int x, int y)
   {
      // Paint checkerboard outline.
       // 绘制棋盘轮廓线

      g.setColor (Color.black);
      g.drawRect (x, y, 8 * SQUAREDIM + 1, 8 * SQUAREDIM + 1);

      // Paint checkerboard.
       // 绘制棋盘

      for (int row = 0; row < 8; row++)
      {
           g.setColor (((row & 1) != 0) ? darkGreen : Color.white);

           for (int col = 0; col < 8; col++)
           {
                g.fillRect (x + 1 + col * SQUAREDIM, y + 1 + row * SQUAREDIM,
                            SQUAREDIM, SQUAREDIM);

                g.setColor ((g.getColor () == darkGreen) ? Color.white :
                            darkGreen);
           }
      }
   }
   
   // The AWT invokes the update() method in response to the repaint() method
   // calls that are made as a checker is dragged. The default implementation
   // of this method, which is inherited from the Container class, clears the
   // applet's drawing area to the background color prior to calling paint().
   // This clearing followed by drawing causes flicker. CheckerDrag overrides
   // update() to prevent the background from being cleared, which eliminates
   // the flicker.
   // AWT调用了update()方法来响应拖动棋子时所调用的repaint()方法。该方法从
   // Container类继承的默认实现会在调用paint()之前，将applet的绘图区域清除
   // 为背景色，这种绘制之后的清除就导致了闪烁。CheckerDrag重写了update()来
   // 防止背景被清除，从而消除了闪烁。

   public void update (Graphics g)
   {
      paint (g);
   }
} 