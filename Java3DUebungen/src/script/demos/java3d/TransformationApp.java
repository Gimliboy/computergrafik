package script.demos.java3d; /**
* Beispiel f�r 3D-Transformation von Objekten
*  @author Juergen Thome
* Vorlesung Computergrafik DHBW Heidenheim
* 
* History
* 	Intial Version 01/2015
*/

import java.awt.*;
import javax.swing.*;
import javax.vecmath.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;


public class TransformationApp extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransformationApp()
	{
		super("Einfache Translationen");
		getContentPane().setLayout(new BorderLayout());
		GraphicsConfiguration eineKonfiguration=SimpleUniverse.getPreferredConfiguration();
		
		//Canvas-3d Objekt erstellen
		Canvas3D eineZeichenflaeche=new Canvas3D(eineKonfiguration);
		getContentPane().add("Center", eineZeichenflaeche);
		
		//ein Simple-Universe erstellen und es beschreiben 
		SimpleUniverse einfachesUniversum=new SimpleUniverse(eineZeichenflaeche);
		einfachesUniversum.getViewingPlatform().setNominalViewingTransform();
		einfachesUniversum.addBranchGraph(erstelleInhaltszweig());
		
		//Fenstereigenschaften
		setSize(300,300);
		setVisible(true);
	}
	
	public BranchGroup erstelleInhaltszweig(){
		BranchGroup dieWurzel=new BranchGroup();
		
		Appearance einAussehen = new Appearance();
		//Material wird blau eingef�rbt, damit Objekte blau eingef�rbt werden.
		Material einMaterial = new Material(new Color3f(0.0f, 0.0f, 1.0f), new Color3f(0.0f, 0.0f, 1.0f), 
			new Color3f(0.0f, 0.0f, 1.0f), new Color3f(0.7f, 0.7f, 0.7f), 10.0f);
		einMaterial.setLightingEnable(true);
		einAussehen.setMaterial(einMaterial);	
		
		//Zylinder zur Szene hinzuf�gen
		Transform3D Translation1 = new Transform3D();
		Translation1.setTranslation(new Vector3f(0.3f,0.3f,0.0f));
		TransformGroup Transformationsgruppe1 = new TransformGroup(Translation1);
		Transformationsgruppe1.addChild(new Cylinder(0.5f,0.15f,Primitive.GENERATE_NORMALS,200,200,einAussehen));
		dieWurzel.addChild(Transformationsgruppe1);
	
		//Kegel zur Szene hinzuf�gen
		Transform3D Translation2 = new Transform3D();
		Translation2.setTranslation(new Vector3f(-0.3f,-0.3f,0.0f));
		TransformGroup Transformationsgruppe2 = new TransformGroup(Translation2);
		Transformationsgruppe2.addChild(new Cone(0.3f,0.5f,Primitive.GENERATE_NORMALS,200,200,einAussehen));
		dieWurzel.addChild(Transformationsgruppe2);
		
		//Background setzen
		Background hintergrund = new Background(new Color3f(Color.lightGray));
        hintergrund.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0f));
        dieWurzel.addChild(hintergrund);
		
		//Inhaltszweig compilieren
		dieWurzel.compile();
		return dieWurzel;
	}
	
	public static void main(String args[]){
		new TransformationApp();
	}
}