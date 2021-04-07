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

public class rotierende_Wuerfel_3D extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	public rotierende_Wuerfel_3D() 
	{
		super("Rotierende Wuerfel 3D");
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
		setSize(1000,500);
		setVisible(true);
		
	}
	
	public BranchGroup erstelleInhaltszweig()
	{
		BranchGroup root_obj=new BranchGroup();
		
		// Drehung des W�rfels, so da� 3 Seiten sichtbar sind
		Transform3D rotate1 = new Transform3D();
		Transform3D rotate2 = new Transform3D();
		
		rotate1.rotX(Math.PI/14.0d);
		rotate2.rotY(Math.PI/8.0d);
		rotate1.mul(rotate2);
		
		TransformGroup rotateTg = new TransformGroup(rotate1);
		
		// Verschieben des W�rfels um 2 Einheiten nach rechtsc
		Transform3D shift1 = new Transform3D();
		Vector3f vector1 = new Vector3f(0.7f, 0.0f, 0.0f);
		shift1.set(vector1);
		TransformGroup shift1Tg = new TransformGroup (shift1);
		
		// Verschieben des W�rfels um 2 Einheiten nach links und rotieren
		Transform3D rotateshift = new Transform3D();
		Transform3D shift2 = new Transform3D();
		rotateshift.rotX(Math.PI / 4.0d);
		Vector3f vektor2 = new Vector3f(-0.7f, 0.0f, 0.0f);
		shift2.set(vektor2);
		rotateshift.mul(shift2);
		TransformGroup shift2Tg = new TransformGroup(rotateshift);

		// Pr�parieren f�r die Animation
		TransformGroup cubeSpin1Tg = new TransformGroup();
		cubeSpin1Tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		TransformGroup cubeSpin2Tg = new TransformGroup();
		cubeSpin2Tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		TransformGroup cubeSpin3Tg = new TransformGroup();
		cubeSpin3Tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
   
		// Objekte an den Szenengraph h�ngen
		cubeSpin1Tg.addChild(new ColorCube(0.2));
		rotateTg.addChild(cubeSpin1Tg);
		root_obj.addChild(rotateTg);
		
		cubeSpin2Tg.addChild(new ColorCube(0.2));
		shift1Tg.addChild(cubeSpin2Tg);
		root_obj.addChild(shift1Tg);
		   
		cubeSpin3Tg.addChild(new ColorCube(0.2));
		shift2Tg.addChild(cubeSpin3Tg);
		root_obj.addChild(shift2Tg);

		// Erzeugen der Rotation
		Alpha spinAlpha1 = new Alpha(-1, 4000);    // -1: Endlosschleife, 4000: 4 sec
	    RotationInterpolator Spin1Ri = new RotationInterpolator(spinAlpha1, cubeSpin1Tg);

	    Alpha spinAlpha2 = new Alpha(-1, 12000);
	    RotationInterpolator Spin2Ri = new RotationInterpolator(spinAlpha2, cubeSpin2Tg);

	    // mit selbem Alpha wie spinAlpha2:
	    RotationInterpolator Spin3Ri = new RotationInterpolator(spinAlpha2, cubeSpin3Tg);

	    // Aktivitaetsbereich:
	    BoundingSphere zone = new BoundingSphere();
	    Spin1Ri.setSchedulingBounds(zone);
	    Spin2Ri.setSchedulingBounds(zone);
	    Spin3Ri.setSchedulingBounds(zone);
	    
	    cubeSpin1Tg.addChild(Spin1Ri);   
	    cubeSpin2Tg.addChild(Spin2Ri);
	    cubeSpin3Tg.addChild(Spin3Ri);

		
		//Background setzen
		Background hintergrund = new Background(new Color3f(Color.lightGray));
		hintergrund.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0f));
		root_obj.addChild(hintergrund);
				
		//Inhaltszweig compilieren
		root_obj.compile();
		return root_obj;
		
	}
	public static void main(String args[]){
		new rotierende_Wuerfel_3D();
	}
}
