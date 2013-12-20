package newtetris;
import java.awt.event.*;

public class Game implements Runnable, KeyListener {
	private int speed = 1000; // Anfangs-Geschwindigkeit
	private int randomNumPreview;
	private boolean isRunning = false; // Laufvariable für Thread
	private Scores scores = null;
	private Field field = null;
	private PreView preview = null;
	private Tile tile = null, previewTile = null;
	private View view = null;
	private QueryDialog qd = null;
	private Thread thread = null;

	/**
	 * managet Spielverlauf
	 * @param f Field Spielfeld
	 * @param t Tile Tetrisstein
	 * @param v View graphische Oberfläche
	 * @param s Scores Punkteverwaltung
	 */
	public Game(Field f, PreView p, Tile t, View v, Scores s, 
			QueryDialog d) {
		field = f;  preview = p;  tile = t; scores = s;  
		view = v;  qd = d;
		randomNumPreview = tile.getRandomNumber();
	} // Konstruktor
	
	void prepareNewGame() {
		scores.reset();
		view.setCurrentScore(scores.getCurrentScore(), 0);
		field.clearField();		
	}

	/**
	 * instanzieren und setzen eines neuen Teils, wenn bisheriges gesetzt
	 */
	void createNewTile() {
		tile.setField(field.getField());
		int startPos[] = field.getStartPosition();
		int randomNum = randomNumPreview;
		randomNumPreview = tile.getRandomNumber();
		tile.createTile(randomNumPreview);
		previewTile = tile;
		preview.setPreviewTile(previewTile.getTile(), previewTile.getColor());
		if(!tile.createTile(startPos[0], startPos[1], 0, randomNum)) {
			finishGame();
		}
		field.setTile(tile.getTile(), tile.getColor());
	} // createNewTile
  
	void finishGame() {
		stopThread();
		int pos = scores.getNewScorePos();
		if(pos != -1) {
			qd.setVisible(true);
		}
		field.removeKeyListener(this);
		view.removePauseListener();
		view.renameWorkButton(View.START);
	}

	/**
	 * Extra-Thread für Bewegen des Teils
	 */
	public void run() {
		try {
			isRunning = true;  
			while(isRunning) {
				if(!tile.moveDown()) {
					handleDownKey();
				}
				view.paintField();
				Thread.sleep(speed);
			}
		} catch(InterruptedException ie) {  }
	} // run

	/**
	 * Bewegen des Teils nach Taste
	 * @param ke KeyEvent gedrückte Richtungstaste
	 */
	public void keyPressed(KeyEvent ke) {
		int code = ke.getKeyCode();
		try {
			switch (code) {
				case 32: tile.moveTotalDown(); break;
				case 37: tile.moveLeft(); break;
				case 38: tile.rotate();
					field.setTile(tile.getTile(), tile.getColor()); break;
				case 39: tile.moveRight(); break;
				case 40: if(!tile.moveDown()) handleDownKey(); break;
				default:;
			}
		} catch (ArrayIndexOutOfBoundsException e) { }
		view.paintField();
	} // keyPressed

	/**
	 * Reaktion auf Nach-Unten-Taste, ggf neues Teil instanzieren
	 */
	void handleDownKey() {
		field.addTileToField();
		int rowCount = field.checkRows();
		if(rowCount > 0) {
			scores.calculateScores(rowCount);
			int line = scores.getLine();
			Score score = scores.raiseCurrentScore();
			view.setCurrentScore(score, line);
			speed = scores.getSpeed();
		}
		createNewTile();
		view.setTile(tile);
		view.paintField();
	} // handleDownKey
  
	/**
	 * hält laufendes Spiel an
	 */
	void stopThread() {
		isRunning = false;
		thread = null;
	}
  
	/**
	 * startet neues Spiel bzw. setzt aktuelles fort
	 */
	void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void keyReleased(KeyEvent ke) { }
	public void keyTyped(KeyEvent ke) { }
}
