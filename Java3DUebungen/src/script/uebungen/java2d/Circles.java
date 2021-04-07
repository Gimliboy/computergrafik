package script.uebungen.java2d;

import script.demos.java2d.Grafik_Objekte_2D;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;

public class Circles extends Frame{

        private static final long serialVersionUID = 1L;
        private int windowHeight;

        /**
         * Konstruktor
         *
         * @param height  Dieser Wert sollte die Hoehe des Fensters angeben.
         */
        Circles(int height)
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

            //Zeichne eine Linie
            g2d.setColor(new Color(0,0,0));
            Ellipse2D.Double circle = new Ellipse2D.Double(10,80, 100, 50);
            g2d.draw(circle);

            g2d.setStroke(new BasicStroke(3.0f));
            g2d.setColor(new Color(0,0,0));
            Ellipse2D.Double circle2 = new Ellipse2D.Double(150,80, 100, 50);
            g2d.draw(circle2);

            g2d.setColor(new Color(0,0,0));
            Ellipse2D.Double circle3 = new Ellipse2D.Double(300,80, 100, 50);
            g2d.draw(circle3);

            g2d.setColor(new Color(0,0,0));
            Ellipse2D.Double circle4 = new Ellipse2D.Double(100,0, 100, 50);
            g2d.draw(circle4);

            g2d.setColor(new Color(0,0,0));
            Ellipse2D.Double circle5 = new Ellipse2D.Double(250,0, 100, 50);
            g2d.draw(circle5);


        }





        public static void main(String[] argv)
        {
            int height = 400;
            Circles f = new Circles(height);
            f.setTitle("Uebung Kreise");
            f.setSize(500,height);
            f.setVisible(true);
        }
}
