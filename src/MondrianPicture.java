import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MondrianPicture {
	static int LOOPS = 5; //depending on how many loops there are, there will be more rectangles (4 will produce 16, 5 will produce 32, 6 will produce 64, etc.)
	static int RECTANGLES = (int) Math.pow(2, LOOPS); //since every loop will split every rectangle in half, the total number of rectangles in the final painting will be 2^(# of loops)
    static int[][] arr = new int[RECTANGLES][4]; //the array will contain 1 line for each rectangle with x, y, width, height
    final static int WIDTH = 800; //Width and Height are used for JFrame and the initial rectangle
    final static int HEIGHT = 600;
    MonPaintComponent paint; //just needed this as a class variable
   
    
    public MondrianPicture() {
    	
    	 JFrame frame = new JFrame();
         frame.setSize(WIDTH, HEIGHT); 
         frame.setTitle("Mondrian Painting");
         frame.setResizable(false);
         frame.setFocusable(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         recursion(0,0,WIDTH,HEIGHT,0);
         
         paint = new MonPaintComponent(arr, LOOPS, RECTANGLES);
         frame.add(paint);
         
         
        KeyListener input = new keyInputClass();
        frame.addKeyListener(input);
         
         frame.setVisible(true);
         
    }
    
    public static void main(String[] args) {
        MondrianPicture painting = new MondrianPicture();
       
        
    }
    
    public class keyInputClass implements KeyListener{
    	public void keyPressed(KeyEvent e) {
    		int keyCode = e.getKeyCode();
    		if (keyCode==e.VK_C) {
    			System.out.println("Color Changed");
    			paint.repaint();
    		}
    		if (keyCode==e.VK_SPACE) {
    			System.out.println("New Mondrian");
    			for(int i = 0; i < RECTANGLES; i++) { //have to set the array back to all 0, so that it will get filled again
    				arr[i][0]=0;
    				arr[i][1]=0;
    				arr[i][2]=0;
    				arr[i][3]=0;
    			}
    			recursion(0,  0, WIDTH, HEIGHT, 0); //redo the recursion method, which will recreate a new array
    			paint.repaint();//repaint 

    			
    		}
    	}
    	public void keyReleased(KeyEvent e) {
    			
    	}
    	public void keyTyped(KeyEvent e) {
    		
    	}
    }
    
    public static void addRect(int inX, int inY, int inWidth, int inHeight)
    {
     System.out.println("New Rectangle: " +  + inX + " " + inY + " "  + inWidth + " " + inHeight);
     for (int i = 0; i<RECTANGLES; i++) {
        if (arr[i][2]==0 && arr[i][3] ==0) 
            {
            arr[i][0] = inX;
            arr[i][1] = inY;
            arr[i][2] = inWidth;
            arr[i][3] = inHeight;
            break;
            }
     	}
    }
    
    private static void recursion(int x, int y, int width, int height, int loopNumber) {
	
    	if (loopNumber == LOOPS){
    		addRect(x, y, width, height);
        }
    	else{
	
    		//calculate new values for next two rectangles
    		Random random = new Random();
        
    		boolean cutWidth;
    		int cutSize = 0;
			
    		int cutWidthNumber = random.nextInt(2);
    		if (cutWidthNumber == 0) cutWidth = false;
    		else cutWidth = true;
			
    		int cutSizeNumber = random.nextInt(4);
    		if (cutSizeNumber == 0) cutSize = 1;
    		else if (cutSizeNumber == 1) cutSize = 2;
    		else if (cutSizeNumber == 2) cutSize = 3;
    		else if (cutSizeNumber == 3) cutSize = 4;
	
        
    		loopNumber++;
        
    		if (cutWidth) {
    			recursion(x, y, (width * cutSize / 5), height, loopNumber);//these are when the x gets cut
    			recursion((x + width * cutSize / 5), y, (width - width * cutSize / 5) , height, loopNumber);
    		}else{
    			recursion(x, y, width, (height * cutSize/5), loopNumber);
    			recursion(x, (y + height * cutSize/5), width, (height - height * cutSize/5), loopNumber);
    		}
        }
    }

}
	
