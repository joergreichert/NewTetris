package newtetris;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewStatistics extends JPanel {
    public final static long serialVersionUID = 0;  
	private JLabel sc, rw, lv; // Beschriftung für Punkte, Zeilen und Level
	  
	ViewStatistics() {
		setLayout(new GridLayout(3, 2));
	    add(new Label("Punkte: "));  add(sc = new JLabel("" + 0));
	    add(new Label("Reihen: "));  add(rw = new JLabel("" + 0));
	    add(new Label("Level:  "));  add(lv = new JLabel("" + 0));
	}
	  
	/**
	 * stellt Spielparameter dar
	 * @param lines int komplettierte Zeilen
	 * @param score int bisherige Punktzahl
	 * @param level int Schwierigkeitsstufe
	 */
	void setCurrScoreLabels(Score score, int rowCount) {
	    sc.setText("" + score.getScore());
	    rw.setText("" + rowCount);
	    lv.setText("" + score.getLevel());
	} // setCurrScoreLabels	  
}
