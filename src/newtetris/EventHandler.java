package newtetris;
import java.awt.event.*;

public class EventHandler implements ActionListener {
	private QueryDialog qd = null;
	private Field field = null;
	private Scores scores = null;
	private View view = null;
	private Game game = null;

	public EventHandler(QueryDialog d, Field f, Scores s, 
			View v, Game g) {
		qd = d;  field = f;  scores = s;  view = v;  game = g;
		qd.setListener(this);
		view.setListeners(this);
	}

	/**
	 * setzen der neuen Highscore
	 * @param a ActionEvent
	 */
	public void actionPerformed(ActionEvent a) {
		String com = a.getActionCommand();
		if(com.equals(View.START)) {
			view.renameWorkButton(View.STOP);
			view.setPauseListener();
			game.prepareNewGame();
			game.createNewTile();
			game.start();
			field.addKeyListener(game);
		} else if(com.equals(View.STOP)) {
			game.finishGame();
		} else if(com.equals(View.PAUSE)) {
			view.renameInterruptButton(QueryDialog.CONTINUE);
			game.stopThread();
		} else if(com.equals(QueryDialog.CONTINUE)) {
			view.renameInterruptButton(View.PAUSE);
			game.start();  		
		} else if(com.equals(QueryDialog.OK)) {
			String user = qd.getUserName();
			scores.setNewScore(user);
			view.setHiscores(scores.getCurrentHiscores());
			qd.dispose();
		} // if-else 
	} // actionPerformed
} // EventHandler
