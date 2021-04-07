package script.demos.java2d; /**
* Beispiel Java Farbmodell RGB, YMCK und HSV und deren Umrechnung
*
*  @author Juergen Thome
* Vorlesung Computergrafik DHBW Heidenheim
* 
* History
* 	Intial Version 01/2015
 */

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.*;

import javax.swing.event.*;

public class FarbModelle 
extends JFrame {
	public FarbModellePaintPanel fmpp;
	public FarbModelleCommandPanel fmcp;
	private static final long serialVersionUID = 1L;
	
	public FarbModelle() {
		super("FarbModelle");
		
		Container currentPane= getContentPane();
		currentPane.setLayout(new BorderLayout());
		fmcp= new FarbModelleCommandPanel(this);
		fmcp.setPreferredSize(new Dimension(400, 400));
		fmcp.setBorder(BorderFactory.createTitledBorder("Settings"));
		fmpp= new FarbModellePaintPanel(this);
		fmpp.setPreferredSize(new Dimension(400, 400));
		currentPane.add(fmcp, BorderLayout.WEST);
		currentPane.add(fmpp, BorderLayout.CENTER);
		setSize(810, 400);
		setResizable(false);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] a) {
		new FarbModelle();
	}
}

class FarbModelleCommandPanel
extends JPanel 
implements ActionListener, ItemListener, ChangeListener {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public JComboBox jcbColorModel;
	
	private JLabel jlFirst, jlSecond, jlThird, jlColor, jlColorModel, jlCurrentColor;
	private JButton jbExit;
	private JSlider jsFirst, jsSecond, jsThird;
	private JTextField jtfFirst, jtfSecond, jtfThird;
	
	private String[][] s= new String[][] {
		{"Rot (in %):", "Gr�n (in %):", "Blau (in %):"},
		{"Cyan (in %):", "Magenta (in %):", "Yellow (in %):"},
		{"Farbwinkel H (in %):", "S�ttigung S (in %):", "Hellwert V (in %):"}
	};
	private FarbModelle fm;
	private Graphics gColor;
	private float dxX, dyX, dxY, dyY, dxZ, dyZ;
	
	private FarbModelleCommandPanel() {
		jlColorModel= new JLabel("Farbmodell:");
		String[] Farbmodelle = {"RGB", "CMYK", "HSV"};
		jcbColorModel= new JComboBox(Farbmodelle);
		jcbColorModel.addItemListener(this);
		jlFirst= new JLabel();		
		jlSecond= new JLabel();		
		jlThird= new JLabel();		
		jsFirst= new JSlider(0, 100, 0);
		jsFirst.setMinorTickSpacing(10);
		jsFirst.setMajorTickSpacing(20);
		jsFirst.setPaintLabels(true);
		jsFirst.setPaintTicks(true);
		jsFirst.setPreferredSize(new Dimension(140, 50));
		jsFirst.addChangeListener(this);
		jsSecond= new JSlider(0, 100, 0);
		jsSecond.setMinorTickSpacing(10);
		jsSecond.setMajorTickSpacing(20);
		jsSecond.setPaintLabels(true);
		jsSecond.setPaintTicks(true);
		jsSecond.setPreferredSize(new Dimension(140, 50));
		jsSecond.addChangeListener(this);
		jsThird= new JSlider(0, 100, 0);
		jsThird.setMinorTickSpacing(10);
		jsThird.setMajorTickSpacing(20);
		jsThird.setPaintLabels(true);
		jsThird.setPaintTicks(true);
		jsThird.setPreferredSize(new Dimension(140, 50));
		jsThird.addChangeListener(this);
		jtfFirst= new JTextField(4);
		jtfSecond= new JTextField(4);
		jtfThird= new JTextField(4);
		jlColor= new JLabel("aktuelle Farbe:");
		BufferedImage bi= new BufferedImage(50, 20, BufferedImage.TYPE_INT_RGB);
		gColor= bi.getGraphics();
		jlCurrentColor= new JLabel(new ImageIcon(bi));
		jbExit= new JButton("Programm beenden");
		jbExit.addActionListener(this);
	}
	
	public FarbModelleCommandPanel(FarbModelle fm) {
		this();
		this.fm= fm;
		
		GridBagLayout gbl= new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.weightx= 1.0d;
		gbc.weighty= 1.0d;
		
		gbc.gridwidth= 1;
		gbc.anchor= GridBagConstraints.CENTER;
		gbl.setConstraints(jlColorModel, gbc);
		add(jlColorModel);
		gbc.anchor= GridBagConstraints.WEST;
		gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbl.setConstraints(jcbColorModel, gbc);
		add(jcbColorModel);
		
		gbc.gridwidth= 1;
		gbc.anchor= GridBagConstraints.CENTER;
		gbl.setConstraints(jlFirst, gbc);
		add(jlFirst);
		gbc.anchor= GridBagConstraints.WEST;
		gbl.setConstraints(jsFirst, gbc);
		add(jsFirst);
		gbc.anchor= GridBagConstraints.WEST;
		gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbl.setConstraints(jtfFirst, gbc);
		add(jtfFirst);
		
		gbc.gridwidth= 1;
		gbc.anchor= GridBagConstraints.CENTER;
		gbl.setConstraints(jlSecond, gbc);
		add(jlSecond);
		gbc.anchor= GridBagConstraints.WEST;
		gbl.setConstraints(jsSecond, gbc);
		add(jsSecond);
		gbc.anchor= GridBagConstraints.WEST;
		gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbl.setConstraints(jtfSecond, gbc);
		add(jtfSecond);
		
		gbc.gridwidth= 1;
		gbc.anchor= GridBagConstraints.CENTER;
		gbl.setConstraints(jlThird, gbc);
		add(jlThird);
		gbc.anchor= GridBagConstraints.WEST;
		gbl.setConstraints(jsThird, gbc);
		add(jsThird);
		gbc.anchor= GridBagConstraints.WEST;
		gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbl.setConstraints(jtfThird, gbc);
		add(jtfThird);

		gbc.gridwidth= 1;
		gbc.anchor= GridBagConstraints.CENTER;
		gbl.setConstraints(jlColor, gbc);
		add(jlColor);
		gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbc.anchor= GridBagConstraints.WEST;
		gbl.setConstraints(jlCurrentColor, gbc);
		add(jlCurrentColor);
		
		gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbc.anchor= GridBagConstraints.CENTER;
		gbl.setConstraints(jbExit, gbc);
		add(jbExit);
		
		updateLabels();
		updateConstants();
		updateColor();
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()== jbExit) {
			System.exit(0);
		}
	}
	
	public void itemStateChanged(ItemEvent ie) {
		if (ie.getSource()== jcbColorModel && ie.getStateChange()== ItemEvent.SELECTED) {
			updateLabels();
			updateConstants();
			convertColor();
			updateColor();
			fm.fmpp.repaint();
		}
	}
	
	public void stateChanged(ChangeEvent ce) {
		Object obj= ce.getSource();
		if (obj== jsFirst || obj== jsSecond || obj== jsThird) {
			updateColor();
			fm.fmpp.repaint();
		}
	}
	
	private void updateColor() {
		int i= jcbColorModel.getSelectedIndex();
		switch (i) {
			case 0: case 1:
				int iFirst= (int) (255.0f/ 100.0f* (float) jsFirst.getValue());
				int iSecond= (int) (255.0f/ 100.0f* (float) jsSecond.getValue());
				int iThird= (int) (255.0f/ 100.0f* (float) jsThird.getValue());
				jtfFirst.setText(formatStringForComponent(jtfFirst, Integer.toString(iFirst), "0000"));
				jtfSecond.setText(formatStringForComponent(jtfSecond, Integer.toString(iSecond), "0000"));
				jtfThird.setText(formatStringForComponent(jtfThird, Integer.toString(iThird), "0000"));
				if (i== 0) gColor.setColor(new Color(iFirst, iSecond, iThird));
				else gColor.setColor(new Color(255- iFirst, 255- iSecond, 255- iThird));
				break;
			case 2:
				float fFirst= (float) jsFirst.getValue()/ 100.0f;
				float fSecond= (float) jsSecond.getValue()/ 100.0f;
				float fThird= (float) jsThird.getValue()/ 100.0f;
				jtfFirst.setText(formatStringForComponent(jtfFirst, Float.toString(fFirst), "0000"));
				jtfSecond.setText(formatStringForComponent(jtfSecond, Float.toString(fSecond), "0000"));
				jtfThird.setText(formatStringForComponent(jtfThird, Float.toString(fThird), "0000"));
				gColor.setColor(new Color(Color.HSBtoRGB(fFirst, fSecond, fThird)));
		}
		gColor.fillRect(0, 0, 50, 20);
	}
	
	private void updateConstants() {
		switch (jcbColorModel.getSelectedIndex()) {
			case 0: 
				dxX= (float) ((375- 4)- (175- 4))/ 100.0f;
				dyX= 0.0f;
				
				dxY= (float) ((125- 4)- (175- 4))/ 100.0f;
				dyY= (float) ((25- 4)- (225- 4))/ 100.0f;
				
				dxZ= (float) ((75- 4)- (175- 4))/ 100.0f;
				dyZ= (float) ((325- 4)- (225- 4))/ 100.0f;
			
				break;
			case 1:
				dxX= (float) ((375- 4)- (175- 4))/ 100.0f;
				dyX= 0.0f;
				
				dxY= (float) ((125- 4)- (175- 4))/ 100.0f;
				dyY= (float) ((25- 4)- (225- 4))/ 100.0f;
				
				dxZ= (float) ((75- 4)- (175- 4))/ 100.0f;
				dyZ= (float) ((325- 4)- (225- 4))/ 100.0f;
				break;
			case 2: 
				dxX= 0.0f;
				dyX= 0.0f;
				
				dxY= 0.0f;
				dyY= (float) ((125- 4)- (325- 4))/ 100.0f;
				
				dxZ= 0.0f;
				dyZ= 0.0f;
		}
	} 
	
	private void updateLabels() {
		int i= jcbColorModel.getSelectedIndex();
		jlFirst.setText(s[i][0]);
		jlSecond.setText(s[i][1]);
		jlThird.setText(s[i][2]);
	}
	
	private String formatStringForComponent(Component c, String s0, String s1) {
		FontMetrics fm= c.getFontMetrics(c.getFont());
		int i= fm.stringWidth(s1);
		while (fm.stringWidth(s0)< i) s0= " "+ s0;
		return s0;
	}
	
	public int[] getPointXYOfCube() {
		int x= (int) (dxX* (float) jsSecond.getValue()+ dxY* (float) jsThird.getValue()+ dxZ* (float) jsFirst.getValue())+ 175- 4;
		int y= (int) (dyX* (float) jsSecond.getValue()+ dyY* (float) jsThird.getValue()+ dyZ* (float) jsFirst.getValue())+ 225 -4;
		
		return new int[] {x, y};
	}
	
	public int[] getLineXYOfCube() {
		float fxX= dxX* (float) jsSecond.getValue(); float fxY= dxY* (float) jsThird.getValue(); float fxZ= dxZ* (float) jsFirst.getValue();
		float fyX= dyX* (float) jsSecond.getValue(); float fyY= dyY* (float) jsThird.getValue(); float fyZ= dyZ* (float) jsFirst.getValue();
		
		int x1= (int) (dxX* 0.0f+ fxY+ fxZ)+ 4+ 175- 4;
		int y1= (int) (dyX* 0.0f+ fyY+ fyZ)+ 4+ 225- 4;
		int x2= (int) (dxX* 100.0f+ fxY+ fxZ)+ 4+ 175- 4;
		int y2= (int) (dyX* 100.0f+ fyY+ fyZ)+ 4+ 225- 4;
		
		int x3= (int) (fxX+ dxX* 0.0f+ fxZ)+ 4+ 175- 4;
		int y3= (int) (fyX+ dyY* 0.0f+ fyZ)+ 4+ 225- 4;
		int x4= (int) (fxX+ dxY* 100.0f+ fxZ)+ 4+ 175- 4;
		int y4= (int) (fyX+ dyY* 100.0f+ fyZ)+ 4+ 225- 4;
		
		int x5= (int) (fxX+ fxY+ dxZ* 0.0f)+ 4+ 175- 4;
		int y5= (int) (fyX+ fyY+ dyZ* 0.0f)+ 4+ 225- 4;
		int x6= (int) (fxX+ fxY+ dxZ* 100.0f)+ 4+ 175- 4;
		int y6= (int) (fyX+ fyY+ dyZ* 100.0f)+ 4+ 225- 4;
		
		return new int[] {x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6};
	}
	
	public int[] getPointXYOfPrisma() {
		float a= 20.0f;
		float b= 10.0f;
		float prop= (200.0f- 350.0f)/ (125.0f- 325.0f);
		int x= (int) (a* Math.cos(Math.PI/ 50.0f* (float) jsFirst.getValue())* (prop* jsThird.getValue()/ 100.0f* (float) jsSecond.getValue()/ 10.0f)+ dxY* (float) jsThird.getValue())+ 200- 4;
		int y= (int) (b* -Math.sin(Math.PI/ 50.0f* (float) jsFirst.getValue())* (prop* jsThird.getValue()/ 100.0f* (float) jsSecond.getValue()/ 10.0f)+ dyY* (float) jsThird.getValue())+ 325- 4;
		
		return new int[] {x, y};
	}
	
	public int[] getLineXYOfPrisma() {
		float a= 20.0f;
		float b= 10.0f;
		float prop= (200.0f- 350.0f)/ (125.0f- 325.0f);
		
		int x1= 200;
		int y1= 325;
		int x2= 200;
		int y2= 125;
		
		int x3= 200;
		int y3= (int) (dyY* (float) jsThird.getValue())+ 325;
		int x4=	(int) (a* Math.cos(Math.PI/ 50.0f* (float) jsFirst.getValue())* (prop* jsThird.getValue()/ 10.0f)+ dxY* (float) jsThird.getValue())+ 200;
		int y4= (int) (b* -Math.sin(Math.PI/ 50.0f* (float) jsFirst.getValue())* (prop* jsThird.getValue()/ 10.0f)+ dyY* (float) jsThird.getValue())+ 325;
		
		int x5= 200;
		int y5= 325;
		int x6= (int) (a* Math.cos(Math.PI/ 50.0f* (float) jsFirst.getValue())* (prop* 100.0f/ 100.0f* (float) jsSecond.getValue()/ 10.0f)+ dxY* (float) 100.0f)+ 200;
		int y6= (int) (b* -Math.sin(Math.PI/ 50.0f* (float) jsFirst.getValue())* (prop* 100.0f/ 100.0f* (float) jsSecond.getValue()/ 10.0f)+ dyY* (float) 100.0f)+ 325;
		
		return new int[] {x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6};
	}
		
	public Color getCurrentColor() {
		return gColor.getColor();
	}
	
	private void convertColor() {
		int colRGB= getCurrentColor().getRGB();
		int r= (colRGB>> 16)& 0xff;
		int g= (colRGB>> 8)& 0xff;
		int b= colRGB & 0xff;
		switch (jcbColorModel.getSelectedIndex()) {
			case 0:
				jsFirst.setValue((int) ((float) r/ 255.0f* 100.0f));
				jsSecond.setValue((int) ((float) g/ 255.0f* 100.0f));
				jsThird.setValue((int) ((float) b/ 255.0f* 100.0f));
				break;
			case 1:
				jsFirst.setValue((int) ((float) (255- r)/ 255.0f* 100.0f));
				jsSecond.setValue((int) ((float) (255- g)/ 255.0f* 100.0f));
				jsThird.setValue((int) ((float) (255- b)/ 255.0f* 100.0f));
				break;
			case 2:
				float[] fHSV= Color.RGBtoHSB(r, g, b, null);
				jsFirst.setValue((int) (fHSV[0]* 100.0f));
				jsSecond.setValue((int) (fHSV[1]* 100.0f));
				jsThird.setValue((int) (fHSV[2]* 100.0f));
		}
	}
}

