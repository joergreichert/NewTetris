package newtetris;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class PreView extends Metrics {
	public final static long serialVersionUID = 0;
	private final int [] previewPos = {0, 3*dim}; 
		// x-y-Position des Vorschau-Teils
	private Color newColor; // Farbe des aktuellen Teils
	private int [] [] newTile = null; // nächstes Teil (Form)	
	
	PreView() {
		this.setPreferredSize(new Dimension(3*dim, 3*dim));
	}
	
	void setPreviewTile(int [] [] t, Color col) {
		newTile = t;  newColor = col;
	} // setTile  	
	
	void paintPreviewTile(Graphics2D g) {
		g.setColor(newColor);
	    for(int i=0; i<4; i++) {
	    	if (newTile != null) g.fill3DRect(previewPos[0] + (newTile[i][0] * dim),
	    			previewPos[1] + (newTile[i][1] * dim), dim, dim, true);
	    }
	} // paintTile
	  
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g.create();
		paintPreviewTile(g2d);	  	
	}
}