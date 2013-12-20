package newtetris;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <p>Title: Tetris-Registrierung</p>
 * <p>Description: Eingabe des Spielernamens nach Ende des Spiels</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author Jörg Reichert
 */
public class QueryDialog extends JDialog 
	implements KeyListener {
	public final static long serialVersionUID = 0;
	public final static String OK = "OK";
	public final static String CONTINUE = "Weiter";
	private final int maxNameLen = 8;
	private JTextField nameField; // Eingabefeld für Namen
	private JButton ok;
	private String name = ""; // leerer Name

	/**
	 * erstellt Maske für Namenseingabe
	 * @param owner Frame View
	 */
	QueryDialog(JFrame owner) {
		super(owner, "HighScore", true);
		nameField = new JTextField(10);
		nameField.addKeyListener(this);
		ok = new JButton(OK);
		JPanel query = new JPanel();
		query.add(new JLabel("Name:"));
		query.add(nameField);
		Container c = getContentPane();
		c.add("Center", query);
		JPanel button = new JPanel();
		button.add(ok);
		c.add("South", button);
		pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
       	int startX = ((int) d.getWidth()  - getWidth()) / 2;
       	int startY = ((int) d.getHeight() - getHeight()) / 2;
       	setBounds(startX, startY, getWidth(), getHeight());
	} // Konstruktor

	/**
	 * setzt Ereignisbehandler
	 * @param h EventHandler
	 */
	void setListener(EventHandler h) {
		ok.addActionListener(h);
	} // setListeners

	/**
	 * @return String Name des Spielers
	 */
	String getUserName() {
		return nameField.getText();
	} // getUserName
	
	public void keyTyped(KeyEvent ke) {
		String aName = nameField.getText(); 
		int len = aName.length();
		if((len > maxNameLen) || (ke.getKeyChar() == ',')) {
			nameField.setText(name);
		} else {
			name = aName;
		}
	}
	
	public void keyReleased(KeyEvent ke) {  }
	public void keyPressed(KeyEvent ke) {  }
}//QueryDialog
