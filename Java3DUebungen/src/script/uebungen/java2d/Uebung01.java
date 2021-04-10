package script.uebungen.java2d;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;

public class Uebung01 extends Frame{

        private static final long serialVersionUID = 1L;
        private int windowHeight;

        /**
         * Konstruktor
         *
         * @param height  Dieser Wert sollte die Hoehe des Fensters angeben.
         */
        Uebung01(int height)
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

            g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER,1.0f, new float[]{2.0f, 5.0f}, 0.0f));
            g2d.setColor(new Color(2, 2, 246));
            Ellipse2D.Double circle2 = new Ellipse2D.Double(150,80, 100, 50);
            g2d.draw(circle2);
            g2d.setStroke(new BasicStroke(3.0f));

            g2d.setColor(new Color(0,0,200));
            Ellipse2D.Double circle3 = new Ellipse2D.Double(300,80, 100, 50);
            g2d.fill(circle3);
            g2d.draw(circle3);

            //Definiere eine Rotation.
            AffineTransform rotation = new AffineTransform();
            rotation.setToRotation(Math.PI/4);

            g2d.setColor(new Color(210, 8, 224));
            Ellipse2D.Double circle4 = new Ellipse2D.Double(100,-150, 100, 50);
            g2d.draw(rotation.createTransformedShape(circle4));

            g2d.setColor(new Color(130, 125, 125));
            Ellipse2D.Double circle5 = new Ellipse2D.Double(250,0, 100, 50);
            Rectangle2D.Double rect = new Rectangle2D.Double(248, 25, 45, 26);

            g2d.clip(rect);
            g2d.fill(circle5);
            g2d.draw(circle5);


        }





        public static void main(String[] argv)
        {
            int height = 400;
            Uebung01 f = new Uebung01(height);
            f.setTitle("Uebung Kreise");
            f.setSize(500,height);
            f.setVisible(true);
        }
}
