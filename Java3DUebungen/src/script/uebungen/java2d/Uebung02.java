package script.uebungen.java2d;

import script.demos.java2d.MyGeneralPath;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;

public class Uebung02 extends Frame{

    private static final long serialVersionUID = 1L;
    private int windowHeight;

    /**
     * Konstruktor
     *
     * @param height  Dieser Wert sollte die Hoehe des Fensters angeben.
     */
    Uebung02(int height)
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
        // parameters
        float startingX = 30.0f;
        float startingY = 280.0f;
        float heightTower = 100.0f;
        float widthTower = 10.0f;
        float heightPillar = 120.0f;
        float widthBase = 200.0f;
        float heightBase = widthBase * 0.05f; // 5% of widthBase
        float widthTop = 0.9f * widthBase; // 90 % of widthBase
        float heightTop = 0.1f * widthTop;
        Graphics2D g2d = (Graphics2D) g;

        GeneralPath gp = new GeneralPath(GeneralPath.WIND_NON_ZERO);

        // Base
        gp.moveTo(startingX, startingY );

        gp.lineTo(startingX, startingY - heightBase);
        gp.lineTo(startingX + widthBase, startingY - heightBase);
        gp.lineTo(startingX + widthBase, startingY);
        gp.lineTo(startingX, startingY);
        // top
        gp.moveTo(startingX + 0.05f * widthBase, startingY - heightBase - heightPillar);

        //pillar 1
        gp.lineTo(startingX + 0.125f * widthBase, startingY - heightBase - heightPillar);
        gp.lineTo(startingX + 0.125f * widthBase, startingY - heightBase);
        gp.lineTo(startingX + 0.225f * widthBase, startingY - heightBase);
        gp.lineTo(startingX + 0.225f * widthBase, startingY - heightBase - heightPillar);

        // pillar 2
        gp.lineTo(startingX + 0.275f * widthBase, startingY - heightBase - heightPillar);
        gp.lineTo(startingX + 0.275f * widthBase, startingY - heightBase);
        gp.lineTo(startingX + 0.375f * widthBase, startingY - heightBase);
        gp.lineTo(startingX + 0.375f * widthBase, startingY - heightBase - heightPillar);

        // pillar 3
        gp.lineTo(startingX + 0.625f * widthBase, startingY - heightBase - heightPillar);
        gp.lineTo(startingX + 0.625f * widthBase, startingY - heightBase);
        gp.lineTo(startingX + 0.725f * widthBase, startingY - heightBase);
        gp.lineTo(startingX + 0.725f * widthBase, startingY - heightBase - heightPillar);

        // pillar 4
        gp.lineTo(startingX + 0.775f * widthBase, startingY - heightBase - heightPillar);
        gp.lineTo(startingX + 0.775f * widthBase, startingY - heightBase);
        gp.lineTo(startingX + 0.875f * widthBase, startingY - heightBase);
        gp.lineTo(startingX + 0.875f * widthBase, startingY - heightBase - heightPillar);

        gp.lineTo(startingX + 0.95f * widthBase, startingY - heightBase - heightPillar);

        // rechte kante top
        gp.lineTo(startingX + 0.95f * widthBase, startingY - heightBase - heightPillar - heightTop);

        // obere kante top
        gp.lineTo(startingX + 0.625f * widthBase, startingY - heightPillar - heightBase - heightTop);
        gp.quadTo(startingX + 0.5f * widthBase, startingY - heightPillar - heightBase - heightTop + 0.5f * heightTop, startingX + 0.375f * widthBase, startingY - heightPillar - heightBase - heightTop);
        gp.quadTo(startingX + 0.5f * widthBase, startingY - heightPillar - heightBase - heightTop - 0.5f * heightTop, startingX + 0.635f * widthBase, startingY - heightPillar - heightBase - heightTop);
        gp.moveTo(startingX + 0.375f * widthBase, startingY - heightPillar - heightBase - heightTop);

        // linke kante top
        gp.lineTo(startingX + 0.05f * widthBase, startingY - heightPillar - heightBase - heightTop);
        gp.lineTo(startingX + 0.05f * widthBase, startingY - heightPillar - heightBase);

        // tower

        startingX = startingX + widthBase + 30.0f;

        gp.moveTo(startingX, startingY);

        // base
        gp.lineTo(startingX, startingY - heightTower);
        gp.lineTo(startingX + widthTower, startingY - heightTower);
        gp.lineTo(startingX + widthTower, startingY);
        gp.lineTo(startingX, startingY);

        // top

        gp.moveTo(startingX, startingY - heightTower + 5.0f);
        gp.curveTo(startingX - 5.0f, startingY - heightTower - 5.0f, startingX + widthTower + 5.0f , startingY - heightTower - 5.0f, startingX + widthTower, startingY - heightTower + 5.0f); //Kuppel
        g2d.translate(0.0f, 0.0f);
        g2d.draw(gp);
    }

    public static void main(String[] argv)
    {
        int height = 300;
        Uebung02 f = new Uebung02(height);
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