class FarbModellePaintPanel
extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FarbModelle fm;
	
	private FarbModellePaintPanel() {
		super();
		setBackground(new Color(200, 200, 200));
		
	}
	
	public FarbModellePaintPanel(FarbModelle fm) {
		this();
		this.fm= fm;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		int[] xy; int[] xyLine;
		switch (fm.fmcp.jcbColorModel.getSelectedIndex()) {
			case 0: 
				g.setColor(Color.MAGENTA);
				g.fillRect(25- 4, 125- 4, 9, 9);
				g.setColor(Color.WHITE);
				g.fillRect(225- 4, 125- 4, 9, 9);
				g.setColor(Color.RED);
				g.fillRect(75- 4, 325- 4, 9, 9);
				g.setColor(Color.YELLOW);
				g.fillRect(275- 4, 325- 4, 9, 9);
				
				g.setColor(Color.BLUE);
				g.fillRect(125- 4, 25- 4, 9, 9);
				g.setColor(Color.CYAN);
				g.fillRect(325- 4, 25- 4, 9, 9);
				g.setColor(Color.BLACK);
				g.fillRect(175- 4, 225- 4, 9, 9);
				g.setColor(Color.GREEN);
				g.fillRect(375- 4, 225- 4, 9, 9);
				
				g.setColor(Color.BLACK);
				g.drawLine(25, 125, 225, 125);
				g.drawLine(225, 125, 275, 325);
				g.drawLine(275, 325, 75, 325);
				g.drawLine(75, 325, 25, 125);
				
				g.drawLine(125, 25, 325, 25);
				g.drawLine(325, 25, 375, 225);
				g.drawLine(375, 225, 175, 225);
				g.drawLine(175, 225, 125, 25);
				
				g.drawLine(25, 125, 125, 25);
				g.drawLine(225, 125, 325, 25);
				g.drawLine(75, 325, 175, 225);
				g.drawLine(275, 325, 375, 225);
				
				g.setColor(fm.fmcp.getCurrentColor());
				xy= fm.fmcp.getPointXYOfCube();
				g.fillRect(xy[0], xy[1], 9, 9);
				
				g.setColor(Color.ORANGE);
				xyLine= fm.fmcp.getLineXYOfCube();
				g.drawLine(xyLine[0], xyLine[1], xyLine[2], xyLine[3]);
				g.drawLine(xyLine[4], xyLine[5], xyLine[6], xyLine[7]);
				g.drawLine(xyLine[8], xyLine[9], xyLine[10], xyLine[11]);
				break;
			case 1:
				g.setColor(Color.GREEN);
				g.fillRect(25- 4, 125- 4, 9, 9);
				g.setColor(Color.BLACK);
				g.fillRect(225- 4, 125- 4, 9, 9);
				g.setColor(Color.CYAN);
				g.fillRect(75- 4, 325- 4, 9, 9);
				g.setColor(Color.BLUE);
				g.fillRect(275- 4, 325- 4, 9, 9);
				
				g.setColor(Color.YELLOW);
				g.fillRect(125- 4, 25- 4, 9, 9);
				g.setColor(Color.RED);
				g.fillRect(325- 4, 25- 4, 9, 9);
				g.setColor(Color.WHITE);
				g.fillRect(175- 4, 225- 4, 9, 9);
				g.setColor(Color.MAGENTA);
				g.fillRect(375- 4, 225- 4, 9, 9);
				
				g.setColor(Color.BLACK);
				g.drawLine(25, 125, 225, 125);
				g.drawLine(225, 125, 275, 325);
				g.drawLine(275, 325, 75, 325);
				g.drawLine(75, 325, 25, 125);
				
				g.drawLine(125, 25, 325, 25);
				g.drawLine(325, 25, 375, 225);
				g.drawLine(375, 225, 175, 225);
				g.drawLine(175, 225, 125, 25);
				
				g.drawLine(25, 125, 125, 25);
				g.drawLine(225, 125, 325, 25);
				g.drawLine(75, 325, 175, 225);
				g.drawLine(275, 325, 375, 225);
				
				g.setColor(fm.fmcp.getCurrentColor());
				xy= fm.fmcp.getPointXYOfCube();
				g.fillRect(xy[0], xy[1], 9, 9);
				
				g.setColor(Color.ORANGE);
				xyLine= fm.fmcp.getLineXYOfCube();
				g.drawLine(xyLine[0], xyLine[1], xyLine[2], xyLine[3]);
				g.drawLine(xyLine[4], xyLine[5], xyLine[6], xyLine[7]);
				g.drawLine(xyLine[8], xyLine[9], xyLine[10], xyLine[11]);
				break;
			case 2:
				g.setColor(Color.GREEN);
				g.fillRect(125-4 +3, 50-4+ 10, 9, 9);
				g.setColor(Color.YELLOW);
				g.fillRect(275-4 -3, 50-4+ 10, 9, 9);
				g.setColor(Color.RED);
				g.fillRect(350-4, 125-4, 9, 9);
				g.setColor(Color.MAGENTA);
				g.fillRect(275-4 -3, 200-4- 9, 9, 9);
				g.setColor(Color.BLACK);
				g.fillRect(200-4, 325-4, 9, 9);
				g.setColor(Color.BLUE);
				g.fillRect(125-4 +3, 200-4- 9, 9, 9);
				g.setColor(Color.CYAN);
				g.fillRect(50-4, 125-4, 9, 9);
				g.setColor(Color.WHITE);
				g.fillRect(200-4, 125-4, 9, 9);
				
				g.setColor(Color.BLACK);
				g.drawLine(125+ 3, 50+ 10, 275- 3, 50+ 10);
				g.drawLine(275- 3, 50+ 10, 350, 125);
				g.drawLine(350, 125, 275- 3, 200- 9);
				g.drawLine(275- 3, 200- 9, 125+ 3, 200- 9);
				g.drawLine(125+ 3, 200- 9, 50, 125);
				g.drawLine(50, 125, 125+ 3, 50+ 10);
				
				g.drawLine(200, 325, 125+ 3, 50+ 10);
				g.drawLine(200, 325, 275- 3, 50+ 10);
				g.drawLine(200, 325, 350, 125);
				g.drawLine(200, 325, 275- 3, 200- 9);
				g.drawLine(200, 325, 125+ 3, 200- 9);
				g.drawLine(200, 325, 50, 125);
				g.drawLine(200, 325, 200, 125);
				
				g.setColor(fm.fmcp.getCurrentColor());
				xy= fm.fmcp.getPointXYOfPrisma();
				g.fillRect(xy[0], xy[1], 9, 9);
				
				g.setColor(Color.ORANGE);
				xyLine= fm.fmcp.getLineXYOfPrisma();
				g.drawLine(xyLine[0], xyLine[1], xyLine[2], xyLine[3]);
				g.drawLine(xyLine[4], xyLine[5], xyLine[6], xyLine[7]);
				g.drawLine(xyLine[8], xyLine[9], xyLine[10], xyLine[11]);
		}
	}
}
