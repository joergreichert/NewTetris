package newtetris;
import javax.swing.JPanel;

public class Metrics extends JPanel {
    public final static long serialVersionUID = 0;
	protected final static int    dim  = 10; // Breite=Höhe der Komponente des Teils
	protected final static int    maxX = 12; // maximale Spalte für Teil
	protected final static int    maxY = 22; // maximale Zeile für Teil
	protected final static int [] dimX = {0, dim * maxX}; // Breite des Spielfelds
	protected final static int [] dimY = {0, dim * maxY}; // Höhe des Spielfelds
}
