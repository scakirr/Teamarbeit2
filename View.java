package teamACS;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Klasse zur graphischen Darstellung des Spieles
 * 
 * @author Batu
 */
@SuppressWarnings("serial")
public class View extends JFrame {

	private Panel p;
	private Controller c;
	private Image imageBright;
	private Image imageDark;
	// attribute fuer das panel
	private Image[] imgs;

	/**
	 * Konstruktor der View-Klasse
	 * 
	 * @param c
	 */
	public View(Controller c) {
		this.imageBright = new ImageIcon(
				new File("src/teamACS/res/grey.jpg").getAbsolutePath())
				.getImage();
		this.imageDark = new ImageIcon(
				new File("src/teamACS/res/black.jpg").getAbsolutePath())
				.getImage();
		this.c = c;
		this.p = new Panel(this, this.c);

		this.imgs = new Image[25];

		this.add(p);

		this.setSize(266, 319);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	/**
	 * getter-Klasse
	 * 
	 * @param index
	 * @return
	 */
	public Image getImage(int index) {
		return imgs[index];
	}

	/**
	 * updatet den Status der Zelle
	 * 
	 * @param i
	 * @param j
	 * @param bright
	 */
	public void updateCell(int i, int j, boolean bright) {
		Image img;
		if (bright) {
			img = imageBright;
		} else {
			img = imageDark;
		}
		imgs[i + 5 * j] = img;
	}
}