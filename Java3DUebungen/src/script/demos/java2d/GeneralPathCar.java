package script.demos.java2d;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;

/**
* Beispiel Java 2D Zusammengesetzte Objekte am Beispiel eines Autos
*
*  @author Juergen Thome
* Vorlesung Computergrafik DHBW Heidenheim
* 
* History
* 	Intial Version 01/2015
*/
public class GeneralPathCar extends Frame
{
  //Konstruktor
	private static final long serialVersionUID = 1L;
	private int windowHeight;
	
  GeneralPathCar(int height)
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


  public void paint(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;

    //Verwendung von Antialiasing, um die Raender weniger ausgefranst
    //erscheinen zu lassen
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    //Die Linien sollen eine Dicke von 3.0 statt 1.0 haben.
    BasicStroke bs = new BasicStroke(3.0f);
    g2d.setStroke(bs);

    //Der GeneralPath, der das Auto beschreiben soll
    GeneralPath gp = new GeneralPath();

    //Beginne an der vorderen unteren Ecke des Autos
    gp.moveTo(60,120);
    //vorderer Unterboden
    gp.lineTo(80,120);
    //Vorderreifen
    gp.quadTo(90,140,100,120);
    //mittlerer Unterboden
    gp.lineTo(160,120);
    //Hinterreifen
    gp.quadTo(170,140,180,120);
    //hinterer Unterboden
    gp.lineTo(200,120);
    //Heck
    gp.curveTo(195,100,200,80,160,80);
    //Dach
    gp.lineTo(110,80);
    //Frontscheibe
    gp.lineTo(90,100);
    //Motorhaube
    gp.lineTo(60,100);
    //Front
    gp.lineTo(60,120);

    //Zeichne das Auto
    g2d.draw(gp);


    g2d.setStroke(new BasicStroke(1.0f));
    //Einzeichnen des Koordinatensystems
    drawSimpleCoordinateSystem(200,150,g2d);

  }



  /**
  * Zeichnet ein Fensterkoordinatensystem.
  *
  * @param xmax     x-Koordinate, bis zu der die x-Achse reichen soll
  * @param ymax     y-Koordinate, bis zu der die y-Achse reichen soll
  * @param g2d      Graphics2D-Objekt, auf das gezeichnet werden soll.
  */
  public static void drawSimpleCoordinateSystem(int xmax, int ymax,
                                                Graphics2D g2d)
  {
    int xOffset = 30;
    int yOffset = 50;
    int step = 20;
    String s;
    //aktuellen Font merken
    Font fo = g2d.getFont();
    //einen kleinen Font einstellen
    g2d.setFont(new Font("sansserif",Font.PLAIN,9));
    //x-Achse
    g2d.drawLine(xOffset,yOffset,xmax,yOffset);
    //Markierungen und Beschriftung der x-Achse
    for (int i=xOffset+step; i<=xmax; i=i+step)
    {
      g2d.drawLine(i,yOffset-2,i,yOffset+2);
      g2d.drawString(String.valueOf(i),i-7,yOffset-7);
    }

    //y-Achse
    g2d.drawLine(xOffset,yOffset,xOffset,ymax);

    //Markierungen und Beschriftung der y-Achse
    s="  "; //zum Einruecken der Zahlen < 100
    for (int i=yOffset+step; i<=ymax; i=i+step)
    {
      g2d.drawLine(xOffset-2,i,xOffset+2,i);
      if (i>99){s="";}
      g2d.drawString(s+String.valueOf(i),xOffset-25,i+5);
    }

    //Font zurueck setzen
    g2d.setFont(fo);
  }


   public static void main(String[] argv)
  {
	int height = 300;
    GeneralPathCar f = new GeneralPathCar(height);
    f.setTitle("GeneralPath-Beispiel");
    f.setSize(250,height);
    f.setVisible(true);
  }


public int getWindowHeight() {
	return windowHeight;
}


public void setWindowHeight(int windowHeight) {
	this.windowHeight = windowHeight;
}
}

