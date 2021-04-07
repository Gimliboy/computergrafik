package script.demos.java2d; /**
* Beispiel Java 2D Zusammengesetzte Objekte
*
*  @author Juergen Thome
* Vorlesung Computergrafik DHBW Heidenheim
* 
* History
* 	Intial Version 01/2015
*/

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;

public class Zusammengesetze_Objekte extends Frame 
{
	
	private static final long serialVersionUID = 1L;
	private int windowHeight;
	
	Zusammengesetze_Objekte(int height)
	  {
		//Ermoeglicht das Schliessen des Fensters
		     addWindowListener(
		         new WindowAdapter() 
		         {
		            public void windowClosing(WindowEvent event)
		            {
		               System.exit(0);
		            }
		         }
		         );
	    setWindowHeight(height);
	  }
		

	public void paint (Graphics g) 
		{
			Graphics2D g2d = (Graphics2D) g;
					
				// Erzeugen verschiedener Shapes und Kompositionen		
				Shape shapeRR_1 = new RoundRectangle2D.Double
				         (10.0d, 30.0d, 50.0d, 30.0d, 70.0d, 10.0d);
				Area areaRR_1 = new Area(shapeRR_1);
				Shape shapeE_1 = new Ellipse2D.Double
				         (30.0d, 40.0d, 50.0d, 30.0d);
				Area areaE_1 = new Area(shapeE_1);
				
				g2d.setPaint(Color.gray);
				g2d.fill(areaRR_1);
				g2d.fill(areaE_1);
				g2d.setStroke(new BasicStroke(2));
				g2d.setPaint(Color.blue);	
				g2d.draw(areaRR_1);
				g2d.draw(areaE_1);
				
				Shape shapeRR_2 = new RoundRectangle2D.Double
				         (110.0d, 30.0d, 50.0d, 30.0d, 70.0d, 10.0d);
				Area areaRR_2 = new Area(shapeRR_2);
				Shape shapeE_2 = new Ellipse2D.Double
				         (130.0d, 40.0d, 50.0d, 30.0d);
				Area areaE_2 = new Area(shapeE_2);
				
							
				areaRR_2.add(areaE_2);
				
				g2d.setPaint(Color.gray);
				g2d.fill(areaRR_2);
				g2d.setStroke(new BasicStroke(2));
				g2d.setPaint(Color.blue);	
				g2d.draw(areaRR_2);

				Shape shapeRR_3 = new RoundRectangle2D.Double
				         (110.0d, 120.0d, 50.0d, 30.0d, 70.0d, 10.0d);
				Area areaRR_3 = new Area(shapeRR_3);
				Shape shapeE_3 = new Ellipse2D.Double
				         (130.0d, 130.0d, 50.0d, 30.0d);
				Area areaE_3 = new Area(shapeE_3);
				
							
				areaRR_3.exclusiveOr(areaE_3);
				
				g2d.setPaint(Color.gray);
				g2d.fill(areaRR_3);
				g2d.setStroke(new BasicStroke(2));
				g2d.setPaint(Color.blue);	
				g2d.draw(areaRR_3);				
				
				
				Shape shapeRR_4 = new RoundRectangle2D.Double
				         (10.0d, 120.0d, 50.0d, 30.0d, 70.0d, 10.0d);
				Area areaRR_4 = new Area(shapeRR_4);
				Shape shapeE_4 = new Ellipse2D.Double
				         (30.0d, 130.0d, 50.0d, 30.0d);
				Area areaE_4 = new Area(shapeE_4);
				
							
				areaRR_4.subtract(areaE_4);
				
				g2d.setPaint(Color.gray);
				g2d.fill(areaRR_4);
				g2d.setStroke(new BasicStroke(2));
				g2d.setPaint(Color.blue);	
				g2d.draw(areaRR_4);
				
				
				Shape shapeRR_5 = new RoundRectangle2D.Double
				         (60.0d, 70.0d, 50.0d, 30.0d, 70.0d, 10.0d);
				Area areaRR_5 = new Area(shapeRR_5);
				Shape shapeE_5 = new Ellipse2D.Double
				         (80.0d, 80.0d, 50.0d, 30.0d);
				Area areaE_5 = new Area(shapeE_5);
				
							
				areaRR_5.intersect(areaE_5);
				
				g2d.setPaint(Color.gray);
				g2d.fill(areaRR_5);
				g2d.setStroke(new BasicStroke(2));
				g2d.setPaint(Color.blue);	
				g2d.draw(areaRR_5);			
			}
		
	
	public static void main(String[] argv)
	{
		int height = 300;
		Zusammengesetze_Objekte f = new Zusammengesetze_Objekte(height);
		f.setTitle("Beispiel Zusammengesetzte Objekte");
		f.setSize(400,height);
		f.setVisible(true);
	}


	public int getWindowHeight() {
		return windowHeight;
	}


	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}
}