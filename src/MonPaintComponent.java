/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.BasicStroke;
import java.util.Random;

public class MonPaintComponent extends JComponent{

private int x;
private int y;
private int width;
private int height;
int loops;
int rectangles;
private Color color;
private Color lineColor = Color.BLACK;
private int[][] arr = new int[16][4];

public MonPaintComponent(int[][] inArr, int loops, int rectangles)
    {
     arr = inArr;
     this.loops = loops;
     this.rectangles = rectangles;

    }
    
public MonPaintComponent()
    {
     for (int i = 0; i < rectangles; i++)
        {
         for (int j = 0; j < 4; j++)
            {
             arr[i][j] = 0;
            }
        }
    }
    
public void paintComponent(Graphics g)
    {
    Graphics2D g2 = (Graphics2D) g;
    for (int i = 0; i < rectangles; i++)
        {
         for (int j = 0; j < 4; j++)
            {
             if (j == 0) x = arr[i][j];
             if (j == 1) y = arr[i][j];
             if (j == 2) width = arr[i][j];
             else height = arr[i][j];
            }
         //System.out.println("Final Rectangle: " + x + " " + y + " " + width + " " + height);
         
         g2.setColor(lineColor);
         g2.setStroke(new BasicStroke(10));
         g2.drawRect(x, y, width, height);
         
         Random RNG = new Random();
         int colorNumber = RNG.nextInt(7);
         if (colorNumber == 0) color = Color.WHITE;
         else if (colorNumber == 1) color = Color.WHITE;
         else if (colorNumber == 2) color = Color.WHITE;
         else if (colorNumber == 3) color = Color.BLUE;
         else if (colorNumber == 4) color = Color.RED;
         else if (colorNumber == 5) color = Color.YELLOW;
         else if (colorNumber == 6) color = Color.BLACK;
         
         g2.setColor(color);
         g2.fillRect(x, y, width, height);
        }
    }


}
