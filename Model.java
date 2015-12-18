package teamACS;

/**
 * Zusätzliche Funktionen:
 * <ul>
 * <li>variable Feldgröße</li>
 * <li>einspeicherbarer Ausgangszustand, der beliebig oft wiederhergestellt
 * werden kann</li>
 * <li>zufälliger Feldzustand</li>
 * </ul>
 * Die einzige Klasse, die diese Klasse direkt verwendet, ist Controller.
 * 
 * @author Salihi
 * @see Controller
 */
public class Model {

	/** enthält die Ausgangszustände der Zellen im Feld */
	private boolean[] initData;
	/** enthält die Zustände der Zellen im Feld */
	private boolean[] data;
	/** enthält die Breite des Feldes */
	private int width;

	/**
	 * erstellt ein neues Model-Objekt, das ein Feld mit den Ausmaßen der
	 * Parameter repräsentiert
	 * 
	 * @param xsize
	 *            die erforderte Breite des Felds
	 * @param ysize
	 *            die erforderte Höhe des Felds
	 */
	public Model(int xsize, int ysize) {
		initData = new boolean[xsize * ysize];
		data = new boolean[xsize * ysize];
		width = xsize;

		scramble();
	}

	/**
	 * gibt die Breite des repräsentierten Feldes (in Zellen) zurück
	 * 
	 * @return die Breite des repräsentierten Feldes anhand des Attributs width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * gibt die Höhe des repräsentierten Feldes (in Zellen) zurück
	 * 
	 * @return die Höhe des repräsentierten Feldes anhand der Formel
	 *         Fläche/Breite
	 */
	public int getHeight() {
		return data.length / width;
	}

	/**
	 * überprüft, ob die übergebenen Koordinaten eine Zelle innerhalb des Feldes
	 * bezeichnen; wirft andernfalls eine IllegalArgumentException, die den
	 * Fehler mittels übergebenem Textes genau beschreibt.
	 * 
	 * @param x
	 *            die x-Koordinate der potenziellen Zelle
	 * @param y
	 *            die y-Koordinate der potenziellen Zelle
	 * @return die Höhe (für weitere Verwendung in der aufrufenden Methode)
	 * @throws IllegalArgumentException
	 *             falls die übergebenen Koordinaten keine Zelle innerhalb des
	 *             repräsentierten Feldes beschreiben.
	 */
	private int checkCoordinates(int x, int y) {
		if (x < 0)
			throw new IllegalArgumentException("x kleiner 0, zu weit links");
		if (x >= width)
			throw new IllegalArgumentException("x größer " + (width - 1)
					+ ", zu weit rechts");
		if (y < 0)
			throw new IllegalArgumentException("y kleiner 0, zu weit oben");
		int height = data.length / width;
		if (y >= height)
			throw new IllegalArgumentException("y größer " + (height - 1)
					+ ", zu weit unten");
		return height;
	}

	/**
	 * gibt zurück, ob die Zelle, die durch das übergebene Koordinatenpaar
	 * beschrieben wird aktiviert ist (true) oder nicht (false); überprüft zuvor
	 * die Validität dieser Koordinaten mittels checkCoordinates(int, int)
	 * 
	 * @param x
	 *            die x-Koordinate der Zelle
	 * @param y
	 *            die y-Koordinate der Zelle
	 * @return true, wenn die durch die übergebenen Koordinaten bezeichnete
	 *         Zelle aktiviert ist, andernfalls false
	 * @throws IllegalArgumentException
	 *             falls die übergebenen Koordinaten keine Zelle innerhalb des
	 *             repräsentierten Feldes beschreiben.
	 * @see poke(int, int)
	 * @see checkCoordinates(int, int)
	 */
	public boolean peek(int x, int y) {
		checkCoordinates(x, y);
		return data[x + width * y];
	}

	/**
	 * registriert eine Benutzerinteraktion auf der durch die übergebenen
	 * Koordinaten bezeichnete Zelle und verändert angemessen den Zustand der
	 * Zelle selbst sowie aller umliegenden Zellen; überprüft zuvor die
	 * Validität dieser Koordinaten mittels checkCoordinates(int, int)
	 * 
	 * @param x
	 *            die x-Koordinate der Zelle
	 * @param y
	 *            die y-Koordinate der Zelle
	 * @throws IllegalArgumentException
	 *             falls die übergebenen Koordinaten keine Zelle innerhalb des
	 *             repräsentierten Feldes beschreiben.
	 * @see peek(int, int)
	 * @see checkCoordinates(int, int)
	 */
	public void poke(int x, int y) {
		int height = checkCoordinates(x, y);
		int pos = x + width * y;
		if (y > 0)
			data[pos - width] = !data[pos - width];
		if (y < height - 1)
			data[pos + width] = !data[pos + width];
		if (x > 0)
			data[pos - 1] = !data[pos - 1];
		if (x < width - 1)
			data[pos + 1] = !data[pos + 1];
		data[pos] = !data[pos];
	}

	/**
	 * setzt den Ausgangszustand der durch die Koordinaten bezeichneten Zelle
	 * auf den übergebenen Zustand - aktiviert(true) oder deaktiviert(false);
	 * Dies beeinflusst nicht das Spielfeld selbst, sondern nur den Zustand auf
	 * den das Feld durch reset() zurückgesetzt werden kann. Zuvor wird die
	 * Validität dieser Koordinaten mittels checkCoordinates(int, int) überprüft
	 * und bei unzureichender Konformität eine IllegalArgumentException
	 * weitergeworfen.
	 * 
	 * @param x
	 *            die x-Koordinate der Zelle
	 * @param y
	 *            die y-Koordinate der Zelle
	 * @param value
	 *            der Ausgangszustand, auf den die Zelle wieder zurückgesetzt
	 *            werden können soll
	 * @throws IllegalArgumentException
	 *             falls die übergebenen Koordinaten keine Zelle innerhalb des
	 *             repräsentierten Feldes beschreiben.
	 * @see reset()
	 * @see checkCoordinates(int, int)
	 */
	public void setInit(int x, int y, boolean value) {
		checkCoordinates(x, y);
		initData[x + width * y] = value;
	}

	/**
	 * setzt das Feld auf den mittels Aufrufe von setInit(int, int, boolean)
	 * gesetzten Ausgangszustand zurück
	 * 
	 * @see setInit(int, int, boolean)
	 */
	public void reset() {
		System.arraycopy(initData, 0, data, 0, data.length);
	}

	/**
	 * setzt die Zustände des Spielfelds basierend auf einer Zufallszahl, sodass
	 * bei unbekanntem seed der Wert keines Feldes vorhersehbar ist
	 */
	public void scramble() {
		long l = 0l;
		if (data.length > 0)
			l = 1l << data.length;
		l = (long) (Math.random() * l);
		for (int i = 0; i < data.length; ++i) {
			data[i] = ((l & 1) != 0);
			l >>= 1;
		}
	}

}