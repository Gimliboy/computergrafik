package script.demos.java3d; /**
* Beispiel eines gedrehten W�rfels mit an den Seiten verschiedenen Farben
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

public class Wuerfel_3D extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	public Wuerfel_3D() 
	{
		super("Wuerfel 3D");
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
		setSize(400,400);
		setVisible(true);
		
	}
	
	public BranchGroup erstelleInhaltszweig()
	{
		BranchGroup root_obj=new BranchGroup();
		
		Transform3D rotate1 = new Transform3D();
		Transform3D rotate2 = new Transform3D();
		
		rotate1.rotX(Math.PI/14.0d);
		rotate2.rotY(Math.PI/8.0d);
		rotate1.mul(rotate2);
		
		TransformGroup rotateTg = new TransformGroup(rotate1);
		
		root_obj.addChild(rotateTg);
		rotateTg.addChild(new ColorCube(0.4));
		
		//Background setzen
		Background hintergrund = new Background(new Color3f(Color.lightGray));
		hintergrund.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0f));
		root_obj.addChild(hintergrund);
				
		//Inhaltszweig compilieren
		root_obj.compile();
		return root_obj;
		
	}
	public static void main(String args[]){
		new Wuerfel_3D();
	}
}
