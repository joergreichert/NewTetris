package newtetris;
/**
 * <p>Title: Tetris</p>
 * <p>Description: klassisches Tetrisspiel</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author Jörg Reichert
 */
public class TetrisMain {

	/**
	 * erstellt Spielumgebung
	 */
	public TetrisMain() {
		Field field = new Field();
		PreView preview = new PreView();
		Tile tile = new Tile(field.getDim(), 
				field.getXBounds(), field.getYBounds(),
				field.getMaxX(), field.getMaxY());
		Scores scores = new Scores();
		View view = new View("JR Tetris", field, preview, 
				tile, scores);
		QueryDialog qd = new QueryDialog(view);
		Game game = new Game(field, preview, tile, view, 
				scores, qd);
		new EventHandler(qd, field, scores, view, game);
		view.setVisible(true);
	} // Konstruktor

	public static void main(String[] args) {
		new TetrisMain();
	} // main
} // TetrisMain
