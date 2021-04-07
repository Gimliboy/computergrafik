package script.demos.java3d; /**
* Beispiel:  W�rfel wird mit der Maus gedreht
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
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;

public class RotateMouseOfWuerfel_3D extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	public RotateMouseOfWuerfel_3D() 
	{
		super("W�rfel 3D");
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
		
		// Verwendung von MouseRotate:    
		TransformGroup RotateTg = new TransformGroup();    
		RotateTg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);    
		RotateTg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);     
		
		MouseRotate RotateMouse = new MouseRotate();    
		RotateMouse.setTransformGroup(RotateTg);    
		RotateMouse.setSchedulingBounds(new BoundingSphere());

		root_obj.addChild(RotateTg);
		root_obj.addChild(RotateMouse);
		RotateTg.addChild(new ColorCube(0.4));

		
		//Background setzen
		Background hintergrund = new Background(new Color3f(Color.lightGray));
		hintergrund.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0f));
		root_obj.addChild(hintergrund);
				
		//Inhaltszweig compilieren
		root_obj.compile();
		return root_obj;
		
	}
	public static void main(String args[]){
		new RotateMouseOfWuerfel_3D();
	}
}
