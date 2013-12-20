package newtetris;
import java.util.Hashtable;
import java.io.*;

public class OutScores {
	private Hashtable <Integer, Score> hiscores;
	private File scoreFile;
	private final int scoreListLen;
	private int newScorePos = -1;
	
	OutScores(Hashtable <Integer, Score> ht, File file, 
			int listLen) {
		hiscores = ht;  scoreFile = file;
		scoreListLen = listLen; 
	}
	
	int getNewScorePos(Score newScoreObject) {
		int newScoreScore = newScoreObject.getScore(); 
		int newScoreLevel = newScoreObject.getLevel();
		Score scoreObject;
	  	int score, level;
		for(int i=0; i<scoreListLen; i++) {
	  		scoreObject = (Score) hiscores.get(new Integer(i));
	  		if((scoreObject == null)) {  
	  			newScorePos = i;
	  			return i;
	  		} else { 
		  		score = scoreObject.getScore();
		  		level = scoreObject.getLevel();
	  			if(newScoreScore > score) {  
		  			newScorePos = i;
		  			return i;	  			
	  			} else if(newScoreScore == score) {
	  				if(newScoreLevel > level) {  
	  					newScorePos = i;
	  					return i;
	  				}
	  			}
	  		}
	  	}
	  	return -1;		
	}
	
	/**
	 * stellt die Position der eben erreichten Punktezahl in der
	 * bisherigen Hiscore fest, -1 wenn zu niedrig 
	 * @return int Rang der Punktzahl in der Hiscore
	 */
	void insertNewScore(Score newScoreObject) {
	  	Score scoreObject = (Score) hiscores.get(new Integer(newScorePos));
  		if(scoreObject != null) {  
  			moveScores(newScorePos);
  		}
  		hiscores.put(new Integer(newScorePos), newScoreObject);
  		writeNewHiScores();
	} // insertNewScore	
	
	private void moveScores(int pos) {
	  	Score obj;
	  	int max = scoreListLen -2;
	  	for(int i=max; i>=pos; i--) {
	  		obj = hiscores.get(new Integer(i));
	  		if(obj != null) {
	  			hiscores.put(new Integer((i+1)), obj);
	  		}
	  	}
	}
	
	/**
	 * schreibt Hiscore in Datei, wenn sie kleinergleich 
	 * der Mindestrangzahl ist
	 * @param name String Name des Spielers
	 */
	private void writeNewHiScores() {
		try {
			FileWriter fw = new FileWriter(scoreFile);
  			Score score;
  			for(int i=0; i<scoreListLen; i++) {
  				score = (Score) hiscores.get(new Integer(i));
  				if(score == null) {  break;  }
  				fw.write(score.getName() + ", " + 
  						score.getLevel() + ", " + 
  						score.getScore() + "\n");
			}
			fw.close();
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		} // try-catch
	} // setHighscores	
	  
	Hashtable <Integer, Score> getHiscores() {
		return hiscores;
	}
}