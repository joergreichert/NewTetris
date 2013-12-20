package newtetris;
import java.awt.GridLayout;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewHiscores extends JPanel {
    public final static long serialVersionUID = 0;
	private JLabel [] [] scoreTab = null; // Hiscore-Anzeige
	private final int scoreListLen = 10; 
	
	ViewHiscores(Hashtable <Integer, Score> scores) {
	    int colCount = 3;
	    scoreTab = new JLabel[scoreListLen][colCount];
	    GridLayout gl = new GridLayout(scoreListLen+1, colCount+1);
	    gl.setHgap(20);
	    JPanel table = new JPanel(gl);
	    String [] titles = {"Rang", "Name", "Level", "Punkte"};
	    int count = titles.length;
	    JLabel [] labels = new JLabel[count];
	    for(int i=0; i<count; i++) {
	    	table.add(labels[i] = new JLabel(titles[i]));
	    	labels[i].setHorizontalAlignment(JLabel.CENTER);
	    }
	    JLabel [] labels2 = new JLabel[scoreListLen];
    	for(int j=0; j<scoreListLen; j++) {
    		table.add(labels2[j] = new JLabel((j+1) + "."));
    		labels2[j].setHorizontalAlignment(JLabel.RIGHT);
   			table.add(scoreTab[j][0] = new JLabel());
   			table.add(scoreTab[j][1] = new JLabel());
   			scoreTab[j][1].setHorizontalAlignment(JLabel.RIGHT);
   			table.add(scoreTab[j][2] = new JLabel());
   			scoreTab[j][2].setHorizontalAlignment(JLabel.RIGHT);   			
	    }
	    add(table);
	    setHighScoreLabels(scores);		
	}
	
	/**
	 * aktualisiert aktuellen Stand von Punkte, Level und Zeilen
	 * @param s Scores
	 */
	void setHighScoreLabels(Hashtable <Integer, Score> scores) {
		int currListLen = scores.size();
	    currListLen = (currListLen <= scoreListLen)? currListLen : scoreListLen;
	  	Score score;
	  	for(int i=0; i<currListLen; i++) {
	  		score = (Score) scores.get(new Integer(i));
	  		setHiscoreLabels(score, i);
	    } // for
	} // setHighScoreLabels
	  
	private void setHiscoreLabels(Score score, int pos) {
		try {
			scoreTab[pos][0].setText(score.getName());
	  		scoreTab[pos][1].setText("" + score.getLevel());
	  		scoreTab[pos][2].setText("" + score.getScore());
		} catch(NullPointerException npe) {  }
	}
}
