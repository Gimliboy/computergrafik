package script.demos.java2d;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;

/**
* Beispiel der Anwendung einer Rotation um 45 Grad auf ein Rechteck
*
*  @author Juergen Thome
* Vorlesung Computergrafik DHBW Heidenheim
* 
* History
* 	Intial Version 01/2015
*/
public class Rotation extends Frame
{
	private static final long serialVersionUID = 1L;
	private int windowHeight;

  /**
  * Konstruktor
  *
  * @param height  Dieser Wert sollte die Hoehe des Fensters angeben.
  */
  Rotation(int height)
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
    g2d.setColor(new Color(0,0,255));
    //Erzeuge und zeichne ein Rechteck, das rotiert werden soll.
    Rectangle2D.Double rect = new Rectangle2D.Double(100,20,100,60);
    g2d.draw(rect);

    //Definiere eine Rotation.
    AffineTransform rotation = new AffineTransform();
    rotation.setToRotation(Math.PI/4);

    //Zeichne das skalierte Rechteck gestrichelt.
    g2d.setStroke(new BasicStroke(3.0f,BasicStroke.CAP_BUTT,
                                  BasicStroke.JOIN_BEVEL,8.0f,
                                  new float[] {20.0f, 10.0f},4.0f));
    g2d.setColor(new Color(255,0,0));
    g2d.draw(rotation.createTransformedShape(rect));

     g2d.setStroke(new BasicStroke(1.0f));
     g2d.setColor(new Color(0,0,0));
    //Einzeichnen des Koordinatensystems
    drawSimpleCoordinateSystem(200,200,g2d);
  }



    /**
  * Zeichnet ein Koordinatensystem.
  *
  * @param xmax     x-Koordinate, bis zu der die x-Achse reichen soll
  * @param ymax     y-Koordinate, bis zu der die y-Achse reichen soll
  * @param g2d      Graphics2D-Objekt, auf das gezeichnet werden soll.
  */
  public static void drawSimpleCoordinateSystem(int xmax, int ymax,
                                                Graphics2D g2d)
  {
    int xOffset = 0;
    int yOffset = 0;
    int step = 20;
    String s;
    //aktuellen Font merken
    Font fo = g2d.getFont();
    //einen kleinen Font einstellen
    int fontSize = 13;
    Font fontCoordSys = new Font("serif",Font.PLAIN,fontSize);
    /*
      Leider werden die Buchstaben durch die auf das Graphics2D-Objekt
      angewendete Transformation auf dem Kopf gezeichnt. Daher: Erzeugung
      eines Fonts, bei dem die Buchstaben auf dem Kopf stehen.
    */
    //Auf den Kopf stellen durch Spiegeln.
    AffineTransform flip = new AffineTransform();
    flip.setToScale(1,-1);
    //Wieder auf die Grundlinie zurueck verschieben.
    AffineTransform lift = new AffineTransform();
    lift.setToTranslation(0,fontSize);
    flip.preConcatenate(lift);

    //Font mit auf den Kopf gestellten Buchstaben erzeugen.
    Font fontUpsideDown = fontCoordSys.deriveFont(flip);

    g2d.setFont(fontUpsideDown);

    //x-Achse
    g2d.drawLine(xOffset,yOffset,xmax,yOffset);
    //Markierungen und Beschriftung der x-Achse
    for (int i=xOffset+step; i<=xmax; i=i+step)
    {
      g2d.drawLine(i,yOffset-2,i,yOffset+2);
      g2d.drawString(String.valueOf(i),i-7,yOffset-30);
    }

    //y-Achse
    g2d.drawLine(xOffset,yOffset,xOffset,ymax);

    //Markierungen und Beschriftung der y-Achse
    s="  "; //zum Einruecken der Zahlen < 100
    for (int i=yOffset+step; i<=ymax; i=i+step)
    {
      g2d.drawLine(xOffset-2,i,xOffset+2,i);
      if (i>99){s="";}
      g2d.drawString(s+String.valueOf(i),xOffset-25,i-20);
    }

    //Font zurueck setzen
    g2d.setFont(fo);
  }


  public static void main(String[] argv)
  {
    int height = 300;
    Rotation f = new Rotation(height);
    f.setTitle("Beispiel Rotation");
    f.setSize(300,height);
    f.setVisible(true);
  }
}

