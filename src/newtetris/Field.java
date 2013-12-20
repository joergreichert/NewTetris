package newtetris;
import java.awt.*;
/**
 * <p>Title: Tetris</p>
 * <p>Description: Spielfeld des Tetrisspiels</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author Jörg Reichert
 */
public class Field extends Metrics {
	public final static long serialVersionUID = 0;  
	private int [] pos; // x-y-Position des aktuellen Teils
	private int [] [] field; // Raster des Spielfeldes mit (un-)besetzen Stellen
	private Color [] [] colors; // Feld mit Farbe für jede Stelle des Feldes
	private Color color; // Farbe des aktuellen Teils
	private int [] [] tile = null; // aktuelles Teil (Form)

	/**
	 * setzt Feld auf leer
	 */
	public Field() {
		field  = new int [maxX] [maxY];
		colors = new Color [maxX] [maxY];
		pos = new int[2];
		setPreferredSize(new Dimension(dimX[1], dimY[1]));
	} // Konstruktor

	void clearField() {
		for(int i=0; i<maxX; i++) {
			for(int j=0; j<maxY; j++) {
				field[i][j] = 0;
			}
		}
	}
  
	int [] getStartPosition() {
		int [] startPos = new int[2]; 
		startPos[0] = ((maxX-1) / 2) * dim;
		startPos[1] = dimY[0];
		return startPos;
	}

	/**
	 * setzt Position des aktuellen Teils
	 * @param x int Spalte
	 * @param y int Zeile
	 */
	void setPosition(int x, int y) {
		pos[0] = x;  pos[1] = y;
	} // setPosition

	/**
	 * setzt aktuelles Tetristeil
	 * @param t int[][] aktuelles Teil (Form)
	 * @param col Color Farbe des aktuellen Teils
	 */
	void setTile(int [] [] t, Color col) {
		tile = t;  color = col;
	} // setTile
  
	/**
	 * setzt Teil dauerhaft in Feld an seine Endposition
	 */
	void addTileToField() {
		int x, y;
		for(int i=0; i<4; i++) {
			x = tile[i][0] + ((pos[0] - dimX[0]) / dim);
			y = tile[i][1] + ((pos[1] - dimY[0]) / dim);
			field[x][y] = 1;
			colors[x][y] = color;
		}
	} // addTileToField

	/**
	 * verschiebt alle Zeilen oberhalb der vollständigen um eins nach unten, um
	 * sie so zu löschen
	 * @param row int Zeile, die gelöscht wurde
	 */
	void pushRows(int row) {
		for(int h=row; h>3; h--) {
			for(int i=1; i<maxX-1; i++) {
				field[i][h] = field[i][h-1];
			}
		}
	} // pushRows

	/**
	 * prüft Feld auf ausgefüllte Zeilen
	 * @return int Anzahl der ausgefüllten Zeilen
	 */
	int checkRows() {
		boolean check;
		int rows = 0;
		for(int h=maxY-1; h>0; h--) {
			while(true) {
				check = true;
				for(int i=1; i<maxX-1; i++) {
					check = check && (field[i][h] == 1);
				} // prüft Feldpositionen der Reihe auf Teile
				if (check) {
					pushRows(h);  rows++; // verschiebt Zeilen über vollständige
				} else break;
			}
		}
		return rows;
	} // checkRows

	/**
	 * zeichnet alle bereits bestehenden Teile im Spielfeld
	 * @param g Graphics Grafikobjekt
	 */
	void paintSoleField(Graphics2D g) {
		g.drawRect(dimX[0]+dim, dimY[0]+dim, dimX[1]-2*dim, dimY[1]-2*dim);
		for(int i=0; i<maxX; i++) {
			for(int j=0; j<maxY; j++) {
				if(field[i][j] == 1) {
					g.setColor(colors[i][j]);
					g.fill3DRect(dimX[0] + (i * dim), dimY[0] + (j * dim), dim , dim, true);
				}
			}
		}
	}

	/**
	 * zeichnet aktuelles Teil (komponentenweise (4))
	 * @param g Graphics Grafikobjekt
	 */
	void paintTile(Graphics2D g) {
		g.setColor(color);
		for(int i=0; i<4; i++) {
			if (tile != null) g.fill3DRect(pos[0] + (tile[i][0] * dim),
					pos[1] + (tile[i][1] * dim), dim, dim, true);
		}
	} // paintTile
  
	/**
	 * zeichnet bestehendes Feld und aktuelles Teil
	 * @param g Graphics Grafikobjekt
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create(); 
		paintSoleField(g2d);
		paintTile(g2d);
		g2d.dispose();		
	} // paintField

	/**
	 * @return int Breite=Höhe der Teilkomponenten
	 */
	int getDim() { return dim; }

	/**
	 * @return int maximale X-Koordinate für Teil
	 */
	int getXBounds() { return dimX[0]; }

	/**
	 * @return int maximale Y-Koordinate für Teil
	 */
	int getYBounds() { return dimY[0]; }

	/**
	 * @param c Color[][] Farbe jeder bereits gesetzten Komponente
	 */
	void setColors(Color [] [] c) { colors = c; }

	/**
	 * @return int maximale Spalte
	 */
	int getMaxX() { return maxX; }

	/**
	 * @return int maximale Zeile
	 */
	int getMaxY() { return maxY; }

	/**
	 * @return int[][] Feld mit (un-)besetzten Raster
	 */
	int [] [] getField() { return field; }
} // Field
