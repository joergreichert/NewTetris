package newtetris;
import java.awt.Color;
import java.util.Random;
import java.util.Vector;
/**
 * <p>Title: Komponenten</p>
 * <p>Description: Tetris-Teile</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author Jörg Reichert
 */
public class Tile {
	private final Color [] colors = {new Color(230,120,0), 
		new Color(50,200,0), new Color(0,150,230),
		new Color(220,220,0), new Color(230,20,20),
		new Color(170,50,220), new Color(150,150,150)}; 
		// vordefinierte Farben
	private int dim = 0; // Breite=Höhe der Quadrate
	private int x = 0, y = 0, rot = 0; // Startposition, Rotationsstand
	private int startX, startY; // Startposition des Feldes
	private int maxX, maxY; // Endposition des Feldes
	private Vector <Vector <int [] []> > tileCollection; // Teilesammlung 
	private Vector <int [] []> tile; 
		// Sammlung des aktuellen Tiletyps 
	private int [] [] t; // aktuelles Teil
	private int [] [] field; //Spielfeld
	private Color color; // Farbe des aktuellen Teils

	private void createObjects() {
		final int [] [] tTile1 = {{1,1}, {1,2}, {2,2}, {1,3}};//  H
		final int [] [] tTile2 = {{1,1}, {2,1}, {3,1}, {2,2}};//  H H
		final int [] [] tTile3 = {{2,1}, {2,2}, {1,2}, {2,3}};//  H
		final int [] [] tTile4 = {{2,1}, {1,2}, {2,2}, {3,2}};//

		final int [] [] lTile1 = {{1,1}, {2,3}, {1,2}, {1,3}};//  H
		final int [] [] lTile2 = {{1,1}, {2,1}, {3,1}, {1,2}};//  H
		final int [] [] lTile3 = {{2,1}, {2,2}, {2,3}, {1,1}};//  H H
		final int [] [] lTile4 = {{3,1}, {1,2}, {2,2}, {3,2}};//

		final int [] [] mTile1 = {{1,1}, {2,1}, {1,2}, {1,3}};//  H H
		final int [] [] mTile2 = {{1,1}, {2,1}, {3,1}, {3,2}};//  H
		final int [] [] mTile3 = {{2,1}, {2,2}, {2,3}, {1,3}};//  H
		final int [] [] mTile4 = {{1,1}, {1,2}, {2,2}, {3,2}};//

		final int [] [] sTile1 = {{1,1}, {1,2}, {2,2}, {2,3}};//  H
		final int [] [] sTile2 = {{2,1}, {3,1}, {1,2}, {2,2}};//  H H
		final int [] [] sTile3 = {{1,1}, {1,2}, {2,2}, {2,3}};//    H
		final int [] [] sTile4 = {{2,1}, {3,1}, {1,2}, {2,2}};//

		final int [] [] zTile1 = {{2,1}, {1,2}, {2,2}, {1,3}};//    H
		final int [] [] zTile2 = {{1,1}, {2,1}, {2,2}, {3,2}};//  H H
		final int [] [] zTile3 = {{2,1}, {2,2}, {1,2}, {1,3}};//  H
		final int [] [] zTile4 = {{1,1}, {2,1}, {2,2}, {3,2}};//

		final int [] [] qTile1 = {{1,1}, {1,2}, {2,2}, {2,1}};//  H H
		final int [] [] qTile2 = {{1,1}, {1,2}, {2,2}, {2,1}};//  H H
		final int [] [] qTile3 = {{1,1}, {1,2}, {2,2}, {2,1}};//
		final int [] [] qTile4 = {{1,1}, {1,2}, {2,2}, {2,1}};//

		final int [] [] iTile1 = {{1,1}, {1,2}, {1,3}, {1,4}};//  H
		final int [] [] iTile2 = {{1,1}, {2,1}, {3,1}, {4,1}};//  H
		final int [] [] iTile3 = {{1,1}, {1,2}, {1,3}, {1,4}};//  H
		final int [] [] iTile4 = {{1,1}, {2,1}, {3,1}, {4,1}};//  H

		Vector <int [] []> tTile = new Vector <int [] []> ();
		tTile.add(tTile1);  tTile.add(tTile2);  tTile.add(tTile3);  tTile.add(tTile4);
		Vector <int [] []> lTile = new Vector <int [] []> ();
		lTile.add(lTile1);  lTile.add(lTile2);  lTile.add(lTile3);  lTile.add(lTile4);
		Vector <int [] []> mTile = new Vector <int [] []> ();
		mTile.add(mTile1);  mTile.add(mTile2);  mTile.add(mTile3);  mTile.add(mTile4);
		Vector <int [] []> sTile = new Vector <int [] []> ();
		sTile.add(sTile1);  sTile.add(sTile2);  sTile.add(sTile3);  tTile.add(sTile4);
		Vector <int [] []> zTile = new Vector <int [] []> ();
		zTile.add(zTile1);  zTile.add(zTile2);  zTile.add(zTile3);  zTile.add(zTile4);
		Vector <int [] []> qTile = new Vector <int [] []> ();
		qTile.add(qTile1);  qTile.add(qTile2);  qTile.add(qTile3);  qTile.add(qTile4);
		Vector <int [] []> iTile = new Vector <int [] []> ();
		iTile.add(iTile1);  iTile.add(iTile2);  iTile.add(iTile3);  iTile.add(iTile4);
		tileCollection = new Vector <Vector <int [] []> > (7);
		tileCollection.add(tTile);  tileCollection.add(lTile);  tileCollection.add(mTile);
		tileCollection.add(sTile);  tileCollection.add(zTile);  tileCollection.add(qTile);
		tileCollection.add(iTile); // Einfügen der möglichen Formen in Sammlung
	}

