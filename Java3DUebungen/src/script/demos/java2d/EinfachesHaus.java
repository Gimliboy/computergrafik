package script.demos.java2d;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class EinfachesHaus
extends JFrame
{
   /**
	 * 
	 */
	private static final long serialVersionUID = -3442093668654530485L;

	public static void main(String[] args)
   {
      @SuppressWarnings("unused")
      EinfachesHaus wnd = new EinfachesHaus();
   }

   public EinfachesHaus()
   {
      super("Simple Home");
      addWindowListener(
         new WindowAdapter() {
            public void windowClosing(WindowEvent event)
            {
               System.exit(0);
            }
         }
      );
      setBackground(Color.white);
	  setForeground(Color.blue);
      setSize(400,400);
      setVisible(true);
   }

   public void paint(Graphics g)
   {
   	  Graphics2D g2 = (Graphics2D) g;
	  
      Point A = new Point(50, 350);
      Point B = new Point(250, 350);
      Point C = new Point(250, 200);
      Point D = new Point(50, 200);
      Point E = new Point(150, 100);
      
      g2.drawLine(A.x, A.y, B.x, B.y);
	  g2.drawLine(B.x, B.y, C.x, C.y);
	  g2.drawLine(C.x, C.y, D.x, D.y);
	  g2.drawLine(D.x, D.y, A.x, A.y);
	  g2.drawLine(D.x, D.y, E.x, E.y);
	  g2.drawLine(E.x, E.y, C.x, C.y);
   }
}