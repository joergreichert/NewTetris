package newtetris;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class InScores {
	private File scoreFile; // lokale Hiscore-Datei
	private Hashtable <Integer, Score> ht; // Hiscoretabelle

	InScores(int scoreCount) {
		String dir = System.getProperty("user.dir");
		scoreFile = new File(dir + "/hiscores.txt");
		ht = new Hashtable <Integer, Score> ();
		try {
			if (!scoreFile.exists()) {
				scoreFile.createNewFile();
			}
			readHighScoreFile();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}        
	}
  
	/**
	 * liest HiScore-Datei aus
	 * @throws IOException wenn die Datei nicht gelesen werden kann
	 */
	private void readHighScoreFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(scoreFile));
		String in = null, name = null;
		int level = -1, score = -1;
		StringTokenizer st;
		for (int i=0; i<10; i++) {
			if((in = br.readLine()) != null) {
				st = new StringTokenizer(in, ", ");
				if(st.countTokens() > 2) {
					name = st.nextToken();
					level = convertToInt(st.nextToken());
					score = convertToInt(st.nextToken());
					ht.put(new Integer(i), new Score(name, level, score));
				}
			} else break;
		}
		br.close();
	} // readHighScoreFile
  
	/**
	 * wandelt String in Integer um
	 * @param item String String-Wert
	 * @return int Integer-Wert, wenn Umwandlung fehl geschlagen = -1
	 */
	private int convertToInt(String item) {
		try {
			return Integer.parseInt(item);
		} catch(NumberFormatException nfe) {
			return -1;
		} // try-catch
	} // convertToInt  
  
	File getHiScoreFile() {
		return scoreFile;
	}
  
	Hashtable <Integer, Score> getHiscores() {
		return ht;
	}
}
