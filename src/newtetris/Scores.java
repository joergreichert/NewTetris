package newtetris;

public class Scores {
	private final int scoreListLen = 10;
	private final int scoreRise = 2; // allg Punkteerhöhung bei Levelwechsel
	private final double speedUp = 0.9; // erhöht Fallgeschwindigkeit bei Levelwechsel
	private final int levelStep = 10; // nächstes Level nach 20 Zeilen
	private int scoreTile = 2; // Punkte für gesetztes Teil im Level 0
	private int scoreLine = 2; // Punkte für vollständige Reihe im Level 0
	private int lines = 0; // Anzahl der vollständigen Zeilen
	private int level = 0; // Levelnummer
	private int score = 0; // Anfangs-Hiscore
	private int speed = 1000; // Anfangsgeschwindigkeit
	private int nextLevel = levelStep; // Stufe für Levelwechsel

	private InScores  inScores  = null;
	private OutScores outScores = null;  

	Scores() {
		inScores = new InScores(scoreListLen);
		java.io.File scoreFile = inScores.getHiScoreFile();
		java.util.Hashtable <Integer, Score> hiscores = 
			inScores.getHiscores();
		outScores = new OutScores(hiscores, scoreFile, scoreListLen);
	} // Konstruktor

	/**
	 * erhöht Punktestand für gesetztes Teil
	 * erhöht Anzahl der vollständige Zeilen um eins: wenn gleich dem Level-
	 * sprung, wird Punkte- und Fallgeschwindigkeitsniveau erhöht, zusätzliche
	 * Punkte für vollständige Reihen, abhängig von Anzahl
	 * @param fullLines int mit dem letzten Teil vervollständige Zeilen
	 */
	void calculateScores(int fullLines) {
		score += (scoreLine + fullLines - 1);
		if((lines < nextLevel) && ((lines += fullLines) 
				>= nextLevel)) {
			level++;
			nextLevel += levelStep;
			scoreTile += scoreRise;
			scoreLine += scoreRise;
			speed = (int) (speed * speedUp);
		} // if
	} // calculateScores

	/**  @return int bisherige vervollständige Zeilen  */
	int getLine() {  return lines;  } // getLine
  
	Score raiseCurrentScore() {
		score += scoreTile;
		return getCurrentScore();
	}
	
	Score getCurrentScore() {
		return new Score(null, level, score);
	}
  
	Score getCurrentScore(String name) {
		return new Score(name, level, score);
	}  

	/**  @return int Fallgeschwindigkeit des Teils  */
	int getSpeed() {  return speed;  } // getSpeed

	/**  @return Hiscores aus Datei  */
	java.util.Hashtable <Integer, Score> getHiscores() {
		return inScores.getHiscores();
	} // getHiscores
  
	java.util.Hashtable <Integer, Score> getCurrentHiscores() {
		return outScores.getHiscores();
	} // getHiscores
  

	int getNewScorePos() {
		Score newScore = new Score(null, level, score);
		return outScores.getNewScorePos(newScore);
	}
	
	/**
	 * setzt neue Score (wenn gut genug) 
	 * @param newScore
	 */
	void setNewScore(String name) {
		Score newScore = new Score(name, level, score);
		outScores.insertNewScore(newScore);
	} // setNewScore
  
	void reset() {
		lines = 0;
		level = 0;
		score = 0;
	}
} // Scores
