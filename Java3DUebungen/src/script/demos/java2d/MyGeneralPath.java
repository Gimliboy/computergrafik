package script.demos.java2d; /**
* Beispiel Java 2D General Path
*
*  @author Juergen Thome
* Vorlesung Computergrafik DHBW Heidenheim
* 
* History
* 	Intial Version 01/2015
*/

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyGeneralPath extends Frame
{
	private static final long serialVersionUID = 1L;
	private int windowHeight;
	
	/**
	  * Konstruktor
	  *
	  * @param height  Dieser Wert sollte die Hoehe des Fensters angeben.
	  */
		MyGeneralPath(int height)
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
						
				GeneralPath gp = new GeneralPath(GeneralPath.WIND_NON_ZERO);

				// Kirche
				gp.moveTo(30.0f, 230.0f );    //ansetzen Ecke l.u.
				gp.lineTo(30.0f, 110.0f);     //li. Turmseite
				gp.moveTo(60.0f, 110.0f);     //neu ansetzen Turmecke re.o.
				gp.lineTo(60.0f, 230.0f);     //re. Turmseite bzw.li Geb�udeseite
				gp.moveTo(30.0f, 230.0f);     //neu ansetzen
				gp.lineTo(240.0f, 230.0f);    //Grundlinie
				gp.moveTo(60.0f, 130.0f);     //neu ansetzen - First
				gp.lineTo(240.0f, 130.0f);    //First
				gp.lineTo(240.0f, 230.0f);    //re. Geb�udeseite
				gp.moveTo(60.0f, 150.0f);     //neu ansetzen - li. Dachunterkante
				gp.lineTo(240.0f, 150.0f);    //Dachunterkante
				zeichneFenster(gp, 80, 220);  //Fenster 1..
				zeichneFenster(gp, 120, 220);
				zeichneFenster(gp, 160, 220);
				zeichneFenster(gp, 200, 220); //..4
				gp.moveTo(30.0f,110.0f);      //neu anstetzen - Kuppel
				gp.curveTo(-10.0f, 50.0f, 100.0f, 50.0f, 60.0f, 110.0f); //Kuppel
				gp.moveTo(30.0f, 70.0f);      //neu ansetzen - Dach
				gp.lineTo(45.0f, 30.0f);      //li. Dachh�lfte
				gp.lineTo(60.0f, 70.0f);      //re. Dachh�lfte
								
				g2d.translate(0.0f, 0.0f);
				g2d.draw(gp);
		}
		
		private void zeichneFenster(GeneralPath gp, int x, int y){
	    gp.moveTo(x,y);                 //li.unt.Ecke
    	gp.lineTo(x,(y - 60));          //li. Fensterkante
		gp.lineTo((x + 20), (y - 60));  //ob. Fensterkante
		gp.lineTo((x + 20), y);         //re. Fensterkante
		gp.lineTo(x, y);                //unt. Fensterkante
		}
		
	

		public static void main(String[] argv)
		{
			int height = 300;
			MyGeneralPath f = new MyGeneralPath(height);
			f.setTitle("Beispiel GeneralPath");
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