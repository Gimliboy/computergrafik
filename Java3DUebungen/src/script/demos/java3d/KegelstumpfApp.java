package script.demos.java3d; /**
 *Beispielprogramm zum "Lehrbuch der Grafikprogrammierung"
 *Prof. Dr. Klaus Zeppenfeld
 *(c) 2003 Prof. Dr. Klaus Zeppenfeld
 *@author  Dirk Schnieders, Yvonne Tepe, Regine Wolters, Prof. Dr. Klaus Zeppenfeld
 *@version 1.0
 */

import java.awt.*;
import javax.swing.*;
import javax.vecmath.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;

public class KegelstumpfApp extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public KegelstumpfApp () {
		super("Kegelstumpf mit TriangleStripArray");
		this.getContentPane().setLayout(new BorderLayout());
		GraphicsConfiguration eineKonfiguration = SimpleUniverse.getPreferredConfiguration();
		Canvas3D eineZeichenflaeche = new Canvas3D(eineKonfiguration);
		SimpleUniverse einfachesUniversum = new SimpleUniverse(eineZeichenflaeche);
		this.getContentPane().add(eineZeichenflaeche, BorderLayout.CENTER);
		einfachesUniversum.getViewingPlatform().setNominalViewingTransform();
		BranchGroup derInhaltszweig = erzeugeInhaltszweig();
		einfachesUniversum.addBranchGraph(derInhaltszweig);	
	
		this.setSize(300, 300);
		this.setVisible(true);
	}
	
	public BranchGroup erzeugeInhaltszweig() {
		BranchGroup dieWurzel = new BranchGroup();
		Transform3D eineTransformation = new Transform3D();
		eineTransformation.rotX(Math.PI/2);
		TransformGroup eineTransformationsgruppe = new TransformGroup(eineTransformation);
		
		Appearance einAussehen = new Appearance();
		PolygonAttributes eineDarstellungsart = new PolygonAttributes();
        eineDarstellungsart.setCullFace(PolygonAttributes.CULL_NONE);
        //hier kann POLYGON_LINE in POLYGON_POINT oder POLYGON_FILL ge�ndert werden
	    eineDarstellungsart.setPolygonMode(PolygonAttributes.POLYGON_LINE);
	    einAussehen.setPolygonAttributes(eineDarstellungsart);
		eineTransformationsgruppe.addChild(new Kegelstumpf(20, 0.6f, 0.4f, 0.7f,einAussehen));
			
		dieWurzel.addChild(eineTransformationsgruppe);
		
		//Zeichenfarbe blau
		ColoringAttributes eineFarbeigenschaft = new ColoringAttributes();
		eineFarbeigenschaft.setColor(new Color3f(0.0f, 0.0f, 1.0f));
		einAussehen.setColoringAttributes(eineFarbeigenschaft);
		
		//Background setzen
		Background hintergrund = new Background(new Color3f(Color.lightGray));
        hintergrund.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0f));
        dieWurzel.addChild(hintergrund);
        
        dieWurzel.compile();
		
		return dieWurzel;
	}
	
	
	public static void main (String args[]) {
		new KegelstumpfApp();	
	}
}



/**
 * Diese Klasse generiert einen Kegelstumpf. Im Gegensatz zur Klasse Cone wird hier noch ein zweiter
 * Radius f�r die obere Kreisfl�che definiert.
 */
class Kegelstumpf extends Primitive
    {
    	Shape3D eineForm;
    	
    	/**
    	 *
    	 * Erstellt einen Kegelstumpf mit gegebenen Werten f�r die Aufl�sung, den Radius der unteren Kreisscheibe, den Radius der oberen 
    	 * Kreisscheibe, die H�he des Kegels und das Appearance-Objekt
    	 *
    	 * @param punktanzahlKreis  Die Anzahl der Punkte der Kreisscheiben
    	 * @param radius1			Der Radius der unteren Kreisscheibe
    	 * @param radius2			Der Radius der oberen Kreisscheibe
    	 * @param hoehe				Die H�he des Kegelstumpfes
    	 * @param einAussehen    	Das Aussehen des Kegelstumpfes
    	 */
		public Kegelstumpf(int punktanzahlKreis, float radius1, float radius2, float hoehe, Appearance einAussehen) {
			eineForm = new Shape3D();
			eineForm.addGeometry(erstelleVerbindung(punktanzahlKreis,radius1,radius2,hoehe));
			eineForm.addGeometry(erstelleKreisscheibe(punktanzahlKreis,radius1,hoehe/2));
			eineForm.addGeometry(erstelleKreisscheibe(punktanzahlKreis,radius2,-hoehe/2));
			eineForm.setAppearance(einAussehen);
			this.addChild(eineForm);
		} 
        //Die Kreisscheibe
    	private Geometry erstelleKreisscheibe(int punktanzahl, float radius, float zPosition)
    	{
    		TriangleFanArray eineGeometrie;
        	int     n = punktanzahl+1; 
        	Point3d diePunkte[] = new Point3d[n];
        	int     punkteProStreifen[] = {n};
        	int     a;
        	double  arc;
        	double  x,y;

        	//Mittelpunkt der Kreisscheibe
        	diePunkte[0] = new Point3d(0.0d, 0.0d,zPosition);
     
        	//Alle anderen Punkte der Kreisscheibe
        	for(arc = 0,a = 0; a < n-1; arc = 2.0*Math.PI/(punktanzahl-1) * ++a){
           		x = (double) (radius * Math.cos(arc)); 
            	y = (double) (radius * Math.sin(arc));
            	diePunkte[a+1] = new Point3d(x, y, zPosition);
        	}
            //TriangleFanArray bekommt Punkte der Kreisscheibe �bergeben
        	eineGeometrie = new TriangleFanArray (n, 
				TriangleFanArray.COORDINATES,
				punkteProStreifen);
		
        	eineGeometrie.setCoordinates(0, diePunkte);
              	
        	
     		return eineGeometrie;
		}
        //Die Verbindung zwischen beiden Kreisscheiben
		private Geometry erstelleVerbindung(int punktanzahlKreis, double radius1, double radius2, double zPosition) 
		{
            TriangleStripArray eineGeometrie;
            int punktanzahl = punktanzahlKreis*2; 
            Point3d diePunkte[] = new Point3d[punktanzahl];
            
            int punkteProStreifen[]={punktanzahl};
            double b=2*Math.PI/(punktanzahlKreis-1);  
            //Berechnung der Position aller Punkte	
       			for(int i = 0, a = 0; i < punktanzahl; a++){
               		if(i%2 == 0){                
       			    	diePunkte[i++]=new Point3d(radius1*Math.cos(b*a),radius1*Math.sin(b*a),zPosition/2);
       				}
       				if(i%2 == 1){                
       					diePunkte[i++]=new Point3d(radius2*Math.cos(b*a),radius2*Math.sin(b*a),-zPosition/2);        		    	
       				}
        		}
	        //TriangleStripArray bekommt Punkte �bergeben
			eineGeometrie = new TriangleStripArray (punktanzahl, 
				TriangleStripArray.COORDINATES,
				punkteProStreifen);
            eineGeometrie.setCoordinates(0, diePunkte);            
            return eineGeometrie;

		} 
		
	public Appearance getAppearance(int Nummer) {
		return null;
    }
    
    public Shape3D getShape(int Nummer) {
    	return eineForm;
  	}
  	
  	public void setAppearance(Appearance einAussehen){
  	}
} 
