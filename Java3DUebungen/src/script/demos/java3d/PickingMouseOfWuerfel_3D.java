package script.demos.java3d; /**
* Beispiel:  Einer der W�rfel kann mit der Maus ausgw�hlt werden und 
* dann kann er gedreht werden mit der Linken Maustaste
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
import com.sun.j3d.utils.behaviors.picking.*;

public class PickingMouseOfWuerfel_3D extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	public PickingMouseOfWuerfel_3D() 
	{
		super("Picking W�rfel 3D");
		getContentPane().setLayout(new BorderLayout());
		GraphicsConfiguration eineKonfiguration=SimpleUniverse.getPreferredConfiguration();
		
		//Canvas-3d Objekt erstellen
		Canvas3D eineZeichenflaeche=new Canvas3D(eineKonfiguration);
		getContentPane().add("Center", eineZeichenflaeche);
		
		//ein Simple-Universe erstellen und es beschreiben 
		SimpleUniverse einfachesUniversum=new SimpleUniverse(eineZeichenflaeche);
		einfachesUniversum.getViewingPlatform().setNominalViewingTransform();
		einfachesUniversum.addBranchGraph(erstelleInhaltszweig(eineZeichenflaeche));
		
		//Fenstereigenschaften
		setSize(400,400);
		setVisible(true);
		
	}
	
	public BranchGroup erstelleInhaltszweig(Canvas3D canvas)
	{
		BranchGroup root_obj=new BranchGroup();
		
		// Initialisierungen:
	      
	      Transform3D shift = new Transform3D();       
	      BoundingSphere zone = new BoundingSphere();         // Generierung der ersten Objekte:
	      shift.setTranslation(new Vector3f(-0.6f, 0.0f, -0.6f));       
	      TransformGroup objDreh = new TransformGroup(shift);       
	      objDreh.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	      objDreh.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);       
	      objDreh.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
	      root_obj.addChild(objDreh);       
	      objDreh.addChild(new ColorCube(0.3));        
	      PickRotateBehavior pickDreh = new PickRotateBehavior(root_obj, canvas, zone);
	      root_obj.addChild(pickDreh);

	      // Hinzufuegen eines zweiten ColorCube:
	      
	      shift.setTranslation(new Vector3f(0.6f, 0.0f, -0.6f));       
	      objDreh = new TransformGroup(shift);       
	      objDreh.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);       
	      objDreh.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);       
	      objDreh.setCapability(TransformGroup.ENABLE_PICK_REPORTING);        
	      root_obj.addChild(objDreh);
	      objDreh.addChild(new ColorCube(0.4));

	    
		//Background setzen
		Background hintergrund = new Background(new Color3f(Color.lightGray));
		hintergrund.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0f));
		root_obj.addChild(hintergrund);
				
		//Inhaltszweig compilieren
		root_obj.compile();
		return root_obj;
		
	}
	
	public static void main(String args[]){
		new PickingMouseOfWuerfel_3D();
	}
}
