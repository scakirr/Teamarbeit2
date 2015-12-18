package teamACS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Cakir
 *
 */
public class Controller implements ActionListener, MouseListener {
	private Model m;
	private View v;

	/**
	 * Konstruktor der Klasse Controller
	 */
	public Controller() {
		this.m = new Model(5, 5);
		this.v = new View(this);
		newGame();
	}

	/**
	 * startet neues Spiel
	 */
	private void newGame() {
		m.scramble();
		updateView();
	}

	/**
	 * Refresh der GUI
	 */
	private void updateView() {
		for (int i = 0; i < m.getWidth(); ++i)
			for (int j = 0; j < m.getHeight(); ++j)
				v.updateCell(i, j, m.peek(i, j));
		v.repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		newGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
		m.poke(e.getX() / 50, e.getY() / 50);
		updateView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
	}
}