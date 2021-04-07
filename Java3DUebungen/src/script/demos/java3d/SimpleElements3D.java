package script.demos.java3d; /**
* Darstellung der Primitiven K�rper im 3D Java
*
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
import com.sun.j3d.utils.geometry.Box;


public class SimpleElements3D extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleElements3D()
	{
		super("3D - Objekte");
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
		
		Appearance einAussehen = new Appearance();
		//Material wird blau eingef�rbt, damit Objekte blau erscheinen.
		Material einMaterial = new Material(new Color3f(0.0f, 0.0f, 1.0f), new Color3f(0.0f, 0.0f, 1.0f), 
			new Color3f(0.0f, 0.0f, 1.0f), new Color3f(0.7f, 0.7f, 0.7f), 10.0f);
		einMaterial.setLightingEnable(true);
		einAussehen.setMaterial(einMaterial);	
		
		//Zylinder zur Szene hinzuf�gen
		Transform3D Translation1 = new Transform3D();
		Translation1.setTranslation(new Vector3f(0.3f,0.4f,0.0f));
		TransformGroup Transformationsgruppe1 = new TransformGroup(Translation1);
		Transformationsgruppe1.addChild(new Cylinder(0.5f,0.15f,Primitive.GENERATE_NORMALS,200,200,einAussehen));
		root_obj.addChild(Transformationsgruppe1);
	
		//Kegel zur Szene hinzuf�gen
		Transform3D Translation2 = new Transform3D();
		Translation2.setTranslation(new Vector3f(-0.3f,-0.3f,0.0f));
		TransformGroup Transformationsgruppe2 = new TransformGroup(Translation2);
		Transformationsgruppe2.addChild(new Cone(0.3f,0.5f,Primitive.GENERATE_NORMALS,200,200,einAussehen));
		root_obj.addChild(Transformationsgruppe2);
		
		//Kugel zur Szene hinzuf�gen
		Transform3D Translation3 = new Transform3D();
		Translation3.setTranslation(new Vector3f(0.0f,-0.0f,0.0f));
		TransformGroup Transformationsgruppe3 = new TransformGroup(Translation3);
		Transformationsgruppe3.addChild(new Sphere(0.2f,Primitive.GENERATE_NORMALS,200,einAussehen));
		root_obj.addChild(Transformationsgruppe3);

		//Box zur Szene hinzuf�gen
		Transform3D Translation4 = new Transform3D();
		Translation4.setTranslation(new Vector3f(0.3f,-0.5f,0.0f));
		TransformGroup Transformationsgruppe4 = new TransformGroup(Translation4);
		Transformationsgruppe4.addChild(new Box(0.1f,0.2f,0.2f,Primitive.GENERATE_NORMALS,einAussehen));
		root_obj.addChild(Transformationsgruppe4);
		
		//Background setzen
		Background hintergrund = new Background(new Color3f(Color.lightGray));
        hintergrund.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0f));
        root_obj.addChild(hintergrund);
		
		//Inhaltszweig compilieren
		root_obj.compile();
		return root_obj;
	}
	
	public static void main(String args[]){
		new SimpleElements3D();
	}
}