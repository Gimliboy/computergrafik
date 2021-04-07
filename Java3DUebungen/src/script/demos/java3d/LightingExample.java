package script.demos.java3d;

import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.vp.*;

import javax.swing.JFrame;


/**
* Beispiel fuer die Verwendung verschiedener Lichtquellen. Auf der glaenzenden
* Kugel sieht man drei Lichtflecken, die im direktionalen Licht, der
* punktfoermigen Lichtquelle und dem Scheinwerfer stammen.
*
* Mit diesem Programm lassen verschiedene Farb- und Beleuchtungseffekte
* ausprobieren. Man kann beispielswiese der Werte der jeweiligen Appearance fuer
* den Wuerfel und die Kugel in der Szene aendern. Oder man aendert Farben und
* andere Parameter der Lichtquellen. Um den Effeckt einzelner Lichtquellen zu
* sehen, kommentiert man einfach das Hinzufuegen der anderen Lichtquellen
* aus:  bgLight.addChild(lightNoX); -> //bgLight.addChild(lighNoX);
*
*  @author Juergen Thome
* Vorlesung Computergrafik DHBW Heidenheim
* 
* History
* 	Intial Version 01/2015
*/
public class LightingExample extends JFrame
{

  //Der Canvas, auf den gezeichnet wird.
  public Canvas3D myCanvas3D;
  private static final long serialVersionUID = 1L;

  public LightingExample()
  {
    //Mechanismus zum Schliessen des Fensters und beenden des Programms
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    //Standardeinstellung fuer das Betrachteruniversum
    myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());


    //Aufbau des SimpleUniverse:
    //Zuerst Erzeugen zusammen mit dem Canvas
    SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);


    //Standardpositionierung des Betrachters
    simpUniv.getViewingPlatform().setNominalViewingTransform();


    //Die Szene wird in dieser Methode erzeugt.
    createSceneGraph(simpUniv);


    //Hinzufuegen von Licht
    addLight(simpUniv);


    //Hierdurch kann man mit der Maus den Betrachterstandpunkt veraendern
    OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
    ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
    simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);


    //Darstellung des Canvas/Fensters:
    setTitle("Verschiedene Beleuchtungseffekte");
    setSize(500,500);
    getContentPane().add("Center", myCanvas3D);
    setVisible(true);

  }




  public static void main(String[] args)
  {
     new LightingExample();
  }



  //In dieser Methode werden die Objekte der Szene aufgebaut und dem
  //SimpleUniverse su hinzugefuegt.
  public void createSceneGraph(SimpleUniverse su)
  {

//*** Erzeuge einen Wuerfel. ***

    //Erzeuge eine Appearance fuer den Wuerfel.
    Color3f ambientColourBox = new Color3f(0.8f,0.0f,0.0f);
    Color3f emissiveColourBox = new Color3f(0.0f,0.0f,0.0f);
    Color3f diffuseColourBox = new Color3f(0.8f,0.0f,0.0f);
    Color3f specularColourBox = new Color3f(1.0f,0.0f,0.0f);
    float shininessBox = 10.0f;

    Appearance boxApp = new Appearance();


    boxApp.setMaterial(new Material(ambientColourBox,emissiveColourBox,
                          diffuseColourBox,specularColourBox,shininessBox));




    Box myBox = new Box(0.2f,0.2f,0.2f,boxApp);

    //Drehe und verschiebe den Wuerfel etwas.
    Transform3D tfBox = new Transform3D();
    tfBox.rotY(Math.PI/6);
    tfBox.rotX(Math.PI/9);
    Transform3D shift = new Transform3D();
    shift.setTranslation(new Vector3f(-0.6f,-0.2f,0.1f));
    tfBox.mul(shift);
    TransformGroup tgBox = new TransformGroup(tfBox);
    tgBox.addChild(myBox);




//*** Erzeuge eine Kugel ***

    //Erzeuge eine Appearance fuer die Kugel
    Color3f ambientColourSphere = new Color3f(0.2f,0.2f,0.0f);
    Color3f emissiveColourSphere = new Color3f(0.0f,0.0f,0.0f);
    Color3f diffuseColourSphere = new Color3f(0.4f,0.4f,0.0f);
    Color3f specularColourSphere = new Color3f(0.8f,0.8f,0.0f);
    float shininessSphere = 120.0f;

    Appearance sphereApp = new Appearance();

    sphereApp.setMaterial(new Material(ambientColourSphere,emissiveColourSphere,
                           diffuseColourSphere,specularColourSphere,shininessSphere));



    Sphere mySphere = new Sphere(0.3f,Sphere.GENERATE_NORMALS,100,sphereApp);
    Transform3D tfSphere = new Transform3D();
    tfSphere.setTranslation(new Vector3f(0.0f,0.0f,-1.0f));

    TransformGroup tgSphere = new TransformGroup(tfSphere);
    tgSphere.addChild(mySphere);




    //*** Die Wurzel des Graphen, der die Szene enthaelt. ***
    BranchGroup root_obj = new BranchGroup();


    //Fuege den Wuerfel und die Kugel zur Szene hinzu.
    root_obj.addChild(tgBox);
    root_obj.addChild(tgSphere);


    //Erzeuge einen weissen Hintergrund
  //Background setzen
  		Background hintergrund = new Background(new Color3f(Color.lightGray));
  		hintergrund.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0f));
  		root_obj.addChild(hintergrund);
    



    root_obj.compile();

    //Hinzufuegen der Szene
    su.addBranchGraph(root_obj);

  }





  //Hier werden verschiedene Lichtquellen zur Szene hinzugefuegt.
  public void addLight(SimpleUniverse su)
  {

    BranchGroup bgLight = new BranchGroup();

    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), Double.MAX_VALUE);




    //Licht Nr.1: direktionales Licht
    Color3f lightColour1 = new Color3f(0.8f, 0.8f, 0.8f);
    Vector3f lightDir1  = new Vector3f(0.0f, 4.0f, -1.0f);
    DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
    light1.setInfluencingBounds(bounds);
    bgLight.addChild(light1);



    //Licht Nr.2: Eine punktfoermige Lichtquelle
    Color3f lightColour2 = new Color3f(0.3f, 0.3f, 0.3f);
    PointLight light2 = new PointLight(lightColour2,
                                       new Point3f(1.0f,1.0f,1.0f),
                                       new Point3f(0.2f,0.01f,0.01f));
    light2.setInfluencingBounds(bounds);
    bgLight.addChild(light2);


    //Licht Nr.3: Ein Scheinwerfer
    Color3f lightColour3 = new Color3f(0.3f, 0.3f, 0.3f);
    SpotLight light3 = new SpotLight(lightColour3,
                                     new Point3f(0.0f,0.0f,1.0f),
                                     new Point3f(0.1f,0.1f,0.01f),
                                     new Vector3f(0.0f,0.0f,-1.0f),
                                     (float) (Math.PI/20),
                                     0.0f);

    light3.setInfluencingBounds(bounds);
    bgLight.addChild(light3);



    //Licht Nr.4: Streulicht
    Color3f lightColour4 = new Color3f(0.2f, 0.2f, 0.2f);
    AmbientLight light4 = new AmbientLight(lightColour4);
    light4.setInfluencingBounds(bounds);
    bgLight.addChild(light4);


    su.addBranchGraph(bgLight);
  }



}
