package script.demos.java2d;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.util.Date;


/**
* Beispiel fuer eine Bewegung. Eine quadratische Uhr mit einem
* Sekundenzeiger bewegt sich ueber das Fenster.
* Der Sekundenzeiger schreitet nicht im Sekundentakt vor.
* Dieses Beispiel verwendet noch kein Double-Buffering, so dass zum
* einen das Abbrechen des Programms kaum moeglich ist und zum
* anderen das Bild teilweise flackert.
*
*  @author Juergen Thome
* Vorlesung Computergrafik DHBW Heidenheim
* 
* History
* 	Intial Version 01/2015
*/
public class NonSynchronizedClock extends Frame
{
	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;

  /**
  * Konstruktor
  *
  * @param width   Dieser Wert sollte die Breite des Fensters angeben.
  * @param height  Dieser Wert sollte die Hoehe des Fensters angeben.
  */
  NonSynchronizedClock(int width, int height)
  {
    //Ermoeglicht das Schliessen des Fensters
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

    windowWidth = width;
    windowHeight = height;
  }


  public void paint(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;

    //Die Linien sollen eine Dicke von 3.0 statt 1.0 haben.
    g2d.setStroke(new BasicStroke(3.0f));

    //Verwendung von Antialiasing, um die Raender weniger ausgefranst
    //erscheinen zu lassen
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);



    /*In yUp wird die Translation definiert, mit der Objekte in "realen"
      Koordinaten, bei denen die y-Achse nach oben zeigt, so in das
      Fenster gezeichnet werden, dass der Koordinatenursprung in der linken
      unteren Ecke im Fenster befindet.
    */
    AffineTransform yUp = new AffineTransform();
    yUp.setToScale(1,-1);
    AffineTransform translate = new AffineTransform();
    translate.setToTranslation(0,windowHeight);
    yUp.preConcatenate(translate);

    //Wende die Transformation auf das Graphics2D-Objekt an. Damit wird
    //alles in realen Koordinaten gezeichnet.
    g2d.transform(yUp);


    //Dieses Rechteck rahmt das Gesamtbild ein.
    Rectangle2D.Double windowFrame = new Rectangle2D.Double(50,50,
                                                            windowWidth-100,
                                                            windowHeight-100);


    //Seitenlaenge fuer die Uhr fuer den Rahmen der Uhr
    double frameSize = 100;

    //Laenge des Sekundenzeigers
    double handLength = 40;
    //Breite des Sekundenzeigers
    double handWidth = 5;

    //Erzeuge den Rahmen der Uhr zentriert im Koordinatenursprung
    Rectangle2D.Double clockFrame = new Rectangle2D.Double(-frameSize/2,
                                                           -frameSize/2,
                                                           frameSize,
                                                           frameSize);

    //Erzeuge den Sekundenzeiger, dessen unteres Ende zu Beginn auf dem
    //Koordinatenursprung steht.
    Rectangle2D.Double clockHand = new Rectangle2D.Double(-handWidth/2,
                                                          0,
                                                          handWidth,
                                                          handLength);

    //Diese Rotation gibt an, um welchen Winkel der Sekundenzeiger
    //in jedem Schritt weiterbewegt.
    AffineTransform singleRotation = new AffineTransform();
    singleRotation.setToRotation(-Math.PI/180);

    //In dieser Rotation werden die einzelnen Rotation akkumuliert.
    AffineTransform accumulatedRotation = new AffineTransform();


   //Diese Translation gibt an, wie weit die Uhr in jedem Schritt
   //weiterbewegt werden soll.
    AffineTransform singleTranslation = new AffineTransform();
    singleTranslation.setToTranslation(2,1);

    //In dieser Translation werden die einzelnen Translationen akkumuliert.
    AffineTransform accumulatedTranslation = new AffineTransform();
    //Damit sich die Uhr zu Beginn im Inneren des Fensters befindet,
    //soll die Translation schon am Anfang eine Verschiebung nach
    //rechts und nach oben ausueben.
    accumulatedTranslation.setToTranslation(150,150);

    //Diese Transformation soll die beiden Einzelbewegungen des
    //Sekundenzeigers (Rotation und Bewegung der Uhr) zusammenfassen.
    AffineTransform handTransform = new AffineTransform();


    //In dieser Schleife werden die Positionen der Uhr und des Zeigers
    //jeweils neu berechnet und das Bild neu gezeichnet.
    for (int i=0; i<250; i++)
    {
       //Gesamttransformation des Sekundenzeigers: Erst Rotation, dann
       //Verschiebung
       handTransform.setTransform(accumulatedRotation);
       handTransform.preConcatenate(accumulatedTranslation);

       //Loeschen des gesamten Fensters
       clearWindow(g2d);

       //Zeichnen des inneren Fensterrahmens
       g2d.draw(windowFrame);

       //Zeichnen des Rahmens der Uhr
       g2d.draw(accumulatedTranslation.createTransformedShape(clockFrame));

       //Zeichnen des Sekundenzeigers
       g2d.fill(handTransform.createTransformedShape(clockHand));

       //Berechnung der naechsten Gesamtverschiebung der Uhr
       accumulatedTranslation.preConcatenate(singleTranslation);

       //Berechnung der naechsten Gesamtrotation des Zeigers
       accumulatedRotation.preConcatenate(singleRotation);

       //Verzoegerung um 100ms bis das naechste Bild gezeichnet wird
       sustain(100);
    }



  }


  /**
  * Eine Methode zum Loeschen (weiss malen) des Fensters.
  *
  * @param g      Graphics2D-Objekt, auf das gezeichnet werden soll.
  */
  public void clearWindow(Graphics2D g)
  {
    g.setPaint(Color.white);
    g.fill(new Rectangle(0,0,windowWidth,windowWidth));
    g.setPaint(Color.black);
  }


  /**
  * Eine Methode, die eine Verzoegerung von t Millisekunden bewirkt.
  * Diese Methode wird hier nur verwendet, um das Programm moeglichst
  * einfach zu Halten. Sie hat jedoch rechenzeitintemsives aktives
  * Warten zur Folge. In realen Anwendungen sollte man Verzoegerungen
  * durch die Verwendung von Threads realisieren.
  *
  * @param t      Wartezeit in Millisekunden.
  */
  public void sustain(long t)
  {
    long finish = (new Date()).getTime() + t;
    while( (new Date()).getTime() < finish ){}
  }



  public static void main(String[] argv)
  {
    int width = 780;
    int height = 530;
    NonSynchronizedClock f = new NonSynchronizedClock(width,height);
    f.setTitle("Bewegung mittels geometrischer Transformationen");
    f.setSize(width,height);
    f.setVisible(true);
  }


}