	/**
	 * Erstellt Teil
	 * @param dimension int
	 * @param boundX int
	 * @param boundY int
	 * @param mX int
	 * @param mY int
	 */
	Tile(int dimension, int boundX, int boundY, int mX, int mY) {
		maxX   = mX;        maxY   = mY;
		startX = boundX;    startY = boundY;
		dim    = dimension;
		createObjects();
	} // Konstruktor

	/**
	 * wählt aus Sammlung zufällig Teil als aktuelles aus
	 * @param posX int
	 * @param posY int
	 * @param rotation int
	 * @return boolean
	 */
	boolean createTile(int posX, int posY, int rotation, int randomNum) {
		x = posX; y = posY;
		color = colors[randomNum];
		rot   = rotation;
		tile  = tileCollection.get(randomNum);
		t     = tile.get(rot);
		int [] pos;  boolean check;
		for(int i=0; i<4; i++) {
			pos = getPos(i, x, y);
			check = testPos(pos) && testRight(pos)
				&& testLeft(pos) && testDown(pos);
			if(!check) return false;
		}
		return true;
	} // createTile
  
	void createTile(int randomNum) {
		x = 0; y = 0;
		color = colors[randomNum];
		rot   = 0;
		tile  = tileCollection.get(randomNum);
		t     = tile.get(rot);
	}
  
	int getRandomNumber() {
		return new Random().nextInt(7);  	
	}

	/**
	 * @param i int
	 * @param a int
	 * @param b int
	 * @return int[] Position des aktuellen Teils
	 */
	int [] getPos(int i, int a, int b) {
		int [] pos = new int[2];
		pos[0] = t[i][0] + ((a - startX) / dim);
		pos[1] = t[i][1] + ((b - startY) / dim);
		return pos;
	} // getPos

	/**
	 * prüft, ob Teil auf neue Position bewegt werden arf
	 * @param pos int[] Position
	 * @return boolean falsch, wenn dort schon Teil existiert
	 */
	boolean testPos(int [] pos) { return field[pos[0]][pos[1]] != 1; }

	/**
	 * @param pos int[]
	 * @return boolean falsch, wenn am rechten Spielfeldrand
	 */
	boolean testRight(int [] pos) { return pos[0] < (maxX - 1); }

	/**
	 * @param pos int[]
	 * @return boolean falsch, wenn am linken Spielfeldrand
	 */
	boolean testLeft(int [] pos) { return pos[0] > 0; }

	/**
	 * @param pos int[]
	 * @return boolean falsch, wenn am unteren Spielfeldrand
	 */
	boolean testDown(int [] pos) { return pos[1] < (maxY - 1); }

	/**
	 * @throws ArrayIndexOutOfBoundsException
	 * @return boolean falsch, wenn nicht weiter nach rechts von aktueller Pos
	 */
	boolean moveRight() throws ArrayIndexOutOfBoundsException {
		int [] pos;
		for(int i=0; i<4; i++) {
			pos = getPos(i, x + dim, y);
			if (!testPos(pos))   return false;
			if (!testRight(pos)) return false;
		}
		x += dim;
		return true;
	} // moveRight

	/**
	 * @return boolean falsch, wenn nicht weiter nach links von aktueller Pos
	 */
	boolean moveLeft() {
		int [] pos;
		for(int i=0; i<4; i++) {
			pos = getPos(i, x - dim, y);
			if (!testPos(pos))  return false;
			if (!testLeft(pos)) return false;
		}
		x -= dim;
		return true;
	} // moveLeft

	/**
	 * @return boolean falsch, wenn nicht weiter nach unten von aktueller Pos
	 */
	boolean moveDown() {
		int [] pos;
		for(int i=0; i<4; i++) {
			pos = getPos(i, x, y + dim);
			if (!testPos(pos))  return false;
			if (!testDown(pos)) return false;
		}
		y += dim;
		return true;
	} // moveDown

	boolean moveTotalDown() {
		boolean down = false;
		do {
			down = moveDown();
		} while(down);
		return true;
	} // moveTotalDown

	/**
	 * @return boolean wahr, wenn sich Teil rotieren ließ
	 */
	boolean rotate() {
		if (rot >= 3) rot = 0; else rot++;
		boolean check = true;
		t = tile.get(rot);
		int [] pos;
		for(int i=0; i<4; i++) {
			pos = getPos(i, x, y);
			check = testPos(pos) && testRight(pos) && testLeft(pos) && testDown(pos);
			if(!check) break;
		}
		if(!check) {
			if (rot < 0) rot = 3; else rot--;
		} return true;
	} // rotate

	/**
	 * @return int Stand der Rotation
	 */
	int getRotation() { return rot; }

	/**
	 * @return Color Farbe des aktuellen Teils
	 */
	Color getColor() { return color; }

	/**
	 * @return int X-Koordinate des aktuellen Teils
	 */
	int getXPos() { return x; }

	/**
	 * @return int Y-Koordinate des aktuellen Teils
	 */
	int getYPos() { return y; }

	/**
	 * @return int[][] aktuelles Teil mit bestimmter Rotation
	 */
	int [] [] getTile() { return tile.get(rot); }

	/**
	 * @param f int[][] Spielfeld
	 */
	void setField(int [] [] f) { field = f; }
} // Tile
