package script.demos.java2d;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;

/**
* Beispiel der Grafikobjekte in 2D
*
*  @author Juergen Thome
* Vorlesung Computergrafik DHBW Heidenheim
* 
* History
* 	Intial Version 01/2015
*/
public class Grafik_Objekte_2D extends Frame
{
	private static final long serialVersionUID = 1L;
	private int windowHeight;

  /**
  * Konstruktor
  *
  * @param height  Dieser Wert sollte die Hoehe des Fensters angeben.
  */
	Grafik_Objekte_2D(int height)
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
    windowHeight = height;
  }


  public void paint(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;

    /*In yUp wird die Translation definiert, mit der Objekte in "realen"
      Koordinaten, bei denen die y-Achse nach oben zeigt, so in das
      Fenster gezeichnet werden, dass der Koordinatenursprung sich links
      unten im Fenster im Fensterkoordinatenpunkt (40,windowHeight-50)
      befindet.
    */
    AffineTransform yUp = new AffineTransform();
    yUp.setToScale(1,-1);
    AffineTransform translate = new AffineTransform();
    translate.setToTranslation(40,windowHeight-50);
    yUp.preConcatenate(translate);

    //Wende die Transformation auf das Graphics2D-Objekt an. Damit wird
    //alles in realen Koordinaten gezeichnet.
    g2d.transform(yUp);
    
    //Die Linien sollen eine Dicke von 3.0 statt 1.0 haben.
    g2d.setStroke(new BasicStroke(3.0f));
    
  //Zeichne eine Linie
    g2d.setColor(new Color(0,0,0));
    Line2D.Double line = new Line2D.Double(10,10,100,60);
    g2d.draw(line);
    
    //Erzeuge ein Rechteck
    g2d.setColor(new Color(0,0,255));
    Rectangle2D.Double rect = new Rectangle2D.Double(200,20,100,60);
    g2d.draw(rect);
    
    //Erzeuge ein abgerundetes Rechteck
    g2d.setColor(new Color(0,255,255));
    RoundRectangle2D.Double rrect = new RoundRectangle2D.Double(50, 100, 100, 60, 10, 10);
    g2d.draw(rrect);
    
    //Erzeuge eine Ellipse
    g2d.setColor(new Color(0,255,0));
    Ellipse2D.Double elli = new Ellipse2D.Double(250, 200, 100, 60);
    g2d.draw(elli);
    
    //Erzeuge einen Ellipseausschnitt
    g2d.setColor(new Color(255,0,255));
    Arc2D.Double arcelli = new Arc2D.Double(50, 200, 100, 60,45,90, Arc2D.PIE);
    g2d.draw(arcelli);
    
  }



 

  public static void main(String[] argv)
  {
    int height = 400;
    Grafik_Objekte_2D f = new Grafik_Objekte_2D(height);
    f.setTitle("Beispiel Grafikobjekte");
    f.setSize(500,height);
    f.setVisible(true);
  }
}
