package newtetris;

public class Score {
	private String name;
	private int level, score;
	
	Score(String aName, int aLevel, int aScore) {
		name = aName;  level = aLevel;  score = aScore;
	}
	
	String getName()   {  return name;    }
	int    getLevel()  {  return level;   }
	int    getScore()  {  return score;  }
}
