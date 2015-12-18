package teamACS;

import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Image;
//import java.io.File;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Batu
 *
 */
@SuppressWarnings("serial")
public class Panel extends JPanel {
	private View v;
	private JButton restart;

	/**
	 * Konstruktor der Panel-Klasse
	 * 
	 * @param v
	 * @param c
	 */
	public Panel(View v, Controller c) {
		this.v = v;
		this.setLayout(null);
		this.restart = new JButton("new Game");
		restart.addActionListener(c);
		restart.setBounds(0, 250, 250, 30);
		this.add(restart);
		this.addMouseListener(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		// Koordinaten der Bilder
		int x = 0;
		int y = 0;
		// schwarz oder weiss
		// boolean white = false;
		int zaehler = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				g2d.drawImage(v.getImage(zaehler), x, y, null);
				x += 50;
				zaehler++;
			}
			// naechste Zeile
			x = 0;
			y += 50;
		}
	}
}