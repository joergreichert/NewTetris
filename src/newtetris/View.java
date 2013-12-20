package newtetris;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

public class View extends JFrame {
    public final static long serialVersionUID = 0;
    public final static String START = "Start";
    public final static String STOP  = "Ende";
    public final static String PAUSE = "Pause";    
	private JButton startBtn, pauseBtn;
	private Field field = null;
	private PreView preview = null;
	private Tile tile;
	private ViewHiscores vh;
	private ViewStatistics vs;

	public View(String title, Field f, PreView p, Tile t, 
			Scores s) {
		super(title);
		field = f;  preview = p;  tile = t;
		Container c = getContentPane();
		BorderLayout bl = new BorderLayout();
		c.setLayout(bl);
		JPanel paintBar = new JPanel(new BorderLayout());
		paintBar.add(field, BorderLayout.CENTER);
		JPanel pre = new JPanel(new BorderLayout());
		pre.add(preview, BorderLayout.CENTER);
		paintBar.add(pre, BorderLayout.EAST);
		JPanel sideBar = new JPanel(new BorderLayout());
		vh = new ViewHiscores(s.getHiscores());
		setBorder("Hiscores", vh);
		sideBar.add(vh, BorderLayout.NORTH);
		sideBar.add(createButtonPanel(), BorderLayout.CENTER);    
		vs = new ViewStatistics();
		setBorder("aktueller Spielstand", vs);
		c.add(paintBar, BorderLayout.CENTER);
		c.add(sideBar, BorderLayout.EAST);
		c.add(vs, BorderLayout.SOUTH);
		paintField();
		setWindow();
	}
  
	/**
	 * setzt Fenster
	 * @param x int Breite
	 * @param y int Höhe
	 */
	private void setWindow() {
		pack();
		int x = getWidth();  int y = getHeight();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int startX = ((int) d.getWidth()-x) / 2;
		int startY = ((int) d.getHeight()-y) / 2;
		setBounds(startX, startY, x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} // setWindow
  
	private void setBorder(String title, JComponent comp) {
		Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border etch = BorderFactory.createEtchedBorder();
		TitledBorder titledBorder = 
			new TitledBorder(etch, title);
		comp.setBorder(new CompoundBorder(empty, titledBorder));  	
	}

	/**
	 * JPanele zum Starte und Beenden des Spiels
	 * @return JPanel
	 */
	private JPanel createButtonPanel() {
		JPanel btnP = new JPanel();
		startBtn = new JButton(START);
		btnP.add("West", startBtn);
		pauseBtn = new JButton(PAUSE);
		btnP.add("East", pauseBtn);
		pauseBtn.setEnabled(false);
		return btnP;
	} // createButtonJPanel

	/**
	 * setzt neues Tile
	 * @param t Tile
	 */
	void setTile(Tile t) {
		tile = t;
	} // setTile
  
	void setListeners(EventHandler h) {
		startBtn.addActionListener(h);
		pauseBtn.addActionListener(h);
	} // setListeners  
  
	/**
	 * setzt Ereignisbehandler
	 * @param h EventHandler
	 */
	void setPauseListener() {
		pauseBtn.setEnabled(true);
	} // setListeners
  
	void removePauseListener() {
		pauseBtn.setEnabled(false);
	}
  
	void renameInterruptButton(String name) {
		pauseBtn.setText(name);
	}
  
	void renameWorkButton(String name) {
		startBtn.setText(name);
	}  
  
	void setCurrentScore(Score score, int rowCount) {
		vs.setCurrScoreLabels(score, rowCount);
	}
  
	void setHiscores(java.util.Hashtable <Integer, Score> ht) {
		vh.setHighScoreLabels(ht);
	}

	/**
	 * setzt aktuelles Teil auf Feld
	 */
	void paintField() {
		field.setPosition(tile.getXPos(), tile.getYPos());
		preview.repaint();
		field.repaint();
		field.requestFocus();
	} // paintField
} // View